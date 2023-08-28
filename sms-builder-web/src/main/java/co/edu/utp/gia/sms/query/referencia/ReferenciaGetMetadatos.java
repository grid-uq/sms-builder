package co.edu.utp.gia.sms.query.referencia;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.entidades.Referencia;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener los Metadatos de una Referencia
 */
public class ReferenciaGetMetadatos {
    /**
     * Consulta que permite obtener los Metadatos de una Referencia
     *
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @return Stream< Metadato > que representa el flujo de datos de la consulta
     */
    public static Stream<Metadato> createQuery(String id){
        return createQuery(DB.root.revision()::getReferencias,id);
    }

    /**
     * Consulta que permite obtener los metadatos de una referencia que pertenecen a un cierto tipo
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @return Stream< Metadato > que representa el flujo de datos de la consulta
     */
    public static Stream<Metadato> createQuery(Provider<Collection<Referencia>> dataProvider, String id){
        return dataProvider.get().stream()
                .filter( referencia -> referencia.getId().equals(id))
                .map(Referencia::getMetadatos)
                .flatMap(List::stream);
    }

}
