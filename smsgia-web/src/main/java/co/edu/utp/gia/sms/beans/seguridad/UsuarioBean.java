package co.edu.utp.gia.sms.beans.seguridad;

import java.util.Arrays;
import java.util.List;

import javax.faces.annotation.ManagedProperty;
import javax.inject.Inject;

import co.edu.utp.gia.sms.beans.AbstractBean;
import co.edu.utp.gia.sms.entidades.EstadoUsuario;
import co.edu.utp.gia.sms.entidades.Usuario;
import co.edu.utp.gia.sms.negocio.UsuarioEJB;
import org.primefaces.event.RowEditEvent;

/**
 * Clase de negocio encargada la interacción del usuario con las funcionalidades
 * de gestion de los {@link Usuario}
 *
 * @author Christian A. Candela <christiancandela@grid.edu.co>
 * @author Luis E. Sepúlveda <lesepulveda@grid.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 2015-12-02
 *
 */

public abstract class UsuarioBean extends AbstractBean {
	/**
	 * Variable que representa el atributo usuarioAutenticado de la clase.
	 * Instancia del usuario autenticado en el sistema
	 */
	@Inject
	@ManagedProperty("#{seguridadBean.usuario}")
	private Usuario usuarioAutenticado;

	/**
	 * Variable que representa el atributo verificacionClave de la clase. Es
	 * usado como verificación de la clave dada por el usuario
	 */
	private String verificacionClave;
	/**
	 * Variable que representa el atributo nuevaClave de la clase. Es usado para
	 * el ingreso de la nueva clave cuando la misma va a ser actualizada por el
	 * usuario
	 */
	private String nuevaClave;
	/**
	 * Variable que representa el atributo claveActual de la clase. Es usada
	 * para verificar la clave actual cuando el usuario está actualizando sus
	 * datos
	 */
	private String claveActual;
	/**
	 * Variable que representa el atributo verificacionClaveEdit de la clase. Es
	 * usado como verificación de la clave dada por el usuario. Esta variable es
	 * usada cuando se esta editando el usuario;
	 */
	private String verificacionClaveEdit;

	/**
	 * Variable que representa el atributo usuarios de la clase. Este contiene
	 * la lista de usuarios registrados en el sistema.
	 */
	private List<Usuario> usuarios;

	/**
	 * Variable que representa el atributo usuarioBO de la clase
	 */
	@Inject
	private UsuarioEJB usuarioEJB;

	/**
	 * Variable que representa el atributo editId de la clase. Id de la
	 * {@link UsuarioBean} que será editada.
	 */
	private Integer editId;

	/**
	 * Variable que representa el listado de valores de tipo
	 * {@link EstadoUsuario}
	 */
	private List<EstadoUsuario> estados;

	/**
	 * Metodo encargado de inicializar los datos de la clase
	 */
	public void inicializar() {
		usuarios = usuarioEJB.listar();
		setUsuario( newUsuario() );
		estados = Arrays.asList(EstadoUsuario.values());
	}

	/**
	 * Método que crear una instacia de Usuario
	 * @return nueva instancia de Usuario
	 */
	public abstract Usuario newUsuario();
	/**
	 * Metodo que permite registrar un usuario en el sistema
	 */
	public String registrar() {
		try {
			usuarioEJB.registrar(getUsuario(), verificacionClave);
			setUsuario(newUsuario());
			verificacionClave = "";
			usuarios = usuarioEJB.listar();
			mostrarMensajeGeneral("Registro exitoso");
		} catch (Exception e) {
			mostrarErrorGeneral(e.getMessage());
		}
		return null;
	} 

	/**
	 * Metodo que permite eliminar un usuario del sistema
	 * 
	 * @param usuario
	 *            Usuario a ser eliminado
	 */
	public void eliminar(Usuario usuario) {
		try {
			usuarioEJB.eliminar(usuario);
			usuarios = usuarioEJB.listar();
			mostrarMensajeGeneral("Registro eliminado");
		} catch (Exception e) {
			mostrarErrorGeneral(e.getMessage());
		}
	}

	/**
	 * Metodo encargado de responder al evento de edicion de usuario
	 * 
	 * @param event
	 *            Evento generado al editar un usuario
	 */
	public void onRowEdit(RowEditEvent<Usuario> event) {
		Usuario usuario = event.getObject();
		actualizar(usuario);
	}

	/**
	 * Permite actualizar un usuario dado
	 * 
	 * @param usuario
	 *            Usuario a ser actualizado
	 * @return Retorna true en caso de que la actualización de datos haya sido exitosa, en caso contrario retorna false           
	 */
	private boolean actualizar(Usuario usuario) {
		boolean exito = false;
		try {
			usuarioEJB.actualizar(usuario, verificacionClaveEdit);
			exito = true;
			verificacionClaveEdit = "";
			// usuarios = usuarioEjb.buscarAll();
			mostrarMensajeGeneral("Registro actializado");
			
		} catch (Exception e) {
			mostrarErrorGeneral(e.getMessage());
			usuarios = usuarioEJB.listar();
		}
		return exito;
	}

