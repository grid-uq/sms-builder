package co.edu.utp.gia.sms.entidades;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Entity implementation class for Entity: Topico, el cual hace referencia a un
 * tema o aspecto particular de una {@link Pregunta}
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Topico implements Entidad<Integer> {
    private static final long serialVersionUID = 1L;
    /**
     * Variable que representa el identificador unico del topico
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Getter @Setter
    private Integer id;
    /**
     * Variable que representa el atributo descripcion de la clase
     */
    @Getter @Setter
    @NonNull
    private String descripcion;
    /**
     * Variable que representa la {@link Pregunta} a la que pertence el
     * {@link Topico}
     */
    @ManyToOne
    @Getter @Setter
    @NonNull
    private Pregunta pregunta;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topico topico = (Topico) o;

        return Objects.equals(id, topico.id);
    }

    @Override
    public int hashCode() {
        return 517866516;
    }
}
