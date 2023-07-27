package co.edu.utp.gia.sms.beans.util;

import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UISelectItems;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import java.util.Collections;
import java.util.List;

/**
 * Clase utilitaria usada para converción de objetos en item JSF y los items JSF
 * en objetos.
 * 
 * @author Adictos al trabajo <http://www.adictosaltrabajo.com>
 * @version 1.0
 *
 */
@FacesConverter("selectItemObjectConverter")
public class SelectItemObjectConvert implements Converter {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext
	 * , javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		final int index = Integer.parseInt(value);
		if (index == -1) {
			return null;
		}
		final List<?> objects = getItemsObjects(component);
		return objects.get(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext
	 * , javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		final List<?> objects = getItemsObjects(component);
		return String.valueOf(objects.indexOf(value));
	}

	/**
	 * Método que permite obtener el listado de opciones usadas al interior del
	 * componente de selección usado por el usuario
	 * 
	 * @param component
	 *            Componente de seleccion de donde se desea obtener el listado
	 *            de opciones
	 * @return Listado de opciones obtenido del componente de selección
	 */
	private List<?> getItemsObjects(UIComponent component) {
		List<?> objects = Collections.emptyList();
		for (UIComponent child : component.getChildren()) {
			if (child.getClass() == UISelectItems.class) {
				objects = (List<?>) ((UISelectItems) child).getValue();
			}
		}
		return objects;
	}
}
