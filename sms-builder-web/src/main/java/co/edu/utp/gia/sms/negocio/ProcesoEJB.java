package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.PasoProceso;
import co.edu.utp.gia.sms.entidades.Referencia;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
/**
 * Clase de negocio encargada de implementar las funciones correspondientes a la
 * gestion del {@link PasoProceso}.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
@ApplicationScoped
public class ProcesoEJB extends AbstractGenericService<PasoProceso, String> {

    @Inject
    private RevisionService revisionService;
    @Inject
    private PasoService pasoService;
    public ProcesoEJB() {
        super(DB.root.getProvider(PasoProceso.class));
    }

    /**
     * Permite registrar un nuevo paso en el proceso
     *
     * @param idPaso      Id del paso que se desea adicionar
     *
     * @return El {@link PasoProceso} registrado
     */
    public PasoProceso save(String idPaso) {
        var paso = pasoService.findOrThrow(idPaso);
        var revision = revisionService.get();
        verificarOrden( revision.getPasosProceso() );
        var pasoProceso = new PasoProceso(revision.getPasosProceso().size()+1,paso);
        save(pasoProceso);
        revision.setPasoSeleccionado(pasoProceso);
        return pasoProceso;
    }

    public void delete(String id) {
        var pasoProceso = findOrThrow(id);
        super.delete(pasoProceso);

        verificarOrden();
        var indice = count() ;
        pasoProceso.getRevision().setPasoSeleccionado(pasoProceso.getRevision().getPasosProceso().get(indice));
        super.eliminar(pasoProceso);
    }

    private void verificarOrden() {
        int i = 1;
        for (var paso:dataProvider.get()) {
            if( paso.getOrden() != i ){
                paso.setOrden(i);
            }
            i++;
        }
    }

    /**
     * Permite obtener los pasos del proceso de una revision dado el id de la revision
     * @param id ID de la revision de la que se desean obtener los pasos del proceso
     * @return Listado con los pasos del proceso de la revision
     */
    public List<PasoProceso> listar(Integer id) {
        var revision = revisionService.obtenerOrThrow(id);
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

    public PasoProceso findByOrden(int orden){

    }
}
