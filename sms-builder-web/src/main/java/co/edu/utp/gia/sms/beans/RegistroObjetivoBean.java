package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.entidades.Objetivo;
import co.edu.utp.gia.sms.negocio.AbstractGenericService;
import co.edu.utp.gia.sms.negocio.ObjetivoService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
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
public class RegistroObjetivoBean extends GenericBeanNew<Objetivo,String> {
    @Getter @Setter
    private String codigo;
    @Getter @Setter
    private String descripcion;

    @Inject
    private ObjetivoService objetivoService;

    public void inicializar() {
        setRecords(objetivoService.get());
    }

    @Override
    protected Objetivo newRecord() {
        return new Objetivo();
    }

    @Override
    protected AbstractGenericService<Objetivo, String> getServices() {
        return objetivoService;
    }

    public void validate(FacesContext facesContext, UIComponent component, java.lang.Object object){
        validateUnique(facesContext, component, object, record -> record.getCodigo().equals(object.toString()) );
    }
}
