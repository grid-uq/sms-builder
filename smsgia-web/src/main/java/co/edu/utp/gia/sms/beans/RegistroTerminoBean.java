package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.entidades.Termino;
import co.edu.utp.gia.sms.negocio.TerminoEJB;
import lombok.Getter;
import lombok.Setter;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

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
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro Adicionado"));
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
        FacesMessage msg = new FacesMessage("Registro eliminado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}

