package co.edu.utp.gia.sms.beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import co.edu.utp.gia.sms.negocio.RevisionEJB;

@Named
@ViewScoped
public class RevisionBean implements Serializable {

	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = -4039953830747820815L;
	private String nombre;
	private String descripcion;
	private String objetivo;
	@Inject
	private RevisionEJB revisionEJB;

	public void registrar() {
		
		List<String> objetivos = Arrays.asList("G1","G2");
		revisionEJB.registrar("revi", "Des", objetivos);
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
	 * Metodo que permite obtener el valor del atributo revisionEJB
	 * 
	 * @return El valor del atributo revisionEJB
	 */
	public RevisionEJB getRevisionEJB() {
		return revisionEJB;
	}

	/**
	 * Metodo que permite asignar un valor al atributo revisionEJB
	 * 
	 * @param revisionEJB Valor a ser asignado al atributo revisionEJB
	 */
	public void setRevisionEJB(RevisionEJB revisionEJB) {
		this.revisionEJB = revisionEJB;
	}

}
