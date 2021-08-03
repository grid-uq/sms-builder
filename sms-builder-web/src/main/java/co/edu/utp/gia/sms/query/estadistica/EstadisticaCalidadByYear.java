package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * Consulta que permite obtener el promedio de calidad por año
 */
@Entity
@NamedQuery(name = EstadisticaCalidadByYear.NAME, query = EstadisticaCalidadByYear.QUERY)
public class EstadisticaCalidadByYear extends Queries {
    public static final String NAME = "Estadistica.calidadByYear";
    public static final String QUERY = "select new co.edu.utp.gia.sms.dtos.DatoDTO( r.year, AVG(r.totalEvaluacionCalidad) ) " +
            "from Revision revision inner join revision.pasoSeleccionado.referencias r where revision.id = :id " +
            "GROUP BY r.year ORDER BY r.year";

    /**
     * Consulta que permite obtener el promedio de calidad por año
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id            Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @return TypedQuery<DatoDTO> que representa la consulta
     */
    public static TypedQuery<DatoDTO> createQuery(EntityManager entityManager, Integer id) {
        return entityManager.createNamedQuery(NAME, DatoDTO.class)
                .setParameter("id", id);
    }
}
