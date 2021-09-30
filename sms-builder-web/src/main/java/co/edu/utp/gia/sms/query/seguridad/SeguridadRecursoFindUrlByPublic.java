package co.edu.utp.gia.sms.query.seguridad;

import co.edu.utp.gia.sms.entidades.Recurso;
import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * Consulta que permite obtener un listado con todos los urls de los {@link Recurso}s segun si son públicos o no
 */
@Entity
@NamedQuery(name = SeguridadRecursoFindUrlByPublic.NAME, query = SeguridadRecursoFindUrlByPublic.QUERY)
public class SeguridadRecursoFindUrlByPublic extends Queries {
    public static final String NAME = "Seguridad.recursoFindUrlByPublic";
    public static final String QUERY = "select recurso.url from Recurso recurso where recurso.publico = :estado";

    /**
     * Consulta que permite obtener un listado con todos los urls de los {@link Recurso}s registrados en el sistema
     *
     * @param entityManager Para la ejecución de la consulta
     * @param isPublico Determina si se desean obtener los recursos públicos o no públicos
     * @return TypedQuery<String> que representa la consulta
     */
    public static TypedQuery<String> createQuery(EntityManager entityManager,boolean isPublico) {
        return entityManager.createNamedQuery(NAME, String.class).setParameter("estado",isPublico);
    }
}
