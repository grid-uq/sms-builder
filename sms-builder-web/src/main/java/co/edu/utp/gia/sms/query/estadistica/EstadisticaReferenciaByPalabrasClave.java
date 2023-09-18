package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener las Referencias que contienen en uno de sus metadatos la palabra dada<br />
 */
public class EstadisticaReferenciaByPalabrasClave {
    /**
     * Consulta que permite obtener las Referencias que contienen en uno de sus metadatos la palabra dada
     *
     * @param keyword       Palabra a buscar
     * @param metadatos     Listado de tipos de metadatos a incluir en la búsqueda.
     * @return Stream<DatoDTO> que representa el resultado de la consulta
     */
    public static Stream<Referencia> createQuery(String keyword, List<TipoMetadato> metadatos) {
        return createQuery(DB.root.revision().getPasoActual()::getReferencias,keyword, metadatos);
    }

    /**
     * Consulta que permite obtener las Referencias que contienen en uno de sus metadatos la palabra dada
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @param keyword       Palabra a buscar
     * @param metadatos     Listado de tipos de metadatos a incluir en la búsqueda.
     * @return Stream<DatoDTO> que representa el resultado de la consulta
     */
    public static Stream<Referencia> createQuery(Provider<Collection<Referencia>> dataProvider, String keyword, List<TipoMetadato> metadatos) {
        Predicate<Metadato> filtroMetadato = metadato -> metadatos.contains(metadato.getIdentifier())
                && metadato.getValue().toUpperCase().contains(keyword.toUpperCase());
        Predicate<Referencia> filtro = referencia -> referencia.getMetadatos().stream().anyMatch( filtroMetadato );
        return dataProvider.get().stream().filter(filtro);
    }
}
