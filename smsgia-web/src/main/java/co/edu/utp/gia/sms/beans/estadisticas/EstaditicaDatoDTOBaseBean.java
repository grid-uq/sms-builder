package co.edu.utp.gia.sms.beans.estadisticas;

import java.util.List;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import co.edu.utp.gia.sms.dtos.DatoDTO;

public abstract class EstaditicaDatoDTOBaseBean extends EstadisticaBean{

	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = -6652760630318393603L;

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

	
//	protected LineChartModel crearLineModel() {
//		LineChartModel model = new LineChartModel();
//		 
//        LineChartSeries series1 = new LineChartSeries();
//        series1.setLabel(titulo);
// 
//        for (DatoDTO datoDTO : datos) {
//        	series1.set(datoDTO.getEtiqueta(), datoDTO.getValor());
//		}
//        model.addSeries(series1);
//        model.setTitle(titulo);
//        model.setLegendPosition("e");
//        model.getAxis(AxisType.Y).setLabel(ejeY);
//        model.getAxis(AxisType.X).setLabel(ejeX);
//        return model;
//    }
	
	/**
	 * Metodo que permite obtener el valor del atributo datos
	 * @return El valor del atributo datos
	 */
	public final List<DatoDTO> getDatos() {
		return datos;
	}

	/**
	 * Metodo que permite asignar un valor al atributo datos
	 * @param datos Valor a ser asignado al atributo datos
	 */
	public final void setDatos(List<DatoDTO> datos) {
		this.datos = datos;
	}
}
