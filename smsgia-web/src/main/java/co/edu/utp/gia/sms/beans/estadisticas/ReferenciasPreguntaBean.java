package co.edu.utp.gia.sms.beans.estadisticas;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ReferenciasPreguntaBean extends EstaditicaDatoDTOBaseBean {

	@PostConstruct
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
