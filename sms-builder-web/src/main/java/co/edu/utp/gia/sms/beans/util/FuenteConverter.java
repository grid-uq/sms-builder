package co.edu.utp.gia.sms.beans.util;

import co.edu.utp.gia.sms.entidades.Fuente;
import co.edu.utp.gia.sms.negocio.FuenteEJB;

import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
/**
 * Clase utilitaria encargada de realizar conversiones de Fuentes a elementos web
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@Named
@FacesConverter(value = "fuenteConverter",managed = true)
public class FuenteConverter extends EntidadConverter<Fuente> {

	@Inject
	private FuenteEJB fuenteEJB;

	@Override
	protected Fuente findById(String id) {
		return fuenteEJB.obtener(Integer.parseInt(id));
	}
}
