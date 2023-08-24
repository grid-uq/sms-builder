package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.query.Queries;
import jakarta.inject.Provider;

import java.util.Collection;

/**
 * Consulta que permite obtener el total de referencias repetidas de una Revision
 */
public class EstadisticaGetTotalReferenciasRepetidas extends Queries{

    /**
     * Consulta que permite obtener el total de referencias repetidas
     *
     * @return Long número de {@link Referencia} repetidas
     */
    public static Long createQuery(){
        return createQuery(DB.root.getProvider(Referencia.class));
    }

    /**
     * Consulta que permite obtener el total de referencias repetidas
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @return Long número de {@link Referencia} repetidas
     */
    public static Long createQuery(Provider<Collection<Referencia>> dataProvider){
        return dataProvider.get().stream().filter(Referencia::getDuplicada).count();
    }
}
