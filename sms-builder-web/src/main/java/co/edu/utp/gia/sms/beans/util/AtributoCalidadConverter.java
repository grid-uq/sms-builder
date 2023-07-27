package co.edu.utp.gia.sms.beans.util;

import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.negocio.AtributoCalidadEJB;

import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
/**
 * Clase utilitaria encargada de realizar conversiones de Atributos a elementos web
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@Named
@FacesConverter(value = "atributoCalidadConverter",managed = true)
public class AtributoCalidadConverter extends EntidadConverter<AtributoCalidad> {

	@Inject
	private AtributoCalidadEJB atributoCalidadEJB;

	@Override
	protected AtributoCalidad findById(String id) {
		return atributoCalidadEJB.obtener(Integer.parseInt(id));
	}
}
