package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.entidades.Topico;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
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
@ApplicationScoped
public class TopicoService extends AbstractGenericService<Topico, String> {

	@Inject
	private PreguntaService preguntaService;
	public TopicoService() {
		super(DB.root.getProvider(Topico.class));
	}

	/**
	 * Permite adicionar un topico a una pregunta
	 * 
	 * @param idPregunta  Identificador de la pregunta
	 * @param descripcion Descripcion del topico que se desea adicionar
	 */
	public Topico save(String idPregunta, String descripcion) {
		Pregunta pregunta = preguntaService.findOrThrow(idPregunta);
		Topico topico = new Topico(descripcion, pregunta);
		this.save(topico);
		pregunta.getTopicos().add(topico);
		return topico;
	}
}
