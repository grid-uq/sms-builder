package co.edu.utp.gia.sms.query.seguridad;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Usuario;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Consulta permite buscar un {@link co.edu.utp.gia.sms.entidades.Usuario} basado en su nombre de usuario y clave
 */
public class SeguridadUsuarioLogin {
    /**
     * Consulta permite buscar un {@link co.edu.utp.gia.sms.entidades.Usuario} basado en su nombre de usuario
     *
     * @param nombreUsuario Email del usuario que se desea autenticar
     * @return Stream< Usuario > que representa el flujo de datos de la consulta de las {@link co.edu.utp.gia.sms.entidades.Usuario}
     */
    public static Stream<Usuario> createQuery(String nombreUsuario) {
        return createQuery(DB.root.revision()::getUsuarios,nombreUsuario);
    }


    /**
     * Consulta permite buscar un {@link co.edu.utp.gia.sms.entidades.Usuario} basado en su nombre de usuario
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @param nombreUsuario Email del usuario que se desea autenticar
     * @return Stream< Usuario > que representa el flujo de datos de la consulta de las {@link co.edu.utp.gia.sms.entidades.Usuario}
     */
    public static Stream<Usuario> createQuery(Provider<Collection<Usuario>> dataProvider, String nombreUsuario) {
        return dataProvider.get().stream().filter( usuario -> usuario.getNombreUsuario().equals(nombreUsuario) );
    }
}
