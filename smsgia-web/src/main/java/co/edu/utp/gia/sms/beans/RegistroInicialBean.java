package co.edu.utp.gia.sms.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.negocio.RevisionEJB;

/*
 * 
 * Deprecated. 
 * This has been replaced by the Managed Beans specification in general and specifically the dependency injection, scopes 
 * and naming from the CDI specification.
 *  Note that the eager attribute for application scoped beans is replaced specifically by observing 
 *  the javax.enterprise.context.Initialized event for javax.enterprise.context.ApplicationScoped. 
 *  See 6.7.3 of the CDI spec for further details.

The presence of this annotation on a class automatically registers the class with the runtime as a managed bean class.
 Classes must be scanned for the presence of this annotation at application startup, before any requests have been serviced.
 * */
//@ManagedBean
//@SessionScoped
@Named("registroInicialBean")
//@SessionScoped
@ViewScoped
public class RegistroInicialBean extends AbstractBean {
	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = -6995163695300909108L;
	private Revision revision;
	private String nombre;
	private String objetivo;
	private String descripcion;
	private Integer id;
	private List<Revision> revisiones;
	@Inject
	private SeguridadBeanImpl seguridadBean;

	@Inject
	private RevisionEJB revisionEJB;

	@PostConstruct
	public void inicializar() {
		revision = (Revision) getFromSession("revision");
		if( seguridadBean.isAutenticado() ) {
			revisiones = revisionEJB.obtenerTodas(seguridadBean.getUsuario().getId());
		}
	}

	public void registrar() {
//		List<String> objetivos = Arrays.asList(objetivo.split(";"));
//		Revision r = revisionEJB.registrar(nombre, descripcion, objetivos);
		Revision r = revisionEJB.registrar(nombre, descripcion);
		id = r.getId();
		revisiones.add(r);
		limpiarCampos();
		revision = r;
	}

	private void limpiarCampos() {
		nombre = "";
		descripcion = "";
//		objetivo = "";
	}

	public String gestionar(int id) {
		this.id = id;
		return "/revision/registroPregunta";
	}

	public void onRowEdit(RowEditEvent<Revision> event) {
		Revision revision =  event.getObject();
		revisionEJB.actualizar(revision);
		mostrarMensajeGeneral("Registro editado");
	}

	public void onRowCancel(RowEditEvent<Revision> event) {
		mostrarMensajeGeneral("Edición cancelada");
	}

	public void onRowSelect(SelectEvent<Revision> event) {
		this.addToSession("revision",event.getObject());
		System.out.println("Se selecciono elemento "+event.getObject().getNombre());
		mostrarMensajeGeneral(String.format( "Revisión Seleccionada - %s ",event.getObject().getNombre() ) );
	}

	/**
	 * Permite eliminar una revision a través de su id
	 * 
	 * @param revision Revisión a eliminar
	 */

	public void eliminar(Revision revision) {
		revisionEJB.eliminar(revision.getId());
		revisiones.remove(revision);
		this.revision = null;
		mostrarMensajeGeneral("Registro eliminado");
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
