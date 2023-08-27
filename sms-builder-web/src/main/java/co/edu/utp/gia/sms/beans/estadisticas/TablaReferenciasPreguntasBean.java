package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.beans.AbstractRevisionBean;
import co.edu.utp.gia.sms.dtos.PreguntaDTO;
import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.PreguntaService;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;
import lombok.Getter;
import lombok.Setter;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;

/**
 * Clase controladora de interfaz web que se encarga de presentar las tablas de las referencias por pregunta.
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
public class TablaReferenciasPreguntasBean extends AbstractRevisionBean {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = -8876888410139722110L;
    @Getter
    @Setter
    private List<ReferenciaDTO> referencias;
    @Inject
    private ReferenciaEJB referenciaEJB;
    @Inject
    private PreguntaService preguntaService;

    @Getter
    @Setter
    private List<PreguntaDTO> preguntas;

    @PostConstruct
    public void inicializar() {
        if (getRevision() != null) {
            referencias = referenciaEJB.findByPaso(getRevision().getPasoSeleccionado().getId());
            preguntas = preguntaService.get();
        }
    }


    public boolean tieneRalacion(ReferenciaDTO referencia, PreguntaDTO pregunta) {
        for (Topico topico : referencia.getTopicos()) {
            if (pregunta.getTopicos().contains(topico)) {
                return true;
            }
        }
        return false;
    }

}
