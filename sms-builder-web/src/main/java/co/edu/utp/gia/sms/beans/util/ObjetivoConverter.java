package co.edu.utp.gia.sms.beans.util;

import co.edu.utp.gia.sms.entidades.Objetivo;
import co.edu.utp.gia.sms.negocio.ObjetivoService;

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
@FacesConverter(value = "objetivoConverter",managed = true)
public class ObjetivoConverter extends EntidadConverter<Objetivo> {

	@Inject
	private ObjetivoService objetivoService;


	@Override
	protected Objetivo findById(String id) {
		return objetivoService.find(id).orElse(null);
	}
}
