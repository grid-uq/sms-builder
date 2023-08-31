package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.PasoProceso;
import co.edu.utp.gia.sms.entidades.Referencia;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
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
public class ProcesoService extends AbstractGenericService<PasoProceso, String> {

    @Inject
    private RevisionService revisionService;
    @Inject
    private PasoService pasoService;
    public ProcesoService() {
        super(DB.root.getProvider(PasoProceso.class));
    }

    @Override
    public PasoProceso save(PasoProceso paso) {
        checkOrder( );
        paso.setOrden( get().size() + 1 );
        pasoService.findOrThrow( paso.getPaso().getId() );
        super.save(paso);
        revisionService.changePasoSeleccionado(paso);
        return paso;
    }

    @Override
    public void delete(PasoProceso entidad) {
        super.delete(entidad);
        checkOrder();
        var indice = count() ;
        var paso = findByOrden(indice);
        revisionService.changePasoSeleccionado(paso);
    }

    /**
     * Reordena el conjunto de pasos tras la eliminación de uno de los pasos
     */
    private void checkOrder() {
        int i = 1;
        for (var paso:dataProvider.get()) {
            if( paso.getOrden() != i ){
                paso.setOrden(i);
            }
            i++;
        }
    }

    /**
     * Adiciona una referencia al paso
     * @param idPasoProceso Identificador del paso al que se desea adicionar la referencia
     * @param referencia Referencia a ser adicionada
     */
    public void addReferencia(String idPasoProceso, Referencia referencia) {
        var paso = findOrThrow(idPasoProceso);
        paso.getReferencias().add(referencia);
    }

    /**
     * Permite remover una referencia de un paso
     * @param idPasoProceso Identificador del paso del que se desea remover la referencia
     * @param referencia Referencia que se desea remover.
     */
    public void removeReferencia(String idPasoProceso, Referencia referencia) {
        var paso = findOrThrow(idPasoProceso);
        paso.getReferencias().remove(referencia);
    }

    /**
     * Permite obtener un paso basado en su posición dentro del proceso
     * @param orden Posición que ocupa un paso dentro del proceso
     * @return El paso correspondiente a la posición dada.
     */
    public PasoProceso findByOrden(int orden){
        return dataProvider.get().stream()
                .filter( pasoProceso -> pasoProceso.getOrden()==orden )
                .findFirst()
                .orElse(null);
    }
}
