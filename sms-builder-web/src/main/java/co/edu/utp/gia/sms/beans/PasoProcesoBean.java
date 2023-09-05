package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.entidades.Paso;
import co.edu.utp.gia.sms.entidades.PasoProceso;
import co.edu.utp.gia.sms.negocio.AbstractGenericService;
import co.edu.utp.gia.sms.negocio.PasoService;
import co.edu.utp.gia.sms.negocio.ProcesoService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.extern.java.Log;

import java.util.Collection;

/**
 * Clase controladora de interfaz web que se encarga de la configuración del proceso.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@Log
@Named
@ViewScoped
public class PasoProcesoBean extends GenericBeanNew<PasoProceso, String> {
    @Inject
    private ProcesoService procesoService;
    @Inject
    private PasoService pasoService;

    @Getter
    private Collection<Paso> pasos;

    public void inicializar() {
        pasos = pasoService.get();
        setRecords(procesoService.get());
        getRecord().setOrden(getRecords().size() + 1);
    }

    @Override
    protected PasoProceso newRecord() {
        var pasoProceso = new PasoProceso();
        if (getRecords() != null) {
            pasoProceso.setOrden(getRecords().size() + 1);
        }
        return pasoProceso;
    }

    @Override
    protected AbstractGenericService<PasoProceso, String> getServices() {
        return procesoService;
    }
}
