package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.entidades.PasoProceso;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.negocio.RevisionService;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.extern.java.Log;

import java.util.function.Function;
import java.util.function.Predicate;

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
    @Getter
    private PasoProceso pasoActual;
    @Getter
    private PasoProceso pasoAnterior;
    @Getter
    private PasoProceso pasoSiguiente;
    @Inject
    protected RevisionService revisionService;

    public Revision getRevision() {
        if( revision == null ){
            revision = revisionService.get();
        }
        return revision;
    }

    @Override
    protected void preInicializar() {
        super.preInicializar();
        initPasos();
    }

    protected void initPasos(){
        Function<Integer,Predicate<PasoProceso>> filtro = (orden)->paso-> paso.getOrden().equals(orden);
        var pasos = getRevision().getPasosProceso();
        pasoActual = revisionService.getPasoActual();
        if (pasoActual != null) {
            int orden = pasoActual.getOrden();
            int ordenAnterior = orden -1;
            int ordenSiguiente = orden +1;
            pasoAnterior = pasos.stream().filter(filtro.apply(ordenAnterior)).findAny().orElse(null);
            pasoSiguiente = pasos.stream().filter(filtro.apply(ordenSiguiente)).findAny().orElse(null);
        }
    }

    public String getUrlActual(){
        String url = "/index.xhtml";
        if( pasoActual != null ){
            url = pasoActual.getPaso().recurso().getUrl();
        }
        return url;
    }
}
