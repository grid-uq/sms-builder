package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.seguridad.SeguridadBean;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.entidades.Usuario;
import co.edu.utp.gia.sms.negocio.RevisionEJB;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;


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
public class SeguridadBeanImpl extends SeguridadBean {
	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Variable que representa el {@link Usuario} que esta autenticado
	 */
	@Getter
	@Setter
	private Usuario usuario = null;
	@Inject
	private RevisionEJB revisionEJB;


	/**
	 * Realiza la verificación de los datos de autenticación proporcioandos por
	 * el {@link Usuario}
	 */
	@Override
	public void ingresar() {
		super.ingresar();
		if(isAutenticado()){
			List<Revision> revisiones = revisionEJB.obtenerTodas(getUsuario().getId());
			if( revisiones.size() > 0 ){
				addToSession("revision", revisiones.get(0));
			}
		}
	}
}
