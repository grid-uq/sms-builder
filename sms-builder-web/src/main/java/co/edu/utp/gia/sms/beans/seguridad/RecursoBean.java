package co.edu.utp.gia.sms.beans.seguridad;

import co.edu.utp.gia.sms.beans.AbstractBean;
import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.Recurso;
import co.edu.utp.gia.sms.negocio.RecursoService;
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
 * de gestion de la entidad {@link Recurso}
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
public class RecursoBean extends AbstractBean {
    /**
     * Variable que representa el atributo {@link Recurso} de la clase
     */
    @Getter
    @Setter
    private Recurso recurso = new Recurso();

    /**
     * Variable que representa el atributo recursos de la clase. Contiene la
     * lista de {@link Recurso} registradas en el sistema
     */
    @Getter
    @Setter
    private Collection<Recurso> recursos;

    /**
     * Variable que representa el atributo recursoEjb de la clase. Instancia
     * del objeto de negocio que permite la gestion de las {@link Recurso}
     */
    @Inject
    private RecursoService recursoService;

    /**
     * Metodo encargado de inicializar los datos de la clase
     */
    @PostConstruct
    public void inicializar() {
        recursos = recursoService.get();
    }

    /**
     * Metodo que permite registrar una {@link Recurso} en el sistema
     */
    public void registrar() {
        try {
            recursoService.save(recurso);
            recurso = new Recurso();
            recursos = recursoService.get();
            mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        } catch (Exception e) {
            mostrarErrorGeneral(e.getMessage());
        }
    }

    /**
     * Metodo que permite eliminar una {@link Recurso} del sistema
     *
     * @param recurso Instancia de la entidad {@link Recurso} a ser eliminada
     */
    public void eliminar(Recurso recurso) {
        try {
            recursoService.delete(recurso);
            recursos = recursoService.get();
            mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        } catch (Exception e) {
            mostrarErrorGeneral(e.getMessage());
        }
    }

    /**
     * Metodo encargado de responder al evento de edicion de la
     * {@link Recurso}
     *
     * @param event Evento generado al editar una {@link Recurso}
     */
    public void onRowEdit(RowEditEvent<Recurso> event) {
        Recurso recursoActual = event.getObject();
        try {
            recursoService.update(recursoActual);
            mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        } catch (Exception e) {
            mostrarErrorGeneral(e.getMessage());
            recursos = recursoService.get();
        }
    }

    /**
     * Metodo encargado de responder al evento de cancelación de la edicion de
     * {@link Recurso}
     *
     * @param event Evento generado al cancela la edición de un {@link Recurso}
     */
    public void onRowCancel(RowEditEvent event) {
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_CANCELADA));
    }
}
