package co.edu.utp.gia.sms.exceptions;

import jakarta.faces.context.ExceptionHandler;
import jakarta.faces.context.ExceptionHandlerFactory;

/**
 * Factory usado para la creación del Handler encargado del manejo de las
 * excepciones generadas por el sistema.
 * 
 */
public class CustomExceptionHandlerFactory extends ExceptionHandlerFactory {

	/**
	 * Constructor de la clase, permite crear una instancia de la ella a partir de
	 * {@link ExceptionHandlerFactory} dado <b>parent</b>.
	 * 
	 * @param parent {@link ExceptionHandlerFactory} a ser envuelto
	 */
	public CustomExceptionHandlerFactory(ExceptionHandlerFactory parent) {
		super(parent);
	}

	/*
	 * Metodo encargado de la construcción del ExceptionHandler, sobreescrito para
	 * crear el propietario
	 * 
	 * @see javax.faces.context.ExceptionHandlerFactory#getExceptionHandler()
	 */
	@Override
	public ExceptionHandler getExceptionHandler() {
		return new CustomExceptionHandler(getWrapped().getExceptionHandler());
	}

}