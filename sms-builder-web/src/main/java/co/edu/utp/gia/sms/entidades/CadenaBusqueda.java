package co.edu.utp.gia.sms.entidades;

import lombok.*;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * Clase que representa la entidad CadenaBusqueda, la cual permite modelar en el
 * sistema las cadenas usadas durante la búsqueda de estudio en las diferentes base de datos.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@RequiredArgsConstructor
@NoArgsConstructor
public class CadenaBusqueda implements Entidad<String> {
    /**
     * Variable que representa el atributo id de la clase
     */
    @GeneratedValue(strategy = IDENTITY)
    @Getter @Setter
    private String id = UUID.randomUUID().toString();

    @Getter @Setter
    @NonNull
    private Fuente baseDatos;

    @Getter @Setter
    @NonNull
    private String consulta;

    @Getter @Setter
    @NonNull
    private Date    fechaConsulta;

    @Getter @Setter
    @NonNull
    private Integer resultadoPreliminar;

    @Getter @Setter
    @NonNull
    private Integer resultadoFinal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CadenaBusqueda that = (CadenaBusqueda) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1706480392;
    }
}
