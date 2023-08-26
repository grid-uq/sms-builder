package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.entidades.EvaluacionCalidad;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.query.Queries;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener el promedio de la evaluación de calidad de un determinado atributo de calidad por Año en una revision
 */
public class EstadisticaCalidadOfAtributoCalidadByYear extends Queries {

    /**
     * Consulta que permite obtener el promedio de la evaluación de calidad de un determinado atributo de calidad por Año en una revision
     *
     * @param idAtributoCalidad Id del atributo de calidad
     *
     * @return TypedQuery<DatoDTO> que representa la consulta
     */
    public static Stream<DatoDTO> createQuery(String idAtributoCalidad) {
        return createQuery(DB.root.revision().getPasoSeleccionado()::getReferencias,idAtributoCalidad);
    }

    /**
     * Consulta que permite obtener el promedio de la evaluación de calidad de un determinado atributo de calidad por Año en una revision
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @param idAtributoCalidad Id del atributo de calidad
     * @return Stream<DatoDTO> que representa el resultado de la consulta
     */
    public static Stream<DatoDTO> createQuery(Provider<Collection<Referencia>> dataProvider, String idAtributoCalidad) {
        ToDoubleFunction<Referencia> mapper = referencia -> referencia.getEvaluacionCalidad().stream()
                .filter(evaluacionCalidad -> evaluacionCalidad.getAtributoCalidad().getId().equals(idAtributoCalidad))
                .collect(Collectors.averagingDouble(EvaluacionCalidad::getEvaluacionCuantitativa));

        return EstadisticaReferenciaByAny.createQuery(dataProvider,r->true,Referencia::getYear,
                Collectors.averagingDouble(mapper));
    }
}
