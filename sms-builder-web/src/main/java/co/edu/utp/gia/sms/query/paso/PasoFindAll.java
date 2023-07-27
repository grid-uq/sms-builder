package co.edu.utp.gia.sms.query.paso;

import co.edu.utp.gia.sms.entidades.Paso;
import co.edu.utp.gia.sms.query.Queries;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;

/**
 * Permite encontrar todos los paso registrados
 */
@Entity
@NamedQuery(name = PasoFindAll.NAME, query = PasoFindAll.QUERY)
public class PasoFindAll extends Queries {
    public static final String NAME = "Paso.findAll";
    public static final String QUERY = "select paso from Paso paso";

    /**
     * Permite encontrar todos los paso registrados
     *
     * @param entityManager Para la ejecución de la consulta
     * @return TypedQuery<Paso> que representa la consulta de las {@link Paso}
     */
    public static TypedQuery<Paso> createQuery(EntityManager entityManager) {
        return entityManager.createNamedQuery(NAME, Paso.class);
    }
}
