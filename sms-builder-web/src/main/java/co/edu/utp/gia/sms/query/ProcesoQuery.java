package co.edu.utp.gia.sms.query;

import co.edu.utp.gia.sms.entidades.PasoProceso;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

@Entity
@NamedQuery(name = ProcesoQuery.FindPasos.NAME, query = ProcesoQuery.FindPasos.QUERY)
public class ProcesoQuery extends Queries {
    private static final String BASE = "Proceso.";

    /**
     * Permite obtener los pasos del proceso asociado a una revision <br />
     * Parametros: <b>id</b>Id de la revision de la que se desea obtener el proceso <br />
     * <br />
     * <code>select paso from PasoProceso paso where paso.revision.id = :id ORDER BY paso.orden ASC</code>
     */
    public static final class FindPasos{
        public static final String NAME = BASE +"findPasos";
        public static final String QUERY = "select paso from PasoProceso paso where paso.revision.id = :id ORDER BY paso.orden ASC";

        /**
         * Permite obtener los pasos del proceso asociado a una revision <br />
         * <code>select paso from PasoProceso paso where paso.revision.id = :id ORDER BY paso.orden ASC</code>
         * @param entityManager Para la ejecución de la consulta
         * @param id Id de la revision de la que se desea obtener los pasos del proceso <br />
         * @return TypedQuery< PasoProceso > que representa la consulta de las {@link PasoProceso}
         *
         */
        public static TypedQuery<PasoProceso> createQuery(EntityManager entityManager, Integer id){
            return entityManager.createNamedQuery(NAME, PasoProceso.class).setParameter("id",id);
        }
    }
}
