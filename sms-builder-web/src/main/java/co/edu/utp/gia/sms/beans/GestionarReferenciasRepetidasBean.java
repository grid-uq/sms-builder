package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
@Log
public class GestionarReferenciasRepetidasBean extends GenericBean<ReferenciaDTO> {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 8275908838815243233L;
    @Getter
    @Setter
    private List<ReferenciaDTO> referencias;
    @Inject
    private ReferenciaEJB referenciaEJB;

    @Inject @ManagedProperty("#{param.paso}")
    private Integer paso;

    public void inicializar() {
        if (getRevision() != null) {
            referencias = referenciaEJB.obtenerTodas(getRevision().getId(), 0);
            //referencias = referenciaEJB.obtenerTodas(paso);
        }
    }

    public void sugerir() {
        sugerirSeleccion();
    }

    private void sugerirSeleccion() {
        ReferenciaDTO anterior = null;
        for (ReferenciaDTO actual : referencias) {
            if (anterior == null || anterior.getNombre() == null || actual.getNombre() == null || !anterior.getNombre().equalsIgnoreCase(actual.getNombre())) {
                actual.setSeleccionada(true);
            }
            anterior = actual;
        }

    }

    public void guardar() {
        for (ReferenciaDTO referencia : referencias) {
            referenciaEJB.actualizarFiltro(referencia.getId(), referencia.getFiltro());
        }
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
    }


}
