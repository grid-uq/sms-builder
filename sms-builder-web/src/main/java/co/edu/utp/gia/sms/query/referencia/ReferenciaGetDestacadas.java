package co.edu.utp.gia.sms.query.referencia;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Referencia;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener los Referencias que han recivido una valoración de su contenido
 */
public class ReferenciaGetDestacadas {
    /**
     * Consulta que permite obtener los Referencias que han recivido una valoración de su contenido
     *
     * @return Stream<Referencia> que representa el resultado de la consulta
     */
    public static Stream<Referencia> createQuery(){
        return createQuery(DB.root.revision().getPasoSeleccionado()::getReferencias);
    }

    /**
     * Consulta que permite obtener los Referencias que han recivido una valoración de su contenido
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @return Stream<Referencia> que representa el resultado de la consulta
     */
    public static Stream<Referencia> createQuery(Provider<Collection<Referencia>> dataProvider){
        return dataProvider.get().stream()
                .filter(referencia -> referencia.getRelevancia()!=null)
                .sorted(Comparator.comparing(Referencia::getSpsid).thenComparing(Referencia::getNombre));
    }
}
