package co.edu.utp.gia.sms.exceptions;


import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.inject.Inject;

import co.edu.utp.gia.sms.util.PropertiesLoader;
import lombok.Getter;


/**
 * Wrapper usado para el manejo de las excepciones generadas por el sistema.
 * 
 * 
 */
public class CustomExceptionHandler extends ExceptionHandlerWrapper {

	/**
	 * Instancia de logger para realizar el registro en el log
	 */
//	@Inject
	private static Logger LOGGER = Logger.getLogger(CustomExceptionHandler.class.getName());

	/**
	 * Variable que representa el atributo exceptionPropertiesLoader de la
	 * clase. Utilidad que permite obtener los mensajes de error de las
	 * excepciones
	 */
//	@Inject
//	@ExceptionPropertiesLoader
	private PropertiesLoader exceptionPropertiesLoader;



	/**
	 * Variable que representa el atributo facesContext de la clase. Identifica
	 * la instacia del {@link FacesContext}
	 */
//	@Inject
//	private FacesContext facesContext;

	/**
	 * Constructor de la clase, permite crear una instancia de la ella a partir
	 * de un {@link ExceptionHandlerWrapper} dado <b>wrapper</b>.
	 * 
	 * @param wrapper
	 *            {@link ExceptionHandlerWrapper} a ser envuelto
	 */
	public CustomExceptionHandler(ExceptionHandler wrapper) {
		super(wrapper);
		exceptionPropertiesLoader = new PropertiesLoader(FacesContext
				.getCurrentInstance().getApplication().getDefaultLocale(),ExceptionConstants.PROPERTIES);
//		System.out.println(FacesContext
//				.getCurrentInstance().getELContext().getLocale());
//		facesContext = FacesContext.getCurrentInstance();
	}


	/*
	 * Metodo handle encargado de procesar la excepcion no capturadas, se
	 * sobreescribe para procesar las excepciones propietarias de forma
	 * personalizada
	 * 
	 * @see javax.faces.context.ExceptionHandlerWrapper#handle()
	 */
	@Override
	public void handle() throws FacesException {
		Iterator<ExceptionQueuedEvent> iteratorUnhandledExceptionQueued = getUnhandledExceptionQueuedEvents()
				.iterator();
		while (iteratorUnhandledExceptionQueued != null
				&& iteratorUnhandledExceptionQueued.hasNext()) {
			ExceptionQueuedEvent evt = iteratorUnhandledExceptionQueued.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) evt
					.getSource();

			Throwable exception = ExceptionUtil
					.extractPrincipalException(context.getException());
			try {
				processException(exception);
			} finally {
				iteratorUnhandledExceptionQueued.remove();
			}
		}
		getWrapped().handle();
	}

	/**
	 * Metodo encargado de realizar el procesamiento de la exepcion.
	 * 
	 * @param exception
	 *            Excepcion a ser procesada
	 */
	private void processException(Throwable exception) {
		if (exception != null) {
			if (!(exception instanceof LogicException)) {
				// LOGGER.error(exception.getMessage(), exception);
				LOGGER.log(Level.SEVERE, exception.getMessage(), exception);
			}
			String msgDetail;
			String msgSummary;
			if (exception instanceof LogicException
					|| (exception instanceof TecnicalException && ((TecnicalException) exception)
							.isVisible())) {
				msgDetail = exception.getLocalizedMessage();
				msgSummary = exception.getMessage();
			} else {
//				msgDetail = exceptionPropertiesLoader
//						.getProperties(ExceptionConstants.EXCEPTION_DEFAULT_DETAIL);
//				msgSummary = exceptionPropertiesLoader
//						.getProperties(ExceptionConstants.EXCEPTION_DEFAULT_SUMMARY);
				ExceptionMessage exceptionMessage = ExceptionMessageFactory.getInstance()
						.getMessageInstance( FacesContext.getCurrentInstance().getViewRoot().getLocale()  );
				msgDetail = exceptionMessage.getDefaultMessageDetail();
				msgSummary = exceptionMessage.getDefaultMessageSumary();
			}
			addError(msgDetail, msgSummary);
		}
	}

	/**
	 * Permite adicionar un mensaje al contexto faces.
	 * 
	 * @param detail
	 *            Detalle del mensaje que se desea adicionar
	 * @param summary
	 *            Resumen del mensaje que se desea adicionar
	 * @param severity
	 *            Severidad del mensaje que se desa adicionar
	 */
	private void addMessage(String detail, String summary, Severity severity) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext != null) {
			// SessionBean sessionBean = (SessionBean) FacesContext
			// .getCurrentInstance().getExternalContext().getSessionMap()
			// .get("sessionBean");
			facesContext.addMessage(null, new FacesMessage(severity, summary,
					detail));
		}
	}

	/**
	 * Permite adicionar un mensaje de Error al contexto faces
	 * 
	 * @param detail
	 *            Detalle del mensaje de error a ser adicionado
	 * @param summary
	 *            Resumen del mensjae de error a ser adicionado
	 */
	public void addError(String detail, String summary) {
		addMessage(detail, summary, FacesMessage.SEVERITY_ERROR);
	}

}
