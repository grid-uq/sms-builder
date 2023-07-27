package co.edu.utp.gia.sms.query.seguridad;

import co.edu.utp.gia.sms.entidades.Recurso;
import co.edu.utp.gia.sms.query.Queries;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;

/**
 * Consulta permite buscar un {@link Recurso} basado en su url
 */
@Entity
@NamedQuery(name = SeguridadRecursoFindByUrl.NAME, query = SeguridadRecursoFindByUrl.QUERY)
public class SeguridadRecursoFindByUrl extends Queries {
    public static final String NAME = "Seguridad.recursoFindByUrl";
    public static final String QUERY = "select recurso from Recurso recurso where recurso.url = :url";

    /**
     * Consulta permite buscar un {@link Recurso} basado en su url
     *
     * @param entityManager Para la ejecución de la consulta
     * @param url Url del recurso que se desea búscar
     * @return TypedQuery<Recurso> que representa la consulta
     */
    public static TypedQuery<Recurso> createQuery(EntityManager entityManager,String url) {
        return entityManager.createNamedQuery(NAME, Recurso.class).setParameter("url",url);
    }
}
