package co.edu.utp.gia.sms.beans;


import co.edu.utp.gia.sms.beans.seguridad.UsuarioBean;
import co.edu.utp.gia.sms.entidades.Usuario;

import javax.faces.view.ViewScoped;
import javax.inject.Named;


/**
 * Implementación de {@link UsuarioBean} usado para la gestion de usuarios del
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
@Named("usuarioBean")
@ViewScoped
public class UsuarioBeanImpl extends UsuarioBean {
	private Usuario usuario;

	/**
	 * Metodo que permite obtener el valor del atributo usuario
	 * 
	 * @return El valor del atributo usuario
	 */
	@Override
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * Metodo que permite asignar un valor al atributo usuario
	 * 
	 * @param usuario
	 *            Valor a ser asignado al atributo usuario
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.uniquindio.grid.common.bean.seguridad.UsuarioBean#newUsuario()
	 */
	@Override
	public Usuario newUsuario() {
		return new Usuario();
	}

}
