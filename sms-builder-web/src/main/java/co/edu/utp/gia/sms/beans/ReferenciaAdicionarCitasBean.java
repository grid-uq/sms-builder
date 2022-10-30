package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;
import co.edu.utp.gia.sms.negocio.RevisionEJB;
import lombok.Getter;
import lombok.Setter;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
/**
 * Clase controladora de interfaz web que se encarga de la gestión de citas.
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
public class ReferenciaAdicionarCitasBean extends GenericBean<ReferenciaDTO> {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 4009685061343184778L;
    @Inject
    private ReferenciaEJB referenciaEJB;

    @Inject
    private RevisionEJB revisionEJB;

    @Getter
    @Setter
    private List<ReferenciaDTO> referencias;

    @Getter
    @Setter
    private List<Topico> topicos;

    public void inicializar() {

        if (getRevision() != null) {
            //referencias = referenciaEJB.obtenerTodas(getRevision().getId(), 3);
            referencias = referenciaEJB.obtenerTodas(getRevision().getPasoSeleccionado().getId());
            topicos = revisionEJB.obtenerTopicos(getRevision().getId());
        }
    }

    public void incluirExcluirTopico(ReferenciaDTO referencia, Topico topico) {
        if (referencia.getTopicos().contains(topico)) {
            referencia.getTopicos().remove(topico);
        } else {
            referencia.getTopicos().add(topico);
        }
    }

    public void guardarYear(ReferenciaDTO referencia) {
        try {
            referenciaEJB.actualizarYear(referencia.getId(), referencia.getYear());
        } catch (Exception e) {
            mostrarErrorGeneral(e.getMessage());

        }
    }

    public void guardarCita(ReferenciaDTO referencia) {
        try {
            referenciaEJB.actualizarCita(referencia.getId(), referencia.getCitas());
        } catch (Exception e) {
            mostrarErrorGeneral(e.getMessage());
        }
    }


    public void guardarRelevancia(ReferenciaDTO referencia) {
        referenciaEJB.actualizarRelevancia(referencia.getId(), referencia.getRelevancia());
    }

    public void guardar() {
        for (ReferenciaDTO referencia : referencias) {
            referenciaEJB.limpiarTopicos(referencia.getId());
            for (Topico topico : referencia.getTopicos()) {
                referenciaEJB.adicionarTopico(referencia.getId(), topico.getId());
            }
        }
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
    }


}
