package co.edu.utp.gia.sms.exceptions;

import jakarta.faces.FacesException;
import jakarta.faces.application.NavigationHandler;
import jakarta.faces.application.ViewExpiredException;
import jakarta.faces.context.ExceptionHandler;
import jakarta.faces.context.ExceptionHandlerWrapper;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ExceptionQueuedEvent;
import jakarta.faces.event.ExceptionQueuedEventContext;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Clase de capturar y procesar excepciones.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
public class DefaultExceptionHandler extends ExceptionHandlerWrapper {

    //@Inject
    private final static Logger LOG = Logger.getLogger(DefaultExceptionHandler.class.getName());

    public DefaultExceptionHandler(ExceptionHandler wrapped) {
        super(wrapped);
    }

    @Override
    public void handle() throws FacesException {
        LOG.log(Level.INFO, "invoking custom ExceptionHandlder...");
        Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents().iterator();

        while (events.hasNext()) {
            ExceptionQueuedEvent event = events.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
            Throwable t = context.getException();
            LOG.log(Level.INFO, "Exception@" + t.getClass().getName());
            LOG.log(Level.INFO, "ExceptionHandlder began.");
            //t.printStackTrace();
            if (t instanceof ViewExpiredException) {
                try {
                    handleViewExpiredException((ViewExpiredException) t);
                } finally {
                    events.remove();
                }
            } else if (t instanceof TecnicalException) {
                try {
                    handleNotFoundException((Exception) t);
                } finally {
                    events.remove();
                }
            } else {

                getWrapped().handle();
            }
            LOG.log(Level.INFO, "ExceptionHandlder end.");
        }

    }

    private void handleViewExpiredException(ViewExpiredException vee) {
        LOG.log(Level.INFO, " handling viewExpiredException...");
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = vee.getViewId();
        LOG.log(Level.INFO, "view id @" + viewId);
        NavigationHandler nav
                = context.getApplication().getNavigationHandler();
        nav.handleNavigation(context, null, viewId);
        context.renderResponse();
    }

    private void handleNotFoundException(Exception e) {
        LOG.log(Level.INFO, "handling exception:...");
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = "/error.xhtml";
        LOG.log(Level.INFO, "view id @" + viewId);
        NavigationHandler nav
                = context.getApplication().getNavigationHandler();
        nav.handleNavigation(context, null, viewId);
        context.getViewRoot().getViewMap(true).put("ex", e);
        context.renderResponse();
    }
}