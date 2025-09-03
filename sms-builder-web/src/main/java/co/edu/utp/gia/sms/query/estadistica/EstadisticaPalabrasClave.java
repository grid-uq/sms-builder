package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener las palabras claves y su número de apariciones en las referencias seleccionadas <br />
 */
public class EstadisticaPalabrasClave {
    /**
     * Consulta que permite obtener las palabras claves y su número de apariciones en las referencias seleccionadas
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @param minimo        Cantidad minima de apariciones que debe tener una palabra clave para ser considerada
     * @return Stream<DatoDTO> que representa el resultado de la consulta
     */
    public static Stream<DatoDTO> createQuery(Provider<Collection<Referencia>> dataProvider, Integer minimo) {
        var tipos = List.of(TipoMetadato.KEYWORD, TipoMetadato.TAG);
        Predicate<Metadato> filtro = metadato -> tipos.contains(metadato.getIdentifier());
        return dataProvider.get().stream()
                .flatMap(referencia -> referencia.getMetadatos().stream().filter(filtro))
                .collect(Collectors.groupingBy(Metadato::getValue,Collectors.counting()))
                .entrySet().stream()
                .filter( entry->entry.getValue()>=minimo)
                .map( entry->new DatoDTO(entry.getKey(), entry.getValue()));
    }
}