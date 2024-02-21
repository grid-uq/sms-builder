package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.EstadoUsuario;
import co.edu.utp.gia.sms.entidades.Rol;
import co.edu.utp.gia.sms.entidades.Usuario;
import co.edu.utp.gia.sms.exceptions.LogicException;
import co.edu.utp.gia.sms.query.seguridad.SeguridadUsuarioLogin;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;

import java.util.List;
import java.util.Optional;

/**
 * Clase de negocio encargada de implementar las funciones correspondientes a la
 * gestion del {@link Usuario}.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
@ApplicationScoped
public class UsuarioService extends AbstractGenericService<Usuario, String> {

    @Inject
    private SecurityContext securityContext;

    public Usuario getUsuario() {
        var principal = securityContext.getCallerPrincipal();
        if( principal != null ) {
            var usuario =  SeguridadUsuarioLogin.createQuery(principal.getName()).findFirst().orElse(null);
            System.out.println("ENCONTRO USUARIO "+usuario);
            return usuario;
        }
        return null;
    }

    public UsuarioService() {
        super(DB.root.getProvider(Usuario.class));
    }

    /**
     * Permite obtener el listado de {@link Rol}es por defecto a ser usados
     * cuando un {@link Usuario} se registra
     *
     * @return El {@link List} de {@link Rol} a ser asiganados al usuario que se
     * registra en la aplicacion
     */
    private static List<Rol> getRolesPorDefecto() {
        // TODO pendiente obtener el estado de una tabla de parametros de
        // configuracionen lugar de estar quemado
        Rol rolUsuario = new Rol();
        rolUsuario.setId("2");
        return List.of(rolUsuario);
    }

    /**
     * Metodo que permite registrar un usuario
     *
     * @param usuario           Usuario a ser registrado
     * @param verificacionClave Variable de verficacion de clave
     */
    public void create(Usuario usuario, String verificacionClave) {
        if (usuario == null) {
            throw new LogicException(exceptionMessage.getDatosIncompletos());
        }
        if (!usuario.getClave().equals(verificacionClave)) {
            throw new LogicException(exceptionMessage.getClaveNoCoincide());
        }
        if (usuario.getRoles() == null) {
            usuario.setRoles(getRolesPorDefecto());
        }
        if (findByName(usuario.getNombreUsuario()).isPresent()) {
			throw new LogicException(exceptionMessage.getRegistroExistente());
        }
        super.save(usuario);
    }

    /**
     * Permite obtener un usuario a partir de su nombre de usuario
     *
     * @param nombreUsuario Nombre de usuario del Usuario que se desea obtener
     * @return El usuario con el nombre de usuario dado, o null en caso de que no exista un usuario con el nombre de usuario dado
     */
    private Optional<Usuario> findByName(String nombreUsuario) {
        return dataProvider.get().stream()
                .filter( usuario->usuario.getNombreUsuario().equals(nombreUsuario) )
                .findFirst();
    }

    /**
     * Metodo que permite actualizar el {@link Usuario}
     *
     * @param usuario           {@link Usuario} a ser actualizado
     * @param verificacionClave Variable de verficacion de clave
     */
    public void update(Usuario usuario, String verificacionClave) {
        if (usuario == null) {
            throw new LogicException(exceptionMessage.getDatosIncompletos());
        }
        if (!usuario.getClave().equals(verificacionClave)) {
            throw new LogicException(exceptionMessage.getClaveNoCoincide());
        }
        update(usuario);
    }

    /**
     * Metodo que permite verificar si los datos de autenticación dados (email y
     * clave) corresponden con datos de un {@link Usuario} registrado en el
     * sistema
     *
     * @param nombreUsuario Email del usuario que se desea autenticar
     * @param clave         Clave del usuario que se desea autenticar
     * @return Si los datos de email y clave corresponden con un {@link Usuario}
     * válido, se retorna dicho {@link Usuario}, en caso contrario
     * retorna null
     * @throws LogicException Error de autenticacion En caso de
     *                        que no se encuentre el nombre de usuario proporcionado
     * TODO Es posible que se requiera un logout
     */
    public Usuario login(String nombreUsuario, String clave) {
        var usuario = findByName(nombreUsuario)
                .orElseThrow( ()->new LogicException(exceptionMessage.getLoginFailMessage()) );

        if (!usuario.getClave().equals(clave)) {
            usuario.setIntentos(usuario.getIntentos() + 1);
            if (usuario.getIntentos() >= 3) {
                usuario.setEstado(EstadoUsuario.BLOQUEADO);
            }
            return null;
        }
        usuario.setIntentos(0);
        return usuario;
    }
}
