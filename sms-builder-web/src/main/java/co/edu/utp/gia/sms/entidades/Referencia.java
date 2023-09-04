package co.edu.utp.gia.sms.entidades;

import co.edu.utp.gia.sms.entidades.util.MetadatoUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * Elemento que representa de forma general una referencia a ser procesada
 *
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @version 1.0
 * @since 6/06/2019
 */
public class Referencia implements Entidad<String> {
    @Getter @Setter
    private String id = UUID.randomUUID().toString();
    /**
     * Variable que representa el atributo SPSID
     */
    @Getter @Setter
    private String spsid;
    /**
     * Variable que representa el atributo nombre de la clase
     */
    @Getter @Setter
    private String nombre;
    /**
     * Variable que representa el atributo year de la clase
     */
    @Getter @Setter
    private String year;
    /**
     * Variable que representa el atributo resumen de la clase
     */
    @Getter @Setter
    private String resumen;

    /**
     * Variable que representa el atributo tipo de la clase
     */
    @Getter @Setter
    private String tipo;

    /**
     * TODO Evaluar mover porque depende del paso o calcularlar
     */
    @Getter @Setter
    private Float totalEvaluacionCalidad;

    @Getter @Setter
    private Integer relevancia;

    @Getter @Setter
    private Integer citas;

    @Getter @Setter
    private Float ponderacionCitas;

    /**
     * TODO Podría remplazarse por una suma de las notas de cada paso
     */
    @Getter @Setter
    private String nota;

    @Getter @Setter
    private Float sci;

    @Getter @Setter
    private Float srrqi;

    /**
     * TODO podría adicionarse un listado de las referencias relacionadas como repetidas
     */
    @Getter @Setter
    private Boolean duplicada;
    @Getter @Setter
    private Fuente fuente;

    /**
     * Variable que representa el atributo metadatos de la clase
     */
    private List<Metadato> metadatos = new ArrayList<>();

    @Getter @Setter
    private List<Topico> topicos = new ArrayList<>();

    @Getter @Setter
    private List<EvaluacionCalidad> evaluacionCalidad = new ArrayList<>();

    /**
     * Metodo que permite inicializar los elementos de la clase Reference
     */
    public Referencia() {
        duplicada = false;
    }

    /**
     * Adiciona un nuevo atributo a una {@link Referencia}
     *
     * @param identifier Identificador del elemento que se adicionara
     * @param value      Valor del elemento a ser adicionado
     */
    public void addElement(TipoMetadato identifier, String value) {
        inicializarElementos();
        switch (identifier) {
            case TITLE -> {
                setNombre(value);
                metadatos.add(new Metadato(identifier, value, this));
            }
            case ABSTRACT -> {
                setResumen(value);
                metadatos.add(new Metadato(identifier, value, this));
            }
            case TYPE -> {
                setTipo(value);
                metadatos.add(new Metadato(identifier, value, this));
            }
            case YEAR -> {
                setYear(value);
                metadatos.add(new Metadato(identifier, value, this));
            }
            default -> metadatos.add(new Metadato(identifier, value, this));
        }
    }

    /**
     * Inicialia el listado de elementos de la referencia
     */
    private void inicializarElementos() {
        if (metadatos == null) {
            metadatos = new ArrayList<>();
        }
    }

    public List<Metadato> getMetadatos(){
        return metadatos;
    }

    public List<String> getAutores(){
        return MetadatoUtil.INSTANCE.getAutores(metadatos);
    }

    public String autoresAsString(){
        return MetadatoUtil.INSTANCE.getAutoresAsString(metadatos);
    }

    public List<String> keyWords(){
        return MetadatoUtil.INSTANCE.getKeyWords(metadatos);
    }

    public String keyWordsAsString(){
        return MetadatoUtil.INSTANCE.getKeyWordsAsString(metadatos);
    }

    public String abstractAsString(){
        return MetadatoUtil.INSTANCE.getAbstractAsString(metadatos);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Referencia that = (Referencia) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 524246431;
    }
}
