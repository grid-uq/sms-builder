package co.edu.utp.gia.sms.entidades;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class PasoProceso implements Entidad<Integer> {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    @NonNull
    private Integer orden;
    @Getter @Setter
    @NonNull
    @ManyToOne
    private Paso paso;
    @Getter @Setter
    @NonNull
    @ManyToOne
    private Revision revision;
    @Getter @Setter
    @OneToMany
    @OrderBy("nombre ASC")
    private List<Referencia> referencias;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasoProceso that = (PasoProceso) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1589365384;
    }
}
