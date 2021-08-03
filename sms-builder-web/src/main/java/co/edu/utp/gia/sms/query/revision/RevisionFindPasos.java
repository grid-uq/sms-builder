package co.edu.utp.gia.sms.query.revision;

import co.edu.utp.gia.sms.entidades.PasoProceso;
import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * Permite obtener los pasos del proceso asociado a una revision
 */
@Entity
@NamedQuery(name = RevisionFindPasos.NAME, query = RevisionFindPasos.QUERY)
public class RevisionFindPasos extends Queries {
    public static final String NAME = "Revision.findPasosProceso";
    public static final String QUERY = "select paso from PasoProceso paso where paso.revision.id = :id ORDER BY paso.orden ASC";

    /**
     * Permite obtener los pasos del proceso asociado a una revision
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id            Id de la revision de la que se desea obtener los pasos del proceso
     * @return TypedQuery<PasoProceso> que representa la consulta de las {@link PasoProceso}
     */
    public static TypedQuery<PasoProceso> createQuery(EntityManager entityManager, Integer id) {
        return entityManager.createNamedQuery(NAME, PasoProceso.class).setParameter("id", id);
    }
}