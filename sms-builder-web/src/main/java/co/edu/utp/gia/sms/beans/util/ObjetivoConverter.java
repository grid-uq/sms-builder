package co.edu.utp.gia.sms.beans.util;

import co.edu.utp.gia.sms.entidades.Objetivo;
import co.edu.utp.gia.sms.negocio.ObjetivoEJB;

import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@FacesConverter(value = "objetivoConverter",managed = true)
public class ObjetivoConverter extends EntidadConverter<Objetivo> {

	@Inject
	private ObjetivoEJB objetivoEJB;


	@Override
	protected Objetivo findById(String id) {
		return objetivoEJB.obtener(Integer.parseInt(id));
	}
}
