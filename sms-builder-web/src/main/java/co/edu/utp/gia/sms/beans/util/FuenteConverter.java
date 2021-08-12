package co.edu.utp.gia.sms.beans.util;

import co.edu.utp.gia.sms.entidades.Fuente;
import co.edu.utp.gia.sms.entidades.Objetivo;
import co.edu.utp.gia.sms.negocio.FuenteEJB;
import co.edu.utp.gia.sms.negocio.ObjetivoEJB;

import javax.enterprise.inject.spi.CDI;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

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
