package co.edu.utp.gia.sms.configuration;

import co.edu.utp.gia.sms.entidades.Paso;
import co.edu.utp.gia.sms.negocio.PasoEJB;
import co.edu.utp.gia.sms.negocio.RecursoEJB;

import javax.inject.Inject;
/**
 * Clase encargada de realizar la configuración inicial de los pasos del proceso en la aplicación
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
public class PasosSetup implements SetupInterface {
    @Inject
    private RecursoEJB recursoEJB;

    @Inject
    private PasoEJB pasoEJB;

    @Override
    public void setup() {
        String[] recursos = {
                "/revision/importarReferencias.xhtml",
                "/revision/importarReferenciasBaseDatos.xhtml",
                "/revision/importarReferenciasManual.xhtml",
                "/revision/importarReferenciasBolaNieve.xhtml",
                "/revision/gestionarReferenciasRepetidas.xhtml",
                "/revision/aplicarCriterios.xhtml",
                "/revision/resumenReferenciasSeleccionadas.xhtml"
        };
        String[] keys = {
                "etiquetaMenuReferenciasImportar",
                "etiquetaMenuReferenciasImportarBD",
                "etiquetaMenuReferenciasImportarID",
                "etiquetaMenuReferenciasImportarBN",
                "etiquetaMenuReferenciasDuplicadas",
                "etiquetaMenuReferenciasSeleccionar",
                "etiquetaMenuReferenciasSeleccionadas"
        };

        for (var i = 0; i < recursos.length; i++) {
            if( pasoEJB.findByName(keys[i]) == null ) {
                pasoEJB.registrar(new Paso(keys[i], recursoEJB.buscarRecurso(recursos[i])));
            }
        }
    }
}
