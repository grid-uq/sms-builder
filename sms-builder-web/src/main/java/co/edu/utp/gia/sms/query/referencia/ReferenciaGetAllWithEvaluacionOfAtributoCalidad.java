package co.edu.utp.gia.sms.query.referencia;

import co.edu.utp.gia.sms.entidades.EvaluacionCalidad;
import co.edu.utp.gia.sms.entidades.Referencia;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener las referencias con evaliacón de un atributo de calidad dado
 */
public class ReferenciaGetAllWithEvaluacionOfAtributoCalidad {
    /**
     * Consulta que permite obtener las referencias con evaliacón de un atributo de calidad dado
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @return Stream<Referencia> que representa el resultado de la consulta
     */
    public static Stream<Referencia> createQuery(Provider<Collection<Referencia>> dataProvider,String idAtributoCalidad){
        Predicate<EvaluacionCalidad> filtro = evaluacion ->evaluacion.getAtributoCalidad().getId().equals(idAtributoCalidad);

        return dataProvider.get().stream()
                .filter(referencia -> referencia.getEvaluacionCalidad().stream().anyMatch(filtro))
                .sorted(Comparator.comparing(Referencia::getSpsid).thenComparing(Referencia::getNombre));
    }
}
