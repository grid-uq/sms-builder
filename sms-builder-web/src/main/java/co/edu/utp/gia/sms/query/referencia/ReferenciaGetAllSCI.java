package co.edu.utp.gia.sms.query.referencia;

import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * Consulta que permite obtener los SCI de las referencia de una revision
 */
@Entity
@NamedQuery(name = ReferenciaGetAllSCI.NAME, query = ReferenciaGetAllSCI.QUERY)
public class ReferenciaGetAllSCI extends Queries{
    public static final String NAME = "Referencia.getAllSCI";
    public static final String QUERY = "select r.sci from Revision revision " +
            "inner join revision.pasoSeleccionado.referencias r where revision.id = :id";

    /**
     * Consulta que permite obtener los SCI de las referencia de una revision
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id de la Revision
     * @return TypedQuery< Double > que representa la consulta
     */
    public static TypedQuery<Float> createQuery(EntityManager entityManager, Integer id){
        return entityManager.createNamedQuery(NAME, Float.class)
                .setParameter("id",id);
    }
}
