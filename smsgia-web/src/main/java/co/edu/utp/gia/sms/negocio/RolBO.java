package co.edu.utp.gia.sms.negocio;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import co.edu.utp.gia.sms.entidades.Rol;

/**
 * Clase de negocio encargada de implementar las funciones correspondientes a la
 * gestion del {@link Rol}.
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
public class RolBO extends AbstractEJB<Rol, Integer>{

	/**
	 * Instancia del entityManager
	 */
	@Inject
	private EntityManager entityManager;

	public RolBO() {
		super(Rol.class);
	}


	/**
	 * Permite obtener un listado con todos los {@link Rol}s registrados en
	 * el sistema
	 * 
	 * @return {@link List} de {@link Rol}, con todos los {@link Rol}
	 *         registrados en el sistema
	 */
	public List<Rol> listar() {
		return entityManager.createNamedQuery(Rol.GET_ALL, Rol.class).getResultList();
	}

}
