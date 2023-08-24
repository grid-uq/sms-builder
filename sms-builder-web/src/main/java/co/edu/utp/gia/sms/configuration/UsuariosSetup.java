package co.edu.utp.gia.sms.configuration;

import co.edu.utp.gia.sms.entidades.Usuario;
import co.edu.utp.gia.sms.negocio.RolEJB;
import co.edu.utp.gia.sms.negocio.UsuarioService;

import jakarta.inject.Inject;
/**
 * Clase encargada de realizar la configuración inicial del usuario administrador de la aplicación
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
public class UsuariosSetup implements SetupInterface{
    /**
     * Variable que representa el atributo ID_USUARIO, que debería ser el ID de
     * registro del rol de usuario usado por la aplicación
     */
    public static final Integer ID_USUARIO = 2;

    /**
     * Variable que representa el atributo usuarioBO de la clase
     */
    @Inject
    private UsuarioService usuarioBO;

    /**
     * Variable que representa el atributo rolBO de la clase
     */
    @Inject
    private RolEJB rolBO;

    @Override
    public void setup() {
        if (usuarioBO.get().isEmpty()) {
            var usuario = new Usuario();
            usuario.setNombreUsuario("root");
            usuario.setClave("12345");
            usuario.setRoles(rolBO.listar());
            usuario.setNombre("root");
            usuario.setEmail("root@email.com");
            usuarioBO.create(usuario, usuario.getClave());
        }
    }
}
