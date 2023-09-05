package co.edu.utp.gia.sms.exceptions;

import co.edu.utp.gia.sms.util.message.GenericMessageFactory;
import lombok.Getter;

/**
 * Clase utilitaria encargada de construir los mensajes para las excepciones.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */

public class ExceptionMessageFactory extends GenericMessageFactory<ExceptionMessage> {

    @Getter
    private static final ExceptionMessageFactory instance = new ExceptionMessageFactory();

    private ExceptionMessageFactory() {
        super("/exception", ExceptionMessage.class);
    }

}
