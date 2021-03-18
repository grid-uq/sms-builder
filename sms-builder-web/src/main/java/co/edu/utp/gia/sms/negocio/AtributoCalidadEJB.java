package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.query.Queries;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class AtributoCalidadEJB extends AbstractEJB<AtributoCalidad, Integer> {
    public static final String IRRQ = "IRRQ";
    public static final String CVI = "CVI";
    public static final String SCI = "SCI";

    @Inject
    private RevisionEJB revisionEJB;

    public AtributoCalidadEJB() {
        super(AtributoCalidad.class);
    }

    /**
     * Permite registrar un atributo de calidad
     *
     * @param descripcion Descripcion del atributo de calidad
     * @param idRevision  Id de la {@link Revision} a la que se desea adicionar el atributo de calidad
     * @return El atributo de calidad registrado
     */
    public AtributoCalidad registrar(String descripcion, Integer idRevision) {
        Revision revision = revisionEJB.obtener(idRevision);
        return registrar(descripcion, revision);
    }

    /**
     * Permite registrar un atributo de calidad
     *
     * @param descripcion Descripcion del atributo de calidad
     * @param revision    {@link Revision} a la que se desea adicionar el atributo de calidad
     * @return El atributo de calidad registrado
     */
    private AtributoCalidad registrar(String descripcion, Revision revision) {
        AtributoCalidad atributoCalidad = null;
        if (revision != null) {
            atributoCalidad = new AtributoCalidad(descripcion, revision);
            entityManager.persist(atributoCalidad);
        }
        return atributoCalidad;
    }

    /**
     * Permite obtener un atributo de calidad basado en su descripción y la revisión a la que pertenece
     *
     * @param descripcion Descripcion del atributo de calidad
     * @param idRevision  Identificador de la revision
     * @return La {@link AtributoCalidad} que se corresponde con el Identificador dado
     */
    public AtributoCalidad obtener(String descripcion, Integer idRevision) {
        return entityManager.createNamedQuery(Queries.ATRIBUTO_CALIDAD_GET_BY_DESCRIPTION_AND_REVISION, AtributoCalidad.class).setParameter("descripcion", descripcion).setParameter("idRevision", idRevision)
                .getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Permite obtener el listado de atributos de calidad de una revision
     *
     * @param id Identificador de la revision
     * @return Listado de {@link AtributoCalidad} de la {@link Revision} identificada con
     * el id dado
     */
    public List<AtributoCalidad> obtenerAtributosCalidad(Integer id) {
        return entityManager.createNamedQuery(Queries.ATRIBUTO_CALIDAD_GET_ALL, AtributoCalidad.class).setParameter("id", id)
                .getResultList();
    }


    /**
     * Premite actualizar un {@link AtributoCalidad}
     *
     * @param atributoCalidad Atributo de calidad a ser actualizada
     */
    @Override
    public void actualizar(AtributoCalidad atributoCalidad) {
        actualizar(atributoCalidad.getId(), atributoCalidad.getDescripcion());
    }

    /**
     * Permite actualizar un atributo de calidad
     *
     * @param id          Id de la {@link AtributoCalidad} a ser actualizada
     * @param descripcion Descripcion del atributo de calidad a actulizar
     */
    public void actualizar(Integer id, String descripcion) {
        AtributoCalidad atributoCalidad = obtener(id);
        if (atributoCalidad != null) {
            atributoCalidad.setDescripcion(descripcion);
        }
    }

    /**
     * Permite crear los atributos de calidad por defecto para una Revision
     * @param revision Revision para la que se crearan los atributos de calidad
     */
    public void crearAtributosCalidadPorDefecto(Revision revision) {
        registrar(SCI, revision);
        registrar(CVI, revision);
        registrar(IRRQ, revision);
    }

    /**
     * Permite eliminar los atributos de calidad de una revision
     * @param idRevision Id de la revision a la cual se le desean eliminar los atributos de calidad
     */
    public void eliminarAtributosCalidad(Integer idRevision){
        obtenerAtributosCalidad(idRevision).stream().forEach( this::eliminar );
    }
}
