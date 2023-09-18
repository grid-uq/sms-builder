package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.entidades.Referencia;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener el número de referencias por Tipo en una revision
 */
public class EstadisticaReferenciaByTipo {

    /**
     * Consulta que permite obtener el número de referencias por Tipo en la revision
     *
     * @return TypedQuery<DatoDTO> que representa la consulta
     */
    public static Stream<DatoDTO> createQuery() {
        return createQuery(DB.root.revision().getPasoActual()::getReferencias);
    }

    /**
     * Consulta que permite obtener el número de referencias por Tipo en la revision
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @return Stream<DatoDTO> que representa el resultado de la consulta
     */
    public static Stream<DatoDTO> createQuery(Provider<Collection<Referencia>> dataProvider) {
        return EstadisticaReferenciaByAny.createQuery(dataProvider,Referencia::getTipo);
    }
}
