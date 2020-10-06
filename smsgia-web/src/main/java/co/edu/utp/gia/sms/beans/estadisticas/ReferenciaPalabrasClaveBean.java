package co.edu.utp.gia.sms.beans.estadisticas;

import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import co.edu.utp.gia.sms.beans.AbstractBean;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.negocio.EstadisticaEJB;

@Named
@ViewScoped
public class ReferenciaPalabrasClaveBean extends AbstractBean {

	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 3927845673735363457L;

	@Inject
	private EstadisticaEJB estadisticaEJB;

	private List<Referencia> referencias;

	private String keyword;

	private String listadoReferencias;

	private TipoMetadato[] metadatos;
	private Integer totalResultados;

	public void inicializar() {
		metadatos = new TipoMetadato[] { TipoMetadato.KEYWORD };
		if (getRevision() != null) {
			consultarReferencias();
		}
	}

	public void consultarReferencias() {
		String separador = "";
		referencias = estadisticaEJB.obtenerReferencias(getRevision().getId(), keyword, Arrays.asList(metadatos));
		listadoReferencias = "";

		for (Referencia referencia : referencias) {
			listadoReferencias = listadoReferencias + separador + referencia.getSpsid();
			separador = ", ";
		}
		totalResultados = referencias != null ? referencias.size() : null;
	}

	/**
	 * Metodo que permite obtener el valor del atributo keyword
	 * 
	 * @return El valor del atributo keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * Metodo que permite asignar un valor al atributo keyword
	 * 
	 * @param keyword Valor a ser asignado al atributo keyword
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * Metodo que permite obtener el valor del atributo listadoReferencias
	 * 
	 * @return El valor del atributo listadoReferencias
	 */
	public String getListadoReferencias() {
		return listadoReferencias;
	}

	/**
	 * Metodo que permite obtener el valor del atributo metadatos
	 * 
	 * @return El valor del atributo metadatos
	 */
	public TipoMetadato[] getMetadatos() {
		return metadatos;
	}

	/**
	 * Metodo que permite asignar un valor al atributo metadatos
	 * 
	 * @param metadatos Valor a ser asignado al atributo metadatos
	 */
	public void setMetadatos(TipoMetadato[] metadatos) {
		this.metadatos = metadatos;
	}

	public TipoMetadato[] getTipoMetadatos() {
		return TipoMetadato.values();
	}

	/**
	 * Metodo que permite obtener el valor del atributo totalResultados
	 * @return El valor del atributo totalResultados
	 */
	public Integer getTotalResultados() {
		return totalResultados;
	}

	
}
