package co.edu.utp.gia.sms.entidades;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
/**
 * Clase que representa la entidad Objetivo, la cual permite modelar en el
 * sistema un objetivo del SMS
 *
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Julio Cesar Chavarro
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 8 abr. 2020
 */
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Objetivo implements Entidad<String> {
    /**
     * Variable que representa el identificador unico del Objetivo
     */
    @Setter
    private String id = UUID.randomUUID().toString();

    /**
     * Variable que representa el código del objetivo
     */
    @Setter
    @NonNull
    private String codigo;

    /**
     * Variable que representa la escripcion del objetivo
     */
    @Setter
    @NonNull
    private String descripcion;

    /**
     * Variable que representa las preguntas que se relacionan con el
     * {@link Objetivo}
     */
    @Setter
    private List<Pregunta> preguntas = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Objetivo objetivo = (Objetivo) o;

        return Objects.equals(id, objetivo.id);
    }

    @Override
    public int hashCode() {
        return 1220655506;
    }

    @Override
    public String toString() {
        return codigo;
    }
}
