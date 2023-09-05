package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.exceptions.LogicException;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;

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
        super(DB.root.getProvider(AtributoCalidad.class));
    }

    /**
     * Permite registrar un atributo de calidad
     *
     * @param descripcion Descripcion del atributo de calidad
     * @param objetivo    Determina si el atributo de calidad es de caracter objetivo (true) o subjetivo (false)
     * @return El atributo de calidad almacenado.
     */
    public AtributoCalidad save(String descripcion, Boolean objetivo) {
        return save(new AtributoCalidad(descripcion, objetivo));
    }

    /**
     * Verificaciones a realizar antes de que la entidad sea almacenada
     * @param entidad Entidad a almacenar
     */
    @Override
    protected void validateBeforeSave(AtributoCalidad entidad) {
        super.validateBeforeSave(entidad);
        if( !findByDescripcion(entidad.getDescripcion()).isEmpty() ){
            throw new LogicException(exceptionMessage.getRegistroExistente());
        }
    }

    @Override
    protected void validateBeforeUpdate(AtributoCalidad entidad) {
        super.validateBeforeUpdate(entidad);
        var recordsFound = findByDescripcion(entidad.getDescripcion());
        if( recordsFound.size() > 1 || (!recordsFound.isEmpty() && !recordsFound.stream().findFirst().get().equals(entidad) )){
            throw new LogicException(exceptionMessage.getRegistroExistente());
        }
    }

    /**
     * Permite obtener un atributo de calidad basado en su descripción y la revisión a la que pertenece
     *
     * @param descripcion Descripcion del atributo de calidad
     * @return El {@link AtributoCalidad} que se corresponde con el Identificador y descripción dados
     */
    public Collection<AtributoCalidad> findByDescripcion(String descripcion) {
        return dataProvider.get().stream()
                .filter( record->record.getDescripcion().equals(descripcion) )
                .toList();
    }
}
