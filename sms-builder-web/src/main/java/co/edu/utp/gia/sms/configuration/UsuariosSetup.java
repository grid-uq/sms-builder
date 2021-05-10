package co.edu.utp.gia.sms.configuration;

import co.edu.utp.gia.sms.entidades.Usuario;
import co.edu.utp.gia.sms.negocio.RolEJB;
import co.edu.utp.gia.sms.negocio.UsuarioEJB;

import javax.inject.Inject;

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
    private UsuarioEJB usuarioBO;

    /**
     * Variable que representa el atributo rolBO de la clase
     */
    @Inject
    private RolEJB rolBO;

    @Override
    public void setup() {
        if (usuarioBO.listar().isEmpty()) {
            var usuario = new Usuario();
            usuario.setNombreUsuario("root");
            usuario.setClave("12345");
            usuario.setRoles(rolBO.listar());
            usuario.setNombre("root");
            usuario.setEmail("root@email.com");
            usuarioBO.registrar(usuario, usuario.getClave());
        }
    }
}
