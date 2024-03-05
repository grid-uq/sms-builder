package co.edu.utp.gia.sms.exceptions;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

/**
 * Clase que representa las excepciones logicas del sistema. Es usada para
 * mostrar los errores poducto de las verificaciones de negocio realizadas y
 * presentarlos de una manera adecuada.
 * 
 * 
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 10 abr. 2020
 *
 */
public class LogicException extends WebApplicationException {
	/**
	 * Metodo que permite inicializar los elementos de la clase TecnicalException
	 * 
	 * @param message Mensaje de error
	 */
	public LogicException(String message) {
		super(message);
	}

	/**
	 * Metodo que permite inicializar los elementos de la clase TecnicalException
	 * 
	 * @param cause Causa del error
	 */
	public LogicException(Throwable cause) {
		super(cause);
	}

	/**
	 * Metodo que permite inicializar los elementos de la clase TecnicalException
	 * 
	 * @param message Mensaje del error
	 * @param cause   Causa del error
	 */
	public LogicException(String message, Throwable cause) {
		super(message, cause);
	}

	public LogicException(String message, Response.Status status) {
		super(message, status);
	}
}
