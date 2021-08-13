package co.edu.utp.gia.sms.entidades;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @version 1.0
 * @since 13/06/2019
 */
@Entity
@RequiredArgsConstructor
public class Termino implements Entidad<Integer> {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = -176556849502833317L;


    /**
     * Variable que representa el atributo id de la clase
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Getter
    @Setter
    private Integer id;
    /**
     * Variable que representa el atributo texto de la clase
     */
    @Column(nullable = false)
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    @NonNull
    private String descripcion;

    /**
     * Revision a la cual pertenece el Termino
     */
    @ManyToOne
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    @NonNull
    private Revision revision;

    /**
     * Lista de sinonimos del termino
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "TERMINO_SINONIMO")
    @Column(name = "sinonimo",length = 50)
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    private List<String> sinonimos;

    public Termino(){
        sinonimos = new ArrayList<>();
    }

    public void adicionarSinonimo(String sinonimo) {
        getSinonimos().add(sinonimo);
    }

    public void removerSinonimo(String sinonimo) {
        getSinonimos().remove(sinonimo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Termino termino = (Termino) o;

        return Objects.equals(id, termino.id);
    }

    @Override
    public int hashCode() {
        return 1361059416;
    }
}
