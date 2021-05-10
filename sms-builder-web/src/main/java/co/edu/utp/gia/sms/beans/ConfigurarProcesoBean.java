package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.entidades.Paso;
import co.edu.utp.gia.sms.entidades.PasoProceso;
import co.edu.utp.gia.sms.negocio.PasoEJB;
import co.edu.utp.gia.sms.negocio.ProcesoEJB;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

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
