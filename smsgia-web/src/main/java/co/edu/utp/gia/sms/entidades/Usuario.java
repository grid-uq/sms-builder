package co.edu.utp.gia.sms.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Clase que implementa la entidad Usuario, la cual representa los usuarios de
 * la aplicación (empleados de la empresa).
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
		@NamedQuery(name = Usuario.FIND_BY_ID, query = "select usuario from Usuario usuario where usuario.id = :id"),
		@NamedQuery(name = Usuario.GET_ALL, query = "select usuario from Usuario usuario"),
		@NamedQuery(name = Usuario.AUTENTICAR, query = "select usuario from Usuario usuario where usuario.nombreUsuario = :nombreUsuario and usuario.estado = co.edu.utp.gia.sms.entidades.EstadoUsuario.ACTIVO"),

})

public class Usuario implements Entidad<Integer> {

	/**
	 * Variable que representa el atributo id de la clase
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * Variable que representa el atributo nombreUsuario, que permite a los
	 * usuario autenticarse en la aplicacion
	 */
	@Column(nullable = false, unique = true,length=50)
	private String nombreUsuario;
	/**
	 * Variable que representa el atributo clave, el cual es usado por los
	 * usuario para autenticarse en la aplicacion
	 */
	@Column(nullable = false, length=50)
	private String clave;

	/**
	 * Representa el {@link EstadoUsuario} de la cuenta del usuario en el sistema.
	 */
	@Enumerated(EnumType.STRING)
	private EstadoUsuario estado = EstadoUsuario.ACTIVO;

	/**
	 * Variable que representa el atributo intentos de la clase, el lleva el
	 * conteo del número de intentos de autenticación fallidos
	 */
	@Column(precision=2)
	private Integer intentos = 0;

	/**
	 * Variable que representa el atributo roles de la clase. Roles a los cuales
	 * pertenece el usuario
	 */
	@ManyToMany
	private List<Rol> roles;

	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constante que identifica la consulta que permite buscar un
	 * {@link Usuario} por su id <br />
	 * {@code select usuario from Usuario usuario where usuario.id = :id}
	 */
	public static final String FIND_BY_ID = "Usuario_findById";
	/**
	 * Constante que identifica la consulta que permite obtener todos los
	 * {@link Usuario} registrados en el sistema <br />
	 * {@code select usuario from Usuario usuario}
	 */
	public static final String GET_ALL = "Usuario_getAll";
	/**
	 * Constante que identifica la consulta que permite buscar un
	 * {@link Usuario} por su {@link Usuario#nombreUsuario} y su
	 * {@link Usuario#clave}<br />
	 * {@code select usuario from Usuario usuario where usuario.nombreUsuario = :nombreUsuario and usuario.estado = co.edu.uniquindio.grid.prueba.rifa.entidades.seguridad.EstadoUsuario.ACTIVO }
	 */
	public static final String AUTENTICAR = "Usuario_autenticar";

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
	 * Metodo que permite obtener el valor del atributo nombreUsuario
	 * 
	 * @return El valor del atributo nombreUsuario
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * Metodo que permite asignar un valor al atributo nombreUsuario
	 * 
	 * @param nombreUsuario
	 *            Valor a ser asignado al atributo nombreUsuario
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	/**
	 * Metodo que permite obtener el valor del atributo clave
	 * 
	 * @return El valor del atributo clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Metodo que permite asignar un valor al atributo clave
	 * 
	 * @param clave
	 *            Valor a ser asignado al atributo clave
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Metodo que permite obtener el valor del atributo roles
	 * 
	 * @return El valor del atributo roles
	 */
	public List<Rol> getRoles() {
		return roles;
	}

	/**
	 * Metodo que permite asignar un valor al atributo roles
	 * 
	 * @param roles
	 *            Valor a ser asignado al atributo roles
	 */
	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	/**
	 * Metodo que permite obtener el valor del atributo estado
	 * 
	 * @return El valor del atributo estado
	 */
	public EstadoUsuario getEstado() {
		return estado;
	}

	/**
	 * Metodo que permite asignar un valor al atributo estado
	 * 
	 * @param estado
	 *            Valor a ser asignado al atributo estado
	 */
	public void setEstado(EstadoUsuario estado) {
		this.estado = estado;
	}

	/**
	 * Metodo que permite obtener el valor del atributo intentos
	 * 
	 * @return El valor del atributo intentos
	 */
	public Integer getIntentos() {
		return intentos;
	}

	/**
	 * Metodo que permite asignar un valor al atributo intentos
	 * 
	 * @param intentos
	 *            Valor a ser asignado al atributo intentos
	 */
	public void setIntentos(Integer intentos) {
		this.intentos = intentos;
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
