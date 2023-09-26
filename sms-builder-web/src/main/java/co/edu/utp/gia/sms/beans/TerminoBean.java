package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.Termino;
import co.edu.utp.gia.sms.negocio.AbstractGenericService;
import co.edu.utp.gia.sms.negocio.TerminoService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
/**
 * Clase controladora de interfaz web que se encarga de la gestión de terminos.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@Named
@ViewScoped
public class TerminoBean extends GenericBeanNew<Termino,String> {
    @Inject
    private TerminoService terminoService;

    public void inicializar() {
        setRecords( terminoService.get() );
    }

    @Override
    protected Termino newRecord() {
        return new Termino();
    }

    @Override
    protected AbstractGenericService<Termino, String> getServices() {
        return terminoService;
    }

    public void actualizarSinonimos(Termino termino){
        terminoService.actualizarSinonimos(termino);
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
    }

    public void validate(FacesContext facesContext, UIComponent component, java.lang.Object object){
        validateUnique(facesContext, component, object, record -> record.getDescripcion().equals(object.toString()) );
    }
}

