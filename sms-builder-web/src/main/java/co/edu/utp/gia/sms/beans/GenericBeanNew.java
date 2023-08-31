package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.entidades.Entidad;
import co.edu.utp.gia.sms.negocio.AbstractGenericService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.RowEditEvent;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * Clase controladora de interfaz web que define los procesos de interacción generales con el usuario.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
public abstract class GenericBeanNew<T extends Entidad<K>,K> extends AbstractRevisionBean {
    @Getter @Setter
    private T record;
    @Getter @Setter
    private Collection<T> records;
    public GenericBeanNew() {
        this.record = newRecord();
    }

    public void onRowEdit(RowEditEvent<T> event) {
        T objeto = event.getObject();
        try {
            actualizar(objeto);
            mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        } catch (Exception e) {
            mostrarErrorGeneral(e.getMessage());
        }
    }

    /**
     * Permite registrar un nuevo registro en el sistema
     *
     */
    public void registrar() {
        getServices().save(record);
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        record = newRecord();
    }

    protected abstract T newRecord();

    /**
     * Permite actualizar un registro
     *
     * @param record Registro a eliminar
     */
    public void actualizar(T record) {
        getServices().update(record);
    }

    /**
     * Permite eliminar un registro
     *
     * @param record Registro a eliminar
     */
    public void eliminar(T record) {
        getServices().delete(record);
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
    }

    public void onRowCancel(RowEditEvent<T> event) {
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_CANCELADA));
    }

    /**
     * Permite obtener el servicio encargado de la gestión de los registros
     * @return el servicio encargado de la gestión de los registros
     */
    protected abstract AbstractGenericService<T,K> getServices();

    public void validateUnique(FacesContext facesContext, UIComponent component, java.lang.Object object, Predicate<T> filter){
        boolean existe = getRecords().stream().anyMatch( filter );
        Object oldValue = ((UIInput) component).getValue();
        if (existe && (oldValue == null || !oldValue.toString().equals(object.toString()))) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error "+exceptionMessage.getRegistroExistente(), "Error "+exceptionMessage.getRegistroExistente());
            throw new ValidatorException(msg);
        }
    }
}
