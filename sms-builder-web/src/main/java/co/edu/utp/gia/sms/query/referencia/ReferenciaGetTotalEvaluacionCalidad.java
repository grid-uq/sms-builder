package co.edu.utp.gia.sms.query.referencia;

import co.edu.utp.gia.sms.query.Queries;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;

/**
 * Consulta que permite obtener el total de las evaluaciones de calidad registradas en el sistema para una referencia
 */
@Entity
@NamedQuery(name = ReferenciaGetTotalEvaluacionCalidad.NAME, query = ReferenciaGetTotalEvaluacionCalidad.QUERY)
public class ReferenciaGetTotalEvaluacionCalidad extends Queries{
    public static final String NAME = "Referencia.getTotalEvaluacionCalidad";
    public static final String QUERY = "select SUM(e.evaluacionCuantitativa) from EvaluacionCalidad e where e.referencia.id = :id";

    /**
     * Consulta que permite obtener el total de las evaluaciones de calidad registradas en el sistema para una referencia
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @return TypedQuery< Double > que representa la consulta
     */
    public static TypedQuery<Double> createQuery(EntityManager entityManager, Integer id){
        return entityManager.createNamedQuery(NAME, Double.class)
                .setParameter("id",id);
    }
}
