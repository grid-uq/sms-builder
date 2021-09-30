package co.edu.utp.gia.sms.entidades;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

/**
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
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Objetivo implements Entidad<Integer> {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 6918765230126877992L;

    /**
     * Variable que representa el identificador unico del Objetivo
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Getter @Setter
    private Integer id;

    /**
     * Variable que representa el código del objetivo
     */
    @Getter @Setter
    @NonNull
    private String codigo;

    /**
     * Variable que representa la escripcion del objetivo
     */
    @Getter @Setter
    @NonNull
    private String descripcion;

    /**
     * Variable que representa la {@link Revision} a la que pertenece el objetivo
     */
    @ManyToOne
    @Getter @Setter
    @NonNull
    private Revision revision;


    /**
     * Variable que representa las preguntas que se relacionan con el
     * {@link Objetivo}
     */
    @ManyToMany(mappedBy = "objetivos", fetch = EAGER)
    @Getter @Setter
    private List<Pregunta> preguntas;

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
}
