package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.entidades.EvaluacionCalidad;
import co.edu.utp.gia.sms.entidades.EvaluacionCualitativa;
import co.edu.utp.gia.sms.negocio.AtributoCalidadEJB;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase controladora de interfaz web que se encarga de la gestión de la evaluación de una referencia.
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
public class EvaluarReferenciaBean extends GenericBean<ReferenciaDTO> {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 6788702926482127829L;
    @Getter
    @Setter
    private List<AtributoCalidad> atributosCalidad;
    @Getter
    @Setter
    private List<EvaluacionCalidad> evaluaciones;
    @Inject
    private AtributoCalidadEJB atributoCalidadEJB;
    @Inject
    private ReferenciaEJB referenciaEJB;
    private ReferenciaDTO referencia;

    public void inicializar() {
        if (getRevision() != null) {
            referencia = (ReferenciaDTO) getFromSession("referenciaDTO");
            atributosCalidad = atributoCalidadEJB.obtenerAtributosCalidad(getRevision().getId());
            if (referencia.getEvaluaciones() == null || referencia.getEvaluaciones().isEmpty()) {
                evaluaciones = new ArrayList<>();
                for (AtributoCalidad atributoCalidad : atributosCalidad) {
                    EvaluacionCalidad evaluacion = new EvaluacionCalidad(referencia.getReferencia(), atributoCalidad);
                    evaluaciones.add(evaluacion);
                    referencia.addEvaluacion(evaluacion);
                }
            } else {
                evaluaciones = referencia.getEvaluaciones();
            }
        }
    }

    public void guardar() {
        for (EvaluacionCalidad evaluacion : evaluaciones) {
            referenciaEJB.guardarEvaluacion(evaluacion);
        }
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        getAndRemoveFromSession("referenciaDTO");
        PrimeFaces.current().dialog().closeDynamic(referencia);
    }

    /**
     * Metodo que retorna los valores de la enumeracion
     * {@link EvaluacionCualitativa}
     *
     * @return Los valores de la {@link EvaluacionCualitativa}
     */
    public EvaluacionCualitativa[] getEvaluacionCalitativaValues() {
        return EvaluacionCualitativa.values();
    }
}
