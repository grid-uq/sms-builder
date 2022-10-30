package co.edu.utp.gia.sms.entidades;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;
/**
 * Clase que representa la entidad Proceso, la cual permite modelar en el
 * sistema un Paso del SMS
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
public class Paso implements Entidad<Integer>{
    @Getter @Setter
    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @Getter @Setter
    @NonNull
    @Column(unique = true,length = 50)
    private String nombre;
    @Getter @Setter
    @NonNull
    @ManyToOne
    private Recurso recurso;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paso paso = (Paso) o;

        return Objects.equals(id, paso.id);
    }

    @Override
    public int hashCode() {
        return 1701416093;
    }
}
