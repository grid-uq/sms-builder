package co.edu.utp.gia.sms.query.referencia;

import co.edu.utp.gia.sms.entidades.EvaluacionCalidad;
import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * Consulta que permite obtener el número de preguntas relacionadas con una referencia
 */
@Entity
@NamedQuery(name = ReferenciaCountPreguntaRelacionada.NAME, query = ReferenciaCountPreguntaRelacionada.QUERY)
public class ReferenciaCountPreguntaRelacionada extends Queries{
    public static final String NAME = "Referencia.countPreguntaRelacionada";
    public static final String QUERY = "select count(distinct t.pregunta.id) from Referencia r LEFT JOIN r.topicos t  where r.id = :id";

    /**
     * Consulta que permite obtener el número de preguntas relacionadas con una referencia
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @return TypedQuery< Long > que representa la consulta
     */
    public static TypedQuery<Long> createQuery(EntityManager entityManager, Integer id){
        return entityManager.createNamedQuery(NAME, Long.class)
                .setParameter("id",id);
    }
}
