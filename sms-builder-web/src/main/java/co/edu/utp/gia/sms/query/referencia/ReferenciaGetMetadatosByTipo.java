package co.edu.utp.gia.sms.query.referencia;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener los metadatos de una referencia que pertenecen a un cierto tipo
 */
public class ReferenciaGetMetadatosByTipo {
    /**
     * Consulta que permite obtener los metadatos de una referencia que pertenecen a un cierto tipo
     *
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @param tipo Tipo de metadato que se desea obtener
     * @return @return Stream< Metadato > que representa el flujo de datos de la consulta de las {@link co.edu.utp.gia.sms.entidades.Metadato}
     */
    public static Stream<Metadato> createQuery(String id, TipoMetadato tipo){
        return createQuery(DB.root.revision()::getReferencias,id,tipo);
    }

    /**
     * Consulta que permite obtener los metadatos de una referencia que pertenecen a un cierto tipo
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @param tipo Tipo de metadato que se desea obtener
     * @return Stream< Metadato > que representa el flujo de datos de la consulta de las {@link co.edu.utp.gia.sms.entidades.Metadato}
     */
    public static Stream<Metadato> createQuery(Provider<Collection<Referencia>> dataProvider,String id, TipoMetadato tipo){
        return ReferenciaGetMetadatos.createQuery(dataProvider,id)
                .filter(metadato->metadato.getIdentifier().equals(tipo));
    }
}
