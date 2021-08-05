package co.edu.utp.gia.sms.query.revision;

import co.edu.utp.gia.sms.entidades.CadenaBusqueda;
import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * Permite encontrar todas las cadenas de busqueda registradas en una revision <br />
 */
@Entity
@NamedQuery(name = RevisionGetCadenaBusquedaQuery.NAME, query = RevisionGetCadenaBusquedaQuery.QUERY)
public class RevisionGetCadenaBusquedaQuery extends Queries {
    public static final String NAME = "Revision.getCadenasBusqueda";
    public static final String QUERY = "select c from CadenaBusqueda c where c.revision.id = :id order by c.baseDatos";

    /**
     * Permite encontrar todas las cadenas de busqueda registradas en una revision
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id            Id de la revision de la que se desean obtener las cadenas de busqueda.
     * @return TypedQuery<CadenaBusqueda> que representa la consulta de las {@link CadenaBusqueda}
     */
    public static TypedQuery<CadenaBusqueda> createQuery(EntityManager entityManager, Integer id) {
        return entityManager.createNamedQuery(NAME, CadenaBusqueda.class).setParameter("id", id);
    }
}
