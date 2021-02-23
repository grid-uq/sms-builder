package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import java.util.ArrayList;
import java.util.List;

public abstract class EstaditicaSerieDatoDTOBaseBean extends EstadisticaBean {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 4628270738923485836L;
    @Getter
    @Setter
    private List<ChartSeries> series;

    public EstaditicaSerieDatoDTOBaseBean() {
        series = new ArrayList<>();
    }

    protected void addSerie(List<DatoDTO> datos) {
        addSerie(crearSerieFromListDatoDTO(datos));
    }


    protected void addSerie(ChartSeries serie) {
        if (series == null) {
            series = new ArrayList<>();
        }
        series.add(serie);
    }


    protected ChartSeries crearSerieFromListDatoDTO(List<DatoDTO> datos) {
        ChartSeries serie = new ChartSeries();
        for (DatoDTO dato : datos) {
            serie.set(dato.getEtiqueta(), dato.getValor());
        }
        return serie;
    }

    protected PieChartModel crearPieModel() {
        PieChartModel model = new PieChartModel();

        if (series != null && !series.isEmpty() ) {
            series.get(0).getData().forEach((k, v) -> model.set(k.toString(), v) );
        }

        model.setTitle(getTitulo());
        model.setLegendPosition("w");
        model.setShadow(false);
        return model;
    }

    protected BarChartModel crearBarModel() {
        BarChartModel model = new BarChartModel();


        if (series != null) {
            series.forEach(model::addSeries);
        }

        model.setTitle(getTitulo());
        model.setLegendPosition("ne");

        model.getAxis(AxisType.X).setLabel(getEjeX());
        model.getAxis(AxisType.Y).setLabel(getEjeY());

        return model;
    }


}
