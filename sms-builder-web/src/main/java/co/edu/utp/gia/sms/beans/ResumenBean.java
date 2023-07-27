package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.entidades.TipoFuente;
import co.edu.utp.gia.sms.negocio.EstadisticaEJB;
import co.edu.utp.gia.sms.negocio.RevisionEJB;
import lombok.Getter;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;
/**
 * Clase controladora de interfaz web que se encarga de presentar un resumen del proceso de SMS.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@Named
public class ResumenBean extends AbstractRevisionBean{
    @Getter
    private long numeroReferencias;
    @Getter
    private long numeroReferenciasBaseDatos;
    @Getter
    private long numeroReferenciasDescartadas;
    @Getter
    private long numeroReferenciasRepetidas;
    @Getter
    private long numeroReferenciasSeleccionadas;
    @Getter
    private long numeroReferenciasSeleccionadasBaseDatos;
    @Getter
    private long numeroReferenciasSeleccionadasBolaNieve;
    @Getter
    private long numeroReferenciasSeleccionadasInclusionDirecta;
    @Getter
    private List<DatoDTO> referenciasPorFuente;
    @Inject
    private RevisionEJB revisionEJB;
    @Inject
    private EstadisticaEJB estadisticaEJB;
    @Override
    public void inicializar() {
        if( getRevision() != null ) {
            numeroReferencias = revisionEJB.totalReferencias(getRevision().getId());
            numeroReferenciasBaseDatos = revisionEJB.totalReferencias(getRevision().getId(), TipoFuente.BASE_DATOS);
            numeroReferenciasRepetidas = revisionEJB.totalReferenciasRepetidas(getRevision().getId());
            numeroReferenciasSeleccionadas = revisionEJB.totalReferenciasSeleccionadas(getRevision().getPasoSeleccionado().getId());
            referenciasPorFuente = estadisticaEJB.obtenerReferenciasTipoFuente(getRevision().getPasoSeleccionado().getId());
            numeroReferenciasDescartadas = numeroReferencias - numeroReferenciasSeleccionadas - numeroReferenciasRepetidas;
            numeroReferenciasSeleccionadasBaseDatos = revisionEJB.totalReferenciasPaso(getRevision().getPasoSeleccionado().getId(), TipoFuente.BASE_DATOS);
            numeroReferenciasSeleccionadasBolaNieve = revisionEJB.totalReferenciasPaso(getRevision().getPasoSeleccionado().getId(), TipoFuente.BOLA_NIEVE);
            numeroReferenciasSeleccionadasInclusionDirecta = revisionEJB.totalReferenciasPaso(getRevision().getPasoSeleccionado().getId(), TipoFuente.INCLUSION_DIRECTA);
        }
    }
}
