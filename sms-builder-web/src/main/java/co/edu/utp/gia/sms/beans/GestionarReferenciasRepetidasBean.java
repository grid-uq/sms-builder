package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
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

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 8275908838815243233L;
    @Getter
    @Setter
    private List<ReferenciaDTO> referencias;
    @Inject
    private ReferenciaEJB referenciaEJB;

//    @Inject @ManagedProperty("#{param.paso}")
//    private Integer paso;

    public void inicializar() {
        if (getRevision() != null) {
            //referencias = referenciaEJB.obtenerTodas(getRevision().getId(), 0);
            referencias = referenciaEJB.findByPaso(getPasoAnterior().getId());
        }
    }

    public void sugerir() {
        sugerirSeleccion();
    }

    private void sugerirSeleccion() {
        ReferenciaDTO anterior = null;
        for (ReferenciaDTO actual : referencias) {
            if( !(anterior == null || anterior.getNombre() == null || actual.getNombre() == null || !anterior.getNombre().equalsIgnoreCase(actual.getNombre())) ){
                actual.setDuplicada(true);
            }

            anterior = actual;
        }

    }

    public void guardar() {
        for (ReferenciaDTO referencia : referencias) {
            referenciaEJB.updateDuplicada(referencia.getId(),referencia.getDuplicada());
            if( !referencia.getDuplicada() ){
                referenciaEJB.avanzarReferecias( getPasoActual().getId() );
//                if( referencia.getFiltro() < paso ) {
//                    referencia.setFiltro(paso);
//                    referenciaEJB.actualizarFiltro(referencia.getId(), paso);
//                }
            } else {
                referenciaEJB.avanzarReferecias( getPasoAnterior().getId() );
//                if( referencia.getFiltro() >= paso ){
//                    referencia.setFiltro(paso-1);
//                    referenciaEJB.actualizarFiltro(referencia.getId(), paso-1);
//                }
            }
        }
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
    }


}
