package co.edu.utp.gia.sms.beans;

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
            referencias = referenciaEJB.obtenerTodas(getRevision().getId(), 3);
            topicos = revisionEJB.obtenerTopicos(getRevision().getId());
        }
    }

    public void incluirExcluirTopico(ReferenciaDTO referencia, Topico topico) {
        //TODO Pendiente por definir si es mejor que al momento de incluir/excluir de una vez se afecte la BD
        if (referencia.getTopicos().contains(topico)) {
            referencia.getTopicos().remove(topico);
            referenciaEJB.removerTopico(referencia.getId(), topico.getId());
            String t = "\n" + "Tópico: " + topico.getDescripcion();
            String nota = referencia.getNota().replace(t, "");
            referenciaEJB.actualizarNota(referencia.getId(), nota);
            referencia.setNota(nota);
        } else {
            referencia.getTopicos().add(topico);
            referenciaEJB.adicionarTopico(referencia.getId(), topico.getId());
            String nota = referencia.getNota() + "\n" + "Tópico: " + topico.getDescripcion();
            referenciaEJB.actualizarNota(referencia.getId(), nota);
            referencia.setNota(nota);

        }

    }

    public void guardar() {
        for (ReferenciaDTO referencia : referencias) {
            referenciaEJB.limpiarTopicos(referencia.getId());
            for (Topico topico : referencia.getTopicos()) {
                referenciaEJB.adicionarTopico(referencia.getId(), topico.getId());
            }
        }
        mostrarMensajeGeneral("Se guardaron los registro");
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
