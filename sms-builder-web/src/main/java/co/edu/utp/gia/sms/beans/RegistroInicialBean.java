package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.negocio.RevisionEJB;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;


@Named("registroInicialBean")
@ViewScoped
public class RegistroInicialBean extends AbstractBean {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = -6995163695300909108L;
    @Getter
    @Setter
    private Revision revision;
    @Getter
    @Setter
    private String nombre;
    @Getter
    @Setter
    private String objetivo;
    @Getter
    @Setter
    private String descripcion;
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private List<Revision> revisiones;
    @Inject
    private SeguridadBeanImpl seguridadBean;

    @Inject
    private RevisionEJB revisionEJB;

    @PostConstruct
    public void inicializar() {
        revision = (Revision) getFromSession("revision");
        if (seguridadBean.isAutenticado()) {
            revisiones = revisionEJB.obtenerTodas(seguridadBean.getUsuario().getId());
        }
    }

    public void registrar() {
        Revision r = revisionEJB.registrar(nombre, descripcion,seguridadBean.getUsuario().getId());
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        id = r.getId();
        revisiones.add(r);
        limpiarCampos();
        revision = r;

    }

    private void limpiarCampos() {
        nombre = "";
        descripcion = "";
    }

    public String gestionar(int id) {
        this.id = id;
        return "/revision/registroPregunta";
    }

    public void onRowEdit(RowEditEvent<Revision> event) {
        Revision revisionActual = event.getObject();
        revisionEJB.actualizar(revisionActual);
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
    }

    public void onRowCancel(RowEditEvent<Revision> event) {
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_CANCELADA));
    }

    public void onRowSelect(SelectEvent<Revision> event) {
		addToSession("revision", event.getObject());
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
    }

    /**
     * Permite eliminar una revision a través de su id
     *
     * @param revision Revisión a eliminar
     */
    public void eliminar(Revision revision) {
        revisionEJB.eliminar(revision.getId());
        revisiones.remove(revision);
        this.revision = null;
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
    }

    public String getTitutlo(){
        return revision == null ? "" : revision.getNombre();
    }

}
