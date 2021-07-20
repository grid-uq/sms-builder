package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.beans.AbstractRevisionBean;
import co.edu.utp.gia.sms.dtos.PreguntaDTO;
import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.PreguntaEJB;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

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
    private PreguntaEJB preguntaEJB;

    @Getter
    @Setter
    private List<PreguntaDTO> preguntas;

    @PostConstruct
    public void inicializar() {
        if (getRevision() != null) {
            referencias = referenciaEJB.obtenerTodas(getRevision().getPasoSeleccionado().getId());
            preguntas = preguntaEJB.obtenerPreguntas(getRevision().getId());
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
