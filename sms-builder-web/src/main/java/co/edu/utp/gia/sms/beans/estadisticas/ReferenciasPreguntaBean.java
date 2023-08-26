package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serial;

/**
 * Clase controladora de interfaz web que se encarga de presentar los datos estadísticos de las preguntas.
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
public class ReferenciasPreguntaBean extends EstaditicaDatoDTOBaseBean {

	
	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	@Serial
	private static final long serialVersionUID = 5565581996776858682L;

	public void inicializar() {
		setEjeX(getMessage(MessageConstants.PREGUNTAS));
		setEjeY("# "+getMessage(MessageConstants.SPS));
		setTitulo(getEjeY() + " - " + getEjeX());
		if (getRevision() != null) {
			setDatos(getEstadisticaService().obtenerReferenciasPregunta());
			crearModelo();
		}
	}

}
