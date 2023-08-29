package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.ReferenciaService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Clase controladora de interfaz web que se encarga de la gestión de las evaluaciones de las referencias.
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
public class EvaluarReferenciasBean extends GenericBean<ReferenciaDTO> {
    @Getter
    @Setter
    private List<ReferenciaDTO> referencias;
    @Inject
    private ReferenciaService referenciaService;

    public void inicializar() {
        referencias = referenciaService.findWithEvaluacion();
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
        //TODO Verificar la evaluación de referencias
//        for (ReferenciaDTO referencia : referencias) {
//            referenciaService.actualizarFiltro(referencia.getId(), referencia.getFiltro());
//        }
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
    }

    public void evaluacionAutomatica() {
        try {
            for (ReferenciaDTO referencia : referencias) {
                referenciaService.evaluacionAutomatica(referencia.getId());
            }
            referencias = referenciaService.findWithEvaluacion();
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
