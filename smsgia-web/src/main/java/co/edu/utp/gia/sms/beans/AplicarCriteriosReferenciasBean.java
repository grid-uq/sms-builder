package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.importutil.FindReferenceCitation;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

@Named
@ViewScoped
@Log
public class AplicarCriteriosReferenciasBean extends GenericBean<ReferenciaDTO> {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 6757021713542648202L;
    @Getter
    @Setter
    private List<ReferenciaDTO> referencias;
    @Inject
    private ReferenciaEJB referenciaEJB;


    public void inicializar() {
        if (getRevision() != null) {
            referencias = referenciaEJB.obtenerTodas(getRevision().getId(), 1);
        }
    }

    public void actualizarNota(ReferenciaDTO referencia) {
        referenciaEJB.actualizarNota(referencia.getId(), referencia.getNota());
    }

    public void adicionarResumen(ReferenciaDTO referencia) {
        try {
            String tranduccion = FindReferenceCitation.getInstans().findTranslate(referencia.getResumen());
            referencia.setNota(referencia.getNota() + "\n" + tranduccion);
            actualizarNota(referencia);
        } catch (IOException e) {
            log.log(Level.WARNING, "No se pudo adicionar el resumen", e);
        }
    }

    public void seleccionarReferencia(ReferenciaDTO referencia) {
        referenciaEJB.actualizarFiltro(referencia.getId(), referencia.getFiltro());
    }

    public void actualizarRelevancia(ReferenciaDTO referencia) {
        referenciaEJB.actualizarRelevancia(referencia.getId(), referencia.getRelevancia());
    }


}
