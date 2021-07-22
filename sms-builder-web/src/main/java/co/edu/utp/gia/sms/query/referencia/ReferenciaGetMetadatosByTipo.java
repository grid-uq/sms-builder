package co.edu.utp.gia.sms.query.referencia;

import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.entidades.Nota;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * Consulta que permite obtener los metadatos de una referencia que pertenecen a un cierto tipo
 */
@Entity
@NamedQuery(name = ReferenciaGetMetadatosByTipo.NAME, query = ReferenciaGetMetadatosByTipo.QUERY)
public class ReferenciaGetMetadatosByTipo extends Queries{
    public static final String NAME = "Referencia.getMetadatosByTipo";
    public static final String QUERY = "select m from Metadato m where m.referencia.id = :id and m.identifier = :tipo ";

    /**
     * Consulta que permite obtener los metadatos de una referencia que pertenecen a un cierto tipo
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @param tipo Tipo de metadato que se desea obtener
     * @return TypedQuery< Metadato > que representa la consulta de las {@link co.edu.utp.gia.sms.entidades.Metadato}
     */
    public static TypedQuery<Metadato> createQuery(EntityManager entityManager, Integer id, TipoMetadato tipo){
        return entityManager.createNamedQuery(NAME, Metadato.class)
                .setParameter("id",id)
                .setParameter("tipo",tipo);
    }
}
