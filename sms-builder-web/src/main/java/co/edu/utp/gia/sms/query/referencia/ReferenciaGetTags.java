package co.edu.utp.gia.sms.query.referencia;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Referencia;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener un listado de los tags usados en las referencias registradas en el sistema
 */
public class ReferenciaGetTags {
    /**
     * Consulta que permite obtener un listado de los tags usados en las referencias registradas en el sistema
     *
     * @return TypedQuery<DatoDTO> que representa la consulta
     */
    public static Stream<String> createQuery() {
        return createQuery(DB.root.revision()::getReferencias);
    }

    /**
     * Consulta que permite obtener un listado de los tags usados en las referencias registradas en el sistema
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @return Stream<String> que representa el resultado de la consulta
     */
    public static Stream<String> createQuery(Provider<Collection<Referencia>> dataProvider) {
        return dataProvider.get().stream()
                .filter(referencia -> referencia.getTags() != null)
                .flatMap(referencia -> referencia.getTags().stream())
                .map(String::toUpperCase)
                .distinct()
                .sorted();
    }
}
