package co.edu.utp.gia.sms.beans.seguridad;

import co.edu.utp.gia.sms.beans.AbstractBean;
import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.Rol;
import co.edu.utp.gia.sms.negocio.RolEJB;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;


/**
 * Clase de negocio encargada la interacción del usuario con las funcionalidades
 * de gestion de la entidad {@link Rol}
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 2015-12-02
 */
@Named
@ViewScoped
public class RolBean extends AbstractBean {
    /**
     * Variable que representa el atributo {@link Rol} de la clase
     */
    @Getter
    @Setter
    private Rol rol = new Rol();

    /**
     * Variable que representa el atributo rols de la clase. Contiene la
     * lista de {@link Rol} registradas en el sistema
     */
    @Getter
    @Setter
    private List<Rol> roles;

    /**
     * Variable que representa el atributo rolEjb de la clase. Instancia
     * del objeto de negocio que permite la gestion de las {@link Rol}
     */
    @Inject
    private RolEJB rolEJB;

    /**
     * Metodo encargado de inicializar los datos de la clase
     */
    @PostConstruct
    public void inicializar() {
        roles = rolEJB.listar();
    }

    /**
     * Metodo que permite registrar una {@link Rol} en el sistema
     */
    public void registrar() {
        try {
            rolEJB.registrar(rol);
            rol = new Rol();
            roles = rolEJB.listar();
            mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        } catch (Exception e) {
            mostrarErrorGeneral(e.getMessage());
        }
    }

    /**
     * Metodo que permite eliminar una {@link Rol} del sistema
     *
     * @param rol Instancia de la entidad {@link Rol} a ser eliminada
     */
    public void eliminar(Rol rol) {
        try {
            rolEJB.eliminar(rol);
            roles = rolEJB.listar();
            mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        } catch (Exception e) {
            mostrarErrorGeneral(e.getMessage());
        }
    }

    /**
     * Metodo encargado de responder al evento de edicion de la
     * {@link Rol}
     *
     * @param event Evento generado al editar una {@link Rol}
     */
    public void onRowEdit(RowEditEvent<Rol> event) {
        Rol rol = event.getObject();
        try {
            rolEJB.actualizar(rol);
            mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        } catch (Exception e) {
            mostrarErrorGeneral(e.getMessage());
            roles = rolEJB.listar();
        }
    }

    /**
     * Metodo encargado de responder al evento de cancelación de la edicion de
     * {@link Rol}
     *
     * @param event Evento generado al cancela la edición de un {@link Rol}
     */
    public void onRowCancel(RowEditEvent event) {
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_CANCELADA));
    }


}
