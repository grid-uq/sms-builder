package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.negocio.ProcesoService;
import co.edu.utp.gia.sms.negocio.ReferenciaService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

/**
 * Clase controladora de interfaz web que se encarga de la gestión de las referencias.
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
public class ReferenciaBean extends AbstractRevisionBean {
//    @Inject @ManagedProperty("#{param.all}")
    protected boolean todas;
    @Inject
    private ReferenciaService referenciaService;
    @Inject
    private ProcesoService procesoService;

    @Getter @Setter
    private Collection<ReferenciaDTO> records;

    public void inicializar() {
        todas = Boolean.parseBoolean( getFacesContext().getExternalContext().getRequestParameterMap().get("all") );
        if( todas ){
            setRecords(referenciaService.findAll());
        } else {
            setRecords(referenciaService.findByPaso(getPasoActual().getId()));
        }
    }

    /**
     * Permite eliminar un registro
     *
     * @param referencia Referencia a eliminar
     */
    public void eliminar(ReferenciaDTO referencia) {
        if(todas) {
            referenciaService.delete(referencia.getReferencia());
        }else {

            procesoService.removeReferencia(getPasoActual().getId(),referencia.getReferencia().getId());
        }
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        inicializar();
    }

    public void actualizarTags(ReferenciaDTO referencia) {
        referenciaService.updateTags(referencia.getId(),referencia.getTags());
    }

    public void actualizarNota(ReferenciaDTO referencia) {
        referenciaService.updateNota(referencia.getId(), referencia.getNota());
    }

    public void actualizarRelevancia(ReferenciaDTO referencia) {
        referenciaService.updateRelevancia(referencia.getId(), referencia.getRelevancia());
    }

    public void actualizarResumen(ReferenciaDTO referencia) {
        referenciaService.updateResumen(referencia.getId(), referencia.getResumen());
    }
}
