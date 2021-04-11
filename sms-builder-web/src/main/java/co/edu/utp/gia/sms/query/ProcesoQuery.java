package co.edu.utp.gia.sms.query;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = ProcesoQuery.PROCESO_PASOS, query = "select paso from PasoProceso paso where paso.revision.id = :id ORDER BY paso.orden ASC")
public class ProcesoQuery extends Queries {
    /**
     * Permite obtener los pasos del proceso asociado a una revision <br />
     *
     * @param id Id de la revision de la que se desea obtener el proceso <br />
     * <code>select paso from PasoProceso paso where paso.revision.id = :id ORDER BY paso.orden ASC </code>
     */
    public static final String PROCESO_PASOS = "Proceso.pasos";
}
