package co.edu.utp.gia.sms.beans.estadisticas;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ReferenciasCalidadYearBean extends EstaditicaDatoDTOBaseBean {

	
	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 1943642325865821264L;

	public void inicializar() {
		setTitulo("Promedio Calidad x Año");
		setEjeX("Años");
		setEjeY("Promedio Calidad");
		if (getRevision() != null) {
			setDatos( getEstadisticaEJB().obtenerReferenciasCalidadYear(getRevision().getId()) );
			crearModelo();
		}
	}
}
