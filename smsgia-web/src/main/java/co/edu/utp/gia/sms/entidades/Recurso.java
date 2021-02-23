package co.edu.utp.gia.sms.entidades;

import lombok.*;

import javax.persistence.*;

/**
 * Clase que representa la entidad Recurso, la cual permite modelar en el
 * sistema las páginas que pertenecen al sistema con el fin de poder restringir
 * el acceso a las mismas
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@Entity
@NamedQuery(name = Recurso.FIND_BY_ID, query = "select recurso from Recurso recurso where recurso.id = :id")
@NamedQuery(name = Recurso.GET_ALL, query = "select recurso from Recurso recurso")
@NamedQuery(name = Recurso.FIND_BY_PUBLIC, query = "select recurso.url from Recurso recurso where recurso.publico = :estado")
@NamedQuery(name = Recurso.FIND_BY_ROL, query = "select recurso from Rol rol inner join rol.recursos recurso where rol = :rol")
@NamedQuery(name = Recurso.FIND_BY_URL, query = "select recurso from Recurso recurso where recurso.url = :url")

@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
public class Recurso implements Entidad<Integer> {

    /**
     * Constante que identifica la consulta que permite buscar un
     * {@link Recurso} por su id <br />
     * {@code select recurso from Recurso recurso where recurso.id = :id}
     */
    public static final String FIND_BY_ID = "Recurso_findById";
    /**
     * Constante que identifica la consulta que permite obtener todos los
     * {@link Recurso} registrados en el sistema <br />
     * {@code select recurso from Recurso recurso}
     */
    public static final String GET_ALL = "Recurso_getAll";
    /**
     * Constante que identifica la consulta que permite obtener todos los
     * {@link Recurso} registrados en el sistema con un determinado valor para su atributo {@link Recurso#publico}<br />
     * {@code select recurso from Recurso recurso where recurso.publico = :estado}
     */
    public static final String FIND_BY_PUBLIC = "Recurso_findByPublic";
    /**
     * Constante que identifica la consulta que permite obtener todos los
     * {@link Recurso} registrados en el sistema asiciados a un {@link Rol} dado<br />
     * {@code select recurso.url from Rol rol inner join rol.recursos recurso where rol = :rol}
     */
    public static final String FIND_BY_ROL = "Recurso_findByRol";
    /**
     * Constante que identifica la consulta que permite obtener todos los
     * {@link Recurso} registrados en el sistema asiciados a un {@link Rol} dado<br />
     * {@code select recurso from Recurso recurso where recurso.url = :url}
     */
    public static final String FIND_BY_URL = "Recurso_findByURL";
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 1L;
    /**
     * Variable que representa el atributo id de la clase. Permite identificar
     * de forma única un recurso
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;
    /**
     * Variable que representa el atributo nombre de la clase. Representa el
     * nombre del recurso
     */
    @Column(nullable = false, length = 50, unique = true)
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    @NonNull
    private String nombre;
    /**
     * Variable que representa el atributo url de la clase. Representa la URL de
     * acceso al recurso en el sistema
     */
    @Column(nullable = false, length = 300, unique = true)
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    @NonNull
    private String url;
    /**
     * Variable que representa el atributo publico de la clase. Determina si un
     * recurso es de acceso público o privado
     */
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    @NonNull
    private Boolean publico;

    /**
     * Permite determinar si un recurso es o no público
     * @return True si el recurso es público, en caso contrario retorna false
     */
    public Boolean isPublico() {
        return publico;
    }
}
