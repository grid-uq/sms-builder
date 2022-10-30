package co.edu.utp.gia.sms.util;

import co.edu.utp.gia.sms.exceptions.ExceptionMessage;
import co.edu.utp.gia.sms.exceptions.ExceptionMessageFactory;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.Locale;
/**
 * Clase utilitaria encargada de producir recursos para inyección usados por otras clases.
 */
@ApplicationScoped
@Log
public class ApplicationGeneralProducer {
    private static final ApplicationGeneralProducer instance = new ApplicationGeneralProducer();

    public static ApplicationGeneralProducer getInstance(){
        return instance;
    }

    @Produces
    @Named("defaultLocale")
    public Locale getDefaultLocale(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
//        if( facesContext!= null && facesContext.getApplication() != null )
//        log.info("LOCALE facesContext.getApplication().getDefaultLocale()"+facesContext.getApplication().getDefaultLocale());
//        if( facesContext!= null && facesContext.getViewRoot() != null )
//        log.info("LOCALE facesContext.getViewRoot().getLocale()"+facesContext.getViewRoot().getLocale());
        return facesContext != null ? facesContext.getViewRoot().getLocale() : Locale.ROOT;
//        return facesContext != null ? facesContext.getApplication().getDefaultLocale() : Locale.ROOT;
    }

    @Produces
    @Named("exceptionMessage")
    public ExceptionMessage getExceptionMessage(){
        Locale locale = getDefaultLocale();
        if( locale == null ){
            locale = Locale.ROOT;
        }
        return ExceptionMessageFactory.getInstance().getMessageInstance( locale );
    }
}
