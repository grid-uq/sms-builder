package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.query.Queries;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener el listado de años que comprenden las Referencias de la Revision
 */
public class EstadisticaGetYears extends Queries{
    /**
     * Consulta que permite obtener el listado de años que comprenden las Referencias de la Revision
     *
     * @return Stream<DatoDTO> que representa el resultado de la consulta
     */
    public static Stream<String> createQuery() {
        return createQuery(DB.root.revision()::getReferencias);
    }

    /**
     * Consulta que permite obtener el listado de años que comprenden las Referencias de la Revision
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @return Stream<DatoDTO> que representa el resultado de la consulta
     */
    public static Stream<String> createQuery(Provider<Collection<Referencia>> dataProvider) {
        return dataProvider.get().stream().map(Referencia::getYear).distinct();
    }
}
