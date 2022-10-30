package co.edu.utp.gia.sms.entidades;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
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
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Fuente implements Entidad<Integer> {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;

    /**
     * Nombre de la fuente
     */
    @Getter @Setter @NonNull
    private String nombre;

    /**
     * Tipo de la fuente
     */
    @Enumerated(EnumType.STRING)
    @Getter @Setter @NonNull
    private TipoFuente tipo;

    @ManyToOne
    @Getter @Setter @NonNull
    private Revision revision;

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
