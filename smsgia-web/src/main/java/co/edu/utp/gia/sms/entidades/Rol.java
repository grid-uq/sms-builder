package co.edu.utp.gia.sms.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa la entidad Rol, la cual representa los diferentes roles
 * dentro de la aplicacion asi como los {@link Recurso} a las que tienen acceso
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@Entity
@NamedQuery(name = Rol.FIND_BY_ID, query = "select rol from Rol rol where rol.id = :id")
@NamedQuery(name = Rol.GET_ALL, query = "select rol from Rol rol")
@NamedQuery(name = Rol.FIND_BY_USUARIO, query = "select rol from Usuario usuario inner join usuario.roles rol where usuario = :usuario")

@EqualsAndHashCode
@NoArgsConstructor
public class Rol implements Entidad<Integer> {

    /**
     * Constante que identifica la consulta que permite buscar un
     * {@link Rol} por su id <br />
     * {@code select rol from Rol rol where rol.id = :id}
     */
    public static final String FIND_BY_ID = "Rol_findById";
    /**
     * Constante que identifica la consulta que permite obtener todos los
     * {@link Recurso} registrados en el sistema <br />
     * {@code select rol from Rol rol}
     */
    public static final String GET_ALL = "Rol_getAll";
    /**
     * Constante que identifica la consulta que permite obtener todos los
     * {@link Rol} asociados a un {@link Usuario} <br />
     * {@code select rol from Usuario usuario inner join usuario.roles rol where usuario = :usuario}
     */
    public static final String FIND_BY_USUARIO = "Rol_findByUsuario";
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 1L;
    /**
     * Variable que representa el atributo id de la clase. Permite identificar
     * de forma única un Rol
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;
    /**
     * Variable que representa el atributo nombre de la clase. Nombre del Rol
     */
    @Column(nullable = false, length = 20, unique = true)
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    private String nombre;
    /**
     * Variable que representa el atributo recursos de la clase. Lista de
     * {@link Recurso} a la cual tiene acceso el {@link Rol}
     */
    @ManyToMany
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    private List<Recurso> recursos;

    /**
     * Permite inicializar los elementos del Rol
     *
     * @param id     Id del Rol
     * @param nombre Nombre del Rol
     */
    public Rol(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        recursos = new ArrayList<>();
    }

}
