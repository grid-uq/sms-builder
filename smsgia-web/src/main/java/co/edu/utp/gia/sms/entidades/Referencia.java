package co.edu.utp.gia.sms.entidades;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Elemento que representa de forma general una referencia a ser procesada
 * 
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @version 1.0
 * @since 6/06/2019
 *
 */
@Entity
public class Referencia implements Entidad<Integer> {

	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = -4002756759383683632L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;

	/**
	 * Variable que representa el atributo nombre de la clase
	 */
	@Lob
	private String nombre;
	/**
	 * Variable que representa el atributo year de la clase
	 */
	@Column(length = 4)
	private String year;
	/**
	 * Variable que representa el atributo resumen de la clase
	 */
	@Lob
	private String resumen;

	/**
	 * Variable que representa el atributo tipo de la clase
	 */
	private String tipo;

	/**
	 * Variable que representa el filtro en el cual esta la referencia, todas
	 * inician con 0 y va aumentando
	 */
	private Integer filtro;

	private Float totalEvaluacionCalidad;
	/**
	 * Variable que representa el atributo metadatos de la clase
	 */
	@OneToMany(mappedBy = "referencia", cascade = PERSIST)
	private List<Metadato> metadatos;

	/**
	 * Variable que representa el atributo revision de la clase
	 */
	@ManyToOne
	private Revision revision;

	@ManyToMany(fetch = EAGER)
	private List<Topico> topicos;

	@OneToMany(mappedBy = "referencia")
	private List<EvaluacionCalidad> evaluacionCalidad;

	/**
	 * Metodo que permite inicializar los elementos de la clase Reference
	 */
	public Referencia() {
		filtro = 0;
	}

	/**
	 * Adiciona un nuevo atributo a una {@link Referencia}
	 * 
	 * @param identifier Identificador del elemento que se adicionara
	 * @param value      Valor del elemento a ser adicionado
	 */
	public void addElement(TipoMetadato identifier, String value) {
		inicializarElementos();
		metadatos.add(new Metadato(identifier, value, this));
		switch (identifier) {
		case TITLE:
			setNombre(value);
			break;
		case ABSTRACT:
			setResumen(value);
			break;
		case TYPE:
			setTipo(value);
			break;
		case YEAR:
			setYear(value);
			break;
		default:
//			metadatos.add(new Metadato(identifier, value, this));
			break;
		}
	}

	/**
	 * Inicialia el listado de elementos de la referencia
	 */
	private void inicializarElementos() {
		if (metadatos == null) {
			metadatos = new ArrayList<Metadato>();
		}
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
	 * Metodo que permite obtener el valor del atributo year
	 * 
	 * @return El valor del atributo year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * Metodo que permite asignar un valor al atributo year
	 * 
	 * @param year Valor a ser asignado al atributo year
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * Metodo que permite obtener el valor del atributo resumen
	 * 
	 * @return El valor del atributo resumen
	 */
	public String getResumen() {
		return resumen;
	}

	/**
	 * Metodo que permite asignar un valor al atributo resumen
	 * 
	 * @param resumen Valor a ser asignado al atributo resumen
	 */
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	/**
	 * Metodo que permite obtener el valor del atributo metadatos
	 * 
	 * @return El valor del atributo metadatos
	 */
	public List<Metadato> getMetadatos() {
		return metadatos;
	}

	/**
	 * Metodo que permite asignar un valor al atributo metadatos
	 * 
	 * @param metadatos Valor a ser asignado al atributo metadatos
	 */
	public void setMetadatos(List<Metadato> metadatos) {
		this.metadatos = metadatos;
	}

	/**
	 * Metodo que permite obtener el valor del atributo filtro
	 * 
	 * @return El valor del atributo filtro
	 */
	public Integer getFiltro() {
		return filtro;
	}

	/**
	 * Metodo que permite asignar un valor al atributo filtro
	 * 
	 * @param filtro Valor a ser asignado al atributo filtro
	 */
	public void setFiltro(Integer filtro) {
		this.filtro = filtro;
	}

	/**
	 * Metodo que permite obtener el valor del atributo tipo
	 * 
	 * @return El valor del atributo tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Metodo que permite asignar un valor al atributo tipo
	 * 
	 * @param tipo Valor a ser asignado al atributo tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	 * Metodo que permite obtener el valor del atributo evaluacionCalidad
	 * 
	 * @return El valor del atributo evaluacionCalidad
	 */
	public List<EvaluacionCalidad> getEvaluacionCalidad() {
		return evaluacionCalidad;
	}

	/**
	 * Metodo que permite asignar un valor al atributo evaluacionCalidad
	 * 
	 * @param evaluacionCalidad Valor a ser asignado al atributo evaluacionCalidad
	 */
	public void setEvaluacionCalidad(List<EvaluacionCalidad> evaluacionCalidad) {
		this.evaluacionCalidad = evaluacionCalidad;
	}

	/**
	 * Metodo que permite obtener el valor del atributo totalEvaluacionCalidad
	 * 
	 * @return El valor del atributo totalEvaluacionCalidad
	 */
	public Float getTotalEvaluacionCalidad() {
		return totalEvaluacionCalidad;
	}

	/**
	 * Metodo que permite asignar un valor al atributo totalEvaluacionCalidad
	 * 
	 * @param totalEvaluacionCalidad Valor a ser asignado al atributo
	 *                               totalEvaluacionCalidad
	 */
	public void setTotalEvaluacionCalidad(Float totalEvaluacionCalidad) {
		this.totalEvaluacionCalidad = totalEvaluacionCalidad;
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
		Referencia other = (Referencia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
