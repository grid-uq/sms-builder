package co.edu.utp.gia.sms.query;

import java.io.Serializable;
/**
 * Clase base para modelar las consultas del sistema.
 */
//@SqlResultSetMapping(name = Queries.RESULT_MAPPING_DATODTO,
//        classes = @ConstructorResult(columns = {
//                @ColumnResult(name = "etiqueta", type = String.class),
//                @ColumnResult(name = "valor", type = Float.class)
//        },
//                targetClass = DatoDTO.class)
//)
public class Queries implements Serializable {
    private static final long serialVersionUID = -7643166662144090738L;
    public static final String RESULT_MAPPING_DATODTO = "DatoDTO";

}
