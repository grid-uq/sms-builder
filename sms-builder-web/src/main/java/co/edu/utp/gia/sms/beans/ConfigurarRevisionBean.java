package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.PasoProceso;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.negocio.ProcesoEJB;
import co.edu.utp.gia.sms.negocio.RevisionEJB;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;


@Named
@ViewScoped
public class ConfigurarRevisionBean extends AbstractBean {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = -6995163695300909108L;
    @Getter
    @Setter
    private Revision revision;
    @Getter
    private List<PasoProceso> pasos;
    @Inject
    private SeguridadBeanImpl seguridadBean;
    @Inject
    private ProcesoEJB procesoEJB;
    @Inject
    private RevisionEJB revisionEJB;

    @PostConstruct
    public void inicializar() {
        if (seguridadBean.isAutenticado()) {
            revision = (Revision) getFromSession("revision");
            pasos = procesoEJB.listar(revision.getId());
        }
    }

    public void actualizar() {
        revisionEJB.actualizar(revision);
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
    }

}
