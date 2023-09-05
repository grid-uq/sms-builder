package co.edu.utp.gia.sms.query.seguridad;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Recurso;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Consulta permite buscar un {@link Recurso} basado en su url
 */
public class SeguridadRecursoFindByUrl {
    /**
     * Consulta permite buscar un {@link Recurso} basado en su url
     *
     * @param url Url del recurso que se desea búscar
     * @return Stream< Recurso > que representa el flujo de datos de la consulta de las {@link co.edu.utp.gia.sms.entidades.Recurso}
     */
    public static Stream<Recurso> createQuery(String url) {
        return createQuery(DB.root.revision()::getRecursos,url);
    }

    /**
     * Consulta permite buscar un {@link Recurso} basado en su url
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @param url Url del recurso que se desea búscar
     * @return Stream< Recurso > que representa el flujo de datos de la consulta de las {@link co.edu.utp.gia.sms.entidades.Recurso}
     */
    public static Stream<Recurso> createQuery(Provider<Collection<Recurso>> dataProvider, String url) {
        return dataProvider.get().stream().filter( recurso -> recurso.getUrl().equals(url));
    }

}
