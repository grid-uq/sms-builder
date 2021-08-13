package co.edu.utp.gia.sms.entidades;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;


/**
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @version 1.0
 * @since 5/06/2019
 */

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Nota implements Entidad<Integer> {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 3693629746218687465L;
    /**
     * Variable que representa el atributo id de la clase
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Getter
    @Setter
    private Integer id;

    /**
     * Variable que representa el identificador del elemento
     */
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    @NonNull
    private Integer etapa;


    /**
     * Variable que representa el valor asiciado al elemento
     */
    @Lob
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    @NonNull
    private String descripcion;


    @ManyToOne(fetch = EAGER)
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    @NonNull
    private Referencia referencia;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nota nota = (Nota) o;

        return Objects.equals(id, nota.id);
    }

    @Override
    public int hashCode() {
        return 1831415248;
    }
}
