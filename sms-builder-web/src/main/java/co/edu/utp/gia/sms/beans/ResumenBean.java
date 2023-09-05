package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.entidades.TipoFuente;
import co.edu.utp.gia.sms.negocio.EstadisticaService;
import co.edu.utp.gia.sms.negocio.RevisionService;
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
    private RevisionService revisionService;
    @Inject
    private EstadisticaService estadisticaService;
    @Override
    public void inicializar() {
        numeroReferencias = revisionService.totalReferencias();
        numeroReferenciasBaseDatos = revisionService.totalReferencias(TipoFuente.BASE_DATOS);
        numeroReferenciasRepetidas = revisionService.totalReferenciasRepetidas();
        numeroReferenciasSeleccionadas = revisionService.totalReferenciasSeleccionadas();
        referenciasPorFuente = estadisticaService.obtenerReferenciasTipoFuente();
        numeroReferenciasDescartadas = numeroReferencias - numeroReferenciasSeleccionadas - numeroReferenciasRepetidas;
        numeroReferenciasSeleccionadasBaseDatos = revisionService.totalReferenciasPaso(TipoFuente.BASE_DATOS);
        numeroReferenciasSeleccionadasBolaNieve = revisionService.totalReferenciasPaso(TipoFuente.BOLA_NIEVE);
        numeroReferenciasSeleccionadasInclusionDirecta = revisionService.totalReferenciasPaso(TipoFuente.INCLUSION_DIRECTA);
    }
}
