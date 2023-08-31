package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.Termino;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.AbstractGenericService;
import co.edu.utp.gia.sms.negocio.TerminoService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import java.util.HashMap;
import java.util.Map;
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
public class RegistroTerminoBean extends GenericBeanNew<Termino,String> {
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

    public void adicionarSinonimo(String id) {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        addToSession("idTermino", id);
        PrimeFaces.current().dialog().openDynamic("/termino/registroSinonimo", options, null);
    }

    public void eliminarSinonimo(Termino termino,String sinonimo){
        terminoService.removeSinonimo(termino.getId(),sinonimo);
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        termino.removerSinonimo(sinonimo);
    }

    public void onSinonimoCreado(SelectEvent<Topico> event) {
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        inicializar();
    }

    public void validate(FacesContext facesContext, UIComponent component, java.lang.Object object){
        validateUnique(facesContext, component, object, record -> record.getDescripcion().equals(object.toString()) );
    }
}

