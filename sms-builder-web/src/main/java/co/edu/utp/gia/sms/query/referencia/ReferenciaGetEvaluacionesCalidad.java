package co.edu.utp.gia.sms.query.referencia;

import co.edu.utp.gia.sms.entidades.EvaluacionCalidad;
import co.edu.utp.gia.sms.query.Queries;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;

/**
 * Consulta que permite obtener las evaluaciones de calidad registradas en el sistema para una referencia
 */
@Entity
@NamedQuery(name = ReferenciaGetEvaluacionesCalidad.NAME, query = ReferenciaGetEvaluacionesCalidad.QUERY)
public class ReferenciaGetEvaluacionesCalidad extends Queries{
    public static final String NAME = "Referencia.getEvaluacionesCalidad";
    public static final String QUERY = "select e from EvaluacionCalidad e where e.referencia.id = :id";

    /**
     * Consulta que permite obtener las evaluaciones de calidad registradas en el sistema para una referencia
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @return TypedQuery< EvaluacionCalidad > que representa la consulta de las {@link EvaluacionCalidad}
     */
    public static TypedQuery<EvaluacionCalidad> createQuery(EntityManager entityManager, Integer id){
        return entityManager.createNamedQuery(NAME, EvaluacionCalidad.class)
                .setParameter("id",id);
    }
}
