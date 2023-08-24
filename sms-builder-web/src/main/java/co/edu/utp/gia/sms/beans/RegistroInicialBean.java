package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.negocio.RevisionService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Collections;
import java.util.List;
/**
 * Clase controladora de interfaz web que se encarga del registro inicial.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
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
    private RevisionService revisionService;

    @PostConstruct
    public void inicializar() {
        revision = (Revision) getFromSession("revision");
        if (seguridadBean.isAutenticado()) {
            revisiones = Collections.emptyList();
        }
    }

    public void registrar() {
        Revision r = revisionService.save(nombre, descripcion);
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        id = 0;
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
        revisionService.save(revisionActual.getNombre(),revisionActual.getDescripcion());
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
        revisiones.remove(revision);
        this.revision = null;
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
    }

    public String getTitutlo(){
        return revision == null ? "" : revision.getNombre();
    }

}
