package co.edu.utp.gia.sms.negocio;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


import co.edu.utp.gia.sms.entidades.EstadoUsuario;
import co.edu.utp.gia.sms.entidades.Rol;
import co.edu.utp.gia.sms.entidades.Usuario;
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
public class UsuarioBO extends AbstractEJB<Usuario,Integer>{



	public UsuarioBO () { super(Usuario.class); }

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
		rolUsuario.setId(3);
		return Arrays.asList(rolUsuario);
	}

	/**
	 * Metodo que permite borrar un {@link Usuario}
	 * 
	 * @param usuario
	 *            Usuario a ser borrado
	 */
	public void eliminar(Usuario usuario) {
		if (usuario == null) {
			throw new LogicException("Faltan dados para completar la operación");
		}
		usuario = obtener(usuario.getId());
		if (!entityManager.contains(usuario) && usuario == null) {
			throw new LogicException("Registro no existente");
		}
		usuario.setEstado(EstadoUsuario.BLOQUEADO);
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
		return crearQuery(Usuario.GET_ALL).getResultList();
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
		TypedQuery<Usuario> query = entityManager.createNamedQuery(Usuario.AUTENTICAR, Usuario.class)
		query.setParameter("nombreUsuario", nombreUsuario);
		List<Usuario> resultado = query.getResultList();

		Usuario usuario = resultado.size() > 0 ? resultado.get(0) : null;
		if (usuario == null) {
			throw new LogicException("Error de autenticación");
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



	/**
	 * Método que permite crear un {@link TypedQuery} basado en el nombre de una
	 * {@link NamedQuery} cuyo resultado es de tipo {@link Usuario}
	 * 
	 * @param namedQuery
	 *            Nombre de la {@link NamedQuery}
	 * @return {@link TypedQuery} creado a partir del namedQuery
	 */
	private TypedQuery<Usuario> crearQuery(String namedQuery) {
		return entityManager.createNamedQuery(namedQuery, Usuario.class);
	}

}
