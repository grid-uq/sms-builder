package co.edu.utp.gia.sms.beans.estadisticas;

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
		setTitulo("Referencias por Tipo");
		setEjeX("Tipo de Referencia");
		setEjeY("# Referencias");
		if (getRevision() != null) {
			setDatos(getEstadisticaEJB().obtenerReferenciasTipo(getRevision().getId()));
			crearModelo();
		}
	}

}
