package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.entidades.Paso;
import co.edu.utp.gia.sms.entidades.PasoProceso;
import co.edu.utp.gia.sms.negocio.PasoEJB;
import co.edu.utp.gia.sms.negocio.ProcesoEJB;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
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
    private ProcesoEJB procesoEJB;
    @Inject
    private PasoEJB pasoEJB;

    @Getter @Setter
    private Paso pasoSeleccionado;
    @Getter
    private List<PasoProceso> pasosProceso;
    @Getter
    private List<Paso> pasos;

    public void inicializar() {
        if (getRevision() != null) {
            pasos = pasoEJB.listar();
            pasosProceso = procesoEJB.listar(getRevision().getId());
        }
    }

    public void adicionar(){
        var nuevoPaso = procesoEJB.registrar(pasoSeleccionado.getId(),getRevision().getId());
        pasosProceso.add( nuevoPaso );
    }

    public void eliminar(PasoProceso pasoProceso){
        procesoEJB.eliminar(pasoProceso.getId());
        pasosProceso.remove(pasoProceso);
    }

}
