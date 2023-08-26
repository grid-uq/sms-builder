package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.entidades.Paso;
import co.edu.utp.gia.sms.entidades.PasoProceso;
import co.edu.utp.gia.sms.negocio.PasoService;
import co.edu.utp.gia.sms.negocio.ProcesoService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Collection;
import java.util.List;
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
public class ConfigurarProcesoBean extends AbstractRevisionBean {

    @Inject
    private ProcesoService procesoService;
    @Inject
    private PasoService pasoService;

    @Getter @Setter
    private Paso pasoSeleccionado;
    @Getter
    private Collection<PasoProceso> pasosProceso;
    @Getter
    private Collection<Paso> pasos;

    public void inicializar() {
        if (getRevision() != null) {
            pasos = pasoService.get();
            pasosProceso = procesoService.get();
        }
    }

    public void adicionar(){
        var nuevoPaso = procesoService.save(pasoSeleccionado.getId());
        pasosProceso.add( nuevoPaso );
    }

    public void eliminar(PasoProceso pasoProceso){
        procesoService.delete(pasoProceso.getId());
        pasosProceso.remove(pasoProceso);
    }

}
