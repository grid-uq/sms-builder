package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.PasoProceso;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.exceptions.LogicException;
import co.edu.utp.gia.sms.query.ProcesoQuery;

import javax.inject.Inject;
import java.util.List;

public class ProcesoEJB extends AbstractEJB<PasoProceso, Integer> {

    @Inject
    private RevisionEJB revisionEJB;
    public ProcesoEJB() {
        super(PasoProceso.class);
    }

    /**
     * Permite obtener los pasos del proceso de una revision dado el id de la revision
     * @param id ID de la revision de la que se desean obtener los pasos del proceso
     * @return Listado con los pasos del proceso de la revision
     */
    public List<PasoProceso> listar(Integer id) {
        Revision revision = revisionEJB.obtenerOrThrow(id);
        return revision.getPasoProcesos();
        //return entityManager.createNamedQuery(ProcesoQuery.PROCESO_PASOS,PasoProceso.class).setParameter("id",id).getResultList();
    }

}
