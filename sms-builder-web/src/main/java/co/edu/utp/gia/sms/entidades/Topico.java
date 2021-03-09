package co.edu.utp.gia.sms.entidades;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Entity implementation class for Entity: Topico, el cual hace referencia a un
 * tema o aspecto particular de una {@link Pregunta}
 */
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
public class Topico implements Entidad<Integer> {
    private static final long serialVersionUID = 1L;
    /**
     * Variable que representa el identificador unico del topico
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Getter
    @Setter
    private Integer id;
    /**
     * Variable que representa el atributo descripcion de la clase
     */
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    @NonNull
    private String descripcion;
    /**
     * Variable que representa la {@link Pregunta} a la que pertence el
     * {@link Topico}
     */
    @ManyToOne
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    @NonNull
    private Pregunta pregunta;


}
