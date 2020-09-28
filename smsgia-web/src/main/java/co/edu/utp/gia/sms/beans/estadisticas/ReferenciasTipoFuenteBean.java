package co.edu.utp.gia.sms.beans.estadisticas;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;

import co.edu.utp.gia.sms.importutil.TipoFuente;

@ManagedBean
@ViewScoped
public class ReferenciasTipoFuenteBean extends EstaditicaDatoDTOBaseBean {

	private TipoFuente tipo;

	@PostConstruct
	public void inicializar() {
		setTitulo("Referencias por Tipo de Fuente");
		setEjeX("Fuente");
		setEjeY("# Referencias");

//		setTiposGrafica(new String[] { "bar" });
//		setTipoGrafica("bar");

		if (getRevision() != null) {
			setDatos(getEstadisticaEJB().obtenerReferenciasTipoFuente(getRevision().getId()));

			crearModelo();
			
		}
	}


	public void onChangeTipoFuente() {
		if (tipo == null) {
			setDatos(getEstadisticaEJB().obtenerReferenciasTipoFuente(getRevision().getId()));
		} else {
			setDatos(getEstadisticaEJB().obtenerReferenciasTipoFuenteNombre(getRevision().getId(),tipo));
		} 
		crearModelo();
	}

	/**
	 * Metodo que permite obtener el valor del atributo tipo
	 * 
	 * @return El valor del atributo tipo
	 */
	public TipoFuente getTipo() {
		return tipo;
	}

	/**
	 * Metodo que permite asignar un valor al atributo tipo
	 * 
	 * @param tipo Valor a ser asignado al atributo tipo
	 */
	public void setTipo(TipoFuente tipo) {
		System.out.println("TIPO "+tipo);
		this.tipo = tipo;
	}

	public TipoFuente[] getTiposFuente() {
		return TipoFuente.values();
	}

}
