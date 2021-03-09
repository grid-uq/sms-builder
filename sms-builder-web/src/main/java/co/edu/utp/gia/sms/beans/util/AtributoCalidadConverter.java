package co.edu.utp.gia.sms.beans.util;

import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.negocio.AtributoCalidadEJB;

import java.util.ResourceBundle;

@FacesConverter("atributoCalidadConverter")
public class AtributoCalidadConverter implements Converter<AtributoCalidad> {

	@Inject
	private AtributoCalidadEJB atributoCalidadEJB;
	@Inject
	@ManagedProperty("#{msg}")
	private ResourceBundle bundle;

	@Override
	public AtributoCalidad getAsObject(FacesContext facesContext, UIComponent componente, String id) {

		AtributoCalidad atributoCalidad = null;
		if (id != null && !"".equals(id.trim())) {
			try {
				atributoCalidad = atributoCalidadEJB.obtener(Integer.parseInt(id));
			} catch (Exception e) {
				e.printStackTrace();
				throw new ConverterException(
						new FacesMessage(
								componente.getClientId() + ":" +
										bundle.getString(MessageConstants.ERROR_CONVERSION_VALOR))
				);
			}
		}
		return atributoCalidad;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent componente, AtributoCalidad atributoCalidad) {
		return  atributoCalidad != null ? atributoCalidad.getId().toString() : "";
	}

}
