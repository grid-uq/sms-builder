package co.edu.utp.gia.sms.query.paso;

import co.edu.utp.gia.sms.importutil.TipoFuente;
import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * Consulta que permite obtener el total de referencias de un determinado {@link TipoFuente} en un {@link co.edu.utp.gia.sms.entidades.PasoProceso}
 */
@Entity
@NamedQuery(name = PasoGetTotalReferenciasByTipoFuente.NAME, query = PasoGetTotalReferenciasByTipoFuente.QUERY)
public class PasoGetTotalReferenciasByTipoFuente extends Queries{
    public static final String NAME = "Paso.getTotalReferenciasByTipoFuente";
    public static final String QUERY = "select count(1) from PasoProceso p join p.referencias r join r.metadatos m,Fuente f where p.id = :id and f.tipo = :tipoFuente and m.identifier = co.edu.utp.gia.sms.entidades.TipoMetadato.FUENTE and m.value = f.nombre ";

    /**
     * Consulta que permite obtener el total de referencias de un determinado {@link TipoFuente} en un {@link co.edu.utp.gia.sms.entidades.PasoProceso}
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id del {@link co.edu.utp.gia.sms.entidades.PasoProceso}
     * @param tipoFuente Tipo de la fuente de la que se desea contar las referencias
     * @return TypedQuery< Long > que representa la consulta
     */
    public static TypedQuery<Long> createQuery(EntityManager entityManager, Integer id, TipoFuente tipoFuente){
        return entityManager.createNamedQuery(NAME,Long.class)
                .setParameter("id",id)
                .setParameter("tipoFuente",tipoFuente);
    }
}
