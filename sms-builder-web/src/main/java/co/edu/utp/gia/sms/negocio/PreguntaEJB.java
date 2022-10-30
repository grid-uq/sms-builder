package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.dtos.PreguntaDTO;
import co.edu.utp.gia.sms.entidades.Objetivo;
import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.query.pregunta.PreguntaCount;
import co.edu.utp.gia.sms.query.pregunta.PreguntaFindAll;

import javax.ejb.Stateless;
import javax.inject.Inject;
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
@Stateless
public class PreguntaEJB extends AbstractEJB<Pregunta, Integer> {
    @Inject
    private ObjetivoEJB objetivoEJB;
    @Inject
    private TopicoEJB topicoEJB;

    public PreguntaEJB() {
        super(Pregunta.class);
    }

    /**
     * Permite registrar una pregunta
     *
     * @param codigo      Codigo de la pregunta
     * @param descripcion Descripcion de la pregunta
     * @param objetivos   Objetivos relacionados a la pregunta
     * @return La pregunta registrada
     */
    public Pregunta registrar(String codigo, String descripcion, List<Objetivo> objetivos) {
        Pregunta pregunta = null;
        if (!objetivos.isEmpty()) {
            pregunta = new Pregunta(codigo, descripcion, objetivos);
            entityManager.persist(pregunta);
        }
        return pregunta;
    }


    /**
     * Permite obtener el listado de preguntas de una revision
     *
     * @param id Identificador de la revision
     * @return Listado de {@link Pregunta} de la {@link Revision} identificada con
     * el id dado
     */
    public List<PreguntaDTO> obtenerPreguntas(Integer id) {
        List<PreguntaDTO> preguntas = PreguntaFindAll.createQuery(entityManager,id).getResultList();
        for (PreguntaDTO pregunta : preguntas) {
            pregunta.setTopicos(topicoEJB.obtenerTopicos(pregunta.getId()));
            pregunta.setObjetivos(objetivoEJB.obtenerObjetivosPregunta(pregunta.getId()));
        }
        return preguntas;
    }

    /**
     * Premite actualizar una {@link Pregunta}
     *
     * @param pregunta Pregunta a ser actualizada
     */
    public void actualizar(PreguntaDTO pregunta) {
        actualizar(pregunta.getId(), pregunta.getCodigo(), pregunta.getDescripcion());
    }

    /**
     * Permite actualizar una pregunta
     *
     * @param id          Id de la {@link Pregunta} a ser actualizada
     * @param codigo      Codigo de la pregunta a actualizar
     * @param descripcion Descripcion de la pregunta a actulizar
     */
    public void actualizar(Integer id, String codigo, String descripcion) {
        Pregunta pregunta = obtener(id);
        if (pregunta != null) {
            pregunta.setCodigo(codigo);
            pregunta.setDescripcion(descripcion);
        }
    }

    /**
     * Consulta que permite obtener el número de preguntas registradas en el sistema para una revision <br />
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @return Número de preguntas registradas en el sistema para una revision
     */
    public long totalPreguntas(Integer id) {
        return PreguntaCount.createQuery(entityManager,id).getSingleResult();
    }

}
