package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.dtos.PreguntaDTO;
import co.edu.utp.gia.sms.entidades.Objetivo;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.PreguntaEJB;
import co.edu.utp.gia.sms.negocio.TopicoEJB;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class RegistroPreguntaBean extends GenericBean<PreguntaDTO> {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    private String descripcion;
    @Getter
    @Setter
    private String codigo;
    @Getter
    @Setter
    private List<PreguntaDTO> preguntas;

    @Getter
    @Setter
    private List<Objetivo> listaObjetivos;


    @Inject
    private PreguntaEJB preguntaEJB;
    @Inject
    private TopicoEJB topicoEJB;

    public void inicializar() {
        if (getRevision() != null) {
            preguntas = preguntaEJB.get();
        }
        listaObjetivos = new ArrayList<>();
    }

    public String registrar() {
        preguntaEJB.save(codigo, descripcion, listaObjetivos);
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        codigo = "";
        descripcion = "";
        return "/revision/registroPregunta";
    }

    @Override
    public void actualizar(PreguntaDTO objeto) {
        preguntaEJB.actualizar(objeto);
    }


    /**
     * Permite eliminar una pregunta
     *
     * @param pregunta pregunta a eliminar
     */
    public void eliminar(PreguntaDTO pregunta) {
        preguntaEJB.eliminar(pregunta.getId());
        preguntas.remove(pregunta);
        mostrarMensajeGeneral("Pregunta eliminada");
    }

    /**
     * Permite eliminar un {@link Topico} de una pregunta
     *
     * @param topico Topico de la pregunta a eliminar
     */
    public void eliminarTopico(PreguntaDTO pregunta, Topico topico) {
        topicoEJB.eliminar(topico.getId());
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        pregunta.getTopicos().remove(topico);
    }

    public void adicionarTopico(Integer id) {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        addToSession("idPregunta", id);
        PrimeFaces.current().dialog().openDynamic("/revision/registrarTopico", options, null);
    }

    public void onTopicoCreado(SelectEvent<Topico> event) {
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        inicializar();
    }

}
