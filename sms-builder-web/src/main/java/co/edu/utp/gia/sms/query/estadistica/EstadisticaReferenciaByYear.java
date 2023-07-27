package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.query.Queries;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;

/**
 * Consulta que permite obtener el número de referencias por año en una revision
 */
@Entity
@NamedQuery(name = EstadisticaReferenciaByYear.NAME, query = EstadisticaReferenciaByYear.QUERY)
public class EstadisticaReferenciaByYear extends Queries {
    public static final String NAME = "Estadistica.referenciaByYear";
    public static final String QUERY = "select new co.edu.utp.gia.sms.dtos.DatoDTO( r.year, COUNT(1) ) " +
            "from Revision revision inner join revision.pasoSeleccionado.referencias r " +
            "where revision.id = :id GROUP BY r.year ORDER BY r.year";

    /**
     * Consulta que permite obtener el número de referencias por año en una revision
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
