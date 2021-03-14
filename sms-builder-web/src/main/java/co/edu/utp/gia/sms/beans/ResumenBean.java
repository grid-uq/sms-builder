package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.negocio.EstadisticaEJB;
import co.edu.utp.gia.sms.negocio.RevisionEJB;
import lombok.Getter;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class ResumenBean extends AbstractRevisionBean{
    @Getter
    private long numeroReferencias;
    @Getter
    private long numeroReferenciasRepetidas;
    @Getter
    private long numeroReferenciasSeleccionadas;
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
            numeroReferenciasRepetidas = revisionEJB.totalReferenciasRepetidas(getRevision().getId());
            numeroReferenciasSeleccionadas = revisionEJB.totalReferenciasSeleccionadas(getRevision().getId());
            referenciasPorFuente = estadisticaEJB.obtenerReferenciasTipoFuente(getRevision().getId());
        }
    }
}
