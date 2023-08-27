package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.entidades.PasoProceso;
import co.edu.utp.gia.sms.entidades.Revision;
import lombok.Getter;
import lombok.extern.java.Log;

import jakarta.faces.annotation.ManagedProperty;
import jakarta.inject.Inject;
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
    protected String paso;
    @Getter
    private PasoProceso pasoActual;
    @Getter
    private PasoProceso pasoAnterior;
    @Getter
    private PasoProceso pasoSiguiente;

    protected Revision getRevision() {
        if( revision == null ){
            revision = (Revision) getFromSession("revision");
        }
        return revision;
    }

    @Override
    protected void preInicializar() {
        super.preInicializar();
        initPasos();
    }

    protected void initPasos(){
        var pasos = getRevision().getPasosProceso();
        pasoActual = pasoAnterior = pasoSiguiente = null;
        pasos.forEach( paso->{
            if( pasoActual != null ){
                if( pasoSiguiente == null ){
                    pasoSiguiente = paso;
                }
            } else {
                if( paso.getId().equals(this.paso)){
                    pasoActual = paso;
                } else {
                    pasoAnterior = paso;
                }
            }
                }
        );
    }
}
