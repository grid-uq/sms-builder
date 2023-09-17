package co.edu.utp.gia.sms.query.fuente;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Fuente;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener las Fuentes con un determinado nombre en la Revision
 */
public class FuenteGetByNombre {

    /**
     * Consulta que permite obtener las Fuentes de un determinado nombre en la Revision
     *
     * @param nombre Nombre de la fuente de la que se desea contar las referencias
     * @return Stream< Fuente > que representa la consulta
     */
    public static Stream<Fuente> createQuery(String nombre){
        return createQuery(DB.root.revision()::getFuentes,nombre);

    }

    /**
     * Consulta que permite obtener las Fuentes de un determinado nombre en la Revision
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @param nombre Nombre de la fuente de la que se desea contar las referencias
     * @return Stream< Fuente > que representa la consulta
     */
    public static Stream<Fuente> createQuery(Provider<Collection<Fuente>> dataProvider, String nombre){
        return dataProvider.get().stream()
                .filter( fuente -> fuente.getNombre().equals(nombre));
    }
}
