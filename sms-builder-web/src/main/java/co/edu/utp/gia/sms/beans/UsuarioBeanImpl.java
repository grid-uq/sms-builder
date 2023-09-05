package co.edu.utp.gia.sms.beans;


import co.edu.utp.gia.sms.beans.seguridad.UsuarioBean;
import co.edu.utp.gia.sms.configuration.RolConstants;
import co.edu.utp.gia.sms.entidades.Usuario;
import co.edu.utp.gia.sms.negocio.RolService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 * Implementación de {@link UsuarioBean} usado para la gestion de usuarios del
 * sistema
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 23/11/2015
 */
@Named("usuarioBean")
@ViewScoped
public class UsuarioBeanImpl extends UsuarioBean {
    @Inject
    private RolService rolService;
    @Getter @Setter
    private Usuario usuario;

    /*
     * (non-Javadoc)
     *
     * @see
     * co.edu.uniquindio.grid.common.bean.seguridad.UsuarioBean#newUsuario()
     */
    @Override
    public Usuario newUsuario() {
        return new Usuario();
    }

    @Override
    public String registrar() {
        usuario.setRoles( rolService.findByName(RolConstants.USER) );
        return super.registrar();
    }
}
