package co.edu.utp.gia.sms.entidades;

import lombok.*;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

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
    @Getter @Setter
    private Integer id;
    /**
     * Variable que representa el atributo nombre de la clase
     */

    @Getter @Setter
    @NonNull
    private String nombre;
    /**
     * Variable que representa el atributo descripcion de la clase
     */
    @Getter @Setter
    @NonNull
    private String descripcion;

    /**
     * Lista de objetivos de la revision
     */
    @Getter @Setter
    private List<Objetivo> objetivos;

    /**
     * Identifica el usuario propieratio de la Revision
     */
    @Getter @Setter
    @NonNull
    private Usuario propietario;

    /**
     * Lista de usuarios con acceso a la revision
     */
    @Getter @Setter
    private List<Usuario> revisores;

    /**
     * Lista de los pasos para la ejecución del proceso de SMS
     */
    @Getter @Setter
    private List<PasoProceso> pasosProceso;

    /**
     * Representa el paso seleccionado para la evaluación y extracción de estadísticas
     */
    @OneToOne
    @Getter @Setter
    private PasoProceso pasoSeleccionado;

    @Getter @Setter
    private List<AtributoCalidad> atributosCalidad;

    @Getter @Setter
    private List<AtributoCalidad> cadenasBusqueda;

    @Getter @Setter
    private List<Fuente> fuentes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Revision revision = (Revision) o;

        return Objects.equals(id, revision.id);
    }

    @Override
    public int hashCode() {
        return 953636847;
    }
}
