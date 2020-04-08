package co.edu.utp.gia.sms.beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.negocio.RevisionEJB;

@ManagedBean
@SessionScoped
public class RegistroInicialBean implements Serializable {
	private Revision revision;
	private String nombre;
	private String objetivo;
	private String descripcion;
	private Integer id;
	private List<Revision> revisiones;

	@Inject
	private RevisionEJB revisionEJB;

	@PostConstruct
	public void inicializar() {
		revisiones = revisionEJB.obtenerTodas();
	}

	public void registrar() {
		List<String> objetivos = Arrays.asList(objetivo.split(";"));
		Revision r = revisionEJB.registrar(nombre, descripcion, objetivos);
		id = r.getId();
		revisiones.add(r);
		limpiarCampos();
		revision = r;
	}

	private void limpiarCampos() {
		nombre = "";
		descripcion = "";
		objetivo = "";
	}

	public String gestionar(int id) {
		this.id = id;
		return "/revision/registroPregunta";
	}

	public void onRowEdit(RowEditEvent event) {
		Revision revision = ((Revision) event.getObject());
		revisionEJB.actualizar(revision);
		FacesMessage msg = new FacesMessage("Registro editado");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edición cancelada");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Revision Seleccionada", ((Revision) event.getObject()).getNombre());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * Permite eliminar una revision a través de su id
	 * 
	 * @param id Id de la revisión a eliminar
	 */

	public void eliminar(Revision revision) {
		revisionEJB.eliminar(revision.getId());
		revisiones.remove(revision);
		this.revision = null;
		FacesMessage msg = new FacesMessage("Registro eliminado");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * Metodo que permite obtener el valor del atributo nombre
	 * 
	 * @return El valor del atributo nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo que permite asignar un valor al atributo nombre
	 * 
	 * @param nombre Valor a ser asignado al atributo nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo que permite obtener el valor del atributo objetivo
	 * 
	 * @return El valor del atributo objetivo
	 */
	public String getObjetivo() {
		return objetivo;
	}

	/**
	 * Metodo que permite asignar un valor al atributo objetivo
	 * 
	 * @param objetivo Valor a ser asignado al atributo objetivo
	 */
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

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
	 * Metodo que permite obtener el valor del atributo id
	 * 
	 * @return El valor del atributo id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Metodo que permite asignar un valor al atributo id
	 * 
	 * @param id Valor a ser asignado al atributo id
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * Metodo que permite obtener el valor del atributo revisiones
	 * 
	 * @return El valor del atributo revisiones
	 */
	public List<Revision> getRevisiones() {
		return revisiones;
	}

	/**
	 * Metodo que permite asignar un valor al atributo revisiones
	 * 
	 * @param revisiones Valor a ser asignado al atributo revisiones
	 */
	public void setRevisiones(List<Revision> revisiones) {
		this.revisiones = revisiones;
	}

}
