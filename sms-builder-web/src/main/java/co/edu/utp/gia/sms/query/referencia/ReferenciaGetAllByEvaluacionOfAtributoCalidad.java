package co.edu.utp.gia.sms.query.referencia;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.EvaluacionCalidad;
import co.edu.utp.gia.sms.entidades.EvaluacionCualitativa;
import co.edu.utp.gia.sms.entidades.Referencia;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener las referencias con una determinada calificación de un atributo de calidad dado
 */
public class ReferenciaGetAllByEvaluacionOfAtributoCalidad {
    /**
     * Consulta que permite obtener las referencias con una determinada calificación de un atributo de calidad dado
     *
     * @param idAtributoCalidad Id del atributo de calidad
     * @param valorEvaluacion Evaluación que deben cumplir las referencias seleccionadas
     * @return Stream<Referencia> que representa el resultado de la consulta
     */
    public static Stream<Referencia> createQuery(String idAtributoCalidad, EvaluacionCualitativa valorEvaluacion){
        return createQuery(DB.root.revision().getPasoSeleccionado()::getReferencias,idAtributoCalidad,valorEvaluacion);
    }

    /**
     * Consulta que permite obtener las referencias con una determinada calificación de un atributo de calidad dado
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @param idAtributoCalidad Id del atributo de calidad
     * @param valorEvaluacion Evaluación que deben cumplir las referencias seleccionadas
     * @return Stream<Referencia> que representa el resultado de la consulta
     */
    public static Stream<Referencia> createQuery(Provider<Collection<Referencia>> dataProvider, String idAtributoCalidad
            , EvaluacionCualitativa valorEvaluacion){
        Predicate<EvaluacionCalidad> filtro = evaluacion ->evaluacion.getAtributoCalidad().getId().equals(idAtributoCalidad)
                && evaluacion.getEvaluacionCualitativa().equals(valorEvaluacion);

        return dataProvider.get().stream()
                .filter(referencia -> referencia.getEvaluacionCalidad().stream().anyMatch(filtro))
                .sorted(Comparator.comparing(Referencia::getSpsid).thenComparing(Referencia::getNombre));
    }
}
