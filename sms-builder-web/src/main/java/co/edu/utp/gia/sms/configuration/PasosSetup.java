package co.edu.utp.gia.sms.configuration;

import co.edu.utp.gia.sms.entidades.Paso;
import co.edu.utp.gia.sms.negocio.PasoService;
import co.edu.utp.gia.sms.negocio.RecursoService;

import jakarta.inject.Inject;

import java.util.List;

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
    private RecursoService recursoService;

    @Inject
    private PasoService pasoService;

    @Override
    public void setup() {
        String[] recursos = {
                "/revision/editarRevision.xhtml",
                "/objetivo/registro.xhtml",
                "/pregunta/registro.xhtml",
                "/termino/registro.xhtml",
                "/atributocalidad/registro.xhtml",
                "/fuente/registro.xhtml",
                "/criterioseleccion/registro.xhtml",
                "/cadenabusqueda/registro.xhtml",
                "/revision/importarReferencias.xhtml",
                "/revision/importarReferenciasBaseDatos.xhtml",
                "/revision/importarReferenciasManual.xhtml",
                "/revision/importarReferenciasBolaNieve.xhtml",
                "/revision/gestionarReferenciasRepetidas.xhtml",
                "/revision/aplicarCriterios.xhtml",
                "/revision/resumenReferenciasSeleccionadas.xhtml",
                "/referencia/adicionarCitas.xhtml",
                "/calidad/evaluarReferencias.xhtml",
                "/referencia/analizar.xhtml"
        };
        String[] keys = {
                "etiquetaMenuRevisionEditar",
                "etiquetaMenuObjetivo",
                "etiquetaMenuPregunta",
                "etiquetaTermino",
                "etiquetaMenuAtributosCalidad",
                "etiquetaFuente",
                "etiquetaMenuCriterioSeleccion",
                "etiquetaMenuCadenaBusqueda",
                "etiquetaMenuReferenciasImportar",
                "etiquetaMenuReferenciasImportarBD",
                "etiquetaMenuReferenciasImportarID",
                "etiquetaMenuReferenciasImportarBN",
                "etiquetaMenuReferenciasDuplicadas",
                "etiquetaMenuReferenciasSeleccionar",
                "etiquetaMenuReferenciasSeleccionadas",
                "etiquetaMenuReferenciaNumeroCitas",
                "etiquetaMenuEvaluarReferencia",
                "etiquetaMenuAnalizarReferencias"
        };

        var seleccionar = List.of("etiquetaMenuReferenciasDuplicadas","etiquetaMenuReferenciasSeleccionar");

        for (var i = 0; i < recursos.length; i++) {
            if( pasoService.findByName(keys[i]) == null ) {
                pasoService.save(new Paso(keys[i], recursoService.findByUrl(recursos[i]),seleccionar.contains(keys[i])));
            }
        }
    }
}
