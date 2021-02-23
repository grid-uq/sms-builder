package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.negocio.AtributoCalidadEJB;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.chart.ChartSeries;

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
        setTitulo("Referencias por Año");
        setEjeX("Año");
        setEjeY("# Referencias");
        setTipoGrafica("bar");
        setTiposGrafica(new String[]{"bar"});
        if (getRevision() != null) {
            ChartSeries serie = crearSerieFromListDatoDTO(getEstadisticaEJB().obtenerReferenciasYear(getRevision().getId()));
            inicializarYears(serie);
            addSerie(serie);
            serie.setLabel("All");

            List<AtributoCalidad> atributosCalidad = atributoCalidadEJB.obtenerAtributosCalidad(getRevision().getId());
            for (AtributoCalidad atributoCalidad : atributosCalidad) {
                serie = crearSerieFromListDatoDTO(getEstadisticaEJB().obtenerReferenciasYear(getRevision().getId(), atributoCalidad.getId()));
                serie.setLabel(atributoCalidad.getDescripcion());
                addSerie(serie);
            }
            crearModelo();
        }
    }

    private void inicializarYears(ChartSeries serie) {
        years = new ArrayList<>();
        serie.getData().keySet().forEach(y -> years.add(y.toString()));
    }


}
