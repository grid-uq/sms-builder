package co.edu.utp.gia.sms.beans;

import lombok.extern.java.Log;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serial;

/**
 * Clase controladora de interfaz web que se encarga de la gestión de referencias.
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
@Log
public class RegistroReferenciasBean extends ImportarReferenciasBean{
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    @Serial
    private static final long serialVersionUID = 1107564281230780705L;

    public RegistroReferenciasBean() {
        super();
        setTipoFuente(null);
    }
}
