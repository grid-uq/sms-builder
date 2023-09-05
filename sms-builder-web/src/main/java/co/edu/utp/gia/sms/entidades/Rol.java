package co.edu.utp.gia.sms.entidades;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Clase que representa la entidad Rol, la cual representa los diferentes roles
 * dentro de la aplicacion asi como los {@link Recurso} a las que tienen acceso
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Rol implements Entidad<String> {
    /**
     * Variable que representa el atributo id de la clase. Permite identificar
     * de forma única un Rol
     */
    @Setter
    @NonNull
    private String id = UUID.randomUUID().toString();
    /**
     * Variable que representa el atributo nombre de la clase. Nombre del Rol
     */
    @Setter
    @NonNull
    private String nombre;
    /**
     * Variable que representa el atributo recursos de la clase. Lista de
     * {@link Recurso} a la cual tiene acceso el {@link Rol}
     */
    @Setter
    private List<Recurso> recursos = new ArrayList<>();

    /**
     * Permite inicializar los elementos del Rol
     *
     * @param id     Id del Rol
     * @param nombre Nombre del Rol
     */
    public Rol(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        recursos = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rol rol = (Rol) o;

        return Objects.equals(id, rol.id);
    }

    @Override
    public int hashCode() {
        return 707023552;
    }
}
