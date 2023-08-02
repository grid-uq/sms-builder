package co.edu.utp.gia.sms.entidades;

import lombok.*;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.GenerationType.IDENTITY;
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
@NoArgsConstructor
@RequiredArgsConstructor
public class Paso implements Entidad<String>{
    @Getter @Setter
    private String id;
    @Getter @Setter
    @NonNull
    private String nombre;
    @Getter @Setter
    @NonNull
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
