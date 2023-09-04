package co.edu.utp.gia.sms.beans.util;

import co.edu.utp.gia.sms.entidades.Entidad;
import co.edu.utp.gia.sms.exceptions.ExceptionMessage;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.Setter;
/**
 * Clase utilitaria encargada de realizar conversiones de Entidades a elementos web
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
public abstract class EntidadConverter<E extends Entidad> implements Converter<E> {
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
		if (id != null && !id.trim().isEmpty()) {
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
