package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import lombok.Getter;

import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.annotation.SessionMap;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.application.FacesMessage.Severity;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;

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
	@Getter
	private Map<String, Object> sessionMap;


	@Inject
	@ManagedProperty("#{msg}")
	private ResourceBundle bundle;

	protected void preInicializar(){}
	protected void postInicializar(){}

	@PostConstruct
	public void init() {
		preInicializar();
		inicializar();
		postInicializar();
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
		if( facesContext == null ){
			facesContext = FacesContext.getCurrentInstance();
		}
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

	protected Object getParameterRequest(String key) {
		return getFacesContext().getExternalContext().getRequestParameterMap().get(key);
	}

	public String getMessage(String key){
		return bundle.getString(key);
	}

}
