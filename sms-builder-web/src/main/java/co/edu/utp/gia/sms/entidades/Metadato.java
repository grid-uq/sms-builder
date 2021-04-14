package co.edu.utp.gia.sms.entidades;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Representa un element del formato RIS
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
@Table(name = "Metadato",
        indexes = {@Index(name = "identifierIndex",  columnList="identifier", unique = false),
                @Index(name = "referenciaIndex", columnList="referencia_id,identifier",     unique = false)})
@EqualsAndHashCode
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
    @Getter
    @Setter
    private Integer id;


    /**
     * Variable que representa el identificador del elemento
     */
    @Enumerated(STRING)
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    @NonNull
    private TipoMetadato identifier;

    /**
     * Variable que representa el valor asiciado al elemento
     */
    @Lob
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    @NonNull
    private String value;

    @ManyToOne(fetch = EAGER)
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
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
}
