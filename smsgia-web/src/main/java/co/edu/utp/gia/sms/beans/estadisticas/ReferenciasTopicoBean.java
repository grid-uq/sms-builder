package co.edu.utp.gia.sms.beans.estadisticas;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ReferenciasTopicoBean extends EstaditicaDatoDTOBaseBean {

	private String codigo;
	
	@PostConstruct
	public void inicializar() {
		setTitulo("Referencias x Topico");
		setEjeX("Topicos");
		setEjeY("# Referencias");
		if (getRevision() != null) {
//			setDatos(getEstadisticaEJB().obtenerReferenciasTopico(getRevision().getId()));
			onChangePregunta();
		}
	}
	
	public void onChangePregunta() {
		if( codigo != null ) {
			setDatos(getEstadisticaEJB().obtenerReferenciasTopico(getRevision().getId(),codigo));
		}else {
			setDatos(getEstadisticaEJB().obtenerReferenciasTopico(getRevision().getId()));
		}
		crearModelo();
	}

	/**
	 * Metodo que permite obtener el valor del atributo codigo
	 * @return El valor del atributo codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Metodo que permite asignar un valor al atributo codigo
	 * @param codigo Valor a ser asignado al atributo codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
}
