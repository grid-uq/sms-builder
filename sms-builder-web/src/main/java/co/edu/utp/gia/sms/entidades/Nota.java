package co.edu.utp.gia.sms.entidades;

import lombok.*;

import java.util.Objects;
import java.util.UUID;

/**
 * TODO Evaluar posibilidad de remplazar por un record
 *
 * Clase que representa la entidad Nota, la cual permite modelar en el
 * sistema una nota de una referencia.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Información y Distribución - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@NoArgsConstructor
@RequiredArgsConstructor
public class Nota implements Entidad<String> {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 3693629746218687465L;
    /**
     * Variable que representa el atributo id de la clase
     */
    @Getter @Setter
    private String id = UUID.randomUUID().toString();

    /**
     * Variable que representa el identificador del elemento
     * TODO evaluar posibilidad de rempalzar por paso
     */
    @Getter @Setter
    @NonNull
    private Integer etapa;


    /**
     * Variable que representa el valor asociado al elemento
     */
    @Getter @Setter
    @NonNull
    private String descripcion;


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
