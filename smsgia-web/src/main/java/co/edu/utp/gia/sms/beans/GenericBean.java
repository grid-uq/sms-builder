package co.edu.utp.gia.sms.beans;

import org.primefaces.event.RowEditEvent;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class GenericBean<T> extends AbstractRevisionBean {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 9060626480979863537L;


    public void onRowEdit(RowEditEvent<T> event) {
        T objeto = event.getObject();
        try {
            actualizar(objeto);
            mostrarMensajeGeneral("Registro actualizado");
        } catch (Exception e) {
            mostrarErrorGeneral(e.getMessage());
        }
    }

    public void actualizar(T objeto) {

    }

    public void onRowCancel(RowEditEvent<T> event) {
        FacesMessage msg = new FacesMessage("Edicion cancelada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }


}
