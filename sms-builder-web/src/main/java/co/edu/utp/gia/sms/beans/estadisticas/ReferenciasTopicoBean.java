package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import lombok.Getter;
import lombok.Setter;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
/**
 * Clase controladora de interfaz web que se encarga de presentar los datos estadísticos de los tópicos.
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
public class ReferenciasTopicoBean extends EstaditicaDatoDTOBaseBean {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = -7097161925478814637L;
    @Getter
    @Setter
    private String codigo;

    public void inicializar() {
        setEjeX(getMessage(MessageConstants.TOPICOS));
        setEjeY("# "+getMessage(MessageConstants.SPS));
        setTitulo(getEjeY() + " - " + getEjeX());
        if (getRevision() != null) {
            onChangePregunta();
        }
    }

    public void onChangePregunta() {
        if (codigo != null) {
            setDatos(getEstadisticaEJB().obtenerReferenciasTopico(getRevision().getId(), codigo));
        } else {
            setDatos(getEstadisticaEJB().obtenerReferenciasTopico(getRevision().getId()));
        }
        crearModelo();
    }


}
