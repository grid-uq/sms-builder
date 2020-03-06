package co.edu.utp.gia.sms.beans.estadisticas;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ReferenciasCalidadYearBean extends EstaditicaDatoDTOBaseBean {

	@PostConstruct
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
