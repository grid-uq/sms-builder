package co.edu.utp.gia.sms.exceptions;


/**
 * Clase usada para la centralización de funcionalidades para facilitar el manejo de excepciones.
 * 
 */
public final class ExceptionUtil {
	/**
	 * Constructor privado para prevenir la creación de instancias de la clase.
	 */
	private ExceptionUtil() {
	}
	
	/**
	 * Metodo que permite obtener la excepcion principal u origen a ser
	 * mostrada.
	 * 
	 * @param exception
	 *            Excepcion de la que se desea extraer la excepcion principal u
	 *            origen
	 * @return excepcion principal u origen de la excepcion dada
	 */
	public static Throwable extractPrincipalException(Throwable exception) {
		while (exception != null
				&& exception.getCause() != null
				&& !(exception instanceof LogicException || exception instanceof TecnicalException)) {
			exception = exception.getCause();
		}
		return exception;
	}
}
