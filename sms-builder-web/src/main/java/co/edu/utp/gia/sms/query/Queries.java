package co.edu.utp.gia.sms.query;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;

import static jakarta.persistence.GenerationType.IDENTITY;
/**
 * Clase base para modelar las consultas del sistema.
 */
@Entity
@SqlResultSetMapping(name = Queries.RESULT_MAPPING_DATODTO,
        classes = @ConstructorResult(columns = {
                @ColumnResult(name = "etiqueta", type = String.class),
                @ColumnResult(name = "valor", type = Float.class)
        },
                targetClass = DatoDTO.class)
)
public class Queries implements Serializable {
    private static final long serialVersionUID = -7643166662144090738L;
    public static final String RESULT_MAPPING_DATODTO = "DatoDTO";
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Getter @Setter
    private Integer id;
}
