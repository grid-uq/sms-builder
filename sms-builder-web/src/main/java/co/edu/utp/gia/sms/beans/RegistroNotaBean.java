package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.Termino;
import co.edu.utp.gia.sms.negocio.TerminoEJB;
import lombok.Getter;
import lombok.Setter;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class RegistroNotaBean extends GenericBean<Termino> {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 9089427454534870601L;
    @Getter
    @Setter
    private String descripcion;
    @Getter
    @Setter
    private List<Termino> terminos;
    @Inject
    private TerminoEJB terminoEJB;


    public void inicializar() {
        if (getRevision() != null) {
            terminos = terminoEJB.obtenerTerminos(getRevision().getId());
        }
    }

    public void registrar() {
        Termino termino = terminoEJB.registrar(descripcion, getRevision().getId());
        terminos.add(termino);
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        descripcion = "";
    }

    @Override
    public void actualizar(Termino objeto) {
        terminoEJB.actualizar(objeto);
    }

    /**
     * Permite eliminar una termino
     *
     * @param termino termino a eliminar
     */
    public void eliminar(Termino termino) {
        terminoEJB.eliminar(termino.getId());
        terminos.remove(termino);
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
    }

}
