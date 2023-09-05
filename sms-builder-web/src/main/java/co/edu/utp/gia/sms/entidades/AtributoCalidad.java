package co.edu.utp.gia.sms.entidades;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

/**
 * Modela en el sistema un atributo de calidad usado para evaluar una referencia.
 *
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @version 1.0
 * @since 13/06/2019
 */
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class AtributoCalidad implements Entidad<String> {
    /**
     * Variable que representa el atributo id de la clase
     */
    @Setter
    private String id = UUID.randomUUID().toString();

    /**
     * Variable que representa el atributo texto de la clase
     */
    @Setter
    @NonNull
    private String descripcion;

    @Setter
    @NonNull
    private Boolean objetivo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtributoCalidad that = (AtributoCalidad) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1251423725;
    }
}
