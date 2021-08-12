package co.edu.utp.gia.sms.entidades;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que representa el ID de una {@link EvaluacionCalidad}
 */
@Embeddable
@NoArgsConstructor
@RequiredArgsConstructor
public class EvaluacionCalidadPK implements Serializable {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 7358776815062993477L;

    /**
     * Id de la referencia involucrada en la evaluación
     */
    @Getter
    @Setter
    @NonNull
    private Integer referenciaId;

    /**
     * Id de atributo de calidad involucrado en la evaluación
     */
    @Getter
    @Setter
    @NonNull
    private Integer atributoCalidadId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EvaluacionCalidadPK that = (EvaluacionCalidadPK) o;

        if (!Objects.equals(referenciaId, that.referenciaId)) return false;
        return Objects.equals(atributoCalidadId, that.atributoCalidadId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(referenciaId);
        result = 31 * result + (Objects.hashCode(atributoCalidadId));
        return result;
    }
}
