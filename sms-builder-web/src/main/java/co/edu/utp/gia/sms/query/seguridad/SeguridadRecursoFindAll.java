package co.edu.utp.gia.sms.query.seguridad;

import co.edu.utp.gia.sms.entidades.Recurso;
import co.edu.utp.gia.sms.query.Queries;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;

/**
 * Consulta obtener un listado con todos los {@link co.edu.utp.gia.sms.entidades.Recurso}s registrados en el sistema
 */
@Entity
@NamedQuery(name = SeguridadRecursoFindAll.NAME, query = SeguridadRecursoFindAll.QUERY)
public class SeguridadRecursoFindAll extends Queries {
    public static final String NAME = "Seguridad.recursoFindAll";
    public static final String QUERY = "select recurso from Recurso recurso";

    /**
     * Consulta obtener un listado con todos los {@link co.edu.utp.gia.sms.entidades.Recurso}s registrados en el sistema
     *
     * @param entityManager Para la ejecución de la consulta
     * @return TypedQuery<Recurso> que representa la consulta
     */
    public static TypedQuery<Recurso> createQuery(EntityManager entityManager) {
        return entityManager.createNamedQuery(NAME, Recurso.class);
    }
}
