package co.edu.utp.gia.sms.query.paso;

import co.edu.utp.gia.sms.entidades.Paso;
import co.edu.utp.gia.sms.query.Queries;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;

/**
 * Permite encontrar un paso a través de su nombre
 */
@Entity
@NamedQuery(name = PasoFindByName.NAME, query = PasoFindByName.QUERY)
public class PasoFindByName extends Queries {
    public static final String NAME = "Paso.findByName";
    public static final String QUERY = "select paso from Paso paso where paso.nombre = :nombre";

    /**
     * Permite encontrar un paso a través de su nombre
     *
     * @param entityManager Para la ejecución de la consulta
     * @param nombre        Nombre del paso que se desea encontrar
     * @return TypedQuery<Paso> que representa la consulta de las {@link Paso}
     */
    public static TypedQuery<Paso> createQuery(EntityManager entityManager, String nombre) {
        return entityManager.createNamedQuery(NAME, Paso.class).setParameter("nombre", nombre);
    }
}