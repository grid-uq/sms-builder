package co.edu.utp.gia.sms.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;

import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.entidades.Termino;
import co.edu.utp.gia.sms.negocio.TerminoEJB;

@ManagedBean
@ViewScoped

public class RegistroTerminoBean implements Serializable {
	private String descripcion;
	@ManagedProperty(value = "#{registroInicialBean.revision}")
	private Revision revision;
	private List<Termino> terminos;
	@Inject
	private TerminoEJB terminoEJB;

	@PostConstruct
	public void inicializar() {
		if (revision != null) {
			terminos = terminoEJB.obtenerTerminos(revision.getId());
		}
	}

	public void registrar() {
		Termino termino = terminoEJB.registrar( descripcion, revision.getId());
		terminos.add(termino);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro Adicionado"));
		descripcion = "";
	}

	public void onRowEdit(RowEditEvent event) {
		Termino termino = ((Termino) event.getObject());
		terminoEJB.actualizar(termino);
		FacesMessage msg = new FacesMessage("Registro editado");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edicion cancelada");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * Permite eliminar una termino
	 * 
	 * @param termino termino a eliminar
	 */
	public void eliminar(Termino termino) {
		terminoEJB.eliminar(termino.getId());
		terminos.remove(termino);
		FacesMessage msg = new FacesMessage("Registro eliminado");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}


	
////////// ----- GET/SET ----- ////////////	
	/**
	 * Metodo que permite obtener el valor del atributo descripcion
	 * 
	 * @return El valor del atributo descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Metodo que permite asignar un valor al atributo descripcion
	 * 
	 * @param descripcion Valor a ser asignado al atributo descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	 * Metodo que permite obtener el valor del atributo terminos
	 * @return El valor del atributo terminos
	 */
	public List<Termino> getTerminos() {
		return terminos;
	}

	/**
	 * Metodo que permite asignar un valor al atributo terminos
	 * @param terminos Valor a ser asignado al atributo terminos
	 */
	public void setTerminos(List<Termino> terminos) {
		this.terminos = terminos;
	}



}
