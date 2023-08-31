package co.edu.utp.gia.sms.entidades;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
@RequiredArgsConstructor
public class Revision implements Serializable {
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
     * Lista de objetivo de la revision
     */
    @Getter @Setter
    private List<Objetivo> objetivos = new ArrayList<>();

    /**
     * Lista de los pasos para la ejecución del proceso de SMS
     */
    @Getter @Setter
    private List<PasoProceso> pasosProceso = new ArrayList<>();

    /**
     * Representa el paso seleccionado para la evaluación y extracción de estadísticas
     */
    @Getter @Setter
    private PasoProceso pasoSeleccionado;

    @Getter @Setter
    private List<AtributoCalidad> atributosCalidad = new ArrayList<>();

    @Getter @Setter
    private List<CadenaBusqueda> cadenasBusqueda = new ArrayList<>();

    @Getter @Setter
    private List<Fuente> fuentes = new ArrayList<>();

    @Getter @Setter
    private List<Termino> terminos = new ArrayList<>();

    @Getter @Setter
    private List<Referencia> referencias = new ArrayList<>();

    @Getter @Setter
    private List<Usuario> usuarios = new ArrayList<>();
    @Getter @Setter
    private List<Rol> roles = new ArrayList<>();
    @Getter @Setter
    private List<Recurso> recursos = new ArrayList<>();
    @Getter @Setter
    private List<Topico> topicos = new ArrayList<>();
    @Getter @Setter
    private List<Pregunta> preguntas = new ArrayList<>();
    @Getter @Setter
    private List<Paso> pasos = new ArrayList<>();
}
