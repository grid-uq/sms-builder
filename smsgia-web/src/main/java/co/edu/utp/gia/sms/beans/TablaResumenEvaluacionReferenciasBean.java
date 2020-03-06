package co.edu.utp.gia.sms.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.AtributoCalidadEJB;
import co.edu.utp.gia.sms.negocio.PreguntaEJB;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;

@ManagedBean
@ViewScoped
public class TablaResumenEvaluacionReferenciasBean {


	private List<ReferenciaDTO> referencias;
	@Inject
	private ReferenciaEJB referenciaEJB;
	@Inject
	private AtributoCalidadEJB atributoCalidadEJB;

	@ManagedProperty("#{registroInicialBean.revision}")
	private Revision revision;

	private List<AtributoCalidad> atributosCalidad;
	
	@PostConstruct
	public void inicializar() {
		if (revision != null) {
			referencias = referenciaEJB.obtenerTodasConEvaluacion(revision.getId(), 3);
			atributosCalidad = atributoCalidadEJB.obtenerAtributosCalidad( revision.getId() );
		}
	}
	

	public boolean tieneRalacion(ReferenciaDTO referencia,Pregunta pregunta) {
		for (Topico topico : referencia.getTopicos()) {
			if( pregunta.getTopicos().contains(topico) ) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Metodo que permite obtener el valor del atributo referencias
	 * 
	 * @return El valor del atributo referencias
	 */
	public List<ReferenciaDTO> getReferencias() {
		return referencias;
	}

	/**
	 * Metodo que permite asignar un valor al atributo referencias
	 * 
	 * @param referencias Valor a ser asignado al atributo referencias
	 */
	public void setReferencias(List<ReferenciaDTO> referencias) {
		this.referencias = referencias;
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
	 * Metodo que permite obtener el valor del atributo atributosCalidad
	 * @return El valor del atributo atributosCalidad
	 */
	public List<AtributoCalidad> getAtributosCalidad() {
		return atributosCalidad;
	}


	/**
	 * Metodo que permite asignar un valor al atributo atributosCalidad
	 * @param atributosCalidad Valor a ser asignado al atributo atributosCalidad
	 */
	public void setAtributosCalidad(List<AtributoCalidad> atributosCalidad) {
		this.atributosCalidad = atributosCalidad;
	}

}
