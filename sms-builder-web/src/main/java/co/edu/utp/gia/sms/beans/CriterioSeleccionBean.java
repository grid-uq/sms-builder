package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.entidades.CriterioSeleccion;
import co.edu.utp.gia.sms.negocio.AbstractGenericService;
import co.edu.utp.gia.sms.negocio.CriterioSeleccionService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * Clase controladora de interfaz web que se encarga de la gestión de objetivo.
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
public class CriterioSeleccionBean extends GenericBeanNew<CriterioSeleccion,String> {
    @Inject
    private CriterioSeleccionService criterioSeleccionService;

    public void inicializar() {
        setRecords(criterioSeleccionService.get());
    }

    @Override
    protected CriterioSeleccion newRecord() {
        return new CriterioSeleccion();
    }

    @Override
    protected AbstractGenericService<CriterioSeleccion, String> getServices() {
        return criterioSeleccionService;
    }

    public void validate(FacesContext facesContext, UIComponent component, Object object){
        validateUnique(facesContext, component, object, record -> record.getNombre().equalsIgnoreCase(object.toString()));
    }
}
