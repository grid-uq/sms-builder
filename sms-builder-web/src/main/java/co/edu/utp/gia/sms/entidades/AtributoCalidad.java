package co.edu.utp.gia.sms.entidades;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Modela en el sistema un atributo de calidad usado para evaluar una referencia.
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @version 1.0
 * @since 13/06/2019
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class AtributoCalidad implements Entidad<Integer> {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = -176556849502833317L;

    /**
     * Variable que representa el atributo id de la clase
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Getter @Setter
    private Integer id;

    /**
     * Variable que representa el atributo texto de la clase
     */
    @Column(nullable = false)
    @Getter @Setter
    @NonNull
    private String descripcion;

    @Column(nullable = false)
    @Getter @Setter
    @NonNull
    private Boolean objetivo;

    /**
     * {@link Revision} a la cual pertenece el atributo de calidad
     */
    @ManyToOne
    @Getter @Setter
    @NonNull
    private Revision revision;


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
