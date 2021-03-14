package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.entidades.Usuario;
import co.edu.utp.gia.sms.query.Queries;
import co.edu.utp.gia.sms.query.RevisionQuery;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class RevisionEJB extends AbstractEJB<Revision, Integer> {
    @Inject
    private AtributoCalidadEJB atributoCalidadEJB;
    @Inject
    private UsuarioEJB usuarioEJB;

    public RevisionEJB() {
        super(Revision.class);
    }

    public Revision registrar(String nombre, String descripcion,Integer idUsuario) {
        Usuario usuario = usuarioEJB.obtenerOrThrow(idUsuario);
        Revision revision = new Revision(nombre, descripcion,usuario);
        registrar(revision);
        atributoCalidadEJB.crearAtributosCalidadPorDefecto(revision);
        return revision;
    }

    /**
     * Permite obtener el listado de preguntas de una revision
     *
     * @param id Identificador de la revision
     * @return Listado de {@link Pregunta} de la {@link Revision} identificada con
     * el id dado
     */
    public List<Pregunta> obtenerPreguntas(Integer id) {
        return entityManager.createNamedQuery(Queries.PREGUNTA_GET_ALL, Pregunta.class).setParameter("id", id)
                .getResultList();
    }


    /**
     * Permite obtener el listado de preguntas de una revision
     *
     * @return Listado de las {@link Revision} registradas
     */
    @Override
    public List<Revision> listar() {
        return entityManager.createNamedQuery(RevisionQuery.REVISION_GET_ALL, Revision.class).getResultList();
    }

    /**
     * Permite obtener el listado de preguntas de una revision
     *
     * @return Listado de las {@link Revision} registradas
     */
    public List<Revision> obtenerTodas(Integer idUsuario) {
        return entityManager.createNamedQuery(RevisionQuery.REVISION_GET_ALL_RELATED, Revision.class)
                .setParameter("id", idUsuario)
                .getResultList();
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
        return entityManager.createNamedQuery(Queries.TOPICO_REVISION_GET_ALL, Topico.class).setParameter("id", id)
                .getResultList();
    }

    /**
     * Permite eliminar una Revision
     * @param id Ide de la revisión que se desea eliminar
     */
    @Override
    public void eliminar(Integer id) {
        atributoCalidadEJB.eliminarAtributosCalidad(id);
        super.eliminar(id);
    }

    /**
     * Permite obterner el total de referencias de una revision
     * @param id Id de la revision
     * @return El número de referencias de la revision
     */
    public long totalReferencias(Integer id){
        return entityManager.createNamedQuery(RevisionQuery.REVISION_TOTAL_REFERENCIAS,Long.class)
                .setParameter("id",id).getSingleResult();
    }

    /**
     * Permite obterner el total de referencias repetidas de una revision
     * @param id Id de la revision
     * @return El número de referencias repetidas de la revision
     */
    public long totalReferenciasRepetidas(Integer id){
        return entityManager.createNamedQuery(RevisionQuery.REVISION_TOTAL_REFERENCIAS_REPETIDAS,Long.class)
                .setParameter("id",id).getSingleResult();
    }

    /**
     * Permite obterner el total de referencias seleccionafas de una revision
     * @param id Id de la revision
     * @return El número de referencias repetidas de la revision
     */
    public long totalReferenciasSeleccionadas(Integer id) {
        return entityManager.createNamedQuery(RevisionQuery.REVISION_TOTAL_REFERENCIAS_SELECCIONADAS,Long.class)
                .setParameter("id",id).getSingleResult();
    }
}
