package co.edu.utp.gia.sms.beans.util;

import co.edu.utp.gia.sms.entidades.Paso;
import co.edu.utp.gia.sms.negocio.PasoService;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * Clase utilitaria encargada de realizar conversiones de Pasos a elementos web
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 31/08/2023
 */
@Named
@FacesConverter(value = "pasoConverter",managed = true)
public class PasoConverter extends EntidadConverter<Paso> {

	@Inject
	private PasoService pasoService;

	@Override
	protected Paso findById(String id) {
		return pasoService.find(id).orElse(null);
	}
}
