package co.edu.utp.gia.sms.entidades;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
//@NamedQueries({
//
//		@NamedQuery(name = Pregunta.PREGUNTA_GET_ALL, query = "select p from Pregunta p where p.revision.id = :id")
//
//})

public class Pregunta implements Serializable {
	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = -176556849502833317L;

	/**
	 * Consulta que permite obtener las preguntas registradas en el sistema para una
	 * revision <br />
	 * <code>select p from Pregunta p where p.revision.id = :id </code>
	 * 
	 */
//	public static final String PREGUNTA_GET_ALL = "Pregunta.getAll";

	/**
	 * Variable que representa el atributo id de la clase
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	/**
	 * Variable que representa el atributo codigo de la clase
	 */
	@Column(length = 3, nullable = false)
	private String codigo;
	/**
	 * Variable que representa el atributo texto de la clase
	 */
	@Column(nullable = false)
	private String descripcion;

	@ManyToOne
	private Revision revision;

	/**
	 * Variable que representa los topico de una pregunta
	 */
	@OneToMany(mappedBy = "pregunta", fetch = EAGER)
	private List<Topico> topicos;

	/**
	 * Variable que representa los objetivos con los que se relaciona una pregunta
	 */
	@ManyToMany
	private List<Objetivo> objetivos;

	/**
	 * Metodo que permite inicializar los elementos de la clase Pregunta
	 * 
	 * @param codigo
	 * @param descripcion
	 * @param revision
	 */
	public Pregunta(String codigo, String descripcion, Revision revision) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.revision = revision;
	}

	/**
	 * Metodo que permite inicializar los elementos de la clase Pregunta
	 */
	public Pregunta() {
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
	 * Metodo que permite obtener el valor del atributo codigo
	 * 
	 * @return El valor del atributo codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Metodo que permite asignar un valor al atributo codigo
	 * 
	 * @param codigo Valor a ser asignado al atributo codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	 * Metodo que permite obtener el valor del atributo topicos
	 * 
	 * @return El valor del atributo topicos
	 */
	public List<Topico> getTopicos() {
		return topicos;
	}

	/**
	 * Metodo que permite asignar un valor al atributo topicos
	 * 
	 * @param topicos Valor a ser asignado al atributo topicos
	 */
	public void setTopicos(List<Topico> topicos) {
		this.topicos = topicos;
	}

	/**
	 * Metodo que permite obtener el valor del atributo objetivos
	 * 
	 * @return El valor del atributo objetivos
	 */
	public List<Objetivo> getObjetivos() {
		return objetivos;
	}

	/**
	 * Metodo que permite asignar un valor al atributo objetivos
	 * 
	 * @param objetivos Valor a ser asignado al atributo objetivos
	 */
	public void setObjetivos(List<Objetivo> objetivos) {
		this.objetivos = objetivos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		Pregunta other = (Pregunta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
