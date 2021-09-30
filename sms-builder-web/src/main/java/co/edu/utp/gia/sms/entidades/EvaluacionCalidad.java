package co.edu.utp.gia.sms.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @version 1.0
 * @since 23/06/2019
 */
@Entity
@NoArgsConstructor
public class EvaluacionCalidad implements Entidad<EvaluacionCalidadPK> {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 3246469713521362393L;

    /**
     * Atributo que permite identificar de forma unica una instancia de la entidad {@link EvaluacionCalidad}
     */
    @EmbeddedId
    @Getter @Setter
    private EvaluacionCalidadPK id;

    /**
     * Instancia de la referencia que se esta evaluando
     */
    @ManyToOne
    @MapsId("referenciaId")
    @Getter @Setter
    private Referencia referencia;

    /**
     * Instancia del atribudo de calidad que se esta evaluando
     */
    @ManyToOne
    @MapsId("atributoCalidadId")
    @Getter @Setter
    private AtributoCalidad atributoCalidad;

    /**
     * Evaluación cualitativa asignada
     */
    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private EvaluacionCualitativa evaluacionCualitativa;

    /**
     * Variable que representa el atributo evaluacionCuantitativa de la clase
     */
    @Getter @Setter
    private Float evaluacionCuantitativa;

    /**
     * Constructor de la {@link EvaluacionCalidad}, permite inicializar los datos de la evaluación
     *
     * @param referencia      {@link Referencia} que se va a evaluar
     * @param atributoCalidad {@link AtributoCalidad} a ser evaluado en la referencia
     */
    public EvaluacionCalidad(Referencia referencia, AtributoCalidad atributoCalidad) {
        this.referencia = referencia;
        this.atributoCalidad = atributoCalidad;
        id = new EvaluacionCalidadPK(referencia.getId(), atributoCalidad.getId());
    }

    /**
     * Permite establecer una evaluación
     *
     * @param evaluacion Evaluación a ser asignada
     */
    public void evaluar(EvaluacionCualitativa evaluacion) {
        setEvaluacionCualitativa(evaluacion);
        calcularEvaluacionCualitativa();
    }

    /**
     * Permite asignar un valor cuantitativo basado en la evaluación cualitativa
     */
    public void calcularEvaluacionCualitativa() {
        switch (evaluacionCualitativa) {
            case NO_CUMPLE:
                setEvaluacionCuantitativa(0.0F);
                break;
            case PARCIALMENTE:
                setEvaluacionCuantitativa(0.5F);
                break;
            case CUMPLE:
                setEvaluacionCuantitativa(1.0F);
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EvaluacionCalidad that = (EvaluacionCalidad) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
