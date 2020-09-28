package co.edu.utp.gia.sms.dtos;

import java.io.Serializable;

public class DatoDTO implements Serializable {
	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = -7860668035943218928L;
	private String etiqueta;
	private Float valor;

	public DatoDTO() {

	}

	public DatoDTO(Object etiqueta, Long valor) {
		this.etiqueta = etiqueta.toString();
		this.valor = valor.floatValue();
	}

	/**
	 * Metodo que permite inicializar los elementos de la clase DatoDTO
	 * 
	 * @param etiqueta
	 * @param valor
	 */
	public DatoDTO(String etiqueta, Long valor) {
		this.etiqueta = etiqueta;
		this.valor = valor.floatValue();
	}

	/**
	 * Metodo que permite inicializar los elementos de la clase DatoDTO
	 * 
	 * @param etiqueta
	 * @param valor
	 */
	public DatoDTO(String etiqueta, Float valor) {
		this.etiqueta = etiqueta;
		this.valor = valor;
	}

	/**
	 * Metodo que permite inicializar los elementos de la clase DatoDTO
	 * 
	 * @param etiqueta
	 * @param valor
	 */
	public DatoDTO(String etiqueta, Double valor) {
		this.etiqueta = etiqueta;
		this.valor = valor.floatValue();
	}
	
	/**
	 * Metodo que permite obtener el valor del atributo etiqueta
	 * 
	 * @return El valor del atributo etiqueta
	 */
	public String getEtiqueta() {
		return etiqueta;
	}

	/**
	 * Metodo que permite asignar un valor al atributo etiqueta
	 * 
	 * @param etiqueta Valor a ser asignado al atributo etiqueta
	 */
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	/**
	 * Metodo que permite obtener el valor del atributo valor
	 * 
	 * @return El valor del atributo valor
	 */
	public Float getValor() {
		return valor;
	}

	/**
	 * Metodo que permite asignar un valor al atributo valor
	 * 
	 * @param valor Valor a ser asignado al atributo valor
	 */
	public void setValor(Float valor) {
		this.valor = valor;
	}

}
