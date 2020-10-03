package co.edu.utp.gia.sms.beans.estadisticas;

import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.negocio.EstadisticaEJB;

public class EstaditicaDatoDTOBaseBean {

	@Inject
	private EstadisticaEJB estadisticaEJB;

	@ManagedProperty("#{registroInicialBean.revision}")
	private Revision revision;

	private List<DatoDTO> datos;

	private ChartModel model;

	private String titulo;

	private String ejeX;
	private String ejeY;

	private String[] tiposGrafica = { "bar", "pie"};

	private String tipoGrafica = "pie";

	protected void crearModelo() {
		switch (tipoGrafica) {
		case "bar":
			model = crearBarModel();
			break;
//		case "line":
//			model = crearLineModel();
//			break;
		case "pie":
		default:
			model = crearPieModel();
			break;
		}
	}
	
	protected PieChartModel crearPieModel() {
		PieChartModel model = new PieChartModel();

		for (DatoDTO datoDTO : datos) {
			model.set(datoDTO.getEtiqueta(), datoDTO.getValor());
		}

		model.setTitle(titulo);
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

		model.setTitle(titulo);
//		model.setLegendPosition("ne");

		model.getAxis(AxisType.X).setLabel(ejeX);
		model.getAxis(AxisType.Y).setLabel(ejeY);

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
	
	public void onChangeTipoGrafica() {
		crearModelo();
	}
	/**
	 * Metodo que permite obtener el valor del atributo titulo
	 * 
	 * @return El valor del atributo titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Metodo que permite asignar un valor al atributo titulo
	 * 
	 * @param titulo Valor a ser asignado al atributo titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Metodo que permite obtener el valor del atributo estadisticaEJB
	 * 
	 * @return El valor del atributo estadisticaEJB
	 */
	public EstadisticaEJB getEstadisticaEJB() {
		return estadisticaEJB;
	}

	/**
	 * Metodo que permite asignar un valor al atributo estadisticaEJB
	 * 
	 * @param estadisticaEJB Valor a ser asignado al atributo estadisticaEJB
	 */
	public void setEstadisticaEJB(EstadisticaEJB estadisticaEJB) {
		this.estadisticaEJB = estadisticaEJB;
	}

	/**
	 * Metodo que permite obtener el valor del atributo revision
	 * 
	 * @return El valor del atributo revision
	 */
	public Revision getRevision() {
		return revision;
	}

	/**
	 * Metodo que permite asignar un valor al atributo revision
	 * 
	 * @param revision Valor a ser asignado al atributo revision
	 */
	public void setRevision(Revision revision) {
		this.revision = revision;
	}

	/**
	 * Metodo que permite obtener el valor del atributo datos
	 * 
	 * @return El valor del atributo datos
	 */
	public List<DatoDTO> getDatos() {
		return datos;
	}

	/**
	 * Metodo que permite asignar un valor al atributo datos
	 * 
	 * @param datos Valor a ser asignado al atributo datos
	 */
	public void setDatos(List<DatoDTO> datos) {
		this.datos = datos;
	}

	/**
	 * Metodo que permite obtener el valor del atributo model
	 * 
	 * @return El valor del atributo model
	 */
	public ChartModel getModel() {
		return model;
	}

	/**
	 * Metodo que permite asignar un valor al atributo model
	 * 
	 * @param model Valor a ser asignado al atributo model
	 */
	public void setModel(ChartModel model) {
		this.model = model;
	}

	/**
	 * Metodo que permite obtener el valor del atributo ejeX
	 * 
	 * @return El valor del atributo ejeX
	 */
	public String getEjeX() {
		return ejeX;
	}

	/**
	 * Metodo que permite asignar un valor al atributo ejeX
	 * 
	 * @param ejeX Valor a ser asignado al atributo ejeX
	 */
	public void setEjeX(String ejeX) {
		this.ejeX = ejeX;
	}

	/**
	 * Metodo que permite obtener el valor del atributo ejeY
	 * 
	 * @return El valor del atributo ejeY
	 */
	public String getEjeY() {
		return ejeY;
	}

	/**
	 * Metodo que permite asignar un valor al atributo ejeY
	 * 
	 * @param ejeY Valor a ser asignado al atributo ejeY
	 */
	public void setEjeY(String ejeY) {
		this.ejeY = ejeY;
	}

	/**
	 * Metodo que permite obtener el valor del atributo tiposGrafica
	 * 
	 * @return El valor del atributo tiposGrafica
	 */
	public String[] getTiposGrafica() {
		return tiposGrafica;
	}

	/**
	 * Metodo que permite asignar un valor al atributo tiposGrafica
	 * 
	 * @param tiposGrafica Valor a ser asignado al atributo tiposGrafica
	 */
	public void setTiposGrafica(String[] tiposGrafica) {
		this.tiposGrafica = tiposGrafica;
	}

	/**
	 * Metodo que permite obtener el valor del atributo tipoGrafica
	 * 
	 * @return El valor del atributo tipoGrafica
	 */
	public String getTipoGrafica() {
		return tipoGrafica;
	}

	/**
	 * Metodo que permite asignar un valor al atributo tipoGrafica
	 * 
	 * @param tipoGrafica Valor a ser asignado al atributo tipoGrafica
	 */
	public void setTipoGrafica(String tipoGrafica) {
		this.tipoGrafica = tipoGrafica;
	}

}
