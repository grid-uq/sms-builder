package co.edu.utp.gia.sms.query.referencia;

import co.edu.utp.gia.sms.entidades.Nota;
import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * Consulta que permite obtener las Notas registrados en el sistema para una Referencia
 */
@Entity
@NamedQuery(name = ReferenciaGetNotas.NAME, query = ReferenciaGetNotas.QUERY)
public class ReferenciaGetNotas extends Queries{
    public static final String NAME = "Referencia.getNotas";
    public static final String QUERY = "select n from Nota n where n.referencia.id = :id";

    /**
     * Consulta que permite obtener las Notas registrados en el sistema para una Referencia
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @return TypedQuery< Nota > que representa la consulta de las {@link co.edu.utp.gia.sms.entidades.Nota}
     */
    public static TypedQuery<Nota> createQuery(EntityManager entityManager, Integer id){
        return entityManager.createNamedQuery(NAME, Nota.class)
                .setParameter("id",id);
    }
}
