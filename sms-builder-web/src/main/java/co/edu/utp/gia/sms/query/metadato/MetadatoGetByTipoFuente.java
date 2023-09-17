package co.edu.utp.gia.sms.query.metadato;

import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener los metadatos de un determinado {@link TipoMetadato}
 */
public class MetadatoGetByTipoFuente {


    /**
     * Consulta que permite obtener las Fuentes de un determinado {@link TipoMetadato} en la Revision
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @param tipo Tipo del metadato que se desea obtener
     * @return Stream< Metadato > que representa la consulta
     */
    public static Stream<Metadato> createQuery(Provider<Collection<Metadato>> dataProvider, TipoMetadato tipo){
        return dataProvider.get().stream()
                .filter( metadato -> metadato.getIdentifier().equals(tipo));

    }
}
