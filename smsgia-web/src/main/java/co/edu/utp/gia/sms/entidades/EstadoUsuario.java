package co.edu.utp.gia.sms.entidades;

/**
 * Enumeración que representa los Estado de la cuenta de un usuario. Estos
 * estados pueden ser: BLOQUEADO, ACTIVO, RETIRADO
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version %I%, %G%
 * @since 1.0
 *
 */
public enum EstadoUsuario {
	/**
	 * Estado es asignado a los usuarios cuya cuenta ha sido bloqueada por
	 * multiples intentos fallidos de autenticación.
	 */
	BLOQUEADO,
	/**
	 * Estado por defecto asignado a todos los usuarios del sistema.
	 */
	ACTIVO,
	/**
	 * Estado asignado a los usuarios (empleados) que son retirados de la
	 * empresa.
	 */
	RETIRADO;
}
