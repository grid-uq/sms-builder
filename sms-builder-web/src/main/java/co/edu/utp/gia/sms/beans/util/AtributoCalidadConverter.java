package co.edu.utp.gia.sms.beans.util;

import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.negocio.AtributoCalidadEJB;

import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

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
