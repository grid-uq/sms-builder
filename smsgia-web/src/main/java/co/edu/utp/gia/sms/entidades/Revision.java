package co.edu.utp.gia.sms.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @version 1.0
 * @since 13/06/2019
 *
 */
@Entity
@NamedQueries({

	@NamedQuery(name = Revision.REVISION_GET_ALL, query = "select p from Revision p")

})

public class Revision implements Serializable {
	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = -7643166662144090738L;
	/**
	 * Consulta que permite obtener las revisiones registradas en el sistema <br />
	 * <code>select p from Revision p  </code>
	 * 
	 */
	public static final String REVISION_GET_ALL = "Revision.getAll";
	
	/**
	 * Variable que representa el atributo id de la clase
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	/**
	 * Variable que representa el atributo nombre de la clase
	 */

	@Column(nullable = false)
	private String nombre;
	/**
	 * Variable que representa el atributo descripcion de la clase
	 */
	private String descripcion;
	/**
	 * Variable que representa el atributo objetivo de la clase
	 */
	private String objetivo;
	/**
	 * Variable que representa el atributo preguntas de la clase
	 */
	@OneToMany(mappedBy = "revision")
	private List<Pregunta> preguntas;

	/**
	 * Metodo que permite inicializar los elementos de la clase Revision
	 * 
	 * @param nombre
	 * @param descripcion
	 * @param objetivo
	 */
	public Revision(String nombre, String descripcion, String objetivo) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.objetivo = objetivo;
	}

	/**
	 * Metodo que permite inicializar los elementos de la clase Revision
	 */
	public Revision() {
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
	 * Metodo que permite obtener el valor del atributo preguntas
	 * 
	 * @return El valor del atributo preguntas
	 */
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	/**
	 * Metodo que permite asignar un valor al atributo preguntas
	 * 
	 * @param preguntas Valor a ser asignado al atributo preguntas
	 */
	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Revision other = (Revision) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
