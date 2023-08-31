/**
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Julio Cesar Chavarro
 * @author Carlos Augusto Meneces
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 9 abr. 2020
 */
package co.edu.utp.gia.sms.dtos;

import co.edu.utp.gia.sms.entidades.Objetivo;
import co.edu.utp.gia.sms.entidades.Topico;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * Clase usada para encapsular datos de una pregunta que serán mostrados al usuario.
 *
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Julio Cesar Chavarro
 * @author Carlos Augusto Meneces
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 9 abr. 2020
 *
 */
@EqualsAndHashCode
@RequiredArgsConstructor
public class PreguntaDTO implements Serializable {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 3245868826313123028L;
    /**
     * Variable que representa el atributo id de la clase
     */
    @Getter
    @Setter
    @NonNull
    private String id;
    /**
     * Variable que representa el atributo codigo de la clase
     */
    @Getter
    @Setter
    @NonNull
    @EqualsAndHashCode.Exclude
    private String codigo;
    /**
     * Variable que representa el atributo texto de la clase
     */
    @Getter
    @Setter
    @NonNull
    @EqualsAndHashCode.Exclude
    private String descripcion;

    /**
     * Variable que representa los topico de una pregunta
     */
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    private List<Topico> topicos;

    /**
     * Variable que representa los objetivo con los que se relaciona una pregunta
     */
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    private List<Objetivo> objetivos;

    /**
     * Metodo que permite inicializar los elementos de la clase PreguntaDTO
     * @param id
     * @param codigo
     * @param descripcion
     * @param topicos
     * @param objetivos
     */
    public PreguntaDTO(String id, String codigo, String descripcion, List<Topico> topicos, List<Objetivo> objetivos) {
        this(id, codigo, descripcion);
        this.topicos = topicos;
        this.objetivos = objetivos;
    }

    public String getListObjetivos() {
        String textoObjetivos = "";
        String separador = "";
        for (Objetivo objetivo : objetivos) {
            textoObjetivos += separador + objetivo.getCodigo();
            separador = ", ";
        }
        return textoObjetivos;
    }

}
