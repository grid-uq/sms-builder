package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.beans.util.MessageConstants;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
/**
 * Clase controladora de interfaz web que se encarga de presentar los datos estadísticos de los tipos de referencias.
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
public class ReferenciasTipoBean extends EstaditicaDatoDTOBaseBean {

	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 8942280676662337920L;

	public void inicializar() {
		setEjeX(getMessage(MessageConstants.TIPO_REFERENCIA));
		setEjeY("# "+getMessage(MessageConstants.SPS));
		setTitulo(getEjeY() + " - " + getEjeX());
		if (getRevision() != null) {
			setDatos(getEstadisticaEJB().obtenerReferenciasTipo(getRevision().getId()));
			crearModelo();
		}
	}

}
