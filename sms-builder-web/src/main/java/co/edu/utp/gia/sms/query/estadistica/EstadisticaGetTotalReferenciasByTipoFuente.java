package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.TipoFuente;
import co.edu.utp.gia.sms.query.Queries;
import co.edu.utp.gia.sms.query.referencia.ReferenciaByTipoFuente;
import jakarta.inject.Provider;

import java.util.Collection;

/**
 * Consulta que permite obtener el total de referencias de un determinado {@link TipoFuente} en una Revision
 */
public class EstadisticaGetTotalReferenciasByTipoFuente extends Queries{

    /**
     * Consulta que permite obtener el total de referencias de un determinado {@link TipoFuente} en una Revision
     *
     * @param tipoFuente Tipo de la fuente de la que se desea contar las referencias
     * @return Long que representa la consulta
     */
    public static Long createQuery(TipoFuente tipoFuente){
        return createQuery(DB.root.getProvider(Referencia.class),tipoFuente);
    }

    /**
     * Consulta que permite obtener el total de referencias de un determinado {@link TipoFuente} en una Revision
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @param tipoFuente Tipo de la fuente de la que se desea contar las referencias
     * @return Long que representa la consulta
     */
    public static Long createQuery(Provider<Collection<Referencia>> dataProvider, TipoFuente tipoFuente){
        return ReferenciaByTipoFuente.createQuery(dataProvider,tipoFuente).count();
    }
}
