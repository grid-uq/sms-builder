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
 * Clase controladora de interfaz web que se encarga de la gestión de citas.
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
public class ReferenciaAdicionarCitasBean extends GenericBean<ReferenciaDTO> {
    @Inject
    private ReferenciaService referenciaService;
    @Inject
    private RevisionService revisionService;
    @Getter @Setter
    private List<ReferenciaDTO> referencias;
    @Getter @Setter
    private List<Topico> topicos;

    public void inicializar() {
        referencias = referenciaService.findByPasoSeleccionado();
        topicos = revisionService.getTopicos();
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
            referenciaService.actualizarYear(referencia.getId(), referencia.getYear());
        } catch (Exception e) {
            mostrarErrorGeneral(e.getMessage());

        }
    }

    public void guardarCita(ReferenciaDTO referencia) {
        try {
            referenciaService.updateCita(referencia.getId(), referencia.getCitas());
        } catch (Exception e) {
            mostrarErrorGeneral(e.getMessage());
        }
    }


    public void guardarRelevancia(ReferenciaDTO referencia) {
        referenciaService.updateRelevancia(referencia.getId(), referencia.getRelevancia());
    }

    public void guardar() {
        for (ReferenciaDTO referencia : referencias) {
            referenciaService.cleanTopicos(referencia.getId());
            for (Topico topico : referencia.getTopicos()) {
                referenciaService.addTopico(referencia.getId(), topico.getId());
            }
        }
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
    }


}
