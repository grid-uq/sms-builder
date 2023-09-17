package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.PasoProceso;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.negocio.ProcesoService;
import co.edu.utp.gia.sms.negocio.RevisionService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

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
public class RevisionBean extends AbstractBean {
    @Getter
    @Setter
    private Revision revision;
    @Getter
    private Collection<PasoProceso> pasos;
    @Inject
    private SeguridadBeanImpl seguridadBean;
    @Inject
    private ProcesoService procesoService;
    @Inject
    private RevisionService revisionService;

    @PostConstruct
    public void inicializar() {
        if (seguridadBean.isAutenticado()) {
            revision = revisionService.get();
            pasos = procesoService.get();
        }
    }

    public String actualizar() {
        revisionService.save(revision.getNombre(), revision.getDescripcion());
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        return siguientePaso();
    }


    public String irPasoActual() {
        return revision.getPasoActual().getPaso().recurso().getUrl();
    }

    public String anteriorPaso() {
        if( revision.getPasoActual().getOrden() > 1) {
            return irPaso(revision.getPasoActual().getOrden() - 1);
        }
        return null;
    }
    public String siguientePaso() {
        if( revision.getPasoActual().getOrden() < revision.getPasosProceso().size()) {
            return irPaso(revision.getPasoActual().getOrden() +1);
        }
        return null;
    }
    public String irPaso(int i) {
        var paso = revision.getPasosProceso().stream().filter( pasoProceso-> pasoProceso.getOrden() == i ).findFirst();
        paso.ifPresent( revisionService::changePasoActual );
        var pasoActual = revision.getPasoActual();
        return pasoActual.getPaso().recurso().getUrl()+"?faces-redirect=true&paso="+pasoActual.getId();
    }
}
