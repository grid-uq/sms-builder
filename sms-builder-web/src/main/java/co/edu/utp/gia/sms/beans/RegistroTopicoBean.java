package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.PreguntaService;
import co.edu.utp.gia.sms.negocio.TopicoService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;

/**
 * Clase controladora de interfaz web que se encarga de la gestión de tópicos.
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
public class RegistroTopicoBean extends GenericBean<Topico> {
    @Getter @Setter
    private String descripcion;
    @Getter @Setter
    private String id;
    @Inject
    private TopicoService topicoService;
    @Inject
    private PreguntaService preguntaService;

    /**
     * Permite registrar un topico
     */
    public void registrar() {
        Topico topico = null;
        id = getAndRemoveFromSession("idPregunta").toString();
        if (id != null) {
            topico = topicoService.save(id, descripcion);
        }
        PrimeFaces.current().dialog().closeDynamic(topico);
    }


    @Override
    public void inicializar() {
        // No se requiere inicializar ningún dato
    }

    public void validate(FacesContext facesContext, UIComponent component, java.lang.Object object){
        var pregunta =  preguntaService.findOrThrow((String) getFromSession("idPregunta"));
        if ( pregunta.getTopicos().stream().map(Topico::getDescripcion).anyMatch(t->t.equals(object.toString())) ) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error "+exceptionMessage.getRegistroExistente(), "Error "+exceptionMessage.getRegistroExistente());
            throw new ValidatorException(msg);
        }
    }
}
