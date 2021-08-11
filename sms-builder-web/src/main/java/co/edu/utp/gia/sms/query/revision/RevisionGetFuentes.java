package co.edu.utp.gia.sms.query.revision;

import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.entidades.Fuente;
import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * Consulta que permite obtener las fuentes registradas en el sistema para una revision
 */
@Entity
@NamedQuery(name = RevisionGetFuentes.NAME, query = RevisionGetFuentes.QUERY)
public class RevisionGetFuentes extends Queries{
    public static final String NAME = "Revision.getFuentes";
    public static final String QUERY = "select fuente from Fuente fuente where fuente.revision.id = :id";

    /**
     * Consulta que permite obtener las fuentes registradas en el sistema para una revision
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @return TypedQuery< Fuente > que representa la consulta
     */
    public static TypedQuery<Fuente> createQuery(EntityManager entityManager, Integer id){
        return entityManager.createNamedQuery(NAME,Fuente.class)
                .setParameter("id",id);
    }
}
