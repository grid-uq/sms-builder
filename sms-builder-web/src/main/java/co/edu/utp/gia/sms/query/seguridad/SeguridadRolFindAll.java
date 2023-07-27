package co.edu.utp.gia.sms.query.seguridad;

import co.edu.utp.gia.sms.entidades.Rol;
import co.edu.utp.gia.sms.query.Queries;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;

/**
 * Consulta obtener un listado con todos los {@link Rol}s registrados en el sistema
 */
@Entity
@NamedQuery(name = SeguridadRolFindAll.NAME, query = SeguridadRolFindAll.QUERY)
public class SeguridadRolFindAll extends Queries {
    public static final String NAME = "Seguridad.rolFindAll";
    public static final String QUERY = "select rol from Rol rol";

    /**
     * Consulta obtener un listado con todos los {@link Rol}s registrados en el sistema
     *
     * @param entityManager Para la ejecución de la consulta
     * @return TypedQuery<Rol> que representa la consulta
     */
    public static TypedQuery<Rol> createQuery(EntityManager entityManager) {
        return entityManager.createNamedQuery(NAME, Rol.class);
    }
}
