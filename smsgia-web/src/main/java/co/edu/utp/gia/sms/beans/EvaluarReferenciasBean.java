package co.edu.utp.gia.sms.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;

@Named
@ViewScoped
public class EvaluarReferenciasBean extends GenericBean<ReferenciaDTO> {

	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = -4485002227034874858L;
	private List<ReferenciaDTO> referencias;
	@Inject
	private ReferenciaEJB referenciaEJB;

	public void inicializar() {

		if (getRevision() != null) {
			referencias = referenciaEJB.obtenerTodasConEvaluacion(getRevision().getId(), 3);
		}
	}

	public void incluirExcluirTopico(ReferenciaDTO referencia, Topico topico) {
		if (referencia.getTopicos().contains(topico)) {
			referencia.getTopicos().remove(topico);
		} else {
			referencia.getTopicos().add(topico);
		}
	}

	public void evaluar(ReferenciaDTO referencia) {
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        
        addToSession("referenciaDTO",referencia);
		PrimeFaces.current().dialog().openDynamic("/revision/evaluarReferencia", options, null);
	}

	public void guardar() {
		for (ReferenciaDTO referencia : referencias) {
			referenciaEJB.actualizarFiltro(referencia.getId(), referencia.getFiltro());
		}
		mostrarMensajeGeneral("Se guardaron los registro");
	}

	public void evaluacionAutomatica() {
		try {
			for (ReferenciaDTO referencia : referencias) {

				referenciaEJB.evaluacionAutomatica(referencia.getId());
			}
			referencias = referenciaEJB.obtenerTodasConEvaluacion(getRevision().getId(), 3);
			mostrarMensajeGeneral("Se guardaron los registro");
		} catch (Exception e) {
			mostrarErrorGeneral( e.getMessage() );
		}
	}
	
    public void onEvaluacionRealizada(SelectEvent<ReferenciaDTO> event) {
        ReferenciaDTO referencia = event.getObject();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Referencia Evaluada", "Id:" + referencia.getId());
		FacesContext.getCurrentInstance().addMessage(null, message);
//		inicializar();
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