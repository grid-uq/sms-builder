package co.edu.utp.gia.sms.beans.estadisticas;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ReferenciasTopicoBean extends EstaditicaDatoDTOBaseBean {

	@PostConstruct
	public void inicializar() {
		setTitulo("Referencias x Topico");
		setEjeX("Topicos");
		setEjeY("# Referencias");
		if (getRevision() != null) {
			setDatos(getEstadisticaEJB().obtenerReferenciasTopico(getRevision().getId()));
			crearModelo();
		}
	}

}
