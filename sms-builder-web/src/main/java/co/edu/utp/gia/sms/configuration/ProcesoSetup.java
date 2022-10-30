package co.edu.utp.gia.sms.configuration;

import co.edu.utp.gia.sms.entidades.PasoProceso;
import co.edu.utp.gia.sms.negocio.PasoEJB;
import co.edu.utp.gia.sms.negocio.ProcesoEJB;

import javax.inject.Inject;
import java.util.List;
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
    private ProcesoEJB procesoEJB;
    @Inject
    private PasoEJB pasoEJB;

    @Override
    public void setup() {
        String[] keys = {
                "etiquetaMenuReferenciasImportarBD",
                "etiquetaMenuReferenciasImportarID",
                "etiquetaMenuReferenciasDuplicadas",
                "etiquetaMenuReferenciasSeleccionar",
                "etiquetaMenuReferenciasImportarBN",
                "etiquetaMenuReferenciasSeleccionar",
                "etiquetaMenuReferenciasSeleccionadas"
        };
        List<PasoProceso> pasos = procesoEJB.listar(1);
        if( pasos.size() == 0 ) {
            for (String key : keys) {
                procesoEJB.registrar(pasoEJB.findByName(key).getId(), 1);
            }
        }
    }
}
