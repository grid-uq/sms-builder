package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.Objetivo;
import co.edu.utp.gia.sms.entidades.Usuario;
import co.edu.utp.gia.sms.negocio.RevisoresEJB;
import lombok.Getter;
import lombok.Setter;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
/**
 * Clase controladora de interfaz web que se encarga de la gestión de revisores.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@Named
@ViewScoped
public class RevisoresBean extends GenericBean<Objetivo> {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 9060626423979863537L;
    @Getter
    @Setter
    private List<Usuario> usuarios;

    @Inject
    private RevisoresEJB revisoresEJB;

    public void inicializar() {
        if (getRevision() != null) {
            usuarios = revisoresEJB.listar(getRevision().getId());
        }
    }

    public void guardar() {
        try {
            revisoresEJB.guardar(getRevision().getId(), usuarios);
            mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        } catch (Exception e) {
            mostrarErrorGeneral(e.getMessage());
        }
    }

}
