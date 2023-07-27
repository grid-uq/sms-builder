package co.edu.utp.gia.sms.query.revision;

import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.query.Queries;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;

/**
 * Consulta que permite obtener los atributos de calidad registradas en el sistema para una revision
 */
@Entity
@NamedQuery(name = RevisionGetAtributosCalidad.NAME, query = RevisionGetAtributosCalidad.QUERY)
public class RevisionGetAtributosCalidad extends Queries{
    public static final String NAME = "Revision.getAtributosCalidad";
    public static final String QUERY = "select a from AtributoCalidad a where a.revision.id = :id";

    /**
     * Consulta que permite obtener los atributos de calidad registradas en el sistema para una revision
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @return TypedQuery< AtributoCalidad > que representa la consulta de las {@link co.edu.utp.gia.sms.entidades.AtributoCalidad}
     */
    public static TypedQuery<AtributoCalidad> createQuery(EntityManager entityManager, Integer id){
        return entityManager.createNamedQuery(NAME,AtributoCalidad.class)
                .setParameter("id",id);
    }
}
