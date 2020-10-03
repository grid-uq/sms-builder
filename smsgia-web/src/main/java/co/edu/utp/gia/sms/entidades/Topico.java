package co.edu.utp.gia.sms.entidades;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Topico, el cual hace referencia a un
 * tema o aspecto particular de una {@link Pregunta}
 *
 */
@Entity
public class Topico implements Entidad<Integer> {
	/**
	 * Variable que representa el identificador unico del topico
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	/**
	 * Variable que representa el atributo descripcion de la clase
	 */
	private String descripcion;
	/**
	 * Variable que representa la {@link Pregunta} a la que pertence el
	 * {@link Topico}
	 */
	@ManyToOne
	private Pregunta pregunta;
	private static final long serialVersionUID = 1L;

	/**
	 * Metodo que permite inicializar los elementos de la clase Topico
	 */
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
