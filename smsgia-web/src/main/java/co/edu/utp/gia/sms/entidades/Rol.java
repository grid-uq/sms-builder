package co.edu.utp.gia.sms.entidades;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Clase que representa la entidad Rol, la cual representa los diferentes roles
 * dentro de la aplicacion asi como los {@link Recurso} a las que tienen acceso
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
		@NamedQuery(name = Rol.FIND_BY_ID, query = "select rol from Rol rol where rol.id = :id"),
		@NamedQuery(name = Rol.GET_ALL, query = "select rol from Rol rol"),
		@NamedQuery(name=Rol.FIND_BY_USUARIO,query="select rol from Usuario usuario inner join usuario.roles rol where usuario = :usuario")

})

public class Rol implements Entidad<Integer> {

	/**
	 * Variable que representa el atributo id de la clase. Permite identificar
	 * de forma única un Rol
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * Variable que representa el atributo nombre de la clase. Nombre del Rol
	 */
	@Column(nullable=false,length=20,unique=true)
	private String nombre;
	/**
	 * Variable que representa el atributo recursos de la clase. Lista de
	 * {@link Recurso} a la cual tiene acceso el {@link Rol}
	 */
	@ManyToMany
	private List<Recurso> recursos;
	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constante que identifica la consulta que permite buscar un
	 * {@link Rol} por su id <br />
	 * {@code select rol from Rol rol where rol.id = :id}
	 */
	public static final String FIND_BY_ID = "Rol_findById";
	/**
	 * Constante que identifica la consulta que permite obtener todos los
	 * {@link Recurso} registrados en el sistema <br />
	 * {@code select rol from Rol rol}
	 */
	public static final String GET_ALL = "Rol_getAll";
	/**
	 * Constante que identifica la consulta que permite obtener todos los
	 * {@link Rol} asociados a un {@link Usuario} <br />
	 * {@code select rol from Usuario usuario inner join usuario.roles rol where usuario = :usuario}
	 */
	public static final String FIND_BY_USUARIO = "Rol_findByUsuario";

	public Rol(){ }

	public Rol(Integer id, String nombre) {
		this.id = id;
		this.nombre = nombre;
		this.recursos = new ArrayList<>();
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
		Rol other = (Rol) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
