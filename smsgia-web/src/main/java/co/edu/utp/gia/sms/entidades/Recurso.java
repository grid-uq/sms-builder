package co.edu.utp.gia.sms.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Clase que representa la entidad Recurso, la cual permite modelar en el
 * sistema las páginas que pertenecen al sistema con el fin de poder restringir
 * el acceso a las mismas
 * 
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version %I%, %G%
 * @since 1.0
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = Recurso.FIND_BY_ID, query = "select recurso from Recurso recurso where recurso.id = :id"),
		@NamedQuery(name = Recurso.GET_ALL, query = "select recurso from Recurso recurso"),
		@NamedQuery(name = Recurso.FIND_BY_PUBLIC, query = "select recurso.url from Recurso recurso where recurso.publico = :estado"),
		@NamedQuery(name = Recurso.FIND_BY_ROL, query = "select recurso from Rol rol inner join rol.recursos recurso where rol = :rol"),
		@NamedQuery(name=Recurso.FIND_BY_URL,query="select recurso from Recurso recurso where recurso.url = :url")

})

public class Recurso implements Entidad<Integer> {

	/**
	 * Variable que representa el atributo id de la clase. Permite identificar
	 * de forma única un recurso
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * Variable que representa el atributo nombre de la clase. Representa el
	 * nombre del recurso
	 */
	@Column(nullable = false, length = 50, unique = true)
	private String nombre;
	/**
	 * Variable que representa el atributo url de la clase. Representa la URL de
	 * acceso al recurso en el sistema
	 */
	@Column(nullable = false, length = 300, unique = true)
	private String url;
	/**
	 * Variable que representa el atributo publico de la clase. Determina si un
	 * recurso es de acceso público o privado
	 */
	private boolean publico;
	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constante que identifica la consulta que permite buscar un
	 * {@link Recurso} por su id <br />
	 * {@code select recurso from Recurso recurso where recurso.id = :id}
	 */
	public static final String FIND_BY_ID = "Recurso_findById";
	/**
	 * Constante que identifica la consulta que permite obtener todos los
	 * {@link Recurso} registrados en el sistema <br />
	 * {@code select recurso from Recurso recurso}
	 */
	public static final String GET_ALL = "Recurso_getAll";
	/**
	 * Constante que identifica la consulta que permite obtener todos los
	 * {@link Recurso} registrados en el sistema con un determinado valor para su atributo {@link Recurso#publico}<br />
	 * {@code select recurso from Recurso recurso where recurso.publico = :estado}
	 */
	public static final String FIND_BY_PUBLIC = "Recurso_findByPublic";
	/**
	 * Constante que identifica la consulta que permite obtener todos los
	 * {@link Recurso} registrados en el sistema asiciados a un {@link Rol} dado<br />
	 * {@code select recurso.url from Rol rol inner join rol.recursos recurso where rol = :rol}
	 */
	public static final String FIND_BY_ROL = "Recurso_findByRol";
	/**
	 * Constante que identifica la consulta que permite obtener todos los
	 * {@link Recurso} registrados en el sistema asiciados a un {@link Rol} dado<br />
	 * {@code select recurso from Recurso recurso where recurso.url = :url}
	 */
	public static final String FIND_BY_URL = "Recurso_findByURL";
	
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
	 * @param id
	 *            Valor a ser asignado al atributo id
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
	 * @param nombre
	 *            Valor a ser asignado al atributo nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo que permite obtener el valor del atributo url
	 * 
	 * @return El valor del atributo url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Metodo que permite asignar un valor al atributo url
	 * 
	 * @param url
	 *            Valor a ser asignado al atributo url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Metodo que permite obtener el valor del atributo publico
	 * 
	 * @return El valor del atributo publico
	 */
	public boolean isPublico() {
		return publico;
	}

	/**
	 * Metodo que permite asignar un valor al atributo publico
	 * 
	 * @param publico
	 *            Valor a ser asignado al atributo publico
	 */
	public void setPublico(boolean publico) {
		this.publico = publico;
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
		Recurso other = (Recurso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
