package co.edu.utp.gia.sms.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import co.edu.utp.gia.sms.importutil.TipoFuente;

@Entity
public class Fuente implements Serializable {
	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Enumerated(EnumType.STRING)
	private co.edu.utp.gia.sms.importutil.Fuente nombre;
	
	@Enumerated(EnumType.STRING)
	private TipoFuente tipo;

	public Fuente(co.edu.utp.gia.sms.importutil.Fuente nombre) {
		super();
		this.nombre = nombre;
		tipo = nombre.getTipo();
	}

	public Fuente() {

	}

	/**
	 * Metodo que permite obtener el valor del atributo nombre
	 * 
	 * @return El valor del atributo nombre
	 */
	public co.edu.utp.gia.sms.importutil.Fuente getNombre() {
		return nombre;
	}

	/**
	 * Metodo que permite asignar un valor al atributo nombre
	 * 
	 * @param nombre Valor a ser asignado al atributo nombre
	 */
	public void setNombre(co.edu.utp.gia.sms.importutil.Fuente nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo que permite obtener el valor del atributo tipo
	 * @return El valor del atributo tipo
	 */
	public TipoFuente getTipo() {
		return tipo;
	}

	/**
	 * Metodo que permite asignar un valor al atributo tipo
	 * @param tipo Valor a ser asignado al atributo tipo
	 */
	public void setTipo(TipoFuente tipo) {
		this.tipo = tipo;
	}

}
