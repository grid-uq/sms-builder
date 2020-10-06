package co.edu.utp.gia.sms.beans;

import java.io.IOException;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.importutil.FindReferenceCitation;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;

@Named
@ViewScoped
public class AplicarCriteriosReferenciasBean extends GenericBean<ReferenciaDTO> {

	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 6757021713542648202L;
	private List<ReferenciaDTO> referencias;
	@Inject
	private ReferenciaEJB referenciaEJB;
	

	public void inicializar() {
		if (getRevision() != null) {
			referencias = referenciaEJB.obtenerTodas(getRevision().getId(), 1);
		}
	}

	public void actualizarNota(ReferenciaDTO referencia) {
		referenciaEJB.actualizarNota(referencia.getId(), referencia.getNota());
	}

	public void adicionarResumen(ReferenciaDTO referencia) {
		try {
			String tranduccion = FindReferenceCitation.getInstans().findTranslate(referencia.getResumen());
			referencia.setNota( referencia.getNota() + "\n" + tranduccion );
			actualizarNota(referencia);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void seleccionarReferencia(ReferenciaDTO referencia) {
		referenciaEJB.actualizarFiltro(referencia.getId(), referencia.getFiltro());
	}

	public void actualizarRelevancia(ReferenciaDTO referencia) {

		referenciaEJB.actualizarRelevancia(referencia.getId(), referencia.getRelevancia());
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

}
