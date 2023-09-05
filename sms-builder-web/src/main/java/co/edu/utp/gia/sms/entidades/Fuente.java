package co.edu.utp.gia.sms.entidades;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

/**
 * Clase que representa la entidad Fuente, la cual permite modelar en el
 * sistema las fuentes de las diferentes referencias a ser incluidas en el SMS.
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
public class Fuente implements Entidad<String> {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 1L;

    @Setter
    private String id = UUID.randomUUID().toString();

    /**
     * Nombre de la fuente
     */
    @Setter @NonNull
    private String nombre;

    /**
     * Tipo de la fuente
     */
    @Setter @NonNull
    private TipoFuente tipo;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fuente fuente = (Fuente) o;

        return Objects.equals(id, fuente.id);
    }

    @Override
    public int hashCode() {
        return 25312639;
    }
}
