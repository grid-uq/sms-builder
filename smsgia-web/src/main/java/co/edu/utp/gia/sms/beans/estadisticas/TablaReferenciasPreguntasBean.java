package co.edu.utp.gia.sms.beans.estadisticas;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import co.edu.utp.gia.sms.beans.AbstractBean;
import co.edu.utp.gia.sms.dtos.PreguntaDTO;
import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.PreguntaEJB;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;

@Named
@ViewScoped
public class TablaReferenciasPreguntasBean extends AbstractBean {
	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = -8876888410139722110L;
	private List<ReferenciaDTO> referencias;
	@Inject
	private ReferenciaEJB referenciaEJB;
	@Inject
	private PreguntaEJB preguntaEJB;


	private List<PreguntaDTO> preguntas;
	
	@PostConstruct
	public void inicializar() {
		if (getRevision() != null) {
			referencias = referenciaEJB.obtenerTodas(getRevision().getId(), 3);
			preguntas = preguntaEJB.obtenerPreguntas( getRevision().getId() );
		}
	}
	

	public boolean tieneRalacion(ReferenciaDTO referencia,PreguntaDTO pregunta) {
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
	 * Metodo que permite obtener el valor del atributo preguntas
	 * @return El valor del atributo preguntas
	 */
	public List<PreguntaDTO> getPreguntas() {
		return preguntas;
	}

	/**
	 * Metodo que permite asignar un valor al atributo preguntas
	 * @param preguntas Valor a ser asignado al atributo preguntas
	 */
	public void setPreguntas(List<PreguntaDTO> preguntas) {
		this.preguntas = preguntas;
	}
}
