package co.edu.utp.gia.sms.beans.seguridad;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import co.edu.utp.gia.sms.beans.AbstractBean;
import co.edu.utp.gia.sms.entidades.Recurso;
import co.edu.utp.gia.sms.negocio.RecursoBO;
import org.primefaces.event.RowEditEvent;



/**
 * Clase de negocio encargada la interacción del usuario con las funcionalidades
 * de gestion de la entidad {@link Recurso}
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 2015-12-02
 *
 */
@Named
@ViewScoped
public class RecursoBean extends AbstractBean {
	/**
	 * Variable que representa el atributo {@link Recurso} de la clase
	 */
	private Recurso recurso = new Recurso();

	/**
	 * Variable que representa el atributo recursos de la clase. Contiene la
	 * lista de {@link Recurso} registradas en el sistema
	 */
	private List<Recurso> recursos;

	/**
	 * Variable que representa el atributo recursoEjb de la clase. Instancia
	 * del objeto de negocio que permite la gestion de las {@link Recurso}
	 */
	@Inject
	private RecursoBO recursoBO;

	/**
	 * Metodo encargado de inicializar los datos de la clase
	 */
	@PostConstruct
	public void inicializar() {
		recursos = recursoBO.listar();
	}

	/**
	 * Metodo que permite registrar una {@link Recurso} en el sistema
	 */
	public void registrar() {
		try {
			recursoBO.registrar(recurso);
			recurso = new Recurso();
			recursos = recursoBO.listar();
			mostrarMensajeGeneral("Registro exitoso");
		} catch (Exception e) {
			mostrarErrorGeneral(e.getMessage());
		}
	}

	/**
	 * Metodo que permite eliminar una {@link Recurso} del sistema
	 * 
	 * @param recurso
	 *            Instancia de la entidad {@link Recurso} a ser eliminada
	 */
	public void eliminar(Recurso recurso) {
		try {
			recursoBO.eliminar(recurso);
			recursos = recursoBO.listar();
			mostrarMensajeGeneral("Registro eliminado");
		} catch (Exception e) {
			mostrarErrorGeneral(e.getMessage());
		}
	}

	/**
	 * Metodo encargado de responder al evento de edicion de la
	 * {@link Recurso}
	 * 
	 * @param event
	 *            Evento generado al editar una {@link Recurso}
	 */
	public void onRowEdit(RowEditEvent event) {
		Recurso recurso = ((Recurso) event.getObject());
		try {
			recursoBO.actualizar(recurso);
			mostrarMensajeGeneral("Registro actializado");
		} catch (Exception e) {
			mostrarErrorGeneral(e.getMessage());
			recursos = recursoBO.listar();
		}
	}

	/**
	 * Metodo encargado de responder al evento de cancelación de la edicion de
	 * {@link Recurso}
	 * 
	 * @param event
	 *            Evento generado al cancela la edición de un {@link Recurso}
	 */
	public void onRowCancel(RowEditEvent event) {
		mostrarMensajeGeneral("Edición cancelada");
	}

	/**
	 * Metodo que permite obtener el valor del atributo recurso
	 * 
	 * @return El valor del atributo recurso
	 */
	public Recurso getRecurso() {
		return recurso;
	}

	/**
	 * Metodo que permite asignar un valor al atributo recurso
	 * 
	 * @param recurso
	 *            Valor a ser asignado al atributo recurso
	 */
	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	/**
	 * Metodo que permite obtener el valor del atributo recursos
	 * 
	 * @return El valor del atributo recursos
	 */
	public List<Recurso> getRecursos() {
		return recursos;
	}

	/**
	 * Metodo que permite asignar un valor al atributo recursos
	 * 
	 * @param recursos
	 *            Valor a ser asignado al atributo recursos
	 */
	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}

}
