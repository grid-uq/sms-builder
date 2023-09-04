package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.Topico;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener el número de referencias por Topico en una revision
 */
public class EstadisticaReferenciaByTopico {
    /**
     * Consulta que permite obtener el número de referencias por Topico en una revision
     *
     * @return TypedQuery<DatoDTO> que representa la consulta
     */
    public static Stream<DatoDTO> createQuery() {
        return createQuery(DB.root.revision().getPasoSeleccionado()::getReferencias);
    }

    /**
     * Consulta que permite obtener el número de referencias por Topico en una revision
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @return Stream<DatoDTO> que representa el resultado de la consulta
     *
     */
    public static Stream<DatoDTO> createQuery(Provider<Collection<Referencia>> dataProvider) {
        Function<Topico,String> getKey = topico-> topico.getPregunta().getCodigo()+"-"+topico.getDescripcion();
        return dataProvider.get().stream()
                .flatMap(referencia -> referencia.getTopicos().stream().distinct())
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet().stream()
                .map( entry->new DatoDTO(getKey.apply(entry.getKey()), entry.getValue()));
    }
}
