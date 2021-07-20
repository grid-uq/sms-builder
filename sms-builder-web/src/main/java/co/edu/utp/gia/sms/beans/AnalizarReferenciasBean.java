package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;
import co.edu.utp.gia.sms.negocio.RevisionEJB;
import lombok.Getter;
import lombok.Setter;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class AnalizarReferenciasBean extends GenericBean<ReferenciaDTO> {

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
    private List<Topico> topicos;
    @Getter
    @Setter
    private List<ReferenciaDTO> referencias;

    public void inicializar() {

        if (getRevision() != null) {
            //referencias = referenciaEJB.obtenerTodas(getRevision().getId(), 3);
            referencias = referenciaEJB.obtenerTodas(getRevision().getPasoSeleccionado().getId());
            topicos = revisionEJB.obtenerTopicos(getRevision().getId());
        }
    }

    public void incluirExcluirTopico(ReferenciaDTO referencia, Topico topico) {
        String topicosText = "\n" + getMessage(MessageConstants.TOPICOS)+": " + topico.getDescripcion();
        String nota;
        if (referencia.getTopicos().contains(topico)) {
            referencia.getTopicos().remove(topico);
            referenciaEJB.removerTopico(referencia.getId(), topico.getId());
            nota = referencia.getNota().replace(topicosText, "");
        } else {
            referencia.getTopicos().add(topico);
            referenciaEJB.adicionarTopico(referencia.getId(), topico.getId());
            nota = referencia.getNota() + topicosText;
        }
        referenciaEJB.actualizarNota(referencia.getId(), nota);
        referencia.setNota(nota);
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


    public void actualizarNota(ReferenciaDTO referencia) {
        referenciaEJB.actualizarNota(referencia.getId(), referencia.getNota());
    }

    public void actualizarRelevancia(ReferenciaDTO referencia) {
        referenciaEJB.actualizarRelevancia(referencia.getId(), referencia.getRelevancia());
    }

    public void actualizarResumen(ReferenciaDTO referencia) {
        referenciaEJB.actualizarResumen(referencia.getId(), referencia.getResumen());
    }

}
