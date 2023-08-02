package co.edu.utp.gia.sms.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/**
 * Clase que implementa la entidad Usuario, la cual representa los usuarios de
 * la aplicación (empleados de la empresa).
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
//@NamedQuery(name = Usuario.FIND_BY_ID, query = "select usuario from Usuario usuario where usuario.id = :id")
//@NamedQuery(name = Usuario.GET_ALL, query = "select usuario from Usuario usuario")
//@NamedQuery(name = Usuario.AUTENTICAR, query = "select usuario from Usuario usuario where usuario.nombreUsuario = :nombreUsuario and usuario.estado = co.edu.utp.gia.sms.entidades.EstadoUsuario.ACTIVO")
@NoArgsConstructor
public class Usuario implements Entidad<String> {

    /**
     * Constante que identifica la consulta que permite buscar un
     * {@link Usuario} por su id <br />
     * {@code select usuario from Usuario usuario where usuario.id = :id}
     */
    public static final String FIND_BY_ID = "Usuario_findById";
    /**
     * Constante que identifica la consulta que permite obtener todos los
     * {@link Usuario} registrados en el sistema <br />
     * {@code select usuario from Usuario usuario}
     */
    public static final String GET_ALL = "Usuario_getAll";
    /**
     * Constante que identifica la consulta que permite buscar un
     * {@link Usuario} por su {@link Usuario#nombreUsuario} y su
     * {@link Usuario#clave}<br />
     * {@code select usuario from Usuario usuario where usuario.nombreUsuario = :nombreUsuario and usuario.estado = co.edu.uniquindio.grid.prueba.rifa.entidades.seguridad.EstadoUsuario.ACTIVO }
     */
    public static final String AUTENTICAR = "Usuario_autenticar";
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 1L;
    /**
     * Variable que representa el atributo id de la clase
     */
    @Getter
    @Setter
    private String id = UUID.randomUUID().toString();
    /**
     * Variable que representa el atributo nombreUsuario, que permite a los
     * usuario autenticarse en la aplicacion
     */
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    private String nombreUsuario;
    /**
     * Variable que representa el atributo clave, el cual es usado por los
     * usuario para autenticarse en la aplicacion
     */
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    private String clave;
    /**
     * Representa el {@link EstadoUsuario} de la cuenta del usuario en el sistema.
     */
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    private EstadoUsuario estado = EstadoUsuario.ACTIVO;
    /**
     * Variable que representa el atributo intentos de la clase, el lleva el
     * conteo del número de intentos de autenticación fallidos
     */
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    private Integer intentos = 0;
    /**
     * Variable que representa el atributo roles de la clase. Roles a los cuales
     * pertenece el usuario
     */
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    private List<Rol> roles;
    /**
     * Variable que representa el atributo nombre del {@link Usuario}
     */
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    private String nombre;
    /**
     * Variable que representa el atributo email del {@link Usuario}
     */
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;

        if (!id.equals(usuario.id)) return false;
        return nombreUsuario.equals(usuario.nombreUsuario);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + nombreUsuario.hashCode();
        return result;
    }
}
