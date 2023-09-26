package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.ReferenciaService;
import co.edu.utp.gia.sms.negocio.RevisionService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Clase controladora de interfaz web que se encarga del análisis inicial de las referencias.
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
public class AnalizarReferenciasBean extends GenericBean<ReferenciaDTO> {
    @Inject
    private ReferenciaService referenciaService;
    @Inject
    private RevisionService revisionService;
    @Getter
    @Setter
    private List<Topico> topicos;
    @Getter
    @Setter
    private List<ReferenciaDTO> referencias;

    public void inicializar() {
        referencias = referenciaService.findByPasoSeleccionado();
        topicos = revisionService.getTopicos();
    }

    public void incluirExcluirTopico(ReferenciaDTO referencia, Topico topico) {
        String topicosText = "\n" + getMessage(MessageConstants.TOPICOS) + ": " + topico.getDescripcion();
        String nota;
        if (referencia.getTopicos().contains(topico)) {
            referenciaService.removeTopico(referencia.getId(), topico.getId());
            nota = referencia.getNota().replace(topicosText, "");
        } else {
            referenciaService.addTopico(referencia.getId(), topico.getId());
            nota = referencia.getNota() == null ? topicosText : referencia.getNota() + topicosText;
        }
        referenciaService.updateNota(referencia.getId(), nota);
        referencia.setNota(nota);
    }

    public void asociacionAutomatica(){
        referenciaService.asociciacionAutomatica();
        referencias = referenciaService.findByPasoSeleccionado();
    }
}
