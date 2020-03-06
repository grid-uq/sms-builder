package co.edu.utp.gia.sms.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;
import co.edu.utp.gia.sms.negocio.RevisionEJB;

@ManagedBean
@ViewScoped
public class EvaluarReferenciasBean {

	private List<ReferenciaDTO> referencias;
	@Inject
	private ReferenciaEJB referenciaEJB;

	@ManagedProperty("#{registroInicialBean.revision}")
	private Revision revision;


	@PostConstruct
	public void inicializar() {

		if (revision != null) {
			referencias = referenciaEJB.obtenerTodasConEvaluacion(revision.getId(), 3);
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
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("referenciaDTO",referencia);
		PrimeFaces.current().dialog().openDynamic("/revision/evaluarReferencia", options, null);
	}


	public void guardar() {
		for (ReferenciaDTO referencia : referencias) {
			referenciaEJB.actualizarFiltro(referencia.getId(), referencia.getFiltro());
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se guardaron los registro"));
	}

    public void onEvaluacionRealizada(SelectEvent event) {
        ReferenciaDTO referencia = (ReferenciaDTO) event.getObject();
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
