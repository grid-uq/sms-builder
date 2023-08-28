package co.edu.utp.gia.sms.query.referencia;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.TipoFuente;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.query.fuente.FuenteGetByTipoFuente;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener las referencias de un determinado {@link TipoFuente} en una Revision
 */
public class ReferenciaByTipoFuente {

    /**
     * Consulta que permite las referencias de un determinado {@link TipoFuente} en una Revision
     *
     * @param tipoFuente Tipo de la fuente de la que se desea contar las referencias
     * @return Stream< Referencia > que representa la consulta
     */
    public static Stream<Referencia> createQuery(TipoFuente tipoFuente){
        return createQuery(DB.root.getProvider(Referencia.class),tipoFuente);

    }

    /**
     * Consulta que permite las referencias de un determinado {@link TipoFuente} en una Revision
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @param tipoFuente Tipo de la fuente de la que se desea contar las referencias
     * @return Stream< Referencia > que representa la consulta
     */
    public static Stream<Referencia> createQuery(Provider<Collection<Referencia>> dataProvider, TipoFuente tipoFuente){
        return dataProvider.get().stream()
                .filter( referencia -> referencia.getMetadatos().stream()
                        .anyMatch(metadato -> metadato.getIdentifier().equals(TipoMetadato.FUENTE) && FuenteGetByTipoFuente.createQuery(tipoFuente).anyMatch(fuente -> fuente.getNombre().equals(metadato.getValue())))
                );

    }
}
