package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.*;
import co.edu.utp.gia.sms.exceptions.LogicException;
import co.edu.utp.gia.sms.query.estadistica.EstadisticaGetTotalReferenciasByTipoFuente;
import co.edu.utp.gia.sms.query.estadistica.EstadisticaGetTotalReferenciasRepetidas;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
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

    @Inject
    private UsuarioService usuarioService;

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

    public PasoProceso getPasoActual(){
        PasoProceso pasoActual = null;
        var usuario = usuarioService.getUsuario();
        if( usuario != null ){
            pasoActual = usuario.getPasoActual();
            if (pasoActual == null){
                pasoActual = get().getPasosProceso().stream().filter(paso->paso.getOrden()==1).findFirst().orElse(null);
                changePasoActual(pasoActual);
            }
        }
        return pasoActual;
    }
    /**
     * Permite obterner el total de referencias de un determinado tipo de fuente en el paso seleccionado
     * @param tipoFuente Tipo de la fuente de la que se desea contar las referencias
     * @return El número total de referencias de un determinado tipo de fuente en el paso seleccionado
     */
    public long totalReferenciasPaso(TipoFuente tipoFuente){
        return EstadisticaGetTotalReferenciasByTipoFuente.createQuery(getPasoActual()::getReferencias,tipoFuente);
    }

    /**
     * Permite obterner el total de referencias repetidas de una revision
     * @return El número de referencias repetidas de la revision
     */
    public long totalReferenciasRepetidas(){
        return EstadisticaGetTotalReferenciasRepetidas.createQuery();
    }

    /**
     * Permite obtener el total de referencias seleccionadas de una revision
     * @return El número de referencias en el paso
     */
    public long totalReferenciasSeleccionadas() {
        return getPasoActual().getReferencias().size();
    }

    public void save() {
        DB.storageManager.store(DB.root.revision());
    }

    public void changePasoActual(PasoProceso pasoProceso ) {
        var usuario = usuarioService.getUsuario();
        if( usuario != null ){
            usuario.setPasoActual(pasoProceso);
            usuarioService.update(usuario);
        }
    }

    public void restore(Revision revisionRestored) {
        try {
            BeanUtils.copyProperties(DB.root.revision(), revisionRestored);
            DB.storageManager.storeRoot();
            DB.storageManager.store(DB.root.revision());

            restoreObjetivos();
            restorePasos();

            restoreRecursos();
            restoreRoles();
            restoreUsuarios();


            restorePreguntas();
            restoreTerminos();
            restoreAtributosCalidad();
            restoreFuentes();
            restoreCriteriosSeleccion();

            restoreTopicos();
            restoreCadenaBusqueda();

            restoreReferencia();
            restorePasoProceso();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new LogicException(e);
        }
    }

    private void restoreUsuarios() {
        DB.storageManager.store(DB.root.revision().getUsuarios());
        DB.root.revision().getUsuarios().forEach(usuario -> DB.storageManager.store(usuario.getRoles()));
    }

    private void restoreRoles() {
        DB.storageManager.store(DB.root.revision().getRoles());
        DB.root.revision().getRoles().forEach(rol -> DB.storageManager.store(rol.getRecursos()));
    }

    private void restorePasoProceso() {
        DB.storageManager.store(DB.root.revision().getPasosProceso());
        DB.root.revision().getPasosProceso().forEach(pasoProceso -> DB.storageManager.store(pasoProceso.getReferencias()));
    }

    private void restorePasos() {
        DB.storageManager.store(DB.root.revision().getPasos());
    }

    private void restoreCadenaBusqueda() {
        DB.storageManager.store(DB.root.revision().getCadenasBusqueda());
    }

    private void restoreCriteriosSeleccion() {
        DB.storageManager.store(DB.root.revision().getCriteriosSeleccion());
    }

    private void restoreTerminos() {
        DB.storageManager.store(DB.root.revision().getTerminos());
        DB.root.revision().getTerminos().forEach(termino -> DB.storageManager.store(termino.getSinonimos()));
    }

    private void restoreObjetivos() {
        DB.storageManager.store(DB.root.revision().getObjetivos());
        DB.root.revision().getObjetivos().forEach(objetivo -> DB.storageManager.store(objetivo.getPreguntas()));
    }

    private void restoreAtributosCalidad() {
        DB.storageManager.store(DB.root.revision().getAtributosCalidad());
    }


    private void restoreFuentes() {
        DB.storageManager.store(DB.root.revision().getFuentes());
    }

    private void restorePreguntas() {
        DB.storageManager.store(DB.root.revision().getPreguntas());
        DB.root.revision().getPreguntas().forEach( pregunta -> {
            DB.storageManager.store(pregunta.getTopicos());
            DB.storageManager.store(pregunta.getObjetivos());
        } );
    }

    private void restoreRecursos() {
        DB.storageManager.store(DB.root.revision().getRecursos());
    }

    private void restoreReferencia() {
        DB.storageManager.store(DB.root.revision().getReferencias());
        DB.root.revision().getReferencias().forEach(referencia -> {
            DB.storageManager.store(referencia.getEvaluacionCalidad());
            DB.storageManager.store(referencia.getMetadatos());
            DB.storageManager.store(referencia.getTopicos());
        });
    }

    private void restoreTopicos() {
        DB.storageManager.store(DB.root.revision().getTopicos());
    }


}
