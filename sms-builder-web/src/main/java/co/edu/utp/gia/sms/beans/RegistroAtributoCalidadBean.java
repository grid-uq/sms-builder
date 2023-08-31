package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.negocio.AbstractGenericService;
import co.edu.utp.gia.sms.negocio.AtributoCalidadService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
/**
 * Clase controladora de interfaz web que se encarga de la gestión de los atributos de calidad.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@Named
@ViewScoped
public class RegistroAtributoCalidadBean extends GenericBeanNew<AtributoCalidad,String> {
    @Getter @Setter
    private boolean objetivo;
    @Inject
    private AtributoCalidadService atributoCalidadService;

    public void inicializar() {
        setRecords(atributoCalidadService.get());
    }

    @Override
    protected AtributoCalidad newRecord() {
        return new AtributoCalidad("",false);
    }

    @Override
    protected AbstractGenericService<AtributoCalidad, String> getServices() {
        return atributoCalidadService;
    }

    public void validate(FacesContext facesContext,
                  UIComponent component, java.lang.Object object){
        validateUnique(facesContext, component, object, atributoCalidad -> atributoCalidad.getDescripcion().equals(object.toString()) );
    }
}
