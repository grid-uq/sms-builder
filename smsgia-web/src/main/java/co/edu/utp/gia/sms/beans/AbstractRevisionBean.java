package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.entidades.Revision;

import javax.faces.annotation.ManagedProperty;
import javax.inject.Inject;

public abstract class AbstractRevisionBean extends AbstractBean {
//    @Inject
//    @ManagedProperty("#{registroInicialBean.revision}")
    private Revision revision;

    protected Revision getRevision() {
        if( revision == null ){
            revision = (Revision) getFromSession("revision");
            System.out.println( "Se tiene " + ( revision ==null ? null : revision.getNombre()) );
        }
        return revision;
    }
}
