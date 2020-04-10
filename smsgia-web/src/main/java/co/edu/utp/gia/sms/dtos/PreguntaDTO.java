/**
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Julio Cesar Chavarro
 * @author Carlos Augusto Meneces
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0 
 * @since 9 abr. 2020
 */
package co.edu.utp.gia.sms.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.edu.utp.gia.sms.entidades.Objetivo;
import co.edu.utp.gia.sms.entidades.Topico;

/**
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Julio Cesar Chavarro
 * @author Carlos Augusto Meneces
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0 
 * @since 9 abr. 2020
 *
 */
public class PreguntaDTO implements Serializable {

	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 3245868826313123028L;
	/**
	 * Variable que representa el atributo id de la clase
	 */
	private Integer id;
	/**
	 * Variable que representa el atributo codigo de la clase
	 */
	private String codigo;
	/**
	 * Variable que representa el atributo texto de la clase
	 */
	private String descripcion;
	
	/**
	 * Variable que representa los topico de una pregunta
	 */
	private List<Topico> topicos;

	/**
	 * Variable que representa los objetivos con los que se relaciona una pregunta
	 */
	private List<Objetivo> objetivos;
	
	
	/**
	 * Metodo que permite inicializar los elementos de la clase PreguntaDTO
	 * @param id
	 * @param codigo
	 * @param descripcion
	 */
	public PreguntaDTO(Integer id, String codigo, String descripcion) {
		this(id, codigo, descripcion, new ArrayList<Topico>(), new ArrayList<Objetivo>());
	}

	
	
	
	
	/**
	 * Metodo que permite inicializar los elementos de la clase PreguntaDTO
	 * @param id
	 * @param codigo
	 * @param descripcion
	 * @param topicos
	 * @param objetivos
	 */
	public PreguntaDTO(Integer id, String codigo, String descripcion, List<Topico> topicos, List<Objetivo> objetivos) {
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.topicos = topicos;
		this.objetivos = objetivos;
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
	 * Metodo que permite obtener el valor del atributo codigo
	 * @return El valor del atributo codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Metodo que permite asignar un valor al atributo codigo
	 * @param codigo Valor a ser asignado al atributo codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	 * Metodo que permite obtener el valor del atributo topicos
	 * @return El valor del atributo topicos
	 */
	public List<Topico> getTopicos() {
		return topicos;
	}

	/**
	 * Metodo que permite asignar un valor al atributo topicos
	 * @param topicos Valor a ser asignado al atributo topicos
	 */
	public void setTopicos(List<Topico> topicos) {
		this.topicos = topicos;
	}

	/**
	 * Metodo que permite obtener el valor del atributo objetivos
	 * @return El valor del atributo objetivos
	 */
	public List<Objetivo> getObjetivos() {
		return objetivos;
	}

	/**
	 * Metodo que permite asignar un valor al atributo objetivos
	 * @param objetivos Valor a ser asignado al atributo objetivos
	 */
	public void setObjetivos(List<Objetivo> objetivos) {
		this.objetivos = objetivos;
	}


	public String getListObjetivos() {
		String textoObjetivos = "";
		String separador = "";
		for (Objetivo objetivo : objetivos) {
			textoObjetivos += separador + objetivo.getCodigo();
			separador = ", ";
		}
		return textoObjetivos;
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
		PreguntaDTO other = (PreguntaDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	
	
	
	
}
