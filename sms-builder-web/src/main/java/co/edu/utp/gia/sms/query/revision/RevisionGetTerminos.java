package co.edu.utp.gia.sms.query.revision;

import co.edu.utp.gia.sms.entidades.Termino;
import co.edu.utp.gia.sms.query.Queries;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;

/**
 * Consulta que permite obtener los terminos registradas en el sistema para una revision
 */
@Entity
@NamedQuery(name = RevisionGetTerminos.NAME, query = RevisionGetTerminos.QUERY)
public class RevisionGetTerminos extends Queries{
    public static final String NAME = "Revision.getTerminos";
    public static final String QUERY = "select t from Termino t where t.revision.id = :id";

    /**
     * Consulta que permite obtener los terminos registradas en el sistema para una revision
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @return TypedQuery< Termino > que representa la consulta de las {@link Termino}
     */
    public static TypedQuery<Termino> createQuery(EntityManager entityManager, Integer id){
        return entityManager.createNamedQuery(NAME,Termino.class)
                .setParameter("id",id);
    }
}
