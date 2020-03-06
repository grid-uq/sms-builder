package co.edu.utp.gia.sms.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;

@ManagedBean
@ViewScoped
public class AplicarCriteriosReferenciasBean {


	private List<ReferenciaDTO> referencias;
	@Inject
	private ReferenciaEJB referenciaEJB;

	@ManagedProperty("#{registroInicialBean.revision}")
	private Revision revision;

	@PostConstruct
	public void inicializar() {

		if (revision != null) {
			referencias = referenciaEJB.obtenerTodas(revision.getId(), 1);
		}
	}
	
	public void guardar() {
		for (ReferenciaDTO referencia : referencias) {
			referenciaEJB.actualizarFiltro(referencia.getId(),referencia.getFiltro());
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se guardaron los registro"));
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

}
