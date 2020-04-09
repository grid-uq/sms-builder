package co.edu.utp.gia.sms.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.utp.gia.sms.entidades.Objetivo;
import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.query.Queries;

@Stateless
public class ObjetivoEJB {
	@PersistenceContext
	private EntityManager entityManager;
	@Inject
	private RevisionEJB revisionEJB;

	/**
	 * Permite registrar una objetivo
	 * 
	 * @param codigo      Código del Objetivo
	 * @param descripcion Descripción del Objetivo
	 * @param idRevision  Id de la {@link Revision} a la que se desea adicionar el
	 *                    Objetivo
	 * @return El Objetivo registrado
	 */
	public Objetivo registrar(String codigo, String descripcion, Integer idRevision) {
		Objetivo objetivo = null;
		Revision revision = revisionEJB.obtener(idRevision);
		if (revision != null) {
			objetivo = new Objetivo(revision, codigo, descripcion);
			entityManager.persist(objetivo);
		}
		return objetivo;
	}

	/**
	 * Permite obtener un Objetivo basado en su Id
	 * 
	 * @param idObjetivo Identificador del Objetivo que se desea obtener
	 * @return El {@link Objetivo} que se corresponde con el Identificador indicado
	 */
	public Objetivo obtener(Integer idObjetivo) {
		return entityManager.find(Objetivo.class, idObjetivo);
	}

	/**
	 * Permite obtener el listado de Objetivos de una revision
	 * 
	 * @param id Identificador de la revision
	 * @return Listado de {@link Objetivo} de la {@link Revision} identificada con
	 *         el id indicado
	 */
	public List<Objetivo> obtenerObjetivo(Integer id) {
		return entityManager.createNamedQuery(Queries.OBJETIVO_GET_ALL, Objetivo.class).setParameter("id", id)
				.getResultList();
	}

	/**
	 * Premite actualizar un {@link Objetivo}
	 * 
	 * @param objetivo Objetivo a ser actualizado
	 */
	public void actualizar(Objetivo objetivo) {
		actualizar(objetivo.getId(), objetivo.getCodigo(), objetivo.getDescripcion());
	}

	/**
	 * Permite actualizar un Objetivo
	 * 
	 * @param id          Id del {@link Objetivo} a ser actualizado
	 * @param codigo      Código del {@link Objetivo} a ser actualizado
	 * @param descripcion Descripción del {@link Objetivo} a ser actualizado
	 */
	public void actualizar(Integer id, String codigo, String descripcion) {
		Objetivo objetivo = obtener(id);
		if (objetivo != null) {
			objetivo.setCodigo(codigo);
			objetivo.setDescripcion(descripcion);
		}
	}

	/**
	 * Permite eliminar un Objetivo basado en su id
	 * 
	 * @param id Id del Objetivo a eliminar
	 */
	public void eliminar(Integer id) {
		Objetivo objetivo = obtener(id);
		if (objetivo != null) {
			entityManager.remove(objetivo);
		}
	}

	/**
	 * Permite obtener el listado de preguntas de un objetivo
	 * 
	 * @param id Identificador del objetivo
	 * @return Listado de {@link Preguntas} del {@link Objetivo} identificado con el
	 *         id dado
	 */

	public List<Pregunta> obtenerPreguntas(Integer id) {
		return entityManager.createNamedQuery(Queries.PREGUNTA_GET_ALL, Pregunta.class).setParameter("id", id)
				.getResultList();
	}

	/**
	 * Permite adicionar una pregunta a un Objetivo
	 * 
	 * @param idObjetivo  Identificador de la pregunta
	 * @param codigo      Código de la pregunta que se desea adicionar
	 * @param descripcion Descripción del topico que se desea adicionar
	 */

	public Pregunta adicionarPregunta(Integer idObjetivo, String codigo, String descripcion) {
		Objetivo objetivo = obtener(idObjetivo);
		Pregunta pregunta = null;

		if (objetivo != null) {
			pregunta = new Pregunta(codigo, descripcion);
			entityManager.persist(pregunta);
		}
		return pregunta;
	}

	/**
	 * Permite eliminar una pregunta
	 * 
	 * @param id Identificador de la pregunta a eliminar
	 */

	public void eliminarPregunta(Integer id) {
		Pregunta pregunta = entityManager.find(Pregunta.class, id);
		if (pregunta != null) {
			entityManager.remove(pregunta);
		}
	}

	public List<Objetivo> obtenerObjetivo(List<Integer> listaIdObjetivos) {
		List<Objetivo> objetivos = new ArrayList<Objetivo>();
		for (Integer id : listaIdObjetivos) {
			objetivos.add(obtener(id));
		}
		
		return objetivos;
	}

	public List<Objetivo> obtenerObjetivosPregunta(Integer id) {
		return entityManager.createNamedQuery(Queries.PREGUNTA_OBJETIVO_GET_ALL, Objetivo.class).setParameter("id", id)
				.getResultList();
	}
}
