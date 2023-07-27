package co.edu.utp.gia.sms.entidades;

import lombok.*;

import jakarta.persistence.*;
import java.util.Objects;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.IDENTITY;

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
@Entity
@Table(indexes = {@Index(name = "identifierIndex",  columnList="identifier"),
                @Index(name = "referenciaIndex", columnList="referencia_id,identifier")})
@NoArgsConstructor
@RequiredArgsConstructor
public class Metadato implements Entidad<Integer> {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 4287992191212757639L;

    /**
     * Variable que representa el atributo id de la clase
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Getter @Setter
    private Integer id;


    /**
     * Variable que representa el identificador del elemento
     */
    @Enumerated(STRING)
    @Getter @Setter
    @NonNull
    private TipoMetadato identifier;

    /**
     * Variable que representa el valor asiciado al elemento
     */
    @Lob
    @Getter @Setter
    @NonNull
    private String value;

    @ManyToOne(fetch = EAGER)
    @Getter @Setter
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
