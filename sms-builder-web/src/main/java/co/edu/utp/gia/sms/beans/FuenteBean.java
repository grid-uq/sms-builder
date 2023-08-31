package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.entidades.Fuente;
import co.edu.utp.gia.sms.entidades.TipoFuente;
import co.edu.utp.gia.sms.negocio.AbstractGenericService;
import co.edu.utp.gia.sms.negocio.FuenteService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Collection;

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
public class FuenteBean extends GenericBeanNew<Fuente,String> {
    @Inject
    private FuenteService fuenteService;

    public void inicializar() {
        setRecords(fuenteService.get());
    }

    @Override
    protected Fuente newRecord() {
        var fuente = new Fuente();
        fuente.setTipo(TipoFuente.BASE_DATOS);
        return fuente;
    }

    @Override
    protected AbstractGenericService<Fuente, String> getServices() {
        return fuenteService;
    }

    public void validate(FacesContext facesContext,
                         UIComponent component, Object object){
        validateUnique(facesContext, component, object, record -> record.getNombre().equals(object.toString()) );
    }

    public Collection<Fuente> getBasesDatos(){
        return getRecords().stream().filter(fuente->fuente.getTipo().equals(TipoFuente.BASE_DATOS)).toList();
    }
}
