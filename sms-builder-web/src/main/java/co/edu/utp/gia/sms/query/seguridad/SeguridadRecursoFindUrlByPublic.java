package co.edu.utp.gia.sms.query.seguridad;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Recurso;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener un listado con todos los urls de los {@link Recurso}s segun si son públicos o no
 */
public class SeguridadRecursoFindUrlByPublic {
    /**
     * Consulta que permite obtener un listado con todos los urls de los {@link Recurso}s registrados en el sistema
     *
     * @param isPublico Determina si se desean obtener los recursos públicos o no públicos
     * @return Stream< String > que representa el flujo de datos de la consulta
     */
    public static Stream<String> createQuery(boolean isPublico) {
        return createQuery(DB.root.revision()::getRecursos,isPublico);
    }

    /**
     * Consulta que permite obtener un listado con todos los urls de los {@link Recurso}s registrados en el sistema
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @param isPublico Determina si se desean obtener los recursos públicos o no públicos
     * @return Stream< String > que representa el flujo de datos de la consulta
     */
    public static Stream<String> createQuery(Provider<Collection<Recurso>> dataProvider, boolean isPublico) {
        return dataProvider.get().stream()
                .filter(recurso -> recurso.getPublico() == isPublico ).map(Recurso::getUrl);
    }
}
