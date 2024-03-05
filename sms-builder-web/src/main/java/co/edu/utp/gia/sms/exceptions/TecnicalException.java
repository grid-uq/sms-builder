package co.edu.utp.gia.sms.exceptions;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response.Status;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa las excepciones tecnicas del sistema. Es usada para
 * envolver errores de caracter tecnico y presentarlos de una manera adecuada.
 *
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 10 abr. 2020
 */
public class TecnicalException extends WebApplicationException {
    /**
     * Determina si la excepcion debe o no tener visibilidad hacia el usuario.
     */
    @Getter
    @Setter
    private boolean visible;

    /**
     * Metodo que permite inicializar los elementos de la clase
     * TecnicalException
     *
     * @param message Mensaje de error
     */
    public TecnicalException(String message) {
        super(message,Status.INTERNAL_SERVER_ERROR);
        setVisible(true);
    }

    /**
     * Metodo que permite inicializar los elementos de la clase
     * TecnicalException
     *
     * @param cause Causa del error
     */
    public TecnicalException(Throwable cause) {
        super(cause, Status.INTERNAL_SERVER_ERROR);
        setVisible(false);
    }

    /**
     * Metodo que permite inicializar los elementos de la clase
     * TecnicalException
     *
     * @param message Mensaje del error
     * @param cause   Causa del error
     */
    public TecnicalException(String message, Throwable cause) {
        super(message, cause,Status.INTERNAL_SERVER_ERROR);
        setVisible(true);
    }

    public TecnicalException(String message, Status status) {
        super(message, status);
        setVisible(true);
    }
}
