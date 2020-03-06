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

import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.negocio.AtributoCalidadEJB;

@ManagedBean
@ViewScoped

public class RegistroAtributoCalidadBean implements Serializable {
	private String descripcion;
	@ManagedProperty(value = "#{registroInicialBean.revision}")
	private Revision revision;
	private List<AtributoCalidad> atributosCalidad;
	@Inject
	private AtributoCalidadEJB atributoCalidadEJB;

	@PostConstruct
	public void inicializar() {
		if (revision != null) {
			atributosCalidad = atributoCalidadEJB.obtenerAtributosCalidad(revision.getId());
		}
	}

	public void registrar() {
		AtributoCalidad atributo = atributoCalidadEJB.registrar( descripcion, revision.getId());
		atributosCalidad.add(atributo);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro Adicionado"));
		descripcion = "";
	}

	public void onRowEdit(RowEditEvent event) {
		AtributoCalidad atributoCalidad = ((AtributoCalidad) event.getObject());
		atributoCalidadEJB.actualizar(atributoCalidad);
		FacesMessage msg = new FacesMessage("Registro editado");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edicion cancelada");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * Permite eliminar una atributo de calidad
	 * 
	 * @param atributoCalidad Atributo de calidad a eliminar
	 */
	public void eliminar(AtributoCalidad atributoCalidad) {
		atributoCalidadEJB.eliminar(atributoCalidad.getId());
		atributosCalidad.remove(atributoCalidad);
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
	 * Metodo que permite obtener el valor del atributo atributosCalidad
	 * @return El valor del atributo atributosCalidad
	 */
	public List<AtributoCalidad> getAtributosCalidad() {
		return atributosCalidad;
	}

	/**
	 * Metodo que permite asignar un valor al atributo atributosCalidad
	 * @param atributosCalidad Valor a ser asignado al atributo atributosCalidad
	 */
	public void setAtributosCalidad(List<AtributoCalidad> atributosCalidad) {
		this.atributosCalidad = atributosCalidad;
	}


}
