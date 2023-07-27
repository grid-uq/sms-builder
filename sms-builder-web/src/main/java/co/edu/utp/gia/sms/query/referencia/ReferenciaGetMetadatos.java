package co.edu.utp.gia.sms.query.referencia;

import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.query.Queries;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;

/**
 * Consulta que permite obtener los Metadatos de una Referencia
 */
@Entity
@NamedQuery(name = ReferenciaGetMetadatos.NAME, query = ReferenciaGetMetadatos.QUERY)
public class ReferenciaGetMetadatos extends Queries{
    public static final String NAME = "Referencia.getMetadatos";
    public static final String QUERY = "select m from Metadato m where m.referencia.id = :id";

    /**
     * Consulta que permite obtener los Metadatos de una Referencia
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @return TypedQuery< Metadato > que representa la consulta
     */
    public static TypedQuery<Metadato> createQuery(EntityManager entityManager, Integer id){
        return entityManager.createNamedQuery(NAME, Metadato.class)
                .setParameter("id",id);
    }
}
