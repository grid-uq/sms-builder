package co.edu.utp.gia.sms.configuration;

import co.edu.utp.gia.sms.entidades.PasoProceso;
import co.edu.utp.gia.sms.negocio.PasoEJB;
import co.edu.utp.gia.sms.negocio.ProcesoEJB;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

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
