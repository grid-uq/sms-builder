package co.edu.utp.gia.sms.query.estadistica;

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
 * Consulta que permite obtener el número de referencias que cumplem con un determinado atributo de calidad por Topico en una revision
 */
public class EstadisticaReferenciaWithAtributoCalidadByTopico {
    /**
     * Consulta que permite obtener el número de referencias que cumplem con un determinado atributo de calidad por Topico en una revision
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @param idAtributoCalidad Id del atributo de calidad
     *
     * @return Stream<DatoDTO> que representa el resultado de la consulta
     *
     */
    public static Stream<DatoDTO> createQuery(Provider<Collection<Referencia>> dataProvider,String idAtributoCalidad) {
        Predicate<Referencia> filtro = referencia -> referencia.getEvaluacionCalidad().stream()
                .anyMatch(evaluacionCalidad ->
                        evaluacionCalidad.getAtributoCalidad().getId().equals(idAtributoCalidad) &&
                                evaluacionCalidad.getEvaluacionCualitativa().equals( EvaluacionCualitativa.CUMPLE )
                );
        Function<Topico,String> getKey = topico-> topico.getPregunta().getCodigo()+"-"+topico.getDescripcion();
        return dataProvider.get().stream()
                .filter(filtro)
                .flatMap(referencia -> referencia.getTopicos().stream().distinct())
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet().stream()
                .map( entry->new DatoDTO(getKey.apply(entry.getKey()), entry.getValue()));
    }
}
