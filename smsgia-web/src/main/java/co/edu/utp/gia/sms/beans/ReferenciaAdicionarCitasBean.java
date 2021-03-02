package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.importutil.FindReferenceCitation;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;
import co.edu.utp.gia.sms.negocio.RevisionEJB;
import lombok.Getter;
import lombok.Setter;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;

@Named
@ViewScoped
public class ReferenciaAdicionarCitasBean extends GenericBean<ReferenciaDTO> {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 4009685061343184778L;
    @Inject
    private ReferenciaEJB referenciaEJB;

    @Inject
    private RevisionEJB revisionEJB;

    @Getter
    @Setter
    private List<ReferenciaDTO> referencias;

    @Getter
    @Setter
    private List<Topico> topicos;

    public void inicializar() {

        if (getRevision() != null) {
            referencias = referenciaEJB.obtenerTodas(getRevision().getId(), 3);
            topicos = revisionEJB.obtenerTopicos(getRevision().getId());
        }
    }

    public void incluirExcluirTopico(ReferenciaDTO referencia, Topico topico) {
        if (referencia.getTopicos().contains(topico)) {
            referencia.getTopicos().remove(topico);
        } else {
            referencia.getTopicos().add(topico);
        }
    }

    public void guardarYear(ReferenciaDTO referencia) {
        try {
            referenciaEJB.actualizarYear(referencia.getId(), referencia.getYear());
        } catch (Exception e) {
            mostrarErrorGeneral(e.getMessage());

        }
    }

    public void guardarCita(ReferenciaDTO referencia) {
        try {
            referenciaEJB.actualizarCita(referencia.getId(), referencia.getCitas());
        } catch (Exception e) {
            mostrarErrorGeneral(e.getMessage());
        }
    }


    public void guardarRelevancia(ReferenciaDTO referencia) {
        referenciaEJB.actualizarRelevancia(referencia.getId(), referencia.getRelevancia());
    }

    public void completarCita(ReferenciaDTO referencia) {
        try {
            FindReferenceCitation.getInstans().findCitation(referencia.getReferencia());
            guardarCita(referencia);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardar() {
        for (ReferenciaDTO referencia : referencias) {
            referenciaEJB.limpiarTopicos(referencia.getId());
            for (Topico topico : referencia.getTopicos()) {
                referenciaEJB.adicionarTopico(referencia.getId(), topico.getId());
            }
        }
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
    }


}
