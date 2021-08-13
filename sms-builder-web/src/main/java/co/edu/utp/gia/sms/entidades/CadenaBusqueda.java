package co.edu.utp.gia.sms.entidades;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class CadenaBusqueda implements Entidad<Integer> {
    /**
     * Variable que representa el atributo id de la clase
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    @EqualsAndHashCode.Exclude
    @NonNull
    @Column(length = 30)
    private String baseDatos;

    @Getter @Setter
    @EqualsAndHashCode.Exclude
    @NonNull
    @Lob
    private String consulta;

    @Getter @Setter
    @EqualsAndHashCode.Exclude
    @NonNull
    private Date    fechaConsulta;

    @Getter @Setter
    @EqualsAndHashCode.Exclude
    @NonNull
    private Integer resultadoPreliminar;

    @Getter @Setter
    @EqualsAndHashCode.Exclude
    @NonNull
    private Integer resultadoFinal;

    @ManyToOne
    @Getter @Setter
    @EqualsAndHashCode.Exclude
    @NonNull
    private Revision revision;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CadenaBusqueda that = (CadenaBusqueda) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1706480392;
    }
}
