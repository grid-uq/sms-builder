package co.edu.utp.gia.sms.beans.estadisticas;

import javax.inject.Inject;

import co.edu.utp.gia.sms.beans.AbstractRevisionBean;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.PieChartModel;

import co.edu.utp.gia.sms.negocio.EstadisticaEJB;

public abstract class EstadisticaBean extends AbstractRevisionBean {
	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 4686100580073797808L;


	@Inject
	private EstadisticaEJB estadisticaEJB;

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
	
	protected abstract PieChartModel crearPieModel();
	protected abstract BarChartModel crearBarModel();

	public void onChangeTipoGrafica() {
		crearModelo();
	}



	/**
	 * Metodo que permite obtener el valor del atributo model
	 * @return El valor del atributo model
	 */
	public final ChartModel getModel() {
		return model;
	}

	/**
	 * Metodo que permite asignar un valor al atributo model
	 * @param model Valor a ser asignado al atributo model
	 */
	public final void setModel(ChartModel model) {
		this.model = model;
	}

	/**
	 * Metodo que permite obtener el valor del atributo titulo
	 * @return El valor del atributo titulo
	 */
	public final String getTitulo() {
		return titulo;
	}

	/**
	 * Metodo que permite asignar un valor al atributo titulo
	 * @param titulo Valor a ser asignado al atributo titulo
	 */
	public final void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Metodo que permite obtener el valor del atributo ejeX
	 * @return El valor del atributo ejeX
	 */
	public final String getEjeX() {
		return ejeX;
	}

	/**
	 * Metodo que permite asignar un valor al atributo ejeX
	 * @param ejeX Valor a ser asignado al atributo ejeX
	 */
	public final void setEjeX(String ejeX) {
		this.ejeX = ejeX;
	}

	/**
	 * Metodo que permite obtener el valor del atributo ejeY
	 * @return El valor del atributo ejeY
	 */
	public final String getEjeY() {
		return ejeY;
	}

	/**
	 * Metodo que permite asignar un valor al atributo ejeY
	 * @param ejeY Valor a ser asignado al atributo ejeY
	 */
	public final void setEjeY(String ejeY) {
		this.ejeY = ejeY;
	}

	/**
	 * Metodo que permite obtener el valor del atributo tiposGrafica
	 * @return El valor del atributo tiposGrafica
	 */
	public final String[] getTiposGrafica() {
		return tiposGrafica;
	}

	/**
	 * Metodo que permite asignar un valor al atributo tiposGrafica
	 * @param tiposGrafica Valor a ser asignado al atributo tiposGrafica
	 */
	public final void setTiposGrafica(String[] tiposGrafica) {
		this.tiposGrafica = tiposGrafica;
	}

	/**
	 * Metodo que permite obtener el valor del atributo tipoGrafica
	 * @return El valor del atributo tipoGrafica
	 */
	public final String getTipoGrafica() {
		return tipoGrafica;
	}

	/**
	 * Metodo que permite asignar un valor al atributo tipoGrafica
	 * @param tipoGrafica Valor a ser asignado al atributo tipoGrafica
	 */
	public final void setTipoGrafica(String tipoGrafica) {
		this.tipoGrafica = tipoGrafica;
	}

	/**
	 * Metodo que permite obtener el valor del atributo estadisticaEJB
	 * @return El valor del atributo estadisticaEJB
	 */
	public final EstadisticaEJB getEstadisticaEJB() {
		return estadisticaEJB;
	}


	

}
