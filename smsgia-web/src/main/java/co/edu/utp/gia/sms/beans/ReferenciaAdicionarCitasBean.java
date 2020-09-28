package co.edu.utp.gia.sms.beans;

import java.io.IOException;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.importutil.FindReferenceCitation;
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

	public void guardarYear(ReferenciaDTO referencia) {
		try {
			System.out.println(String.format("Actualizando %d-%s",referencia.getId(),referencia.getYear()));
			referenciaEJB.actualizarYear(referencia.getId(), referencia.getYear());
		} catch (Exception e) {
			mostrarErrorGeneral(e.getMessage());

		}
	}
	
	public void guardarCita(ReferenciaDTO referencia) {
		try {
			referenciaEJB.actualizarCita(referencia.getId(), referencia.getCitas());
		} catch (Exception e) {
			mostrarErrorGeneral(e.getMessage());
		}
	}
	
	
	public void guardarRelevancia(ReferenciaDTO referencia) {
		
		referenciaEJB.actualizarRelevancia(referencia.getId(), referencia.getRelevancia());

	}
	
	public void completarCita(ReferenciaDTO referencia) {
		try {
			int i = FindReferenceCitation.getInstans().findCitation(referencia.getReferencia());
			guardarCita(referencia);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
