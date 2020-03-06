package co.edu.utp.gia.sms.beans.estadisticas;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ReferenciasYearBean extends EstaditicaDatoDTOBaseBean {

	@PostConstruct
	public void inicializar() {
		setTitulo("Referencias por Año");
		setEjeX("Año");
		setEjeY("# Referencias");
		if (getRevision() != null) {
			setDatos(getEstadisticaEJB().obtenerReferenciasYear(getRevision().getId()));
			crearModelo();
		}
	}

}
