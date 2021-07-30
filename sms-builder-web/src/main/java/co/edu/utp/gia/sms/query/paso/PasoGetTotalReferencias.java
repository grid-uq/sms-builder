package co.edu.utp.gia.sms.query.paso;

import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * Consulta que permite obtener el total de referencias de un {@link co.edu.utp.gia.sms.entidades.PasoProceso}
 */
@Entity
@NamedQuery(name = PasoGetTotalReferencias.NAME, query = PasoGetTotalReferencias.QUERY)
public class PasoGetTotalReferencias extends Queries{
    public static final String NAME = "Paso.getTotalReferencias";
    public static final String QUERY = "select count(1) from PasoProceso p join p.referencias r where p.id = :id ";

    /**
     * Consulta que permite obtener el total de referencias de un {@link co.edu.utp.gia.sms.entidades.PasoProceso}
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id del {@link co.edu.utp.gia.sms.entidades.PasoProceso}
     * @return TypedQuery< Long > que representa la consulta
     */
    public static TypedQuery<Long> createQuery(EntityManager entityManager, Integer id){
        return entityManager.createNamedQuery(NAME,Long.class)
                .setParameter("id",id);
    }
}
