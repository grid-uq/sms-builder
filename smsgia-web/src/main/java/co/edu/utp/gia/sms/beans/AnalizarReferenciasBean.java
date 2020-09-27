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
public class AnalizarReferenciasBean extends GenericBean<ReferenciaDTO>{

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
		//TODO Pendiente por definir si es mejor que al momento de incluir/excluir de una vez se afecte la BD
		if (referencia.getTopicos().contains(topico)) {
			referencia.getTopicos().remove(topico);
			referenciaEJB.removerTopico(referencia.getId(),topico.getId() );
			String t = "\n" + "Tópico: " + topico.getDescripcion();
			String nota = referencia.getNota().replace(t, "");
			referenciaEJB.actualizarNota(referencia.getId(), nota);
			referencia.setNota(nota);
		} else {
			referencia.getTopicos().add(topico);
			referenciaEJB.adicionarTopico(referencia.getId(),topico.getId() );
			String nota = referencia.getNota() + "\n" + "Tópico: " + topico.getDescripcion();
			referenciaEJB.actualizarNota(referencia.getId(), nota);
			referencia.setNota(nota);
			
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
	
//	private Topico obtenerTopico(int topicoId) {
//		for (Topico topico : topicos) {
//			if (topico.getId().intValue() == topicoId) {
//				return topico;
//			}
//		}
//		return null;
//	}
//
//	private ReferenciaDTO obtenerReferencia(int referenciaId) {
//		for (ReferenciaDTO referencia : referencias) {
//			if (referencia.getId().intValue() == referenciaId) {
//				return referencia;
//			}
//		}
//		return null;
//	}

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

	public void actualizarNota(ReferenciaDTO referencia) {
		referenciaEJB.actualizarNota(referencia.getId(),referencia.getNota());
		
//		notaEJB.actualizar(referencia.getId(), referencia.getNota().getId(), referencia.getNota().getDescripcion(), referencia.getNota().getEtapa());
	}
	

	public void actualizarResumen(ReferenciaDTO referencia) {
		referenciaEJB.actualizarResumen(referencia.getId(),referencia.getResumen());
//		referenciaEJB.actualizarNota(referencia.getId(),referencia.getNota());
		
//		notaEJB.actualizar(referencia.getId(), referencia.getNota().getId(), referencia.getNota().getDescripcion(), referencia.getNota().getEtapa());
	}
	
	
	public void actualizarRelevancia(ReferenciaDTO referencia) {
		
		referenciaEJB.actualizarRelevancia(referencia.getId(), referencia.getRelevancia());
	}

	
}
