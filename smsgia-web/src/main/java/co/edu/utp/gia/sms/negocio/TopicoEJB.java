package co.edu.utp.gia.sms.negocio;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.query.Queries;

@Stateless
@LocalBean
public class TopicoEJB extends AbstractEJB<Topico, Integer> {

	@Inject
	private PreguntaEJB preguntaEJB;
	public TopicoEJB() {
		super(Topico.class);
	}

	/**
	 * Permite adicionar un topico a una pregunta
	 * 
	 * @param idPregunta  Identificador de la pregunta
	 * @param descripcion Descripcion del topico que se desea adicionar
	 */
	public Topico registrar(Integer idPregunta, String descripcion) {
		Pregunta pregunta = preguntaEJB.obtener(idPregunta);
		Topico topico = null;
		if (pregunta != null) {
			topico = new Topico(descripcion, pregunta);
			registrar(topico);
		}
		return topico;
	}

	/**
	 * Permite obtener el listado de topicos de una pregunta
	 * 
	 * @param id Identificador de la pregunta
	 * @return Listado de {@link Topico} de la {@link Pregunta} identificada con el
	 *         id dado
	 */
	public List<Topico> obtenerTopicos(Integer id) {
		return entityManager.createNamedQuery(Queries.TOPICO_PREGUNTA_GET_ALL, Topico.class).setParameter("id", id)
				.getResultList();
	}
	
}
