package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Objetivo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
/**
 * Clase de negocio encargada de implementar las funciones correspondientes a la
 * gestion del {@link Objetivo}.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
@ApplicationScoped
public class ObjetivoService extends AbstractGenericService<Objetivo, String> {
	@Inject
	private PreguntaService preguntaService;

	public ObjetivoService() {
		super(DB.root.getProvider(Objetivo.class));
	}

	/**
	 * Permite registrar una objetivo
	 * 
	 * @param codigo      Código del Objetivo
	 * @param descripcion Descripción del Objetivo
	 *
	 * @return El Objetivo registrado
	 */
	public Objetivo save(String codigo, String descripcion) {
		return save(new Objetivo( codigo, descripcion));
	}

	/**
	 * Permite obtener los objetivos relacionados a una pregunta
	 * @param id Id de la pregunta
	 * @return List< Objetivo > relacionados con la preguna
	 */
	public List<Objetivo> findByPregunta(String id) {
		return preguntaService.findOrThrow(id).getObjetivos();
	}

}
