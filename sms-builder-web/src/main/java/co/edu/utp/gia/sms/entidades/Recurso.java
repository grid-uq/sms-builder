package co.edu.utp.gia.sms.entidades;

import lombok.*;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * Clase que representa la entidad Recurso, la cual permite modelar en el
 * sistema las páginas que pertenecen al sistema con el fin de poder restringir
 * el acceso a las mismas
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
public class Recurso implements Entidad<Integer> {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 1L;
    /**
     * Variable que representa el atributo id de la clase. Permite identificar
     * de forma única un recurso
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
    /**
     * Variable que representa el atributo nombre de la clase. Representa el
     * nombre del recurso
     */
    @Column(nullable = false, length = 50, unique = true)
    @Getter @Setter
    @NonNull
    private String nombre;
    /**
     * Variable que representa el atributo url de la clase. Representa la URL de
     * acceso al recurso en el sistema
     */
    @Column(nullable = false, length = 300, unique = true)
    @Getter @Setter
    @NonNull
    private String url;
    /**
     * Variable que representa el atributo publico de la clase. Determina si un
     * recurso es de acceso público o privado
     */
    @Getter @Setter
    @NonNull
    private Boolean publico;

    /**
     * Permite determinar si un recurso es o no público
     * @return True si el recurso es público, en caso contrario retorna false
     */
    public Boolean isPublico() {
        return publico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recurso recurso = (Recurso) o;

        return Objects.equals(id, recurso.id);
    }

    @Override
    public int hashCode() {
        return 1158230854;
    }
}
