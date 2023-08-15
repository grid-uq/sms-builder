package co.edu.utp.gia.sms.entidades;

import java.io.Serializable;
/**
 * Interfaz que modela de forma general una Entidad.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
public interface Entidad<TipoId> extends Serializable {
	TipoId getId();
}
