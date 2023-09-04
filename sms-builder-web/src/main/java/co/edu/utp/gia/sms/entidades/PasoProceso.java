package co.edu.utp.gia.sms.entidades;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


/**
 * Clase que representa la entidad PasoProceso, la cual permite modelar en el
 * sistema los pasos para la ejecución del SMS.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@NoArgsConstructor
@RequiredArgsConstructor
public class PasoProceso implements Entidad<String> {
    @Getter @Setter
    private String id = UUID.randomUUID().toString();
    @Getter @Setter
    @NonNull
    private Integer orden;
    @Getter @Setter
    @NonNull
    private Paso paso;
    @Getter @Setter
    private List<Referencia> referencias = new ArrayList<>();

    public PasoProceso(@NonNull Paso paso) {
        this.paso = paso;
        this.orden = 0;
    }

    public String getNombre(){
        return paso.nombre();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasoProceso that = (PasoProceso) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1589365384;
    }
}
