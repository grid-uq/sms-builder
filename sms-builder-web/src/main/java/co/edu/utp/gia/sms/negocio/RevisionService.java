package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.PasoProceso;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.entidades.TipoFuente;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.query.estadistica.EstadisticaGetTotalReferenciasByTipoFuente;
import co.edu.utp.gia.sms.query.estadistica.EstadisticaGetTotalReferenciasRepetidas;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
/**
 * Clase de negocio encargada de implementar las funciones correspondientes a la
 * gestion de la {@link Revision}.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
@ApplicationScoped
public class RevisionService {
    /**
     * Constructor del servicio.
     */
    public RevisionService() {

    }

    /**
     * Permite actualizar el nombre y la drescripción de la revision
     * @param nombre Nombre de la revision
     * @param descripcion Descripción de la revision
     * @return Revision actualizada
     */
    public Revision save(String nombre, String descripcion) {
        get().setNombre(nombre);
        get().setDescripcion(descripcion);
        save();
        return DB.root.revision();
    }

    /**
     * Permite obtener la {@link Revision}
     * @return Retorna la revision
     */
    public Revision get(){
        return DB.root.revision();
    }

    /**
     * Permite obtener el listado de topicos de la revision
     *
     * @return Listado de {@link Topico} de la {@link Revision}
     */
    public List<Topico> getTopicos() {
        return get().getTopicos();
    }

    /**
     * Permite obterner el total de referencias de una revision
     * @return El número de referencias de la revision
     */
    public long totalReferencias(){
        return get().getReferencias().size();
    }

    /**
     * Permite obterner el total de referencias de una revision
     * @param tipoFuente Tipo de la fuente de la que se desea contar las referencias
     * @return El número de referencias de la revision
     */
    public long totalReferencias(TipoFuente tipoFuente){
        return EstadisticaGetTotalReferenciasByTipoFuente.createQuery(tipoFuente);
    }

    /**
     * Permite obterner el total de referencias de un determinado tipo de fuente en el paso seleccionado
     * @param tipoFuente Tipo de la fuente de la que se desea contar las referencias
     * @return El número total de referencias de un determinado tipo de fuente en el paso seleccionado
     */
    public long totalReferenciasPaso(TipoFuente tipoFuente){
        return EstadisticaGetTotalReferenciasByTipoFuente.createQuery(get().getPasoSeleccionado()::getReferencias,tipoFuente);
    }

    /**
     * Permite obterner el total de referencias repetidas de una revision
     * @return El número de referencias repetidas de la revision
     */
    public long totalReferenciasRepetidas(){
        return EstadisticaGetTotalReferenciasRepetidas.createQuery();
    }

    /**
     * Permite obterner el total de referencias seleccionadas de una revision
     * @return El número de referencias en el paso
     */
    public long totalReferenciasSeleccionadas() {
        return get().getPasoSeleccionado().getReferencias().size();
    }

    public void save() {
        DB.storageManager.store(DB.root.revision());
    }

    public void changePasoSeleccionado(PasoProceso pasoProceso) {
        get().setPasoSeleccionado(pasoProceso);
        save();
    }
}
