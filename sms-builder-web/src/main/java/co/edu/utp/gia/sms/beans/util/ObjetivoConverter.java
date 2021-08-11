package co.edu.utp.gia.sms.beans.util;

import co.edu.utp.gia.sms.entidades.Objetivo;
import co.edu.utp.gia.sms.negocio.ObjetivoEJB;

import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter("objetivoConverter")
public class ObjetivoConverter extends EntidadConverter<Objetivo> {

	@Inject
	private ObjetivoEJB objetivoEJB;


	@Override
	protected Objetivo findById(String id) {
		return objetivoEJB.obtener(Integer.parseInt(id));
	}
}
