package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.Termino;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.TerminoEJB;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class RegistroTerminoBean extends GenericBean<Termino> {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 4369004470790305574L;
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

    public void adicionarSinonimo(Integer id) {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        addToSession("idTermino", id);
        PrimeFaces.current().dialog().openDynamic("/revision/registrarSinonimo", options, null);
    }

    public void eliminarSinonimo(Termino termino,String sinonimo){
        terminoEJB.removerSinonimo(termino.getId(),sinonimo);
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        termino.removerSinonimo(sinonimo);
    }

    public void onTopicoCreado(SelectEvent<Topico> event) {
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        inicializar();
    }
}

