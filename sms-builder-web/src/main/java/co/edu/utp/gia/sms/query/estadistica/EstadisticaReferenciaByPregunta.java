package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.entidades.Referencia;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener el número de referencias por Pregunta en una revision
 */
public class EstadisticaReferenciaByPregunta {
    /**
     * Consulta que permite obtener el número de referencias por Pregunta en una revision
     *
     * @return TypedQuery<DatoDTO> que representa la consulta
     */
    public static Stream<DatoDTO> createQuery() {
        return createQuery(DB.root.revision().getPasoActual()::getReferencias);
    }

    /**
     * Consulta que permite obtener el número de referencias por Pregunta en una revision
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @return Stream<DatoDTO> que representa el resultado de la consulta
     */
    public static Stream<DatoDTO> createQuery(Provider<Collection<Referencia>> dataProvider) {

        return dataProvider.get().stream()
                .flatMap(referencia -> referencia.getTopicos().stream().map(t->new Tupla(t.getPregunta(),referencia)))
                .distinct()
                .collect(Collectors.groupingBy(Tupla::pregunta,Collectors.counting()))
                .entrySet().stream()
                .map( entry->new DatoDTO(entry.getKey().getDescripcion(), entry.getValue()));
    }

    private record Tupla(Pregunta pregunta, Referencia referencia){ }
}
