package co.edu.utp.gia.sms.beans.estadisticas;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.model.chart.ChartSeries;

import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.negocio.AtributoCalidadEJB;

@ManagedBean
@ViewScoped
public class ReferenciasYearBean extends EstaditicaSerieDatoDTOBaseBean {

	@Inject
	private AtributoCalidadEJB atributoCalidadEJB;
	
	private List<String> years;
	
	@PostConstruct
	public void inicializar() {
		setTitulo("Referencias por Año");
		setEjeX("Año");
		setEjeY("# Referencias");
		setTipoGrafica("bar");
		setTiposGrafica( new String[] {"bar"} );
		if (getRevision() != null) {
//			setDatos();
			ChartSeries serie = crearSerieFromListDatoDTO(getEstadisticaEJB().obtenerReferenciasYear(getRevision().getId()));
			inicializarYears(serie);
			addSerie( serie );
			serie.setLabel( "All" );
			
			List<AtributoCalidad> atributosCalidad = atributoCalidadEJB.obtenerAtributosCalidad(getRevision().getId()); 
			for (AtributoCalidad atributoCalidad : atributosCalidad) {
				serie = crearSerieFromListDatoDTO( getEstadisticaEJB().obtenerReferenciasYear( getRevision().getId() , atributoCalidad.getId() ) );
				serie.setLabel( atributoCalidad.getDescripcion() );
				addSerie( serie );
			}
			crearModelo();
		}
	}

	private void inicializarYears(ChartSeries serie) {
		years = new ArrayList<>(  );
		serie.getData().keySet().forEach( y->years.add(y.toString()) );
	}

	/**
	 * Metodo que permite obtener el valor del atributo years
	 * @return El valor del atributo years
	 */
	public List<String> getYears() {
		return years;
	}

	/**
	 * Metodo que permite asignar un valor al atributo years
	 * @param years Valor a ser asignado al atributo years
	 */
	public void setYears(List<String> years) {
		this.years = years;
	}

	
}
