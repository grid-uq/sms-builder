package co.edu.utp.gia.sms.entidades;

import lombok.*;

import java.util.List;
import java.util.Objects;

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
public class Revision {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = -7643166662144090738L;

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
     * Lista de los pasos para la ejecución del proceso de SMS
     */
    @Getter @Setter
    private List<PasoProceso> pasosProceso;

    /**
     * Representa el paso seleccionado para la evaluación y extracción de estadísticas
     */
    @Getter @Setter
    private PasoProceso pasoSeleccionado;

    @Getter @Setter
    private List<AtributoCalidad> atributosCalidad;

    @Getter @Setter
    private List<CadenaBusqueda> cadenasBusqueda;

    @Getter @Setter
    private List<Fuente> fuentes;

    @Getter @Setter
    private List<Termino> terminos;

    @Getter @Setter
    private List<Referencia> referencias;

    @Getter @Setter
    private List<Usuario> usuarios;
    @Getter @Setter
    private List<Rol> roles;
    @Getter @Setter
    private List<Recurso> recursos;
    @Getter @Setter
    private List<Topico> topicos;
    @Getter @Setter
    private List<Pregunta> preguntas;

}
