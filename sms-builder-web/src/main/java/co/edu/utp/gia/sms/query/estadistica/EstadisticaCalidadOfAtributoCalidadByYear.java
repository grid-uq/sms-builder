package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * Consulta que permite obtener el promedio de la evaluación de calidad de un determinado atributo de calidad por Año en una revision
 */
@Entity
@NamedQuery(name = EstadisticaCalidadOfAtributoCalidadByYear.NAME, query = EstadisticaCalidadOfAtributoCalidadByYear.QUERY)
public class EstadisticaCalidadOfAtributoCalidadByYear extends Queries {
    public static final String NAME = "Estadistica.calidadOfAtributoCalidadByYear";
    public static final String QUERY = "select new co.edu.utp.gia.sms.dtos.DatoDTO( r.year, AVG(e.evaluacionCuantitativa) ) " +
            "from Revision revision inner join revision.pasoSeleccionado.referencias r inner join r.evaluacionCalidad e " +
            "where revision.id = :id and e.atributoCalidad.id = :idAtributoCalidad GROUP BY r.year ORDER BY r.year";

    /**
     * Consulta que permite obtener el promedio de la evaluación de calidad de un determinado atributo de calidad por Año en una revision
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id            Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @param idAtributoCalidad Id del atributo de calidad
     * @return TypedQuery<DatoDTO> que representa la consulta
     */
    public static TypedQuery<DatoDTO> createQuery(EntityManager entityManager, Integer id, Integer idAtributoCalidad) {
        return entityManager.createNamedQuery(NAME, DatoDTO.class)
                .setParameter("id", id)
                .setParameter("idAtributoCalidad",idAtributoCalidad);
    }
}
