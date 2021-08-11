package co.edu.utp.gia.sms.query.referencia;

import co.edu.utp.gia.sms.entidades.Fuente;
import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * Consulta que permite obtener las fuentes registradas en el sistema para una referencia
 */
@Entity
@NamedQuery(name = ReferenciaGetFuentes.NAME, query = ReferenciaGetFuentes.QUERY)
public class ReferenciaGetFuentes extends Queries{
    public static final String NAME = "Referencia.getFuentes";
    public static final String QUERY = "select f from Fuente f,  Metadato m where m.referencia.id = :id " +
            "and m.referencia.revision = f.revision " +
            "and m.identifier = co.edu.utp.gia.sms.entidades.TipoMetadato.FUENTE " +
            "and m.value = f.nombre ";


    /**
     * Consulta que permite obtener las fuentes registradas en el sistema para una referencia
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @return TypedQuery< Fuente > que representa la consulta
     */
    public static TypedQuery<Fuente> createQuery(EntityManager entityManager, Integer id){
        return entityManager.createNamedQuery(NAME, Fuente.class)
                .setParameter("id",id);
    }
}
