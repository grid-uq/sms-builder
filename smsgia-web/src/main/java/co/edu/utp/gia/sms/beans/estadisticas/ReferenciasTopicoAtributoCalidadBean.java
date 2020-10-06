package co.edu.utp.gia.sms.beans.estadisticas;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.ChartSeries;

import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.negocio.AtributoCalidadEJB;

@Named
@ViewScoped
public class ReferenciasTopicoAtributoCalidadBean extends EstaditicaSerieDatoDTOBaseBean {

	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 3695939063364135580L;

	@Inject
	private AtributoCalidadEJB atributoCalidadEJB;

	private String codigo;
	
	private List<String> topicos;
	
	public void inicializar() {
		setTitulo("Referencias x Topico");
		setEjeX("Topicos");
		setEjeY("# Referencias");
		setTipoGrafica("bar");
		setTiposGrafica( new String[] {"bar"} );
		
		if (getRevision() != null) {
//			setDatos(getEstadisticaEJB().obtenerReferenciasTopico(getRevision().getId()));
			onChangePregunta();
		}
	}
	
	public void onChangePregunta() {
		getSeries().clear();
		ChartSeries serie;
		if( codigo != null ) {
			serie = crearSerieFromListDatoDTO( getEstadisticaEJB().obtenerReferenciasTopico(getRevision().getId(),codigo) );
		}else {
			serie = crearSerieFromListDatoDTO( getEstadisticaEJB().obtenerReferenciasTopico(getRevision().getId()));
		}
		serie.setLabel("SPSs");
		addSerie( serie );
		inicializarTopicos(serie);
		
		List<AtributoCalidad> atributosCalidad = atributoCalidadEJB.obtenerAtributosCalidad(getRevision().getId()); 
		for (AtributoCalidad atributoCalidad : atributosCalidad) {
			if( codigo != null ) {
				serie = crearSerieFromListDatoDTO( getEstadisticaEJB().obtenerReferenciasTopico(getRevision().getId(),codigo , atributoCalidad.getId() ) );
			}else {
				serie = crearSerieFromListDatoDTO( getEstadisticaEJB().obtenerReferenciasTopico(getRevision().getId(), atributoCalidad.getId() ) );
			}
			serie.setLabel( atributoCalidad.getDescripcion() );
			addSerie( serie );
		}
		
		crearModelo();
	}

	private void inicializarTopicos(ChartSeries serie) {
		topicos = new ArrayList<>(  );
		serie.getData().keySet().forEach( y->topicos.add(y.toString()) );
	}

	/**
	 * Metodo que permite obtener el valor del atributo codigo
	 * @return El valor del atributo codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Metodo que permite asignar un valor al atributo codigo
	 * @param codigo Valor a ser asignado al atributo codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Metodo que permite obtener el valor del atributo topicos
	 * @return El valor del atributo topicos
	 */
	public List<String> getTopicos() {
		return topicos;
	}

	/**
	 * Metodo que permite asignar un valor al atributo topicos
	 * @param topicos Valor a ser asignado al atributo topicos
	 */
	public void setTopicos(List<String> topicos) {
		this.topicos = topicos;
	}

	
	
}
