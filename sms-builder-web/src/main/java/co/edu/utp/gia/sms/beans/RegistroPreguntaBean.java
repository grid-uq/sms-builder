package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.dtos.PreguntaDTO;
import co.edu.utp.gia.sms.entidades.Objetivo;
import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.AbstractGenericService;
import co.edu.utp.gia.sms.negocio.PreguntaService;
import co.edu.utp.gia.sms.negocio.TopicoService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import java.util.*;

/**
 * Clase controladora de interfaz web que se encarga de la gestión de preguntas.
 *
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Julio Cesar Chavarro
 * @author Carlos Augusto Meneces
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 9 abr. 2020
 */
@Named
@ViewScoped
public class RegistroPreguntaBean extends GenericBeanNew<Pregunta,String> {
    @Getter @Setter
    private List<Objetivo> listaObjetivos;
    @Inject
    private PreguntaService preguntaService;
    @Inject
    private TopicoService topicoService;

    public void inicializar() {
        setRecords(preguntaService.get());
        listaObjetivos = new ArrayList<>();
    }

    @Override
    protected Pregunta newRecord() {
        return new Pregunta();
    }

    @Override
    protected AbstractGenericService<Pregunta, String> getServices() {
        return preguntaService;
    }

    /**
     * Permite eliminar un {@link Topico} de una pregunta
     *
     * @param topico Topico de la pregunta a eliminar
     */
    public void eliminarTopico(Pregunta pregunta, Topico topico) {
        preguntaService.remove(pregunta,topico);
        topicoService.delete(topico.getId());
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
    }

    public void adicionarTopico(String id) {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        addToSession("idPregunta", id);
        PrimeFaces.current().dialog().openDynamic("/pregunta/registroTopico", options, null);
    }

    public void onTopicoCreado(SelectEvent<Topico> event) {
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        inicializar();
    }

    public void validate(FacesContext facesContext, UIComponent component, java.lang.Object object){
        validateUnique(facesContext, component, object, record -> record.getCodigo().equals(object.toString()) );
    }
}
