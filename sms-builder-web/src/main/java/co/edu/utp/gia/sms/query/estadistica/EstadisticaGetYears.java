package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * Consulta que permite obtener el listado de años que comprenden las Referencias de la Revision
 */
@Entity
@NamedQuery(name = EstadisticaGetYears.NAME, query = EstadisticaGetYears.QUERY)
public class EstadisticaGetYears extends Queries{
    public static final String NAME = "Estadistica.getYears";
    public static final String QUERY = "select distinct r.year from Referencia r  where r.revision.id = :id order by r.year ASC";

    /**
     * Consulta que permite obtener el listado de años que comprenden las Referencias de la Revision
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @return TypedQuery< String > que representa la consulta
     */
    public static TypedQuery<String> createQuery(EntityManager entityManager, Integer id){
        return entityManager.createNamedQuery(NAME,String.class)
                .setParameter("id",id);
    }
}
