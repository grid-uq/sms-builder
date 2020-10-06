package co.edu.utp.gia.sms.beans.estadisticas;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ReferenciasPreguntaBean extends EstaditicaDatoDTOBaseBean {

	
	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 5565581996776858682L;

	public void inicializar() {
		setTitulo("Referencias x Pregunta");
		setEjeX("Preguntas");
		setEjeY("# Referencias");
		if (getRevision() != null) {
			setDatos(getEstadisticaEJB().obtenerReferenciasPregunta(getRevision().getId()));
			crearModelo();
		}
	}

}
