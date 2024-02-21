package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.entidades.EvaluacionCualitativa;
import co.edu.utp.gia.sms.entidades.Referencia;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener el número de referencias que cumplem con un determinado atributo de calidad por Año en una revision
 */
public class EstadisticaReferenciaWithAtributoCalidadByYear {
    /**
     * Consulta que permite obtener el número de referencias que cumplem con un determinado atributo de calidad por Año en una revision
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @param idAtributoCalidad Id del atributo de calidad
     * @return Stream<DatoDTO> que representa el resultado de la consulta
     */
    public static Stream<DatoDTO> createQuery(Provider<Collection<Referencia>> dataProvider, String idAtributoCalidad) {
        Predicate<Referencia> filtro = referencia -> referencia.getEvaluacionCalidad().stream()
                .anyMatch(evaluacionCalidad ->
                        evaluacionCalidad.getAtributoCalidad().getId().equals(idAtributoCalidad) &&
                                evaluacionCalidad.getEvaluacionCualitativa().equals( EvaluacionCualitativa.CUMPLE )
                );
        return EstadisticaReferenciaByAny.createQuery(dataProvider, filtro,Referencia::getYear);
    }
}
