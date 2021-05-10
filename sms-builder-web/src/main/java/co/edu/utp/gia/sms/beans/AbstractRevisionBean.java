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
    @Inject @ManagedProperty("#{param.paso}")
    protected Integer paso;

    protected Revision getRevision() {
        if( revision == null ){
            revision = (Revision) getFromSession("revision");
        }
        return revision;
    }
}
