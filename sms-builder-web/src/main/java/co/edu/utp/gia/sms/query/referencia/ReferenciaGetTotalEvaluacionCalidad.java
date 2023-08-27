package co.edu.utp.gia.sms.query.referencia;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.EvaluacionCalidad;
import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.query.Queries;

import jakarta.inject.Provider;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;

import java.util.Collection;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener el total de las evaluaciones de calidad registradas en el sistema para una referencia
 */
@Entity
@NamedQuery(name = ReferenciaGetTotalEvaluacionCalidad.NAME, query = ReferenciaGetTotalEvaluacionCalidad.QUERY)
public class ReferenciaGetTotalEvaluacionCalidad extends Queries{
    public static final String NAME = "Referencia.getTotalEvaluacionCalidad";
    public static final String QUERY = "select SUM(e.evaluacionCuantitativa) from EvaluacionCalidad e where e.referencia.id = :id";

    /**
     * Consulta que permite obtener el total de las evaluaciones de calidad registradas en el sistema para una referencia
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @return TypedQuery< Double > que representa la consulta
     */
    public static TypedQuery<Double> createQuery(EntityManager entityManager, Integer id){
        return entityManager.createNamedQuery(NAME, Double.class)
                .setParameter("id",id);
    }

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
                .mapToDouble(EvaluacionCalidad::getEvaluacionCuantitativa)
                .distinct();
    }
}
