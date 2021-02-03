package co.edu.utp.gia.sms.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Entidad que modela (representa) las personas que interacturan con la
 * aplicacion
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = Persona.FIND_BY_ID, query = "select persona from Persona persona where persona.id = :id"),
		@NamedQuery(name = Persona.GET_ALL, query = "select persona from Persona persona")

})

public class Persona extends Usuario implements Serializable {
	/**
	 * Variable que representa el atributo numeroIdentificacion, el cual
	 * representa el número de identificación de la {@link Persona}
	 */
	@Column(length = 20)
	private String numeroIdentificacion;
	/**
	 * Variable que representa el atributo nombre de la {@link Persona}
	 */
	@Column(length = 70)
	private String nombre;
	/**
	 * Variable que representa el atributo email de la {@link Persona}
	 */
	@Column(length = 150)
	private String email;

	/**
	 * Variable que representa el número de telefono de la {@link Persona}
	 */
	private String telefono;

	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constante que identifica la consulta que permite buscar una
	 * {@link Persona} por su id <br />
	 * {@code select persona from Persona persona where persona.id = :id}
	 */
	public static final String FIND_BY_ID = "Persona_findById";
	/**
	 * Constante que identifica la consulta que permite obtener todas las
	 * {@link Persona} registradas en el sistema <br />
	 * {@code select persona from Persona persona}
	 */
	public static final String GET_ALL = "Persona_getAll";

	/**
	 * Metodo que permite obtener el valor del atributo numeroIdentificacion
	 * 
	 * @return El valor del atributo numeroIdentificacion
	 */
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	/**
	 * Metodo que permite asignar un valor al atributo numeroIdentificacion
	 * 
	 * @param numeroIdentificacion
	 *            Valor a ser asignado al atributo numeroIdentificacion
	 */
	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
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
	 * @param nombre
	 *            Valor a ser asignado al atributo nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo que permite obtener el valor del atributo email
	 * 
	 * @return El valor del atributo email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Metodo que permite asignar un valor al atributo email
	 * 
	 * @param email
	 *            Valor a ser asignado al atributo email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Metodo que permite obtener el valor del atributo telefono
	 * 
	 * @return El valor del atributo telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Metodo que permite asignar un valor al atributo telefono
	 * 
	 * @param telefono
	 *            Valor a ser asignado al atributo telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
