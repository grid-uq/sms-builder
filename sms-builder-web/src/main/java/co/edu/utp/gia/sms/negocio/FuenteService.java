package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Fuente;
import co.edu.utp.gia.sms.entidades.TipoFuente;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.stream.Collectors;

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
@ApplicationScoped
public class FuenteService extends AbstractGenericService<Fuente, String> {
    public static final String SNOWBALL_BACKWARD = "SNOWBALL_BACKWARD";
    public static final String SNOWBALL_FORWARD = "SNOWBALL_FORWARD";
    public static final String INCLUSION_DIRECTA = "INCLUSION_DIRECTA";

    public FuenteService() {
        super(DB.root.getProvider(Fuente.class));
    }

    /**
     * Permite registrar un fuente
     *
     * @param nombre      nombre de la fuente
     * @param tipoFuente  tipo de la fuente
     * @return El Fuente registrado
     */
    public Fuente save(String nombre, TipoFuente tipoFuente) {
        return save(new Fuente(nombre,tipoFuente));
    }

    /**
     * Consulta que permite obtener las fuentes registradas en el sistema para una revision
     *
     * @param tipo tipo de fuente ({@link TipoFuente}) por la que se desea filtrar las fuentes a buscar
     *
     * @return Listado de fuentes encontradas
     */
    public List<Fuente> getByTipoFuente(TipoFuente tipo) {
        return get().stream().filter(record->record.getTipo()==tipo).collect(Collectors.toList());
    }
}
