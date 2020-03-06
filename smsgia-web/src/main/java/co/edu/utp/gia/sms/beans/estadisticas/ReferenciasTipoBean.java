package co.edu.utp.gia.sms.beans.estadisticas;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ReferenciasTipoBean extends EstaditicaDatoDTOBaseBean {

	@PostConstruct
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
