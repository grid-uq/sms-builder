package co.edu.utp.gia.sms.entidades;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Clase que representa la entidad Nota, la cual permite modelar en el
 * sistema una nota de una referencia.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
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
    @Getter @Setter
    private Integer id;

    /**
     * Variable que representa el identificador del elemento
     */
    @Getter @Setter
    @NonNull
    private Integer etapa;


    /**
     * Variable que representa el valor asiciado al elemento
     */
    @Lob
    @Getter @Setter
    @NonNull
    private String descripcion;


    @ManyToOne(fetch = EAGER)
    @Getter @Setter
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
