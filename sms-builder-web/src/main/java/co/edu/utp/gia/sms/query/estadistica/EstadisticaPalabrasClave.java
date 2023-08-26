package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.query.Queries;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener las palabras claves y su número de apariciones en las referencias seleccionadas <br />
 */
public class EstadisticaPalabrasClave extends Queries {
    /**
     * Consulta que permite obtener las palabras claves y su número de apariciones en las referencias seleccionadas
     *
     * @param minimo        Cantidad minima de apariciones que debe tener una palabra clave para ser considerada
     *
     * @return TypedQuery<DatoDTO> que representa la consulta
     */
    public static Stream<DatoDTO> createQuery(Integer minimo) {
        return createQuery(DB.root.revision().getPasoSeleccionado()::getReferencias,minimo);
    }

    /**
     * Consulta que permite obtener las palabras claves y su número de apariciones en las referencias seleccionadas
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @param minimo        Cantidad minima de apariciones que debe tener una palabra clave para ser considerada
     * @return Stream<DatoDTO> que representa el resultado de la consulta
     */
    public static Stream<DatoDTO> createQuery(Provider<Collection<Referencia>> dataProvider, Integer minimo) {
        Predicate<Metadato> filtro = metadato -> metadato.getIdentifier().equals(TipoMetadato.KEYWORD);
        return dataProvider.get().stream()
                .flatMap(referencia -> referencia.getMetadatos().stream().filter(filtro))
                .collect(Collectors.groupingBy(Metadato::getValue,Collectors.counting()))
                .entrySet().stream()
                .filter( entry->entry.getValue()>=minimo)
                .map( entry->new DatoDTO(entry.getKey(), entry.getValue()));
    }
}