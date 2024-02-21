package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.entidades.Referencia;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener el promedio de calidad por año
 */
public class EstadisticaCalidadByYear {
    /**
     * Consulta que permite obtener el promedio de calidad por año
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @return Stream<DatoDTO> que representa el resultado de la consulta
     */
    public static Stream<DatoDTO> createQuery(Provider<Collection<Referencia>> dataProvider) {
        return EstadisticaReferenciaByAny.createQuery(dataProvider,r->true,Referencia::getYear,
                Collectors.averagingDouble(Referencia::getTotalEvaluacionCalidad));
    }
}
