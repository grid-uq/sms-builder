package co.edu.utp.gia.sms.exceptions;

/**
 * Clase que representa las excepciones tecnicas del sistema. Es usada para
 * envolver errores de caracter tecnico y presentarlos de una manera adecuada.
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
public class TecnicalException extends RuntimeException {

	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Determina si la excepcion debe o no tener visibilidad hacia el usuario. 
	 */
	private boolean visible;
	
	/**
	 * Metodo que permite inicializar los elementos de la clase
	 * TecnicalException
	 * 
	 * @param message
	 *            Mensaje de error
	 */
	public TecnicalException(String message) {
		super(message);
		setVisible(true);
	}

	/**
	 * Metodo que permite inicializar los elementos de la clase
	 * TecnicalException
	 * 
	 * @param cause
	 *            Causa del error
	 */
	public TecnicalException(Throwable cause) {
		super(cause);
		setVisible(false);
	}

	/**
	 * Metodo que permite inicializar los elementos de la clase
	 * TecnicalException
	 * 
	 * @param message
	 *            Mensaje del error
	 * @param cause
	 *            Causa del error
	 */
	public TecnicalException(String message, Throwable cause) {
		super(message, cause);
		setVisible(true);
	}
	
	/**
	 * Permite obtener el valor del atributo {@link TecnicalException#visible}, el cual determina si la
	 * excepción debe o no ser mostrada al usuario
	 * 
	 * @return El valor del atributo {@link TecnicalException#visible}
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Permite asignar un valor al atributo {@link TecnicalException#visible}.
	 * 
	 * @param visible
	 *            Valor a ser asignado al atributo {@link IESTecnicalException#visible}
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}	
}
