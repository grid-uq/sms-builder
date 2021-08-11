package co.edu.utp.gia.sms.query.revision;

import co.edu.utp.gia.sms.entidades.TipoFuente;
import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * Consulta que permite obtener el total de referencias de un determinado {@link TipoFuente} en una Revision
 */
@Entity
@NamedQuery(name = RevisionGetTotalReferenciasByTipoFuente.NAME, query = RevisionGetTotalReferenciasByTipoFuente.QUERY)
public class RevisionGetTotalReferenciasByTipoFuente extends Queries{
    public static final String NAME = "Revision.getTotalReferenciasByTipoFuente";
    public static final String QUERY = "select count(1) from Metadato m,Fuente f where m.referencia.revision.id = :id and f.tipo = :tipoFuente and m.identifier = co.edu.utp.gia.sms.entidades.TipoMetadato.FUENTE and m.value = f.nombre ";

    /**
     * Consulta que permite obtener el total de referencias de un determinado {@link TipoFuente} en una Revision
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @param tipoFuente Tipo de la fuente de la que se desea contar las referencias
     * @return TypedQuery< Long > que representa la consulta
     */
    public static TypedQuery<Long> createQuery(EntityManager entityManager, Integer id, TipoFuente tipoFuente){
        return entityManager.createNamedQuery(NAME,Long.class)
                .setParameter("id",id)
                .setParameter("tipoFuente",tipoFuente);
    }
}
