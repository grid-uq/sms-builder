package co.edu.utp.gia.sms.query.fuente;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Fuente;
import co.edu.utp.gia.sms.entidades.TipoFuente;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener las Fuentes de un determinado {@link TipoFuente} en la Revision
 */
public class FuenteGetByTipoFuente {

    /**
     * Consulta que permite obtener las Fuentes de un determinado {@link TipoFuente} en la Revision
     *
     * @param tipoFuente Tipo de la fuente de la que se desea contar las referencias
     * @return Stream< Fuente > que representa la consulta
     */
    public static Stream<Fuente> createQuery(TipoFuente tipoFuente){
        return createQuery(DB.root.revision()::getFuentes,tipoFuente);

    }

    /**
     * Consulta que permite obtener las Fuentes de un determinado {@link TipoFuente} en la Revision
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @param tipoFuente Tipo de la fuente de la que se desea contar las referencias
     * @return Stream< Fuente > que representa la consulta
     */
    public static Stream<Fuente> createQuery(Provider<Collection<Fuente>> dataProvider, TipoFuente tipoFuente){
        return dataProvider.get().stream()
                .filter( fuente -> fuente.getTipo().equals(tipoFuente));

    }
}
