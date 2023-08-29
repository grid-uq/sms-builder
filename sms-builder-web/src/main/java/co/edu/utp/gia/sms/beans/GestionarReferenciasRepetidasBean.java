package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.negocio.ReferenciaService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serial;
import java.util.List;

/**
 * Clase controladora de interfaz web que se encarga de la gestión de referencias repetidas.
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
public class GestionarReferenciasRepetidasBean extends GenericBean<ReferenciaDTO> {
    @Getter
    @Setter
    private List<ReferenciaDTO> referencias;
    @Inject
    private ReferenciaService referenciaService;

//    @Inject @ManagedProperty("#{param.paso}")
//    private Integer paso;

    public void inicializar() {
        referencias = referenciaService.findByPaso(getPasoAnterior().getId());
    }

    public void sugerir() {
        sugerirSeleccion();
    }

    private void sugerirSeleccion() {
        ReferenciaDTO anterior = null;
        for (ReferenciaDTO actual : referencias) {
            if (!(anterior == null || anterior.getNombre() == null || actual.getNombre() == null || !anterior.getNombre().equalsIgnoreCase(actual.getNombre()))) {
                actual.setDuplicada(true);
            }

            anterior = actual;
        }

    }

    public void guardar() {
        for (ReferenciaDTO referencia : referencias) {
            referenciaService.updateDuplicada(referencia.getId(), referencia.getDuplicada());
            if (!referencia.getDuplicada()) {
                referenciaService.avanzarReferecias(getPasoActual().getId());
//                if( referencia.getFiltro() < paso ) {
//                    referencia.setFiltro(paso);
//                    referenciaService.actualizarFiltro(referencia.getId(), paso);
//                }
            } else {
                referenciaService.avanzarReferecias(getPasoAnterior().getId());
//                if( referencia.getFiltro() >= paso ){
//                    referencia.setFiltro(paso-1);
//                    referenciaService.actualizarFiltro(referencia.getId(), paso-1);
//                }
            }
        }
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
    }


}
