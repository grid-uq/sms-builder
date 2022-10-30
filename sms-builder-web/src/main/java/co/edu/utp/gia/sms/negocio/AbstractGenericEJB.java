package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.Entidad;
import co.edu.utp.gia.sms.exceptions.ExceptionMessage;
import co.edu.utp.gia.sms.exceptions.LogicException;
import co.edu.utp.gia.sms.exceptions.TecnicalException;
import lombok.Getter;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

/**
 * Clase abstracta que define los elementos de logica generales asociados al
 * CRUD de una entidad
 * 
 */
public abstract class AbstractGenericEJB<E extends Entidad<TipoId>, TipoId> implements Serializable {

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

	public AbstractGenericEJB(){
		entityClass = findGenericType(0);
	}

	public AbstractGenericEJB(Class<E> entityClass) {
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

	public E create(E entidad) {
		try {
			if (find(entidad.getId()).isPresent() ) {
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

	public void update(E entidad) {
		try {
			findOrThrow(entidad.getId());
			entityManager.merge(entidad);
		} catch (Throwable t) {
			throw new TecnicalException(t);
		}
	}

	public void delete(E entidad) {
		try {
			entityManager.remove(findOrThrow(entidad.getId()));
		} catch (Throwable t) {
			throw new TecnicalException(t);
		}
	}

	public void delete(TipoId id) {
		try {
			delete(	findOrThrow(id) );
		} catch (Throwable t) {
			throw new TecnicalException(t);
		}
	}

	public Optional<E> find(TipoId id) {
		try {
			return Optional.ofNullable(id != null ? entityManager.find(entityClass, id) : null);
		} catch (Throwable t) {
			throw new TecnicalException(t);
		}
	}

	public E findOrThrow(TipoId id) {
		return find(id).orElseThrow(
				()->new LogicException(entityClass.getName()+":"+exceptionMessage.getRegistroNoEncontrado()) );
	}

	public List<E> findAll() {
		return entityManager.createQuery( listarCriteriaQuery() ).getResultList();
	}
	
	protected javax.persistence.TypedQuery<E> createQuery(String name){
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
