package co.edu.utp.gia.sms.entidades;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
public class Revision implements Entidad<Integer> {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = -7643166662144090738L;

    /**
     * Variable que representa el atributo id de la clase
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Getter
    @Setter
    private Integer id;
    /**
     * Variable que representa el atributo nombre de la clase
     */

    @Column(nullable = false)
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    @NonNull
    private String nombre;
    /**
     * Variable que representa el atributo descripcion de la clase
     */
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    @NonNull
    private String descripcion;

    /**
     * Lista de objetivos de la revision
     */
    @OneToMany(mappedBy = "revision")
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    private List<Objetivo> objetivos;

    /**
     * Identifica el usuario propieratio de la Revision
     */
    @ManyToOne
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    private Usuario propietario;

    /**
     * Lista de usuarios con acceso a la revision
     */
    @ManyToMany
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    private List<Usuario> revisores;

}
