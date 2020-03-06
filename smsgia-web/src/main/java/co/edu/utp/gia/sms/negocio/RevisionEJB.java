package co.edu.utp.gia.sms.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.entidades.Topico;

@Stateless
public class RevisionEJB {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Permite registrar una revision
	 * 
	 * @param nombre      Nombre de la revision
	 * @param descripcion Descripcion de la revision
	 * @param objetivo    Objetivo de la revision
	 * @return Revision registrada
	 */
	public Revision registrar(String nombre, String descripcion, String objetivo) {
		Revision revision = new Revision(nombre, descripcion, objetivo);
		entityManager.persist(revision);
		return revision;
	}

	/**
	 * Permite obtener el listado de preguntas de una revision
	 * 
	 * @param id Identificador de la revision
	 * @return Listado de {@link Pregunta} de la {@link Revision} identificada con
	 *         el id dado
	 */
	public List<Pregunta> obtenerPreguntas(Integer id) {
		return entityManager.createNamedQuery(Pregunta.PREGUNTA_GET_ALL, Pregunta.class).setParameter("id", id)
				.getResultList();
	}

	/**
	 * Permite obtener una revision por medio del Identificador
	 * 
	 * @param idRevision Identificador de la revision que se desea obtener
	 * @return Retorna la revision que corresponde con el identificador
	 *         proporcionado
	 */
	public Revision obtener(Integer idRevision) {
		return entityManager.find(Revision.class, idRevision);
	}

	/**
	 * Permite obtener el listado de preguntas de una revision
	 * 
	 * @return Listado de las {@link Revision} registradas
	 */
	public List<Revision> obtenerTodas() {
		return entityManager.createNamedQuery(Revision.REVISION_GET_ALL, Revision.class).getResultList();
	}

	/**
	 * Permite actualizar una revision
	 * 
	 * @param revision Revision a ser actualizada
	 */
	public void actualizar(Revision revision) {
		actualizar(revision.getId(), revision.getNombre(), revision.getDescripcion(), revision.getObjetivo());
	}

	/**
	 * Permite actualizar una revision
	 * 
	 * @param id          Id de la {@link Pregunta} a ser actualizada
	 * @param nombre      Codigo de la pregunta a actualizar
	 * @param descripcion Descripcion de la pregunta a actulizar
	 * @param objetivo    Objetivo de la revision
	 */
	public void actualizar(Integer id, String nombre, String descripcion, String objetivo) {
		Revision revision = obtener(id);
		if (revision != null) {
			revision.setNombre(nombre);
			revision.setDescripcion(descripcion);
			revision.setObjetivo(objetivo);
		}
	}

	/**
	 * Permite eliminar una revision basado en su id
	 * 
	 * @param id Id de la revision a eliminar
	 */
	public void eliminar(Integer id) {
		Revision revision = obtener(id);
		if (revision != null) {
			entityManager.remove(revision);
		}
	}
	
	
	/**
	 * Permite obtener el listado de topicos de la revision
	 * 
	 * @param id Identificador de la revision
	 * @return Listado de {@link Topcios} de la {@link Revision} identificada con el
	 *         id dado
	 */
	public List<Topico> obtenerTopicos(Integer id) {
		return entityManager.createNamedQuery(Topico.TOPICO_REVISION_GET_ALL, Topico.class).setParameter("id", id)
				.getResultList();
	}

}
