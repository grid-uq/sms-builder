package co.edu.utp.gia.sms.query.referencia;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.EvaluacionCalidad;
import co.edu.utp.gia.sms.entidades.Referencia;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.List;
import java.util.stream.DoubleStream;

/**
 * Consulta que permite obtener el total de las evaluaciones de calidad registradas en el sistema para una referencia
 */

public class ReferenciaGetTotalEvaluacionCalidad {
    /**
     * Consulta que permite obtener el total de las evaluaciones de calidad registradas en el sistema para una referencia
     *
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @return DoubleStream que representa el flujo de datos de la consulta
     */
    public static DoubleStream createQuery(String id){
        return createQuery(DB.root.revision()::getReferencias,id);
    }

    /**
     * Consulta que permite obtener el total de las evaluaciones de calidad registradas en el sistema para una referencia
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @return DoubleStream que representa el flujo de datos de la consulta
     */
    public static DoubleStream createQuery(Provider<Collection<Referencia>> dataProvider, String id){
        return dataProvider.get().stream()
                .filter( referencia -> referencia.getId().equals(id))
                .map(Referencia::getEvaluacionCalidad)
                .flatMap(List::stream)
                .mapToDouble(EvaluacionCalidad::getEvaluacionCuantitativa);
    }
}
