package co.edu.utp.gia.sms.configuration;

import co.edu.utp.gia.sms.entidades.Usuario;
import co.edu.utp.gia.sms.negocio.RolService;
import co.edu.utp.gia.sms.negocio.UsuarioService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

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
@ApplicationScoped
public class UsuariosSetup implements SetupInterface{
    /**
     * Variable que representa el atributo usuarioBO de la clase
     */
    @Inject
    private UsuarioService usuarioBO;

    /**
     * Variable que representa el atributo rolBO de la clase
     */
    @Inject
    private RolService rolBO;

    @Override
    public void setup() {
        if (usuarioBO.get().isEmpty()) {
            var usuario = new Usuario();
            usuario.setNombreUsuario("root");
            usuario.setClave("12345");
            usuario.setRoles(List.copyOf(rolBO.get()));
            usuario.setNombre("root");
            usuario.setEmail("root@email.com");
            usuarioBO.create(usuario, usuario.getClave());
        }
    }
}
