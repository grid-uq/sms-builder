package co.edu.utp.gia.sms.entidades;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

/**
 * Representa un metadato perteneciente a una referencia.
 *
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @version 1.0
 * @since 5/06/2019
 */
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Metadato implements Entidad<String> {
    /**
     * Variable que representa el atributo id de la clase
     */
    @Setter
    private String id = UUID.randomUUID().toString();


    /**
     * Variable que representa el identificador del elemento
     */
    @Setter
    @NonNull
    private TipoMetadato identifier;

    /**
     * Variable que representa el valor asiciado al elemento
     */
    @Setter
    @NonNull
    private String value;

    @Setter
    private Referencia referencia;

    /**
     * Metodo que permite inicializar los elementos de la clase Metadato
     *
     * @param identifier Identificador del metadato
     * @param value      Valor a ser asignado al metadato
     * @param referencia Referencia a la que pertenece el metadato
     */
    public Metadato(TipoMetadato identifier, String value, Referencia referencia) {
        this.identifier = identifier;
        this.value = value;
        this.referencia = referencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metadato metadato = (Metadato) o;

        return Objects.equals(id, metadato.id);
    }

    @Override
    public int hashCode() {
        return 1915190472;
    }
}
