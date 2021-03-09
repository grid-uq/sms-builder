package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class EvaluarReferenciasBean extends GenericBean<ReferenciaDTO> {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = -4485002227034874858L;
    @Getter
    @Setter
    private List<ReferenciaDTO> referencias;
    @Inject
    private ReferenciaEJB referenciaEJB;

    public void inicializar() {
        if (getRevision() != null) {
            referencias = referenciaEJB.obtenerTodasConEvaluacion(getRevision().getId(), 3);
        }
    }

    public void incluirExcluirTopico(ReferenciaDTO referencia, Topico topico) {
        if (referencia.getTopicos().contains(topico)) {
            referencia.getTopicos().remove(topico);
        } else {
            referencia.getTopicos().add(topico);
        }
    }

    public void evaluar(ReferenciaDTO referencia) {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);

        addToSession("referenciaDTO", referencia);
        PrimeFaces.current().dialog().openDynamic("/revision/evaluarReferencia", options, null);
    }

    public void guardar() {
        for (ReferenciaDTO referencia : referencias) {
            referenciaEJB.actualizarFiltro(referencia.getId(), referencia.getFiltro());
        }
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
    }

    public void evaluacionAutomatica() {
        try {
            for (ReferenciaDTO referencia : referencias) {
                referenciaEJB.evaluacionAutomatica(referencia.getId());
            }
            referencias = referenciaEJB.obtenerTodasConEvaluacion(getRevision().getId(), 3);
            mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        } catch (Exception e) {
            mostrarErrorGeneral(e.getMessage());
        }
    }

    public void onEvaluacionRealizada(SelectEvent<ReferenciaDTO> event) {
        ReferenciaDTO referencia = event.getObject();
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
    }

}
