package co.edu.utp.gia.sms.beans.util;

import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.negocio.PreguntaService;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * Clase utilitaria encargada de realizar conversiones de preguntas (entidades) a elementos web
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@Named
@FacesConverter(value = "preguntaConverter",managed = true)
public class PreguntaConverter extends EntidadConverter<Pregunta> {

	@Inject
	private PreguntaService preguntaService;


	@Override
	protected Pregunta findById(String id) {
		return preguntaService.find(id).orElse(null);
	}
}
