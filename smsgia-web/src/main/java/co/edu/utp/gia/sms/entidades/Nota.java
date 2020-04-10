package co.edu.utp.gia.sms.entidades;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;


/**
 * 
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @version 1.0
 * @since 5/06/2019
 *
 */

@Entity
public class Nota implements Serializable {

	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 3693629746218687465L;
	/**
	 * Variable que representa el atributo id de la clase
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	/**
	 * Variable que representa el identificador del elemento
	 */
	
	
	private Integer etapa;
	
	
	/**
	 * Variable que representa el valor asiciado al elemento
	 */
	@Lob
	private String descripcion;
	
	

	@ManyToOne( fetch = EAGER )
	private Referencia referencia;

	
	public Nota() {
	}


	/**
	 * Metodo que permite inicializar los elementos de la clase Nota
	 * @param etapa
	 * @param descripcion
	 * @param referencia
	 */
	public Nota(Integer etapa, String descripcion, Referencia referencia) {
		this.etapa = etapa;
		this.descripcion = descripcion;
		this.referencia = referencia;
	}


	/**
	 * Metodo que permite obtener el valor del atributo id
	 * @return El valor del atributo id
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * Metodo que permite asignar un valor al atributo id
	 * @param id Valor a ser asignado al atributo id
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * Metodo que permite obtener el valor del atributo etapa
	 * @return El valor del atributo etapa
	 */
	public Integer getEtapa() {
		return etapa;
	}


	/**
	 * Metodo que permite asignar un valor al atributo etapa
	 * @param etapa Valor a ser asignado al atributo etapa
	 */
	public void setEtapa(Integer etapa) {
		this.etapa = etapa;
	}


	/**
	 * Metodo que permite obtener el valor del atributo descripcion
	 * @return El valor del atributo descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}


	/**
	 * Metodo que permite asignar un valor al atributo descripcion
	 * @param descripcion Valor a ser asignado al atributo descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	/**
	 * Metodo que permite obtener el valor del atributo referencia
	 * @return El valor del atributo referencia
	 */
	public Referencia getReferencia() {
		return referencia;
	}


	/**
	 * Metodo que permite asignar un valor al atributo referencia
	 * @param referencia Valor a ser asignado al atributo referencia
	 */
	public void setReferencia(Referencia referencia) {
		this.referencia = referencia;
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
		Nota other = (Nota) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
