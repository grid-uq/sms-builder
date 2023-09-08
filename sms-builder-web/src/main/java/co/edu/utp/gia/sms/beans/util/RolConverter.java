package co.edu.utp.gia.sms.beans.util;

import co.edu.utp.gia.sms.entidades.Rol;
import co.edu.utp.gia.sms.negocio.RolService;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * Clase utilitaria encargada de realizar conversiones de objetos (entidades) a elementos web
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@Named
@FacesConverter(value = "rolConverter",managed = true)
public class RolConverter extends EntidadConverter<Rol> {

	@Inject
	private RolService rolService;


	@Override
	protected Rol findById(String id) {
		return rolService.find(id).orElse(null);
	}
}
