package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.beans.util.MessageConstants;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ReferenciasTipoBean extends EstaditicaDatoDTOBaseBean {

	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 8942280676662337920L;

	public void inicializar() {
		setEjeX(getMessage(MessageConstants.TIPO_REFERENCIA));
		setEjeY("# "+getMessage(MessageConstants.REFERENCIA));
		setTitulo(getEjeY() + " - " + getEjeX());
		if (getRevision() != null) {
			setDatos(getEstadisticaEJB().obtenerReferenciasTipo(getRevision().getId()));
			crearModelo();
		}
	}

}
