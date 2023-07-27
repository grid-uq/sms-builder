package co.edu.utp.gia.sms.query.revision;

import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.query.Queries;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;

/**
 * Consulta que permite obtener las revisiones registradas en el sistema
 */
@Entity
@NamedQuery(name = RevisionFindAll.NAME, query = RevisionFindAll.QUERY)
public class RevisionFindAll extends Queries{
    public static final String NAME = "Revision.findAll";
    public static final String QUERY = "select p from Revision p";

    /**
     * Consulta que permite obtener las revisiones registradas en el sistema
     *
     * @param entityManager Para la ejecución de la consulta
     * @return TypedQuery< Revision > que representa la consulta de las {@link Revision}
     */
    public static TypedQuery<Revision> createQuery(EntityManager entityManager){
        return entityManager.createNamedQuery(NAME, Revision.class);
    }
}
