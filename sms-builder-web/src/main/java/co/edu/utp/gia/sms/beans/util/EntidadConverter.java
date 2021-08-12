package co.edu.utp.gia.sms.beans.util;

import co.edu.utp.gia.sms.entidades.Entidad;
import co.edu.utp.gia.sms.exceptions.ExceptionMessage;
import lombok.Getter;
import lombok.Setter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;

public abstract class EntidadConverter<E extends Entidad> implements Converter<E> {

//	@Inject
//	@ManagedProperty("#{msg}")
//	private ResourceBundle bundle;
	/**
	 * Instancia que perite obtener los mensajes de las excepciones generadas.
	 */
	@Inject
	@Getter @Setter
	protected ExceptionMessage exceptionMessage;

	protected abstract E findById(String id);

	@Override
	public E getAsObject(FacesContext facesContext, UIComponent componente, String id) {

		E entidad = null;
		if (id != null && !"".equals(id.trim())) {
			try {
				entidad = findById( id );
			} catch (Exception e) {
				throw new ConverterException(
						new FacesMessage(
								componente.getClientId() + ":" +
										exceptionMessage.getConversionError()),
						e
				);
			}
		}
		return entidad;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent componente, E entidad) {
		return  entidad != null ? entidad.getId().toString() : "";
	}

}
