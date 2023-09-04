package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.entidades.EvaluacionCalidad;
import co.edu.utp.gia.sms.entidades.EvaluacionCualitativa;
import co.edu.utp.gia.sms.negocio.AtributoCalidadService;
import co.edu.utp.gia.sms.negocio.EvaluacionCalidadService;
import co.edu.utp.gia.sms.negocio.ReferenciaService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

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
    @Getter
    @Setter
    private Collection<AtributoCalidad> atributosCalidad;
    @Getter
    @Setter
    private List<EvaluacionCalidad> evaluaciones;
    @Inject
    private AtributoCalidadService atributoCalidadService;
    @Inject
    private EvaluacionCalidadService evaluacionCalidadService;
    @Inject
    private ReferenciaService referenciaService;
    private ReferenciaDTO referencia;

    public void inicializar() {
        referencia = (ReferenciaDTO) getFromSession("referenciaDTO");
        atributosCalidad = atributoCalidadService.get();
        evaluaciones = new LinkedList<>(referencia.getEvaluaciones());
        completarEvaluaciones();
    }

    private void completarEvaluaciones() {
        Predicate<AtributoCalidad> filtro = atributoCalidad->evaluaciones.stream()
                .map(EvaluacionCalidad::getAtributoCalidad)
                .noneMatch(atributoCalidad::equals);
        Consumer<AtributoCalidad> consumer = atributoCalidad-> evaluaciones.add(new EvaluacionCalidad(referencia.getReferencia(), atributoCalidad));
        atributosCalidad.stream().filter(filtro).forEach(consumer);
    }

    public void guardar() {

        evaluaciones.forEach( evaluacionCalidadService::update );

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