	/**
	 * Metodo encargado de responder al evento de cancelación de la edicion de
	 * usuario
	 * 
	 * @param event
	 *            Evento generado al cancela la edición de un usuario
	 */
	public void onRowCancel(RowEditEvent<Usuario> event) {
		verificacionClaveEdit = "";
		mostrarMensajeGeneral("Edición cancelada");
	}

	/**
	 * Permite obtener el listado de usuarios registrados en el sistema
	 * 
	 * @return {@link List} de {@link Usuario} registrados en el sistema
	 */
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * Metodo que permite obtener el valor del atributo verificacionClave
	 * 
	 * @return El valor del atributo verificacionClave
	 */
	public String getVerificacionClave() {
		return verificacionClave;
	}

	/**
	 * Metodo que permite asignar un valor al atributo verificacionClave
	 * 
	 * @param verificacionClave
	 *            Valor a ser asignado al atributo verificacionClave
	 */
	public void setVerificacionClave(String verificacionClave) {
		this.verificacionClave = verificacionClave;
	}

	/**
	 * Metodo que permite obtener el valor del atributo usuario
	 * 
	 * @return El valor del atributo usuario
	 */
	public abstract Usuario getUsuario();

	/**
	 * Metodo que permite asignar un valor al atributo usuario
	 * 
	 * @param usuario
	 *            Valor a ser asignado al atributo usuario
	 */
	public abstract void setUsuario(Usuario usuario);

	/**
	 * Metodo que permite obtener el valor del atributo verificacionClaveEdit
	 * 
	 * @return El valor del atributo verificacionClaveEdit
	 */
	public String getVerificacionClaveEdit() {
		return verificacionClaveEdit;
	}

	/**
	 * Metodo que permite asignar un valor al atributo verificacionClaveEdit
	 * 
	 * @param verificacionClaveEdit
	 *            Valor a ser asignado al atributo verificacionClaveEdit
	 */
	public void setVerificacionClaveEdit(String verificacionClaveEdit) {
		this.verificacionClaveEdit = verificacionClaveEdit;
	}

	/**
	 * Metodo que permite asignar un valor al atributo usuarios
	 * 
	 * @param usuarios
	 *            Valor a ser asignado al atributo usuarios
	 */
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	/**
	 * Metodo que permite obtener el valor del atributo editId
	 * 
	 * @return El valor del atributo editId
	 */
	public Integer getEditId() {
		return editId;
	}

	/**
	 * Metodo que permite asignar un valor al atributo editId
	 * 
	 * @param editId
	 *            Valor a ser asignado al atributo editId
	 */

	public void setEditId(Integer editId) {
		this.editId = editId;
	}

	/**
	 * Metodo que guarda los cambios realizados a un {@link Usuario}
	 * 
	 * @param usuario
	 *            {@link Usuario} que se desea guardar
	 */

	public void guardar(Usuario usuario) {
		try {
			usuarioEJB.actualizar(usuario);
			setEditId(null);
			mostrarMensajeGeneral("Registro actualizado");

		} catch (Exception e) {
			mostrarErrorGeneral(e.getMessage());
		}
	}

	/**
	 * Método que perite la actualización de los datos del usuario
	 */
	public void actualizar() {
		if (!usuarioAutenticado.getClave().equals(claveActual)) {
			mostrarErrorGeneral("Error al actualizar la información");
		} else {
			String anterior = usuarioAutenticado.getClave();
			usuarioAutenticado.setClave(nuevaClave);
			if (! actualizar(usuarioAutenticado) ){
				usuarioAutenticado.setClave(anterior);
			}
		}

	}

	/**
	 * Permite obtener los posibles estados de un usuario
	 * 
	 * @return Arreglo de los posibles estados de una cuenta de usuario
	 */
	public List<EstadoUsuario> getEstados() {
		return estados;
	}

	/**
	 * Metodo que permite obtener el valor del atributo usuarioAutenticado
	 * 
	 * @return El valor del atributo usuarioAutenticado
	 */
	public Usuario getUsuarioAutenticado() {
		return usuarioAutenticado;
	}

	/**
	 * Metodo que permite asignar un valor al atributo usuarioAutenticado
	 * 
	 * @param usuarioAutenticado
	 *            Valor a ser asignado al atributo usuarioAutenticado
	 */
	public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
	}

	/**
	 * Metodo que permite obtener el valor del atributo nuevaClave
	 * 
	 * @return El valor del atributo nuevaClave
	 */
	public String getNuevaClave() {
		return nuevaClave;
	}

	/**
	 * Metodo que permite asignar un valor al atributo nuevaClave
	 * 
	 * @param nuevaClave
	 *            Valor a ser asignado al atributo nuevaClave
	 */
	public void setNuevaClave(String nuevaClave) {
		this.nuevaClave = nuevaClave;
	}

	/**
	 * Metodo que permite obtener el valor del atributo claveActual
	 * 
	 * @return El valor del atributo claveActual
	 */
	public String getClaveActual() {
		return claveActual;
	}

	/**
	 * Metodo que permite asignar un valor al atributo claveActual
	 * 
	 * @param claveActual
	 *            Valor a ser asignado al atributo claveActual
	 */
	public void setClaveActual(String claveActual) {
		this.claveActual = claveActual;
	}

}
