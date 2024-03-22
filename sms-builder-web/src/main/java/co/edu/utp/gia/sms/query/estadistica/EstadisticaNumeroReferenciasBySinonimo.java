package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.Termino;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import jakarta.inject.Provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener el número de referencias por termino y sinonimo
 */
public class EstadisticaNumeroReferenciasBySinonimo {
    /**
     * Consulta que permite obtener el número de referencias por termino y sinonimo
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @return Stream<DatoDTO> que representa el resultado de la consulta
     */
    public static Stream<DatoDTO> createQuery(Provider<Collection<Referencia>> dataProvider) {

        var sinonimos = DB.root.revision().getTerminos().stream()
                .filter(termino -> termino.getSinonimos() != null)
                .flatMap(termino -> termino.getSinonimos().stream())
                .toList();
        var terminos = DB.root.revision().getTerminos().stream()
                .map(Termino::getDescripcion)
                .toList();
        var palabras = new ArrayList<>(terminos);
        palabras.addAll(sinonimos);

        return palabras.stream().distinct()
                .map(palabra -> new DatoDTO(palabra,count(dataProvider,palabra)));
    }

    private static long count(Provider<Collection<Referencia>> dataProvider, String palabra){
        final var metadatos = List.of(TipoMetadato.KEYWORD,TipoMetadato.TITLE,TipoMetadato.ABSTRACT);

        Predicate<Metadato> filtroMetadato = metadato -> metadatos.contains(metadato.getIdentifier())
                && metadato.getValue().toUpperCase().contains(palabra.toUpperCase());
        Predicate<Referencia> filtro = referencia -> referencia.getMetadatos().stream().anyMatch( filtroMetadato );
        return dataProvider.get().stream().filter(filtro).count();
    }
}
