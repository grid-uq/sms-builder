package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.query.topico.TopicoFindAllByPregunta;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
/**
 * Clase de negocio encargada de implementar las funciones correspondientes a la
 * gestion del {@link Topico}.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
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
		return TopicoFindAllByPregunta.createQuery(entityManager,id).getResultList();
	}
	
}
