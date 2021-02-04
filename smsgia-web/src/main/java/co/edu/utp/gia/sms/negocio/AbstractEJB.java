package co.edu.utp.gia.sms.negocio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.utp.gia.sms.entidades.Entidad;
import co.edu.utp.gia.sms.exceptions.LogicException;
import co.edu.utp.gia.sms.exceptions.TecnicalException;

/**
 * Clase abstracta que define los elementos de logica generales asociados al
 * CRUD de una entidad
 * 
 * 
 */
public abstract class AbstractEJB<E extends Entidad<TipoId>, TipoId> implements Serializable {

	/**
	 * Representa la clase a la que pertenece la entidad que esta manipulando el DAO
	 */
	private Class<E> entityClass;

	@PersistenceContext
	protected EntityManager entityManager;
	

	public AbstractEJB(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	public E registrar(E entidad) {
		try {
			if (obtener(entidad.getId()) != null) {
				throw new LogicException("Registro ya existente");
			}
			entityManager.persist(entidad);
			return entidad;
		} catch (Throwable t) {
			throw new TecnicalException(t);
		}
	}

	public void actualizar(E entidad) {
		try {
			if (obtener(entidad.getId()) == null) {
				throw new LogicException("Registro no existente");
			}
			entityManager.merge(entidad);
		} catch (Throwable t) {
			throw new TecnicalException(t);
		}
	}

	public void eliminar(E entidad) {
		try {
			if (!entityManager.contains(entidad) && obtener(entidad.getId()) == null) {
				throw new LogicException("Registro no existente");
			}
			entityManager.remove(entidad);
		} catch (Throwable t) {
			throw new TecnicalException(t);
		}
	}

	public void eliminar(TipoId id) {
		try {
			E entidad = obtener(id);
			if (entidad == null) {
				throw new LogicException("Registro no existente");
			}
			eliminar(entidad);
		} catch (Throwable t) {
			throw new TecnicalException(t);
		}
	}

	public E obtener(TipoId id) {
		try {
			return id != null ? entityManager.find(entityClass, id) : null;
		} catch (Throwable t) {
			throw new TecnicalException(t);
		}
	}

	public List<E> listar() {
		throw new LogicException("Operacion no soportada");
	}
	
	
//	protected abstract EntityManager getEntityManager();
//	public List<E> listar(String consulta) {
//		return listar(consulta, null);
//	}
//	
//	public List<E> listar(String consulta, Map<String, Object> filtro) {
//		try {
//			TypedQuery<E> query = entityManager.createNamedQuery(consulta, entityClass);
//			if (filtro != null) {
//				filtro.forEach((parametro, valor) -> {
//					query.setParameter(parametro, valor);
//				});
//			}
//			return query.getResultList();
//		} catch (Throwable t) {
//			throw new TecnicalException(t);
//		}
//	}

}
