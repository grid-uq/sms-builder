package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.dtos.PreguntaDTO;
import co.edu.utp.gia.sms.entidades.Objetivo;
import co.edu.utp.gia.sms.entidades.Pregunta;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
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
}
