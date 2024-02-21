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
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class Revision implements Serializable {
    /**
     * Variable que representa el atributo nombre de la clase
     */

    @Setter
    @NonNull
    private String nombre;
    /**
     * Variable que representa el atributo descripcion de la clase
     */
    @Setter
    @NonNull
    private String descripcion;

    /**
     * Lista de objetivo de la revision
     */
    @Setter
    private List<Objetivo> objetivos = new ArrayList<>();

    /**
     * Lista de los pasos para la ejecución del proceso de SMS
     */
    @Setter
    private List<PasoProceso> pasosProceso = new ArrayList<>();

//    /**
//     * Representa el paso que está siendo procesado
//     */
//    @Setter
//    private PasoProceso pasoActual;

    @Setter
    private List<AtributoCalidad> atributosCalidad = new ArrayList<>();

    @Setter
    private List<CadenaBusqueda> cadenasBusqueda = new ArrayList<>();

    @Setter
    private List<Fuente> fuentes = new ArrayList<>();

    @Setter
    private List<Termino> terminos = new ArrayList<>();

    @Setter
    private List<Referencia> referencias = new ArrayList<>();

    @Setter
    private List<Usuario> usuarios = new ArrayList<>();
    @Setter
    private List<Rol> roles = new ArrayList<>();
    @Setter
    private List<Recurso> recursos = new ArrayList<>();
    @Setter
    private List<Topico> topicos = new ArrayList<>();
    @Setter
    private List<Pregunta> preguntas = new ArrayList<>();
    @Setter
    private List<Paso> pasos = new ArrayList<>();
    @Setter
    private List<CriterioSeleccion> criteriosSeleccion = new ArrayList<>();

//    public PasoProceso getPasoActual(){
//        if( pasoActual == null ){
//            pasoActual = pasosProceso.stream().filter(paso->paso.getOrden()==1).findFirst().orElse(null);
//        }
//        return pasoActual;
//    }
}
