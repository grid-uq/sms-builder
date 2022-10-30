package co.edu.utp.gia.sms.importutil;

import co.edu.utp.gia.sms.entidades.Referencia;

import java.io.InputStream;
import java.util.List;
/**
 * Interfaz que representa de forma general un intérprete que dado un flujo de entrada lo convierte en referencias.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */

public interface ReferenceParse {
    List<Referencia> parse(InputStream input);
}
