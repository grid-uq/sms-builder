package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.query.Queries;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener el número de referencias por año en una revision
 */
public class EstadisticaReferenciaByAny extends Queries {

    /**
     * Consulta que permite obtener el número de referencias por año en una revision
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @param mapper mapper – Función que transformará la referencia en un valor
     * @return Stream<DatoDTO> que representa el resultado de la consulta
     */
    public static Stream<DatoDTO> createQuery(Provider<Collection<Referencia>> dataProvider, Function<Referencia, ? > mapper) {
        return createQuery(dataProvider,r->true,mapper);
    }

    /**
     * Consulta que permite obtener el número de referencias por año en una revision
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @param filtro Función que permite filtrar (seleccionar o no) una referencia
     * @param mapper mapper – Función que transformará la referencia en un valor
     * @return Stream<DatoDTO> que representa el resultado de la consulta
     */
    public static Stream<DatoDTO> createQuery(Provider<Collection<Referencia>> dataProvider, Predicate<Referencia> filtro, Function<Referencia, ? > mapper) {
        return createQuery(dataProvider,filtro,mapper,Collectors.counting());
    }

    /**
     * Consulta que permite obtener el número de referencias por año en una revision
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @param filtro Función que permite filtrar (seleccionar o no) una referencia
     * @param mapper mapper – Función que transformará la referencia en un valor
     * @param collector un Colector que aplica la reducción descendente (conteo, suma, promedio, minimo, maximo)
     * @return Stream<DatoDTO> que representa el resultado de la consulta
     */
    public static Stream<DatoDTO> createQuery(Provider<Collection<Referencia>> dataProvider,
                                              Predicate<Referencia> filtro, Function<Referencia, ? > mapper,
                                              Collector<Referencia, ?, ? extends Number> collector) {
        return dataProvider.get().stream()
                .filter(filtro)
                .collect(Collectors.groupingBy(mapper,collector))
                .entrySet().stream()
                .map( entry->new DatoDTO(entry.getKey(), entry.getValue()));
    }
}
