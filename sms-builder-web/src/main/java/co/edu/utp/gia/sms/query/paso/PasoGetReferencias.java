package co.edu.utp.gia.sms.query.paso;

import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * Consulta que permite obtener las de referencias de un {@link co.edu.utp.gia.sms.entidades.PasoProceso}
 */
@Entity
@NamedQuery(name = PasoGetReferencias.NAME, query = PasoGetReferencias.QUERY)
public class PasoGetReferencias extends Queries{
    public static final String NAME = "Paso.getReferencias";
    public static final String QUERY = "select r from PasoProceso p join p.referencias r where p.id = :id ";

    /**
     * Consulta que permite obtener las de referencias de un {@link co.edu.utp.gia.sms.entidades.PasoProceso}
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id del {@link co.edu.utp.gia.sms.entidades.PasoProceso}
     * @return TypedQuery< Referencia > que representa la consulta
     */
    public static TypedQuery<Referencia> createQuery(EntityManager entityManager, Integer id){
        return entityManager.createNamedQuery(NAME,Referencia.class)
                .setParameter("id",id);
    }
}
