package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.PasoProceso;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.Revision;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@LocalBean
public class ProcesoEJB extends AbstractEJB<PasoProceso, Integer> {

    @Inject
    private RevisionEJB revisionEJB;
    @Inject
    private PasoEJB pasoEJB;
    public ProcesoEJB() {
        super(PasoProceso.class);
    }

    /**
     * Permite registrar un nuevo paso en el proceso
     *
     * @param idPaso      Id del paso que se desea adicionar
     * @param idRevision  Id de la {@link Revision} a la que se desea adicionar el
     *                    Objetivo
     * @return El {@link PasoProceso} registrado
     */
    public PasoProceso registrar(Integer idPaso, Integer idRevision) {
        var paso = pasoEJB.obtenerOrThrow(idPaso);
        var revision = revisionEJB.obtenerOrThrow(idRevision);
        verificarOrden( revision.getPasosProceso() );
        var pasoProceso = new PasoProceso(revision.getPasosProceso().size(),paso,revision);
        entityManager.persist(pasoProceso);
        revision.getPasosProceso().add(pasoProceso);
        revision.setPasoSeleccionado(pasoProceso);
        return pasoProceso;
    }

    @Override
    public void eliminar(Integer id) {
        var pasoProceso = obtenerOrThrow(id);
        pasoProceso.getRevision().getPasosProceso().remove(pasoProceso);
        verificarOrden(pasoProceso.getRevision().getPasosProceso());
        var indice = pasoProceso.getRevision().getPasosProceso().size() -1;
        pasoProceso.getRevision().setPasoSeleccionado(pasoProceso.getRevision().getPasosProceso().get(indice));
        super.eliminar(pasoProceso);
    }

    private void verificarOrden(List<PasoProceso> pasosProceso) {
        for (var i = 1; i <= pasosProceso.size() ; i++) {
            var paso = pasosProceso.get(i-1);
            if( paso.getOrden() != i ){
                paso.setOrden(i);
            }
        }
    }

    /**
     * Permite obtener los pasos del proceso de una revision dado el id de la revision
     * @param id ID de la revision de la que se desean obtener los pasos del proceso
     * @return Listado con los pasos del proceso de la revision
     */
    public List<PasoProceso> listar(Integer id) {
        var revision = revisionEJB.obtenerOrThrow(id);
        return revision.getPasosProceso();
    }

    public void addReferencia(Integer idPasoProceso, Referencia referencia) {
        var paso = obtenerOrThrow(idPasoProceso);
        paso.getReferencias().add(referencia);
    }

    public void removeReferencia(Integer idPasoProceso, Referencia referencia) {
        var paso = obtenerOrThrow(idPasoProceso);
        paso.getReferencias().remove(referencia);
    }
}
