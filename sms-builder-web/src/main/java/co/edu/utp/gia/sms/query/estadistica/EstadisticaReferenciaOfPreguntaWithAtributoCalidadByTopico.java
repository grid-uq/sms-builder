package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.entidades.EvaluacionCualitativa;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.Topico;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener el número de referencias que cumplem con un determinado atributo de calidad por Topico de una Pregunta en una revision
 */
public class EstadisticaReferenciaOfPreguntaWithAtributoCalidadByTopico {
    /**
     * Consulta que permite obtener el número de referencias que cumplem con un determinado atributo de calidad por Topico de una Pregunta en una revision
     *
     * @param codigo        Codigo de la pregunta de la que se desean obtener las estadisticas
     * @param idAtributoCalidad Id del atributo de calidad
     * @return TypedQuery<DatoDTO> que representa la consulta
     */
    public static Stream<DatoDTO> createQuery(String codigo, String idAtributoCalidad) {
        return createQuery(DB.root.revision().getPasoActual()::getReferencias,codigo,idAtributoCalidad);
    }

    /**
     * Consulta que permite obtener el número de referencias que cumplem con un determinado atributo de calidad por Topico de una Pregunta en una revision
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @param codigo        Codigo de la pregunta de la que se desean obtener las estadisticas
     * @param idAtributoCalidad Id del atributo de calidad
     * @return Stream<DatoDTO> que representa el resultado de la consulta
     */
    public static Stream<DatoDTO> createQuery(Provider<Collection<Referencia>> dataProvider, String codigo, String idAtributoCalidad) {
        Predicate<Topico> filtro = topico -> topico.getPregunta().getCodigo().equals(codigo);
        Predicate<Referencia> filtroReferencia = referencia -> referencia.getEvaluacionCalidad().stream()
                .anyMatch(evaluacionCalidad ->
                        evaluacionCalidad.getAtributoCalidad().getId().equals(idAtributoCalidad) &&
                                evaluacionCalidad.getEvaluacionCualitativa().equals( EvaluacionCualitativa.CUMPLE )
                );
        return dataProvider.get().stream()
                .filter(filtroReferencia)
                .flatMap(referencia -> referencia.getTopicos().stream().distinct().filter(filtro))
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet().stream()
                .map( entry->new DatoDTO(entry.getKey().getDescripcion(), entry.getValue()));
    }
}
