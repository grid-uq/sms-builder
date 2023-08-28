package co.edu.utp.gia.sms.entidades;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

/**
 * Modela en el sistema un atributo de calidad usado para evaluar una referencia.
 *
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @version 1.0
 * @since 13/06/2019
 */
@RequiredArgsConstructor
public class AtributoCalidad implements Entidad<String> {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = -176556849502833317L;

    /**
     * Variable que representa el atributo id de la clase
     */
    @Getter
    @Setter
    private String id = UUID.randomUUID().toString();

    /**
     * Variable que representa el atributo texto de la clase
     */
    @Getter
    @Setter
    @NonNull
    private String descripcion;

    @Getter
    @Setter
    @NonNull
    private Boolean objetivo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtributoCalidad that = (AtributoCalidad) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1251423725;
    }
}
