package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.entidades.Revision;
import lombok.Getter;
import lombok.extern.java.Log;

import javax.faces.annotation.ManagedProperty;
import javax.inject.Inject;

@Log
public abstract class AbstractRevisionBean extends AbstractBean {
//    @Inject
//    @ManagedProperty("#{registroInicialBean.revision}")
    private Revision revision;
    private Integer idPaso;
    @Inject @ManagedProperty("#{param.paso}")
    protected Integer paso;

    public AbstractRevisionBean() {
        Object value = getParameterRequest("paso");
        idPaso = value == null ? null : Integer.parseInt( value.toString() );
        log.info("PASO -- "+idPaso);
    }

    protected Revision getRevision() {
        if( revision == null ){
            revision = (Revision) getFromSession("revision");
        }
        return revision;
    }
}
