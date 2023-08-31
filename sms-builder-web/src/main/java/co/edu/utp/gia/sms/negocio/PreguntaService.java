package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.dtos.PreguntaDTO;
import co.edu.utp.gia.sms.entidades.Objetivo;
import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.entidades.Termino;
import co.edu.utp.gia.sms.entidades.Topico;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

/**
 * Clase de negocio encargada de implementar las funciones correspondientes a la
 * gestion de la {@link Pregunta}.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
@ApplicationScoped
public class PreguntaService extends AbstractGenericService<Pregunta, String> {

    @Inject
    private TopicoService topicoService;
    public PreguntaService() {
        super(DB.root.getProvider(Pregunta.class));
    }

    /**
     * Permite registrar una pregunta
     *
     * @param codigo      Codigo de la pregunta
     * @param descripcion Descripcion de la pregunta
     * @param objetivos   Objetivos relacionados a la pregunta
     * @return La pregunta registrada
     */
    public Pregunta save(String codigo, String descripcion, List<Objetivo> objetivos) {
        Pregunta pregunta = null;
        if (!objetivos.isEmpty()) {
            final Pregunta nuevapPregunta = new Pregunta(codigo, descripcion, objetivos);
            pregunta = save(nuevapPregunta);
            objetivos.forEach( objetivo -> objetivo.getPreguntas().add(nuevapPregunta) );
        }
        return pregunta;
    }

    /**
     * Premite actualizar una {@link Pregunta}
     *
     * @param pregunta Pregunta a ser actualizada
     */
    public void update(PreguntaDTO pregunta) {
        update(pregunta.getId(), pregunta.getCodigo(), pregunta.getDescripcion());
    }

    /**
     * Permite actualizar una pregunta
     *
     * @param id          Id de la {@link Pregunta} a ser actualizada
     * @param codigo      Codigo de la pregunta a actualizar
     * @param descripcion Descripcion de la pregunta a actulizar
     */
    public void update(String id, String codigo, String descripcion) {
        Pregunta pregunta = findOrThrow(id);
        if (pregunta != null) {
            pregunta.setCodigo(codigo);
            pregunta.setDescripcion(descripcion);
        }
    }

    /**
     * Permite adicionar un topico a una pregunta
     *
     * @param pregunta Pregunta a la que se desea adicionar un tópico.
     * @param topico Topico a ser adicionado a la pregunta.
     */
    public void add(@NotNull Pregunta pregunta,@NotNull Topico topico) {
        var record = findOrThrow(pregunta.getId());
        var topicoStored = topicoService.findOrThrow(topico.getId());
        record.getTopicos().add(topicoStored);
        DB.storageManager.store(record.getTopicos());
    }

    /**
     * Permite remover un topico a una pregunta
     *
     * @param pregunta Pregunta a la que se desea adicionar un tópico.
     * @param topico Topico a ser adicionado a la pregunta.
     */
    public void remove(@NotNull Pregunta pregunta,@NotNull Topico topico) {
        var record = findOrThrow(pregunta.getId());
        var topicoStored = topicoService.findOrThrow(topico.getId());
        record.getTopicos().remove(topicoStored);
        DB.storageManager.store(record.getTopicos());
    }

}
