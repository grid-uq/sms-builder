package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.Fuente;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.entidades.TipoFuente;
import co.edu.utp.gia.sms.query.revision.RevisionGetFuentes;
import co.edu.utp.gia.sms.query.revision.RevisionGetFuentesByTipo;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;
/**
 * Clase de negocio encargada de implementar las funciones correspondientes a la
 * gestion de la {@link Fuente}.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
@Stateless
public class FuenteEJB extends AbstractEJB<Fuente, Integer> {
    public static final String SNOWBALL_BACKWARD = "SNOWBALL_BACKWARD";
    public static final String SNOWBALL_FORWARD = "SNOWBALL_FORWARD";
    public static final String INCLUSION_DIRECTA = "INCLUSION_DIRECTA";

    @Inject
    private RevisionEJB revisionEJB;

    public FuenteEJB() {
        super(Fuente.class, dataProvider);
    }

    /**
     * Permite registrar un fuente
     *
     * @param nombre      nombre de la fuente
     * @param tipoFuente  tipo de la fuente
     * @param idRevision  Id de la {@link Revision} a la que se desea adicionar la fuente
     * @return El Fuente registrado
     */
    public Fuente registrar(String nombre, TipoFuente tipoFuente, Integer idRevision) {
        Revision revision = revisionEJB.obtenerOrThrow(idRevision);
        return registrar(nombre,tipoFuente,revision);
    }

    /**
     * Permite registrar un fuente
     *
     * @param nombre      nombre de la fuente
     * @param tipoFuente  tipo de la fuente
     * @param revision La {@link Revision} a la que se desea adicionar la fuente
     * @return El Fuente registrado
     */
    public Fuente registrar(String nombre, TipoFuente tipoFuente, Revision revision) {
        Fuente fuente = new Fuente(nombre,tipoFuente,revision);
        return registrar(fuente);
    }

    /**
     * Consulta que permite obtener las fuentes registradas en el sistema para una revision
     *
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @return Listado de fuentes encontradas
     */
    public List<Fuente> listar(Integer id) {
        return RevisionGetFuentes.createQuery(entityManager,id).getResultList();
    }

    /**
     * Consulta que permite obtener las fuentes registradas en el sistema para una revision
     *
     * @param tipo tipo de fuente ({@link TipoFuente}) por la que se desea filtrar las fuentes a buscar
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     *
     * @return Listado de fuentes encontradas
     */
    public List<Fuente> listarByTipoFuente(TipoFuente tipo,Integer id) {
        return RevisionGetFuentesByTipo.createQuery(entityManager,tipo,id).getResultList();
    }

    /**
     * Permite crear las fuentes por defecto para una revision
     * @param revision Revision para la que se desean crear las fuentes por defecto
     */
    public void crearFuentesPorDefecto(Revision revision){
        registrar(INCLUSION_DIRECTA,TipoFuente.INCLUSION_DIRECTA,revision);
        registrar(SNOWBALL_BACKWARD,TipoFuente.BOLA_NIEVE,revision);
        registrar(SNOWBALL_FORWARD,TipoFuente.BOLA_NIEVE,revision);
    }
}
