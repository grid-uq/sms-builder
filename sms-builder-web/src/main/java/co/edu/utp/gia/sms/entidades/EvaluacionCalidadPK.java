package co.edu.utp.gia.sms.entidades;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Clase que representa el ID de una {@link EvaluacionCalidad}
 */
@Embeddable
@EqualsAndHashCode
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

}
