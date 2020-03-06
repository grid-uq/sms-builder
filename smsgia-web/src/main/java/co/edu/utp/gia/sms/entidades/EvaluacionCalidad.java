package co.edu.utp.gia.sms.entidades;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @version 1.0
 * @since 23/06/2019
 *
 */
@Entity
@NamedQueries({

		@NamedQuery(name = EvaluacionCalidad.EVALUACION_CALIDAD_GET_ALL, query = "select e from EvaluacionCalidad e where e.referencia.id = :id"),
		@NamedQuery(name = EvaluacionCalidad.EVALUACION_TOTAL_CALIDAD, query = "select SUM(e.evaluacionCuantitativa) from EvaluacionCalidad e where e.referencia.id = :id")
		

})

public class EvaluacionCalidad implements Serializable {

	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 3246469713521362393L;

	/**
	 * Consulta que permite obtener las revisiones de calidad registradas en el
	 * sistema para una referencia <br />
	 * <code>select e from EvaluacionCalidad e where e.referencia.id = :id </code>
	 * 
	 */
	public static final String EVALUACION_CALIDAD_GET_ALL = "EvaluacionCalidad.getAll";
	/**
	 * Consulta que permite obtener el total de las evaluaciones de calidad registradas en el
	 * sistema para una referencia <br />
	 * <code>select SUM(e.evaluacionCuantitativa) from EvaluacionCalidad e where e.referencia.id = :id </code>
	 * 
	 */
	public static final String EVALUACION_TOTAL_CALIDAD = "EvaluacionCalidad.totalCalidad";

	@EmbeddedId
	private EvaluacionCalidadPK id;
	@ManyToOne
	@MapsId("referenciaId")
	private Referencia referencia;
	@ManyToOne
	@MapsId("atributoCalidadId")
	private AtributoCalidad atributoCalidad;
	@Enumerated(EnumType.STRING)
	private EvaluacionCualitativa evaluacionCualitativa;
	/**
	 * Variable que representa el atributo evaluacionCuantitativa de la clase
	 */
	private Float evaluacionCuantitativa;

	public EvaluacionCalidad() {
		// TODO Auto-generated constructor stub
	}

	public EvaluacionCalidad(Referencia referencia, AtributoCalidad atributoCalidad) {
		this.referencia = referencia;
		this.atributoCalidad = atributoCalidad;
		this.id = new EvaluacionCalidadPK(referencia.getId(), atributoCalidad.getId());
	}

	/**
	 * Metodo que permite obtener el valor del atributo id
	 * 
	 * @return El valor del atributo id
	 */
	public EvaluacionCalidadPK getId() {
		return id;
	}

	/**
	 * Metodo que permite asignar un valor al atributo id
	 * 
	 * @param id Valor a ser asignado al atributo id
	 */
	public void setId(EvaluacionCalidadPK id) {
		this.id = id;
	}

	/**
	 * Metodo que permite obtener el valor del atributo atributoCalidad
	 * 
	 * @return El valor del atributo atributoCalidad
	 */
	public AtributoCalidad getAtributoCalidad() {
		return atributoCalidad;
	}

	/**
	 * Metodo que permite asignar un valor al atributo atributoCalidad
	 * 
	 * @param atributoCalidad Valor a ser asignado al atributo atributoCalidad
	 */
	public void setAtributoCalidad(AtributoCalidad atributoCalidad) {
		this.atributoCalidad = atributoCalidad;
	}

	/**
	 * Metodo que permite obtener el valor del atributo referencia
	 * 
	 * @return El valor del atributo referencia
	 */
	public Referencia getReferencia() {
		return referencia;
	}

	/**
	 * Metodo que permite asignar un valor al atributo referencia
	 * 
	 * @param referencia Valor a ser asignado al atributo referencia
	 */
	public void setReferencia(Referencia referencia) {
		this.referencia = referencia;
	}

	/**
	 * Metodo que permite obtener el valor del atributo evaluacionCualitativa
	 * 
	 * @return El valor del atributo evaluacionCualitativa
	 */
	public EvaluacionCualitativa getEvaluacionCualitativa() {
		return evaluacionCualitativa;
	}

	/**
	 * Metodo que permite asignar un valor al atributo evaluacionCualitativa
	 * 
	 * @param evaluacionCualitativa Valor a ser asignado al atributo
	 *                              evaluacionCualitativa
	 */
	public void setEvaluacionCualitativa(EvaluacionCualitativa evaluacionCualitativa) {
		this.evaluacionCualitativa = evaluacionCualitativa;
	}

	/**
	 * Metodo que permite obtener el valor del atributo evaluacionCuantitativa
	 * 
	 * @return El valor del atributo evaluacionCuantitativa
	 */
	public Float getEvaluacionCuantitativa() {
		return evaluacionCuantitativa;
	}

	/**
	 * Metodo que permite asignar un valor al atributo evaluacionCuantitativa
	 * 
	 * @param evaluacionCuantitativa Valor a ser asignado al atributo
	 *                               evaluacionCuantitativa
	 */
	public void setEvaluacionCuantitativa(Float evaluacionCuantitativa) {
		this.evaluacionCuantitativa = evaluacionCuantitativa;
	}

	public void evaluar(EvaluacionCualitativa evaluacion) {
		setEvaluacionCualitativa(evaluacion);
		calcularEvaluacionCualitativa();
	}

	public void calcularEvaluacionCualitativa() {
		switch (evaluacionCualitativa) {
		case NO_CUMPLE:
			setEvaluacionCuantitativa(0.0F);
			break;
		case PARCIALMENTE:
			setEvaluacionCuantitativa(0.5F);
			break;
		case CUMPLE:
			setEvaluacionCuantitativa(1.0F);
			break;
		}
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
		EvaluacionCalidad other = (EvaluacionCalidad) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
