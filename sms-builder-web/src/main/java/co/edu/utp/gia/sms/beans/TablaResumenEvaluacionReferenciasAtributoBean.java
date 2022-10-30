package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.entidades.EvaluacionCualitativa;
import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.AtributoCalidadEJB;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;
import lombok.Getter;
import lombok.Setter;

import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase controladora de interfaz web que se encarga de presentar una tabla con el resumen de la evación de referencias por atributo de calidad.
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
public class TablaResumenEvaluacionReferenciasAtributoBean extends GenericBean<ReferenciaDTO> {


    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = -668848541409393797L;
    @Getter
    @Setter
    private List<ReferenciaDTO> referencias;
    @Inject
    private ReferenciaEJB referenciaEJB;
    @Inject
    private AtributoCalidadEJB atributoCalidadEJB;

    @Getter
    @Setter
    private List<AtributoCalidad> atributosCalidad;
    @Getter
    @Setter
    private AtributoCalidad atributoCalidad;
    @Getter
    @Setter
    private Integer idAtributo;

    public void inicializar() {
        if (getRevision() != null) {
            referencias = referenciaEJB.obtenerTodasConEvaluacion(getRevision().getId());
            atributosCalidad = atributoCalidadEJB.obtenerAtributosCalidad(getRevision().getId());
            atributoCalidad = atributosCalidad.stream().findFirst().orElse(null);
            idAtributo = atributoCalidad != null ? atributoCalidad.getId() : null;
        }
    }


    public boolean tieneRalacion(ReferenciaDTO referencia, Pregunta pregunta) {
        for (Topico topico : referencia.getTopicos()) {
            if (pregunta.getTopicos().contains(topico)) {
                return true;
            }
        }
        return false;
    }


    public void atributoSeleccionado() {
        atributoCalidad = atributosCalidad.stream().filter(a -> a.getId().equals( idAtributo )).findFirst().orElse(atributoCalidad);
    }


    /**
     * Permite obtener un arreglo con los valores de la {@link EvaluacionCualitativa}
     *
     * @return Arreglo de valores de la {@link EvaluacionCualitativa}
     */
    public List<SelectItem> getListaValores() {
        List<SelectItem> valores = new ArrayList<>();
        valores.add(new SelectItem("", "", "", false, false, true));
        for (EvaluacionCualitativa evaluacion : EvaluacionCualitativa.values()) {
            valores.add(new SelectItem(evaluacion));
        }
        return valores;
    }

}
