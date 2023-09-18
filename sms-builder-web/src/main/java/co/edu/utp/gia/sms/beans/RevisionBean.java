package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.PasoProceso;
import co.edu.utp.gia.sms.negocio.ProcesoService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;

import java.util.Collection;

/**
 * Clase controladora de interfaz web que se encarga de la configuración de una revision.
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
public class RevisionBean extends AbstractRevisionBean {

    @Getter
    private Collection<PasoProceso> pasos;
    @Inject
    private SeguridadBeanImpl seguridadBean;
    @Inject
    private ProcesoService procesoService;

    @PostConstruct
    public void inicializar() {
        if (seguridadBean.isAutenticado()) {
            pasos = procesoService.get();
        }
    }

    public String actualizar() {
        revisionService.save(getRevision().getNombre(), getRevision().getDescripcion());
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        return siguientePaso();
    }


    public String irPasoActual() {
        return getRevision().getPasoActual().getPaso().recurso().getUrl();
    }

    public String anteriorPaso() {
        if( getRevision().getPasoActual().getOrden() > 1) {
            return irPaso(getRevision().getPasoActual().getOrden() - 1);
        }
        return null;
    }
    public String siguientePaso() {
        if( getRevision().getPasoActual().getOrden() < getRevision().getPasosProceso().size()) {
            return irPaso(getRevision().getPasoActual().getOrden() +1);
        }
        return null;
    }
    public String irPaso(int i) {
        var paso = getRevision().getPasosProceso().stream().filter( pasoProceso-> pasoProceso.getOrden() == i ).findFirst();
        paso.ifPresent( revisionService::changePasoActual );
        return "/sms.xhtml?faces-redirect=true";
    }
}
