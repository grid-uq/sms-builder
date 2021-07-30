package co.edu.utp.gia.sms.query.revision;

import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * Consulta que permite obtener las revisiones registradas en el sistema que están relacionadas con un usuario
 */
@Entity
@NamedQuery(name = RevisionFindByUsuarioId.NAME, query = RevisionFindByUsuarioId.QUERY)
public class RevisionFindByUsuarioId extends Queries{
    public static final String NAME = "Revision.findByUsuarioId";
    public static final String QUERY = "select distinct p from Revision p left join p.revisores as r where p.propietario.id = :id or r.id = :id";

    /**
     * Consulta que permite obtener las revisiones registradas en el sistema que están relacionadas con un usuario
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id del {@link co.edu.utp.gia.sms.entidades.Usuario}
     * @return TypedQuery< Revision > que representa la consulta de las {@link co.edu.utp.gia.sms.entidades.Revision}
     */
    public static TypedQuery<Revision> createQuery(EntityManager entityManager, Integer id){
        return entityManager.createNamedQuery(NAME,Revision.class)
                .setParameter("id",id);
    }
}
