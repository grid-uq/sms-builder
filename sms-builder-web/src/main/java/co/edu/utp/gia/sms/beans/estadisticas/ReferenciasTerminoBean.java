package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import lombok.Getter;
import lombok.Setter;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serial;

/**
 * Clase controladora de interfaz web que se encarga de presentar los datos estadísticos de los terminos usados para clasificar las referencias.
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
public class ReferenciasTerminoBean extends EstaditicaDatoDTOBaseBean {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    @Serial
    private static final long serialVersionUID = -7097161925478814637L;
    @Getter
    @Setter
    private String informacion;

    public void inicializar() {
        setEjeX(getMessage(MessageConstants.TERMINO));
        setEjeY("# "+getMessage(MessageConstants.SPS));
        setTitulo(getEjeY() + " - " + getEjeX());
        if (getRevision() != null) {
            onChangePregunta();
        }
    }


    public void onChangePregunta() {
        if ( "2".equals(informacion) ) {
            setDatos(getEstadisticaService().obtenerReferenciasSinonimo());
        } else {
            setDatos(getEstadisticaService().obtenerReferenciasTermino());
        }
        crearModelo();
    }


}
