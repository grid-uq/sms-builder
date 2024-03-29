package co.edu.utp.gia.sms.entidades;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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
@Setter
@Getter
@NoArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Usuario implements Entidad<String> {
    /**
     * Variable que representa el atributo id de la clase
     */
    private String id = UUID.randomUUID().toString();
    /**
     * Variable que representa el atributo nombreUsuario, que permite a los
     * usuario autenticarse en la aplicacion
     */
    @EqualsAndHashCode.Exclude
    private String nombreUsuario;
    /**
     * Variable que representa el atributo clave, el cual es usado por los
     * usuario para autenticarse en la aplicacion
     */
    @EqualsAndHashCode.Exclude
    private String clave;
    /**
     * Representa el {@link EstadoUsuario} de la cuenta del usuario en el sistema.
     */
    @EqualsAndHashCode.Exclude
    private EstadoUsuario estado = EstadoUsuario.ACTIVO;
    /**
     * Variable que representa el atributo intentos de la clase, el lleva el
     * conteo del número de intentos de autenticación fallidos
     */
    @EqualsAndHashCode.Exclude
    private Integer intentos = 0;
    /**
     * Variable que representa el atributo roles de la clase. Roles a los cuales
     * pertenece el usuario
     */
    @EqualsAndHashCode.Exclude
    private List<Rol> roles = new ArrayList<>();
    /**
     * Variable que representa el atributo nombre del {@link Usuario}
     */
    @EqualsAndHashCode.Exclude
    private String nombre;
    /**
     * Variable que representa el atributo email del {@link Usuario}
     */
    @EqualsAndHashCode.Exclude
    private String email;

    /**
     * Representa el paso que está siendo procesado por el usuario
     */
    private PasoProceso pasoActual;


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
