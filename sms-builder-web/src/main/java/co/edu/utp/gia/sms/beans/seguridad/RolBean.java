package co.edu.utp.gia.sms.beans.seguridad;

import co.edu.utp.gia.sms.beans.AbstractBean;
import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.Rol;
import co.edu.utp.gia.sms.negocio.RolService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.RowEditEvent;

import java.util.Collection;


/**
 * Clase encargada la interacción del usuario con las funcionalidades
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
    private Collection<Rol> roles;

    /**
     * Variable que representa el atributo rolEjb de la clase. Instancia
     * del objeto de negocio que permite la gestion de las {@link Rol}
     */
    @Inject
    private RolService rolService;

    /**
     * Metodo encargado de inicializar los datos de la clase
     */
    @PostConstruct
    public void inicializar() {
        roles = rolService.get();
    }

    /**
     * Metodo que permite registrar una {@link Rol} en el sistema
     */
    public void registrar() {
        try {
            rolService.save(rol);
            rol = new Rol();
            roles = rolService.get();
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
            rolService.delete(rol);
            roles = rolService.get();
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
            rolService.update(rol);
            mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        } catch (Exception e) {
            mostrarErrorGeneral(e.getMessage());
            roles = rolService.get();
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
