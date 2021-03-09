package co.edu.utp.gia.sms.beans;

import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.annotation.SessionMap;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.Revision;

/**
 * Clase que define los elementos basicos de los Bean a ser usados en el
 * proyecto
 * 
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Julio Cesar Chavarro
 * @author Carlos Augusto Meneces
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 6 oct. 2020
 *
 */
public abstract class AbstractBean implements Serializable {

	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = -8685025655659669181L;
	@Inject
	private FacesContext facesContext;
	@Inject
	@SessionMap
	private Map<String, Object> sessionMap;

	@Inject
	@ManagedProperty("#{msg}")
	private ResourceBundle bundle;

	@PostConstruct
	public void init() {
//		if (registroInicialBean != null) {
//			revision = registroInicialBean.getRevision();
//		}
		inicializar();
	}

	public abstract void inicializar();

	protected void mostrarErrorGeneral(String mensaje) {
		mostrarErrorEspecifico(null, mensaje);
	}

	protected void mostrarErrorEspecifico(String idComponente, String mensaje) {
		mostrarMensaje(idComponente, mensaje, FacesMessage.SEVERITY_ERROR);
	}

	protected void mostrarAdvertenciaGeneral(String mensaje) {
		mostrarAdvertenciaEspecifico(null, mensaje);
	}

	protected void mostrarAdvertenciaEspecifico(String idComponente, String mensaje) {
		mostrarMensaje(idComponente, mensaje, FacesMessage.SEVERITY_WARN);
	}

	protected void mostrarMensajeGeneral(String mensaje) {
		mostrarMensajeEspecifico(null, mensaje);
	}

	protected void mostrarMensajeEspecifico(String idComponente, String mensaje) {
		mostrarMensaje(idComponente, mensaje, FacesMessage.SEVERITY_INFO);
	}

	protected void mostrarMensaje(String idComponente, String mensaje, Severity severidad) {
		FacesMessage facesMessage = new FacesMessage(severidad, mensaje, mensaje);
		getFacesContext().addMessage(idComponente, facesMessage);
	}

	private String getSeverityText(Severity severidad) {
		String key;
		if( FacesMessage.SEVERITY_ERROR.equals(severidad) ){
			key = MessageConstants.ERROR;
		} else if( FacesMessage.SEVERITY_WARN.equals(severidad) ){
			key = MessageConstants.WARNING;
		} else {
			key = MessageConstants.INFORMATION;
		}
		return getMessage(key);
	}

	protected FacesContext getFacesContext() {
		return facesContext;
	}

	protected void addToSession(String key, Object value) {
		getSessionMap().put(key, value);
	}

	protected Object getFromSession(String key) {
		return getSessionMap().get(key);
	}

	protected Object getAndRemoveFromSession(String key) {
		return getSessionMap().remove(key);
	}

	public String getMessage(String key){
		return bundle.getString(key);
	}
////////// ----- GET/SET ----- ////////////	

	/**
	 * Metodo que permite obtener el valor del atributo sessionMap
	 * 
	 * @return El valor del atributo sessionMap
	 */
	protected Map<String, Object> getSessionMap() {
		return sessionMap;
	}

}
