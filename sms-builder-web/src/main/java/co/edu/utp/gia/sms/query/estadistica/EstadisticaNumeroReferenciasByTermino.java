package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.Termino;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener el número de referencias por termino
 */
public class EstadisticaNumeroReferenciasByTermino {
    /**
     * Consulta que permite obtener el número de referencias por termino
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @return Stream<DatoDTO> que representa el resultado de la consulta
     */
    public static Stream<DatoDTO> createQuery(Provider<Collection<Referencia>> dataProvider) {
        return DB.root.revision().getTerminos().stream()
                .map(termino -> new DatoDTO(termino.getDescripcion(),count(dataProvider,termino)));
    }

    private static long count(Provider<Collection<Referencia>> dataProvider, Termino termino){
        List<String> sinonimos = termino.getSinonimos() == null ? Collections.emptyList() : termino.getSinonimos();
        var palabras = new LinkedList<>(sinonimos.stream().map(String::toUpperCase).toList());
        palabras.add(termino.getDescripcion().toUpperCase());

        final var metadatos = List.of(TipoMetadato.KEYWORD,TipoMetadato.TITLE,TipoMetadato.ABSTRACT);

        Predicate<Metadato> filtroMetadato = metadato -> metadatos.contains(metadato.getIdentifier())
                && palabras.stream().anyMatch( palabra-> metadato.getValue().toUpperCase().contains(palabra) );
        Predicate<Referencia> filtro = referencia -> referencia.getMetadatos().stream().anyMatch( filtroMetadato );
        return dataProvider.get().stream().filter(filtro).count();
    }
}
