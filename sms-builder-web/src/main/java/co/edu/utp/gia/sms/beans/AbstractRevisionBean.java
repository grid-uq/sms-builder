package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.entidades.Revision;
import lombok.extern.java.Log;

import javax.faces.annotation.ManagedProperty;
import javax.inject.Inject;
/**
 * Clase controladora de interfaz web que define los elementos básicos de las interfaces para el manejo de referencias.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@Log
public abstract class AbstractRevisionBean extends AbstractBean {
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
