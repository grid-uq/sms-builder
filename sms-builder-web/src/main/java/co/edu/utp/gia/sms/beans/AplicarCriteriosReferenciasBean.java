package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.negocio.ProcesoService;
import co.edu.utp.gia.sms.negocio.ReferenciaService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import java.util.List;

/**
 * Clase controladora de interfaz web que se encarga de la aplicación de los criterios de evaluación.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@Named
@ViewScoped
@Log
public class AplicarCriteriosReferenciasBean extends GenericBean<ReferenciaDTO> {
    @Getter
    @Setter
    private List<ReferenciaDTO> referencias;
    @Inject
    private ReferenciaService referenciaService;
    @Inject
    private ProcesoService procesoService;

    public void inicializar() {
        referencias = referenciaService.findByPaso(getPasoAnterior().getId());

        referencias.forEach(
                referencia->referencia.setSeleccionada( getPasoActual().getReferencias().stream()
                        .anyMatch(referencia.getReferencia()::equals)
                )
        );
    }

    public void seleccionarReferencia(ReferenciaDTO referencia) {
        if (referencia.isSeleccionada()) {
            procesoService.addReferencia(getPasoActual().getId(),referencia.getId());
        } else {
            procesoService.removeReferencia(getPasoActual().getId(),referencia.getId());
        }
    }

}
