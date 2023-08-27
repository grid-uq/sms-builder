package co.edu.utp.gia.sms.query.referencia;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.query.Queries;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener los Referencias seleccionadas de una revision
 */
public class ReferenciaGetAll extends Queries{

    /**
     * Consulta que permite obtener los Referencias seleccionadas de una revision
     *
     * @return Stream<Referencia> que representa el resultado de la consulta
     */
    public static Stream<Referencia> createQuery(){
        return createQuery(DB.root.revision().getPasoSeleccionado()::getReferencias);
    }

    /**
     * Consulta que permite obtener los Referencias seleccionadas de una revision
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @return Stream<Referencia> que representa el resultado de la consulta
     */
    public static Stream<Referencia> createQuery(Provider<Collection<Referencia>> dataProvider){
        return dataProvider.get().stream();
    }
}
