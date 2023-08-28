package co.edu.utp.gia.sms.entidades;

import lombok.*;

import java.util.Objects;
import java.util.UUID;

/**
 * Entity implementation class for Entity: Topico, el cual hace referencia a un
 * tema o aspecto particular de una {@link Pregunta}
 */
@NoArgsConstructor
@RequiredArgsConstructor
public class Topico implements Entidad<String> {
    /**
     * Variable que representa el identificador unico del topico
     */
    @Getter @Setter
    private String id = UUID.randomUUID().toString();
    /**
     * Variable que representa el atributo descripcion de la clase
     */
    @Getter @Setter
    @NonNull
    private String descripcion;

    @Getter @Setter
    @NonNull
    private Pregunta Pregunta;

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
