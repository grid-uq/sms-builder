package co.edu.utp.gia.sms.exceptions;


/**
 * Clase que contiene las constantes usadas en el manejo de excepciones
 * 
 * 
 */
public class ExceptionConstants {
	/**
	 * Metodo que permite inicializar los elementos de la clase
	 * ExceptionConstants
	 */
	private ExceptionConstants() {

	}

	/**
	 * Variable que representa el atributo PROPERTIES de la clase 
	 */
	public static final String PROPERTIES = "exception";
	/**
	 * Variable que representa el atributo EXCEPTION_DEFAULT_DETAIL de la clase.
	 * Identifica la clave que contiene el texto del detalle del mensaje de
	 * error por defecto
	 */
	public static final String EXCEPTION_DEFAULT_DETAIL = "message.exception.defaultDetail";

	/**
	 * Variable que representa el atributo EXCEPTION_DEFAULT_SUMMARY de la
	 * clase. Identifica la clave que contiene el texto del resumen del mensaje
	 * de error por defecto
	 */
	public static final String EXCEPTION_DEFAULT_SUMMARY = "message.exception.defaultSummary";

}
