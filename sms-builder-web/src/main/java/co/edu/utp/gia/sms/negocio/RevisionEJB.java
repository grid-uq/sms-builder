package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.*;
import co.edu.utp.gia.sms.query.estadistica.EstadisticaGetTotalReferenciasRepetidas;
import co.edu.utp.gia.sms.query.paso.PasoGetTotalReferencias;
import co.edu.utp.gia.sms.query.paso.PasoGetTotalReferenciasByTipoFuente;
import co.edu.utp.gia.sms.query.revision.RevisionFindAll;
import co.edu.utp.gia.sms.query.revision.RevisionFindByUsuarioId;
import co.edu.utp.gia.sms.query.revision.RevisionGetTotalReferencias;
import co.edu.utp.gia.sms.query.revision.RevisionGetTotalReferenciasByTipoFuente;
import co.edu.utp.gia.sms.query.topico.TopicoFindAll;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
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
@Stateless
public class RevisionEJB extends AbstractEJB<Revision, Integer> {
    @Inject
    private AtributoCalidadService atributoCalidadService;
    @Inject
    private FuenteService fuenteService;
    @Inject
    private UsuarioService usuarioService;

    public RevisionEJB() {
        super(Revision.class, dataProvider);
    }

    public Revision registrar(String nombre, String descripcion,Integer idUsuario) {
        Usuario usuario = usuarioService.obtenerOrThrow(idUsuario);
        Revision revision = new Revision(nombre, descripcion,usuario);
        registrar(revision);
        atributoCalidadService.crearAtributosCalidadPorDefecto();
        fuenteService.crearFuentesPorDefecto();
        return revision;
    }

    /**
     * Permite obtener el listado de preguntas de una revision
     *
     * @return Listado de las {@link Revision} registradas
     */
    @Override
    public List<Revision> listar() {
        return RevisionFindAll.createQuery(entityManager).getResultList();
    }

    /**
     * Permite obtener el listado de preguntas de una revision
     *
     * @return Listado de las {@link Revision} registradas
     */
    public List<Revision> obtenerTodas(Integer idUsuario) {
        return RevisionFindByUsuarioId.createQuery(entityManager,idUsuario).getResultList();
    }

    /**
     * Permite actualizar una revision
     *
     * @param id          Id de la {@link Pregunta} a ser actualizada
     * @param nombre      Codigo de la pregunta a actualizar
     * @param descripcion Descripcion de la pregunta a actulizar
     */
    public void actualizar(Integer id, String nombre, String descripcion) {
        Revision revision = obtener(id);
        if (revision != null) {
            revision.setNombre(nombre);
            revision.setDescripcion(descripcion);
        }
    }

    /**
     * Permite obtener el listado de topicos de la revision
     *
     * @param id Identificador de la revision
     * @return Listado de {@link Topico} de la {@link Revision} identificada con el
     * id dado
     */
    public List<Topico> obtenerTopicos(Integer id) {
        return TopicoFindAll.createQuery(entityManager,id).getResultList();
    }

    /**
     * Permite eliminar una Revision
     * @param id Ide de la revisión que se desea eliminar
     */
    @Override
    public void eliminar(Integer id) {
        super.eliminar(id);
    }

    /**
     * Permite obterner el total de referencias de una revision
     * @param id Id de la revision
     * @return El número de referencias de la revision
     */
    public long totalReferencias(Integer id){
        return RevisionGetTotalReferencias.createQuery(entityManager,id).getSingleResult();
    }

    /**
     * Permite obterner el total de referencias de una revision
     * @param id Id de la revision
     * @param tipoFuente Tipo de la fuente de la que se desea contar las referencias
     * @return El número de referencias de la revision
     */
    public long totalReferencias(Integer id, TipoFuente tipoFuente){
        return RevisionGetTotalReferenciasByTipoFuente.createQuery(entityManager,id,tipoFuente).getSingleResult();
    }

    /**
     * Permite obterner el total de referencias de una revision
     * @param id id del Paso que indica la etapata en la que se quieren contar las referencias
     * @param tipoFuente Tipo de la fuente de la que se desea contar las referencias
     * @return El número de referencias de la revision
     */
    public long totalReferenciasPaso(Integer id, TipoFuente tipoFuente){
        return PasoGetTotalReferenciasByTipoFuente.createQuery(entityManager,id,tipoFuente).getSingleResult();
    }

    /**
     * Permite obterner el total de referencias repetidas de una revision
     * @param id Id de la revision
     * @return El número de referencias repetidas de la revision
     */
    public long totalReferenciasRepetidas(Integer id){
        return EstadisticaGetTotalReferenciasRepetidas.createQuery(entityManager,id).getSingleResult();
    }

    /**
     * Permite obterner el total de referencias seleccionadas de una revision
     * @param id Id del paso seleccionado
     * @return El número de referencias en el paso
     */
    public long totalReferenciasSeleccionadas(Integer id) {
        return PasoGetTotalReferencias.createQuery(entityManager,id).getSingleResult();
    }
}
