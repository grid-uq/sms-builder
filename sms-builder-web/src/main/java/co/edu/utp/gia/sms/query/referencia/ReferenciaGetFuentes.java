package co.edu.utp.gia.sms.query.referencia;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Fuente;
import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.query.Queries;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener las fuentes registradas en el sistema para una referencia
 */
public class ReferenciaGetFuentes extends Queries{
    /**
     * Consulta que permite obtener las fuentes registradas en el sistema para una referencia
     *
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @return TypedQuery<DatoDTO> que representa la consulta
     */
    public static Stream<Fuente> createQuery(String id) {
        return createQuery(DB.root.revision().getPasoSeleccionado()::getReferencias,id);
    }

    /**
     * Consulta que permite obtener las fuentes registradas en el sistema para una referencia
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @return Stream<DatoDTO> que representa el resultado de la consulta
     */
    public static Stream<Fuente> createQuery(Provider<Collection<Referencia>> dataProvider,String id) {
        final var nombre = dataProvider.get().stream()
                .filter(referencia -> referencia.getId().equals(id))
                .flatMap(referencia -> referencia.getMetadatos().stream())
                .filter(metadato -> metadato.getIdentifier().equals(TipoMetadato.FUENTE))
                .map(Metadato::getValue)
                .findFirst().orElse(null);
        return  DB.root.getProvider(Fuente.class).get().stream()
                .filter(fuente -> fuente.getNombre().equals(nombre));
    }
}
