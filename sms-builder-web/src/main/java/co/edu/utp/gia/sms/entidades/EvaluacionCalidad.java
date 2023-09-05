package co.edu.utp.gia.sms.entidades;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.util.UUID;

/**
 * Clase que representa una evaluación de calidad en un SMS.
 *
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @version 1.0
 * @since 23/06/2019
 */
@Getter
@NoArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class EvaluacionCalidad implements Entidad<String> {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    @Serial
    private static final long serialVersionUID = 3246469713521362393L;

    @Setter
    private String id = UUID.randomUUID().toString();
    /**
     * Instancia de la referencia que se esta evaluando
     */
    @Setter
    private Referencia referencia;

    /**
     * Instancia del atribudo de calidad que se esta evaluando
     */
    @Setter
    private AtributoCalidad atributoCalidad;

    /**
     * Evaluación cualitativa asignada
     */
    @Setter
    private EvaluacionCualitativa evaluacionCualitativa;

    /**
     * Variable que representa el atributo evaluacionCuantitativa de la clase
     */
    @Setter
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
            case NO_CUMPLE -> setEvaluacionCuantitativa(0.0F);
            case PARCIALMENTE -> setEvaluacionCuantitativa(0.5F);
            case CUMPLE -> setEvaluacionCuantitativa(1.0F);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EvaluacionCalidad that)) return false;

        if (!referencia.equals(that.referencia)) return false;
        return atributoCalidad.equals(that.atributoCalidad);
    }

    @Override
    public int hashCode() {
        int result = referencia.hashCode();
        result = 31 * result + atributoCalidad.hashCode();
        return result;
    }
}
