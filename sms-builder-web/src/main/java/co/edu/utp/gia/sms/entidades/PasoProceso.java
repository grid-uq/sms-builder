package co.edu.utp.gia.sms.entidades;

import lombok.*;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
public class PasoProceso implements Entidad<Integer> {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    @NonNull
    private Integer orden;
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    @NonNull
    @ManyToOne
    private Paso paso;
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    @NonNull
    @ManyToOne
    private Revision revision;
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    @OneToMany
    private List<Referencia> referencias;
}
