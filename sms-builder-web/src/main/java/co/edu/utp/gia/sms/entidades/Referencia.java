package co.edu.utp.gia.sms.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

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
@Entity
public class Referencia implements Entidad<Integer> {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = -4002756759383683632L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Getter @Setter
    private Integer id;
    /**
     * Variable que representa el atributo SPSID
     */
    @Column(length = 50)
    @Getter @Setter
    private String spsid;
    /**
     * Variable que representa el atributo nombre de la clase
     */
    @Lob
    @Getter @Setter
    private String nombre;
    /**
     * Variable que representa el atributo year de la clase
     */
    @Column(length = 4)
    @Getter @Setter
    private String year;
    /**
     * Variable que representa el atributo resumen de la clase
     */
    @Lob
    @Getter @Setter
    private String resumen;

    /**
     * Variable que representa el atributo tipo de la clase
     */
    @Getter @Setter
    private String tipo;

    /**
     * Variable que representa el filtro en el cual esta la referencia, todas
     * inician con 0 y va aumentando
     */
    @Getter @Setter
    private Integer filtro;

    @Getter @Setter
    private Float totalEvaluacionCalidad;

    @Getter @Setter
    private Integer relevancia;

    @Getter @Setter
    private Integer citas;

    @Getter @Setter
    private Float ponderacionCitas;

    @Lob
    @Getter @Setter
    private String nota;

    @Getter @Setter
    private Float sci;

    @Getter @Setter
    private Float srrqi;

    @Getter @Setter
    private Boolean duplicada;

    /**
     * Variable que representa el atributo metadatos de la clase
     */
    @OneToMany(mappedBy = "referencia", cascade = PERSIST)
    @Getter @Setter
    private List<Metadato> metadatos;

    /**
     * Variable que representa el atributo revision de la clase
     */
    @ManyToOne
    @Getter @Setter
    private Revision revision;

    @ManyToMany(fetch = EAGER)
    @Getter @Setter
    private List<Topico> topicos;

    @OneToMany(mappedBy = "referencia")
    @Getter @Setter
    private List<EvaluacionCalidad> evaluacionCalidad;

    /**
     * Metodo que permite inicializar los elementos de la clase Reference
     */
    public Referencia() {
        filtro = 0;
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
            case TITLE:
                setNombre(value);
                metadatos.add(new Metadato(identifier, value, this));
                break;
            case ABSTRACT:
                setResumen(value);
                metadatos.add(new Metadato(identifier, value, this));
                break;
            case TYPE:
                setTipo(value);
                metadatos.add(new Metadato(identifier, value, this));
                break;
            case YEAR:
                setYear(value);
                metadatos.add(new Metadato(identifier, value, this));
                break;
            default:
                metadatos.add(new Metadato(identifier, value, this));
                break;
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
