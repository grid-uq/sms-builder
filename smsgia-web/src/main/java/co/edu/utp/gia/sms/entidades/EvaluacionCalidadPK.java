package co.edu.utp.gia.sms.entidades;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class EvaluacionCalidadPK implements Serializable {
	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 7358776815062993477L;
	private Integer referenciaId;
	private Integer atributoCalidadId;

	/**
	 * Metodo que permite inicializar los elementos de la clase EvaluacionCalidadPK
	 */
	public EvaluacionCalidadPK() {
	}
	
	/**
	 * Metodo que permite inicializar los elementos de la clase EvaluacionCalidadPK
	 * @param referenciaId
	 * @param atributoCalidadId
	 */
	public EvaluacionCalidadPK(Integer referenciaId, Integer atributoCalidadId) {
		this.referenciaId = referenciaId;
		this.atributoCalidadId = atributoCalidadId;
	}

	/**
	 * Metodo que permite obtener el valor del atributo referenciaId
	 * 
	 * @return El valor del atributo referenciaId
	 */
	public Integer getReferenciaId() {
		return referenciaId;
	}

	/**
	 * Metodo que permite asignar un valor al atributo referenciaId
	 * 
	 * @param referenciaId Valor a ser asignado al atributo referenciaId
	 */
	public void setReferenciaId(Integer referenciaId) {
		this.referenciaId = referenciaId;
	}

	/**
	 * Metodo que permite obtener el valor del atributo atributoCalidadId
	 * 
	 * @return El valor del atributo atributoCalidadId
	 */
	public Integer getAtributoCalidadId() {
		return atributoCalidadId;
	}

	/**
	 * Metodo que permite asignar un valor al atributo atributoCalidadId
	 * 
	 * @param atributoCalidadId Valor a ser asignado al atributo atributoCalidadId
	 */
	public void setAtributoCalidadId(Integer atributoCalidadId) {
		this.atributoCalidadId = atributoCalidadId;
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
		result = prime * result + ((atributoCalidadId == null) ? 0 : atributoCalidadId.hashCode());
		result = prime * result + ((referenciaId == null) ? 0 : referenciaId.hashCode());
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
		EvaluacionCalidadPK other = (EvaluacionCalidadPK) obj;
		if (atributoCalidadId == null) {
			if (other.atributoCalidadId != null)
				return false;
		} else if (!atributoCalidadId.equals(other.atributoCalidadId))
			return false;
		if (referenciaId == null) {
			if (other.referenciaId != null)
				return false;
		} else if (!referenciaId.equals(other.referenciaId))
			return false;
		return true;
	}

}
