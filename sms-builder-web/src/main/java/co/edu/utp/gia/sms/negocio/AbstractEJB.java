package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.Entidad;
import co.edu.utp.gia.sms.exceptions.ExceptionMessage;
import co.edu.utp.gia.sms.exceptions.LogicException;
import co.edu.utp.gia.sms.exceptions.TecnicalException;
import lombok.Getter;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Clase abstracta que define los elementos de logica generales asociados al
 * CRUD de una entidad
 * 
 */
public abstract class AbstractEJB<E extends Entidad<TipoId>, TipoId> implements Serializable {

	/**
	 * Representa la clase a la que pertenece la entidad que esta manipulando el DAO
	 */
	private final Class<E> entityClass;

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Instancia que perite obtener los mensajes de las excepciones generadas.
	 */
	@Inject
	@Getter
	protected ExceptionMessage exceptionMessage;

	public AbstractEJB(){
		entityClass = findGenericType(0);
	}

	public AbstractEJB(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	private Class<E> findGenericType(int index) {
		Class<?> klass = getClass();
		while (klass.getSuperclass() != null && (!(klass.getGenericSuperclass() instanceof ParameterizedType) || !( ((ParameterizedType)klass
				.getGenericSuperclass()).getActualTypeArguments()[0] instanceof Class ) ) ) {
			klass = klass.getSuperclass();
		}
		return (Class<E>) ((ParameterizedType)klass.getGenericSuperclass()).getActualTypeArguments()[index];
	}

	public E registrar(E entidad) {
		try {
			if (obtener(entidad.getId()) != null) {
				throw new LogicException(entityClass.getName()+":"+exceptionMessage.getRegistroExistente());
			}
			entityManager.persist(entidad);
			entityManager.flush();
			entityManager.refresh(entidad);
			return entidad;
		} catch (Throwable t) {
			throw new TecnicalException(t);
		}
	}

	public void actualizar(E entidad) {
		try {
			obtenerOrThrow(entidad.getId());
			entityManager.merge(entidad);
		} catch (Throwable t) {
			throw new TecnicalException(t);
		}
	}

	public void eliminar(E entidad) {
		try {
			if (!entityManager.contains(entidad) && obtener(entidad.getId()) == null) {
				throw new LogicException(entityClass.getName()+":"+exceptionMessage.getRegistroNoEncontrado());
			}
			entityManager.remove(entidad);
		} catch (Throwable t) {
			throw new TecnicalException(t);
		}
	}

	public void eliminar(TipoId id) {
		try {
			eliminar(
					obtenerOrThrow(id)
			);
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

	public E obtenerOrThrow(TipoId id) {
		E registro = obtener(id);
		if (registro == null) {
			throw new LogicException(entityClass.getName()+":"+exceptionMessage.getRegistroNoEncontrado());
		}
		return registro;
	}

	public List<E> listar() {
		return entityManager.createQuery( listarCriteriaQuery() ).getResultList();
	}
	
	protected jakarta.persistence.TypedQuery<E> createQuery(String name){
		return entityManager.createNamedQuery(name,entityClass);
	}

	protected CriteriaQuery<E> listarCriteriaQuery(){
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<E> criteriaQuery = cb.createQuery(entityClass);
		Root<E> root = criteriaQuery.from(entityClass);
		return criteriaQuery.select(root);
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
