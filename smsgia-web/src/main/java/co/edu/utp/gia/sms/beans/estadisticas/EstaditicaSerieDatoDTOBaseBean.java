package co.edu.utp.gia.sms.beans.estadisticas;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import co.edu.utp.gia.sms.dtos.DatoDTO;

public abstract class EstaditicaSerieDatoDTOBaseBean extends EstadisticaBean{

	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 4628270738923485836L;
	private List<ChartSeries> series;

	public EstaditicaSerieDatoDTOBaseBean() {
		series = new ArrayList<>();
	}
	
	protected void addSerie(List<DatoDTO> datos) {
		addSerie( crearSerieFromListDatoDTO(datos) );
	}
	
	
	protected void addSerie(ChartSeries serie) {
		if( series == null ) {
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

		if( series != null && series.size() > 0 ) {
			series.get(0).getData().forEach( (k,v)->{ model.set(k.toString(),v); } );
		}
		
//		for (DatoDTO datoDTO : datos) {
//			model.set(datoDTO.getEtiqueta(), datoDTO.getValor());
//		}

		model.setTitle(getTitulo());
		model.setLegendPosition("w");
		model.setShadow(false);
		return model;
	}

	protected BarChartModel crearBarModel() {
		BarChartModel model = new BarChartModel();

//		ChartSeries referencias = new ChartSeries();
		
//		referencias.setLabel(titulo);
//		for (DatoDTO datoDTO : datos) {
//			referencias.set(datoDTO.getEtiqueta(), datoDTO.getValor());
//		}
//		model.addSeries(referencias);

		if( series != null ) {
			series.forEach( s->model.addSeries(s) );
		}
		
		model.setTitle(getTitulo());
		model.setLegendPosition("ne");

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
	 * Metodo que permite obtener el valor del atributo series
	 * @return El valor del atributo series
	 */
	public List<ChartSeries> getSeries() {
		return series;
	}

	/**
	 * Metodo que permite asignar un valor al atributo series
	 * @param series Valor a ser asignado al atributo series
	 */
	public void setSeries(List<ChartSeries> series) {
		this.series = series;
	}

	
}
