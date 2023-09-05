package co.edu.utp.gia.sms.entidades;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Clase que representa la entidad Pregunta, la cual permite modelar en el
 * sistema las preguntas del SMS
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Pregunta implements Entidad<String> {
    /**
     * Identificador único de la pregunta
     */
    @Setter
    private String id = UUID.randomUUID().toString();
    /**
     * Variable que representa el atributo codigo de la clase
     */
    @Setter
    @NonNull
    private String codigo;
    /**
     * Variable que representa el atributo texto de la clase
     */
    @Setter
    @NonNull
    private String descripcion;


    /**
     * Variable que representa los topico de una pregunta
     */
    @Setter
    private List<Topico> topicos = new ArrayList<>();

    /**
     * Variable que representa los objetivo con los que se relaciona una pregunta
     */
    @Setter
    private List<Objetivo> objetivos = new ArrayList<>();


    /**
     * Metodo que permite inicializar los elementos de la clase Pregunta
     *
     * @param codigo      Codigo de la pregunta
     * @param descripcion Descripción de la pregunta
     * @param objetivos   Objetivos con los que se relaciona la pregunta
     */
    public Pregunta(String codigo, String descripcion, List<Objetivo> objetivos) {
        this(codigo, descripcion);
        this.objetivos = objetivos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pregunta pregunta = (Pregunta) o;

        return Objects.equals(getId(), pregunta.getId());
    }

    @Override
    public int hashCode() {
        return 119604760;
    }

}
