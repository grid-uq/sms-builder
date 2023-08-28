package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.negocio.AtributoCalidadService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.Collection;
/**
 * Clase controladora de interfaz web que se encarga de la gestión de los atributos de calidad.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@Named
@ViewScoped
public class RegistroAtributoCalidadBean extends GenericBean<AtributoCalidad> {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    @Serial
    private static final long serialVersionUID = 1755642135191615078L;
    @Getter @Setter
    private String descripcion;
    @Getter @Setter
    private Collection<AtributoCalidad> atributosCalidad;
    @Getter @Setter
    private boolean objetivo;
    @Inject
    private AtributoCalidadService atributoCalidadService;

    public void inicializar() {
        if (getRevision() != null) {
            atributosCalidad = atributoCalidadService.get();
        }
    }

    public void registrar() {
        AtributoCalidad atributo = atributoCalidadService.save(descripcion,objetivo);
        atributosCalidad.add(atributo);
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        descripcion = "";
    }

    @Override
    public void actualizar(AtributoCalidad objeto) {
        atributoCalidadService.update(objeto);
    }


    /**
     * Permite eliminar una atributo de calidad
     *
     * @param atributoCalidad Atributo de calidad a eliminar
     */
    public void eliminar(AtributoCalidad atributoCalidad) {
        atributoCalidadService.delete(atributoCalidad.getId());
        atributosCalidad.remove(atributoCalidad);
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
    }

}
