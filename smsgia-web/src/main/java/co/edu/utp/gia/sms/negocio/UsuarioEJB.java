package co.edu.utp.gia.sms.negocio;

import java.util.Arrays;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import co.edu.utp.gia.sms.entidades.EstadoUsuario;
import co.edu.utp.gia.sms.entidades.Rol;
import co.edu.utp.gia.sms.entidades.Usuario;
import co.edu.utp.gia.sms.exceptions.ExceptionMessage;
import co.edu.utp.gia.sms.exceptions.LogicException;

/**
 * Clase de negocio encargada de implementar las funciones correspondientes a la
 * gestion del {@link Usuario}.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 *
 */
@Stateless
@LocalBean
public class UsuarioEJB extends AbstractEJB<Usuario,Integer>{

	/**
	 * Instancia que perite obtener los mensajes de las excepciones generadas.
	 */
	@Inject
	private ExceptionMessage exceptionMessage;

	public UsuarioEJB() { super(Usuario.class); }

	/**
	 * Metodo que permite registrar un usuario
	 * 
	 * @param usuario
	 *            Usuario a ser registrado
	 * @param verificacionClave
	 *            Variable de verficacion de clave
	 */
	public void registrar(Usuario usuario, String verificacionClave) {
		if (usuario == null) {
			throw new LogicException("Faltan dados para completar la operación");
		}
		if (!usuario.getClave().equals(verificacionClave)) {
			throw new LogicException("La clave no coincide");
		}
		if (usuario.getRoles() == null) {
			usuario.setRoles(getRolesPorDefecto());
		}
		super.registrar(usuario);
	}

	/**
	 * Permite obtener el listado de {@link Rol}es por defecto a ser usados
	 * cuando un {@link Usuario} se registra
	 *
	 *
	 * @return El {@link List} de {@link Rol} a ser asiganados al usuario que se
	 *         registra en la aplicacion
	 */
	private static List<Rol> getRolesPorDefecto() {
		// TODO pendiente obtener el estado de una tabla de parametros de
		// configuracionen lugar de estar quemado
		Rol rolUsuario = new Rol();
		rolUsuario.setId(2);
		return Arrays.asList(rolUsuario);
	}

	/**
	 * Metodo que permite actualizar el {@link Usuario}
	 * 
	 * @param usuario
	 *            {@link Usuario} a ser actualizado
	 * @param verificacionClave
	 *            Variable de verficacion de clave
	 */
	public void actualizar(Usuario usuario, String verificacionClave) {
		if (usuario == null) {
			throw new LogicException("Faltan dados para completar la operación");
		}
		if (!usuario.getClave().equals(verificacionClave)) {
			throw new LogicException("La clave no coincide");
		}
		actualizar(usuario);
	}

	/**
	 * Permite obtener un listado con todos los {@link Usuario}s registrados en
	 * el sistema
	 * 
	 * @return {@link List} de {@link Usuario}, con todos los {@link Usuario}
	 *         registrados en el sistema
	 */
	public List<Usuario> listar() {
		return entityManager.createNamedQuery(Usuario.GET_ALL, Usuario.class).getResultList();
	}

	/**
	 * Metodo que permite verificar si los datos de autenticación dados (email y
	 * clave) corresponden con datos de un {@link Usuario} registrado en el
	 * sistema
	 *
	 * @param nombreUsuario
	 *            Email del usuario que se desea autenticar
	 * @param clave
	 *            Clave del usuario que se desea autenticar
	 * @return Si los datos de email y clave corresponden con un {@link Usuario}
	 *         valido, se retorna dicho {@link Usuario}, en caso contrario
	 *         retorna null
	 * @throws LogicException
	 *             Error de autenticacion En caso de
	 *             que no se encuentre el nombre de usuario proporcionado
	 */
	public Usuario autenticarUsuario(String nombreUsuario, String clave) {
		Usuario usuario = entityManager.createNamedQuery(Usuario.AUTENTICAR, Usuario.class)
				.setParameter("nombreUsuario", nombreUsuario)
				.getResultList()
				.stream()
				.findFirst()
				.orElse(null);

		if (usuario == null) {
			throw new LogicException(exceptionMessage.getLoginFailMessage());
		}
		if (!usuario.getClave().equals(clave)) {
			usuario.setIntentos(usuario.getIntentos() + 1);
			if (usuario.getIntentos() >= 3) {
				usuario.setEstado(EstadoUsuario.BLOQUEADO);
			}
			return null;
		}
		usuario.setIntentos(0);
		return usuario;
	}


}
