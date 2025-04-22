package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.negocio.AtributoCalidadService;
import co.edu.utp.gia.sms.negocio.ReferenciaService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static co.edu.utp.gia.sms.entidades.EvaluacionCualitativa.CUMPLE;

/**
 * Clase controladora de interfaz web que se encarga de presentar una tabla de resumen con las referencias mejor calificadas por índice de calidad
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
public class TablaResumenMejorEvaluacionReferenciasBean extends GenericBean<ReferenciaDTO> {
    @Getter @Setter
    private List<ReferenciaDTO> referencias;
    @Inject
    private ReferenciaService referenciaService;
    @Inject
    private AtributoCalidadService atributoCalidadService;

    @Getter @Setter
    private Collection<AtributoCalidad> atributosCalidad;

    public void inicializar() {
        referencias = referenciaService.findWithEvaluacion();
        atributosCalidad = atributoCalidadService.get();
    }

    public String mejoresReferencias(AtributoCalidad atributoCalidad) {
        Predicate<ReferenciaDTO> filtro = referencia -> referencia.getEvaluacionCalidad(atributoCalidad)  != null
                && CUMPLE.equals(referencia.getEvaluacionCalidad(atributoCalidad).getEvaluacionCualitativa());
        return referencias.stream().filter(filtro).map(ReferenciaDTO::getSpsid).collect(Collectors.joining(" "));
    }
}
