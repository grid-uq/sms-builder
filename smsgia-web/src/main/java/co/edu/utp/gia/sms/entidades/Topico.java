package co.edu.utp.gia.sms.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Entity implementation class for Entity: Topico
 *
 */
@Entity
@NamedQueries({

		@NamedQuery(name = Topico.TOPICO_PREGUNTA_GET_ALL, query = "select t from Topico t where t.pregunta.id = :id"),
		@NamedQuery(name = Topico.TOPICO_REVISION_GET_ALL, query = "select t from Topico t where t.pregunta.revision.id = :id order by t.pregunta.codigo")

})

public class Topico implements Serializable {

	/**
	 * Consulta que permite obtener los topicos registradas en el sistema para una
	 * pregunta <br />
	 * <code>select t from Topico t where t.pregunta.id = :id </code>
	 * 
	 */
	public static final String TOPICO_PREGUNTA_GET_ALL = "Topico.PreguntaGetAll";
	/**
	 * Consulta que permite obtener los topicos registradas en el sistema para una
	 * pregunta <br />
	 * <code>select t from Topico t where t.pregunta.revision.id = :id  order by t.pregunta.codigo</code>
	 * 
	 */
	public static final String TOPICO_REVISION_GET_ALL = "Topico.RevisionGetAll";

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	private String descripcion;
	@ManyToOne
	private Pregunta pregunta;
	private static final long serialVersionUID = 1L;

	public Topico() {
		super();
	}

	/**
	 * Metodo que permite inicializar los elementos de la clase Topico
	 * 
	 * @param descripcion
	 * @param pregunta
	 */
	public Topico(String descripcion, Pregunta pregunta) {
		this.descripcion = descripcion;
		this.pregunta = pregunta;
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
	 * Metodo que permite obtener el valor del atributo pregunta
	 * 
	 * @return El valor del atributo pregunta
	 */
	public Pregunta getPregunta() {
		return pregunta;
	}

	/**
	 * Metodo que permite asignar un valor al atributo pregunta
	 * 
	 * @param pregunta Valor a ser asignado al atributo pregunta
	 */
	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
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
		Topico other = (Topico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
