package co.edu.utp.gia.sms.entidades;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Fuente implements Entidad<Integer> {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;

    /**
     * Nombre de la fuente
     */
    @Getter @Setter @NonNull
    private String nombre;

    /**
     * Tipo de la fuente
     */
    @Enumerated(EnumType.STRING)
    @Getter @Setter @NonNull
    @EqualsAndHashCode.Exclude
    private TipoFuente tipo;

    @ManyToOne
    @Getter @Setter @NonNull
    private Revision revision;

}
