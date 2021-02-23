package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import java.util.List;

public abstract class EstaditicaDatoDTOBaseBean extends EstadisticaBean {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = -6652760630318393603L;

    @Getter
    @Setter
    private List<DatoDTO> datos;

    protected PieChartModel crearPieModel() {
        PieChartModel model = new PieChartModel();

        for (DatoDTO datoDTO : datos) {
            model.set(datoDTO.getEtiqueta(), datoDTO.getValor());
        }

        model.setTitle(getTitulo());
        model.setLegendPosition("w");
        model.setShadow(false);
        return model;
    }

    protected BarChartModel crearBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries referencias = new ChartSeries();


//        referencias.setLabel("Referencias");
//		referencias.setLabel(titulo);
        for (DatoDTO datoDTO : datos) {
            referencias.set(datoDTO.getEtiqueta(), datoDTO.getValor());
        }

        model.addSeries(referencias);

        model.setTitle(getTitulo());
//		model.setLegendPosition("ne");

        model.getAxis(AxisType.X).setLabel(getEjeX());
        model.getAxis(AxisType.Y).setLabel(getEjeY());

        return model;
    }

}
