package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.beans.estadisticas.util.SerieDatos;
import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.negocio.AtributoCalidadService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.charts.bar.BarChartModel;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase controladora de interfaz web que se encarga de presentar los datos estadísticos de las referencias por año.
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
public class ReferenciasYearBean extends EstaditicaSerieDatoDTOBaseBean {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    @Serial
    private static final long serialVersionUID = 1765173044631798246L;

    @Inject
    private AtributoCalidadService atributoCalidadService;


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
            addSerie(getEstadisticaService().obtenerReferenciasYear(),"All");
//            BarChartDataSet serie = crearBarSerieFromListDatoDTO(getEstadisticaService().obtenerReferenciasYear(getRevision().getId()),"All");
            inicializarYears(getDatosSeries().get("All"));
//            addSerie(serie);

            var atributosCalidad = atributoCalidadService.get();
            for (AtributoCalidad atributoCalidad : atributosCalidad) {
                addSerie(getEstadisticaService().obtenerReferenciasYear(atributoCalidad.getId()),atributoCalidad.getDescripcion());
//                serie = crearBarSerieFromListDatoDTO(getEstadisticaService().obtenerReferenciasYear(getRevision().getId(), atributoCalidad.getId()),atributoCalidad.getDescripcion());
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
