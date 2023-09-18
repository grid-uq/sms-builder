package co.edu.utp.gia.sms.query.referencia;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Referencia;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener los SCI de las referencia de una revision
 */
public class ReferenciaGetAllSCI {
    /**
     * Consulta que permite obtener los SCI de las referencia de una revision
     *
     * @return Stream<Float> que representa el resultado de la consulta
     */
    public static Stream<Float> createQuery(){
        return createQuery(DB.root.revision().getPasoActual()::getReferencias);
    }

    /**
     * Consulta que permite obtener los SCI de las referencia de una revision
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @return Stream<Float> que representa el resultado de la consulta
     */
    public static Stream<Float> createQuery(Provider<Collection<Referencia>> dataProvider){
        return dataProvider.get().stream().map(Referencia::getSci);
    }
}
