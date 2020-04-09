package co.edu.utp.gia.sms.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.utp.gia.sms.dtos.PreguntaDTO;
import co.edu.utp.gia.sms.entidades.Objetivo;
import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.query.Queries;

@Stateless
public class PreguntaEJB {
	@PersistenceContext
	private EntityManager entityManager;
	@Inject
	private ObjetivoEJB objetivoEJB;

	/**
	 * Permite registrar una pregunta
	 * 
	 * @param codigo      Codigo de la pregunta
	 * @param descripcion Descripcion de la pregunta
	 * @param listaIdObjetivos  Id de la {@link Revision} a la que se desea adicionar la
	 *                    pregunta
	 * @return La pregunta registrada
	 */
	public Pregunta registrar(String codigo, String descripcion, List<Integer> listaIdObjetivos) {
		Pregunta pregunta = null;
		List<Objetivo> objetivos = objetivoEJB.obtenerObjetivo(listaIdObjetivos);
		if (objetivos.size()>0) {
			pregunta = new Pregunta(codigo, descripcion, objetivos);
			entityManager.persist(pregunta);
		}
		return pregunta;
	}

	/**
	 * Permite obtener una Pregunta basado en su Id
	 * 
	 * @param idPregunta Identificador de la pregunta que se desea obtener
	 * @return La {@link Pregunta} que se corresponde con el Identificador dado
	 */
	public Pregunta obtener(Integer idPregunta) {
		return entityManager.find(Pregunta.class, idPregunta);
	}

	/**
	 * Permite obtener el listado de preguntas de una revision
	 * 
	 * @param id Identificador de la revision
	 * @return Listado de {@link Pregunta} de la {@link Revision} identificada con
	 *         el id dado
	 */
//	public List<Pregunta> obtenerPreguntas(Integer id) {
//		
//		List<Pregunta> preguntas = entityManager.createNamedQuery(Queries.PREGUNTA_GET_ALL, Pregunta.class).setParameter("id", id)
//				.getResultList();
//
//			
//		for (Pregunta pregunta : preguntas) {
//			pregunta.getTopicos();
//		}
//		
//		return preguntas;
//	}
	public List<PreguntaDTO> obtenerPreguntas(Integer id) {
		
		List<PreguntaDTO> preguntas = entityManager.createNamedQuery(Queries.PREGUNTA_GET_ALL, PreguntaDTO.class).setParameter("id", id)
				.getResultList();

			
		for (PreguntaDTO pregunta : preguntas) {
			pregunta.setTopicos(obtenerTopicos(pregunta.getId()));
			pregunta.setObjetivos(objetivoEJB.obtenerObjetivosPregunta(pregunta.getId()));
		}
		
		return preguntas;
	}
	/**
	 * Permite obtener el listado de topicos de una pregunta
	 * 
	 * @param id Identificador de la pregunta
	 * @return Listado de {@link Topcios} de la {@link Pregunta} identificada con el
	 *         id dado
	 */
	public List<Topico> obtenerTopicos(Integer id) {
		return entityManager.createNamedQuery(Queries.TOPICO_PREGUNTA_GET_ALL, Topico.class).setParameter("id", id)
				.getResultList();
	}

	/**
	 * Permite adicionar un topico a una pregunta
	 * 
	 * @param idPregunta  Identificador de la pregunta
	 * @param descripcion Descripcion del topico que se desea adicionar
	 */
	public Topico adicionarTopico(Integer idPregunta, String descripcion) {
		Pregunta pregunta = obtener(idPregunta);
		Topico topico = null;
		if (pregunta != null) {
			topico = new Topico(descripcion, pregunta);
			entityManager.persist(topico);
		}
		return topico;
	}

	/**
	 * Premite actualizar una {@link Pregunta}
	 * 
	 * @param pregunta Pregunta a ser actualizada
	 */
	public void actualizar(Pregunta pregunta) {
		actualizar(pregunta.getId(), pregunta.getCodigo(), pregunta.getDescripcion());
	}

	/**
	 * Permite actualizar una pregunta
	 * 
	 * @param id          Id de la {@link Pregunta} a ser actualizada
	 * @param codigo      Codigo de la pregunta a actualizar
	 * @param descripcion Descripcion de la pregunta a actulizar
	 */
	public void actualizar(Integer id, String codigo, String descripcion) {
		Pregunta pregunta = obtener(id);
		if (pregunta != null) {
			pregunta.setCodigo(codigo);
			pregunta.setDescripcion(descripcion);
		}
	}

	/**
	 * Permite eliminar una pregunta basado en su id
	 * 
	 * @param id Id de la pregunta a eliminar
	 */
	public void eliminar(Integer id) {
		Pregunta pregunta = obtener(id);
		if (pregunta != null) {
			entityManager.remove(pregunta);
		}
	}

	/**
	 * Permite eliminar un topico
	 * 
	 * @param id Identificador del topico a eliminar
	 */
	public void eliminarTopico(Integer id) {
		Topico topico = entityManager.find(Topico.class, id);
		if (topico != null) {
			entityManager.remove(topico);
		}
	}
}
