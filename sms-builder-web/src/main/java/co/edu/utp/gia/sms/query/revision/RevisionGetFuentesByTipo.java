package co.edu.utp.gia.sms.query.revision;

import co.edu.utp.gia.sms.entidades.Fuente;
import co.edu.utp.gia.sms.entidades.TipoFuente;
import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * Consulta que permite obtener las fuentes registradas en el sistema para una revision
 */
@Entity
@NamedQuery(name = RevisionGetFuentesByTipo.NAME, query = RevisionGetFuentesByTipo.QUERY)
public class RevisionGetFuentesByTipo extends Queries{
    public static final String NAME = "Revision.getFuentesByTipo";
    public static final String QUERY = "select fuente from Fuente fuente where fuente.revision.id = :id and fuente.tipo = :tipo";

    /**
     * Consulta que permite obtener las fuentes registradas en el sistema para una revision
     *
     * @param entityManager Para la ejecución de la consulta
     * @param tipo tipo de fuente por la que se desea filtrar las fuentes a buscar
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @return TypedQuery< Fuente > que representa la consulta
     */
    public static TypedQuery<Fuente> createQuery(EntityManager entityManager, TipoFuente tipo, Integer id){
        return entityManager.createNamedQuery(NAME,Fuente.class)
                .setParameter("id",id)
                .setParameter("tipo",tipo);
    }
}
