package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.entidades.CadenaBusqueda;
import co.edu.utp.gia.sms.negocio.AbstractGenericService;
import co.edu.utp.gia.sms.negocio.CadenaBusquedaService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
/**
 * Clase controladora de interfaz web que se encarga de la gestión de las cadenas de búsqueda.
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
public class CadenaBusquedaBean extends GenericBeanNew<CadenaBusqueda,String> {
    @Inject
    private CadenaBusquedaService cadenaBusquedaService;

    public void inicializar() {
        setRecords( cadenaBusquedaService.get() );
        sugerirConsulta();
    }

    @Override
    public void registrar() {
        super.registrar();
        sugerirConsulta();
    }

    @Override
    protected CadenaBusqueda newRecord() {
        return new CadenaBusqueda();
    }

    public void sugerirConsulta(){
        getRecord().setConsulta( cadenaBusquedaService.sugerirConsulta() );
    }

    @Override
    protected AbstractGenericService<CadenaBusqueda, String> getServices() {
        return cadenaBusquedaService;
    }
}