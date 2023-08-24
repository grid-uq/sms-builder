package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Clase de negocio encargada de implementar las funciones correspondientes a la
 * gestion del {@link AtributoCalidad}.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
@ApplicationScoped
public class AtributoCalidadService extends AbstractGenericService<AtributoCalidad, String> {
    public static final String IRRQ = "IRRQ";
    public static final String CVI = "CVI";
    public static final String SCI = "SCI";

    public AtributoCalidadService() {
        super(DB.root.revision()::getAtributosCalidad);
    }

    /**
     * Permite registrar un atributo de calidad
     *
     * @param descripcion Descripcion del atributo de calidad
     * @param objetivo    Determina si el atributo de calidad es de caracter objetivo (true) o subjetivo (false)
     */
    public void save(String descripcion, Boolean objetivo) {
        save(new AtributoCalidad(descripcion, objetivo));
    }

    /**
     * Permite obtener un atributo de calidad basado en su descripción y la revisión a la que pertenece
     *
     * @param descripcion Descripcion del atributo de calidad
     * @return El {@link AtributoCalidad} que se corresponde con el Identificador y descripción dados
     */
    public AtributoCalidad findByDescripcion(String descripcion) {
        return dataProvider.get().stream()
                .filter( record->record.getDescripcion().equals(descripcion) )
                .findFirst().orElse(null);
    }
}
