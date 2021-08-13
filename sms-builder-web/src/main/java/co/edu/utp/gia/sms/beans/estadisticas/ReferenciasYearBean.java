package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.beans.estadisticas.util.SerieDatos;
import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.negocio.AtributoCalidadEJB;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.charts.bar.BarChartModel;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class ReferenciasYearBean extends EstaditicaSerieDatoDTOBaseBean {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 1765173044631798246L;

    @Inject
    private AtributoCalidadEJB atributoCalidadEJB;


    @Getter
    @Setter
    private List<String> years;

    public void inicializar() {
        setEjeX(getMessage(MessageConstants.YEAR));
        setEjeY("# "+getMessage(MessageConstants.SPS));
        setTitulo(getEjeY() + " - " + getEjeX());
        setTipoGrafica("bar");
        setTiposGrafica(new String[]{"bar"});
        if (getRevision() != null) {
            addSerie(getEstadisticaEJB().obtenerReferenciasYear(getRevision().getId()),"All");
//            BarChartDataSet serie = crearBarSerieFromListDatoDTO(getEstadisticaEJB().obtenerReferenciasYear(getRevision().getId()),"All");
            inicializarYears(getDatosSeries().get("All"));
//            addSerie(serie);

            List<AtributoCalidad> atributosCalidad = atributoCalidadEJB.obtenerAtributosCalidad(getRevision().getId());
            for (AtributoCalidad atributoCalidad : atributosCalidad) {
                addSerie(getEstadisticaEJB().obtenerReferenciasYear(getRevision().getId(), atributoCalidad.getId()),atributoCalidad.getDescripcion());
//                serie = crearBarSerieFromListDatoDTO(getEstadisticaEJB().obtenerReferenciasYear(getRevision().getId(), atributoCalidad.getId()),atributoCalidad.getDescripcion());
//                addSerie(serie);
            }
            crearModelo();
        }
    }

    private void inicializarYears(SerieDatos serie) {
        years = new ArrayList<>();
        serie.getDatos().forEach(y -> years.add(y.getEtiqueta()));
    }

    public BarChartModel getModel(){
        return (BarChartModel) super.getModel();
    }

}
