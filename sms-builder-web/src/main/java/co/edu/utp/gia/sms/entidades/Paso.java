package co.edu.utp.gia.sms.entidades;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

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
