package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * Consulta que permite obtener el total de referencias repetidas de una Revision
 */
@Entity
@NamedQuery(name = EstadisticaGetTotalReferenciasRepetidas.NAME, query = EstadisticaGetTotalReferenciasRepetidas.QUERY)
public class EstadisticaGetTotalReferenciasRepetidas extends Queries{
    public static final String NAME = "Estadistica.getTotalReferenciasRepeditas";
    public static final String QUERY = "select count(1) from Referencia r  where r.revision.id = :id and r.duplicada = true";

    /**
     * Consulta que permite obtener el total de referencias repetidas de una Revision
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @return TypedQuery< Long > que representa la consulta
     */
    public static TypedQuery<Long> createQuery(EntityManager entityManager, Integer id){
        return entityManager.createNamedQuery(NAME,Long.class)
                .setParameter("id",id);
    }
}
