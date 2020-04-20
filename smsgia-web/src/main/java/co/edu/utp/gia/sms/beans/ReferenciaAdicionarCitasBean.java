package co.edu.utp.gia.sms.beans;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;
import co.edu.utp.gia.sms.negocio.RevisionEJB;

@Named
@ViewScoped
public class ReferenciaAdicionarCitasBean extends GenericBean<ReferenciaDTO>{

	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 4009685061343184778L;
	private List<ReferenciaDTO> referencias;
	@Inject
	private ReferenciaEJB referenciaEJB;
	@Inject
	private RevisionEJB revisionEJB;
	private List<Topico> topicos;

	public void inicializar() {

		if (revision != null) {
			referencias = referenciaEJB.obtenerTodas(revision.getId(), 3);
			topicos = revisionEJB.obtenerTopicos(revision.getId());
		}
	}

	public void incluirExcluirTopico(ReferenciaDTO referencia, Topico topico) {
		if (referencia.getTopicos().contains(topico)) {
			referencia.getTopicos().remove(topico);
		} else {
			referencia.getTopicos().add(topico);
		}
	}

	
	public void guardarCita(ReferenciaDTO referencia) {
		
		referenciaEJB.actualizarCita(referencia.getId(), referencia.getCitas());
	}
	
	
	public void guardarRelevancia(ReferenciaDTO referencia) {
		
		referenciaEJB.actualizarRelevancia(referencia.getId(), referencia.getRelevancia());

	}
	

	public void guardar() {
		for (ReferenciaDTO referencia : referencias) {
			referenciaEJB.limpiarTopicos(referencia.getId());
			for (Topico topico : referencia.getTopicos()) {
				referenciaEJB.adicionarTopico(referencia.getId(),topico.getId() );
			}
		}
		mostrarMensajeGeneral("Se guardaron los registro");
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
