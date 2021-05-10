package co.edu.utp.gia.sms.entidades;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
public class Paso implements Entidad<Integer>{
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    @NonNull
    @Column(unique = true,length = 50)
    private String nombre;
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    @NonNull
    @ManyToOne
    private Recurso recurso;
}
