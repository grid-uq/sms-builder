package co.edu.utp.gia.sms.beans;

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
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;
import co.edu.utp.gia.sms.negocio.RevisionEJB;

@ManagedBean
@ViewScoped
public class AnalizarReferenciasBean {

	private List<ReferenciaDTO> referencias;
	@Inject
	private ReferenciaEJB referenciaEJB;
	@Inject
	private RevisionEJB revisionEJB;

	@ManagedProperty("#{registroInicialBean.revision}")
	private Revision revision;

	private List<Topico> topicos;

	@PostConstruct
	public void inicializar() {

		if (revision != null) {
			referencias = referenciaEJB.obtenerTodas(revision.getId(), 3);
			topicos = revisionEJB.obtenerTopicos(revision.getId());
		}
	}

	public void incluirExcluirTopico(ReferenciaDTO referencia, Topico topico) {
		//TODO Pendiente por definir si es mejor que al momento de incluir/excluir de una vez se afecte la BD
		if (referencia.getTopicos().contains(topico)) {
			referencia.getTopicos().remove(topico);
		} else {
			referencia.getTopicos().add(topico);
		}
	}

//	public void incluirExcluirTopico(Integer referenciaId, Integer topicoId) {
//		ReferenciaDTO referencia = obtenerReferencia(referenciaId);
//		Topico topico = obtenerTopico(topicoId);
//		if (referencia.getTopicos().contains(topico)) {
//			referencia.getTopicos().remove(topico);
//		} else {
//			referencia.getTopicos().add(topico);
//			
//		}
//	}
	
	private Topico obtenerTopico(int topicoId) {
		for (Topico topico : topicos) {
			if (topico.getId().intValue() == topicoId) {
				return topico;
			}
		}
		return null;
	}

	private ReferenciaDTO obtenerReferencia(int referenciaId) {
		for (ReferenciaDTO referencia : referencias) {
			if (referencia.getId().intValue() == referenciaId) {
				return referencia;
			}
		}
		return null;
	}

	public void guardar() {
		for (ReferenciaDTO referencia : referencias) {
			referenciaEJB.limpiarTopicos(referencia.getId());
			for (Topico topico : referencia.getTopicos()) {
				referenciaEJB.adicionarTopico(referencia.getId(),topico.getId() );
			}
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

	/**
	 * Metodo que permite obtener el valor del atributo topicos
	 * 
	 * @return El valor del atributo topicos
	 */
	public List<Topico> getTopicos() {
		return topicos;
	}

	/**
	 * Metodo que permite asignar un valor al atributo topicos
	 * 
	 * @param topicos Valor a ser asignado al atributo topicos
	 */
	public void setTopicos(List<Topico> topicos) {
		this.topicos = topicos;
	}

}
