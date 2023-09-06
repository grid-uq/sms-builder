package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.seguridad.SeguridadBean;
import co.edu.utp.gia.sms.entidades.Usuario;
import co.edu.utp.gia.sms.negocio.RevisionService;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.extern.java.Log;

import java.io.IOException;


/**
 * Implementación de {@link SeguridadBean} usado para la gestion de seguridad del
 * sistema
 * 
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 23/11/2015
 *
 */
@Named("seguridadBean")
@SessionScoped
@Log
public class SeguridadBeanImpl extends SeguridadBean {
	/**
	 * Variable que representa el {@link Usuario} que esta autenticado
	 */
	@Getter
	@Setter
	private Usuario usuario = null;
	@Inject
	private RevisionService revisionService;

	/**
	 * Realiza la verificación de los datos de autenticación proporcioandos por
	 * el {@link Usuario}
	 */
	@Override
	public void ingresar() {
		super.ingresar();
		if(isAutenticado()){
			addToSession("revision", revisionService.get());
		}
		var url = revisionService.get().getPasoActual().getPaso().recurso().getUrl();
		log.info( url );

		try {
			getFacesContext().getExternalContext().redirect(getFacesContext().getExternalContext().getApplicationContextPath() + url);
			getFacesContext().responseComplete();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
