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

@Named
@ViewScoped
public class TablaResumenEvaluacionReferenciasBean extends GenericBean<ReferenciaDTO> {


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

    public void inicializar() {
        if (getRevision() != null) {
            referencias = referenciaEJB.obtenerTodasConEvaluacion(getRevision().getId());
            atributosCalidad = atributoCalidadEJB.obtenerAtributosCalidad(getRevision().getId());
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
