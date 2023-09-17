package co.edu.utp.gia.sms.configuration;

import co.edu.utp.gia.sms.entidades.PasoProceso;
import co.edu.utp.gia.sms.negocio.PasoService;
import co.edu.utp.gia.sms.negocio.ProcesoService;
import jakarta.inject.Inject;

import java.util.Arrays;

/**
 * Clase encargada de realizar la configuración inicial del proceso del sms en la aplicación
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
public class ProcesoSetup implements SetupInterface{
    @Inject
    private ProcesoService procesoService;
    @Inject
    private PasoService pasoService;

    @Override
    public void setup() {
        String[] keys = {
                "etiquetaMenuRevisionEditar",
                "etiquetaMenuObjetivo",
                "etiquetaMenuPregunta",
                "etiquetaTermino",
                "etiquetaMenuAtributosCalidad",
                "etiquetaFuente",
                "etiquetaMenuCriterioSeleccion",
                "etiquetaMenuCadenaBusqueda",
                "etiquetaMenuReferenciasImportarBD",
                "etiquetaMenuReferenciasImportarID",
                "etiquetaMenuReferenciasDuplicadas",
                "etiquetaMenuReferenciasSeleccionar",
                "etiquetaMenuReferenciaNumeroCitas",
                "etiquetaMenuEvaluarReferencia",
                "etiquetaMenuReferenciasImportarBN",
                "etiquetaMenuReferenciasSeleccionar",
                "etiquetaMenuReferenciasSeleccionadas",
                "etiquetaMenuReferenciaNumeroCitas",
                "etiquetaMenuAnalizarReferencias",
                "etiquetaMenuEvaluarReferencia"
        };
        var pasos = procesoService.get();

        if(pasos.isEmpty()) {
            Arrays.stream(keys).map( pasoService::findByName ).map( PasoProceso::new ).forEach( procesoService::save );
//            for (String key : keys) {
//                procesoService.save(new PasoProceso()pasoService.findByName(key).getId());
//            }
        }
    }
}
