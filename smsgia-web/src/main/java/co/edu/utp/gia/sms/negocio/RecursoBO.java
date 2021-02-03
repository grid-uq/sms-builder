package co.edu.utp.gia.sms.negocio;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

import co.edu.utp.gia.sms.entidades.Recurso;


/**
 * Clase de negocio encargada de implementar las funciones correspondientes a la
 * gestion del {@link Recurso}.
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
public class RecursoBO extends AbstractEJB<Recurso,Integer>{


	/**
	 * Instancia del entityManager
	 */
	@Inject
	private EntityManager entityManager;

	public RecursoBO() {
		super(Recurso.class);
	}

	/**
	 * Permite obtener un listado con todos los {@link Recurso}s registrados en
	 * el sistema
	 *
	 * @return {@link List} de {@link Recurso}, con todos los {@link Recurso}
	 *         registrados en el sistema
	 */
	public List<Recurso> listar() {
		return crearQuery(Recurso.GET_ALL).getResultList();
	}


	/**
	 * Metodo que permite buscar un {@link Recurso} basado en su url
	 * 
	 * @param url
	 *            Url del {@link Recurso} que se desea buscar
	 * @return {@link Recurso} correspondiente al url dado, o null si no existe
	 *         un {@link Recurso} con dicho id registrado en el sistema
	 */
	public Recurso buscarRecurso(String url) {
		return entityManager.createNamedQuery(Recurso.FIND_BY_URL, Recurso.class)
			.setParameter("url", url)
			.getResultList()
			.stream()
			.findFirst()
			.orElse(null);
	}

	/**
	 * Metodo que permite buscar los {@link Recurso} publicos
	 * 
	 * @return Lista de las URL de los {@link Recurso} registrados en el sistema
	 *         como públicos
	 */
	public List<String> buscarRecursosPublicos() {
		TypedQuery<String> query = entityManager.createNamedQuery(Recurso.FIND_BY_PUBLIC,String.class);
		query.setParameter("estado", true);
		return query.getResultList();
	}


	
	/**
	 * Método que permite crear un {@link TypedQuery} basado en el nombre de una
	 * {@link NamedQuery} cuyo resultado es de tipo {@link Recurso}
	 * 
	 * @param namedQuery
	 *            Nombre de la {@link NamedQuery}
	 * @return {@link TypedQuery} creado a partir del namedQuery
	 */
	private TypedQuery<Recurso> crearQuery(String namedQuery) {
		return entityManager.createNamedQuery(namedQuery, Recurso.class);
	}
	
}
