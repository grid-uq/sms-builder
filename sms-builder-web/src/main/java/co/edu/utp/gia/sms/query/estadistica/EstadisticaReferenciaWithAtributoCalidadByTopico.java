package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.query.Queries;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;

/**
 * Consulta que permite obtener el número de referencias que cumplem con un determinado atributo de calidad por Topico en una revision
 */
@Entity
@NamedQuery(name = EstadisticaReferenciaWithAtributoCalidadByTopico.NAME, query = EstadisticaReferenciaWithAtributoCalidadByTopico.QUERY)
public class EstadisticaReferenciaWithAtributoCalidadByTopico extends Queries {
    public static final String NAME = "Estadistica.referenciaWithAtributoCalidadByTopico";
    public static final String QUERY = "select new co.edu.utp.gia.sms.dtos.DatoDTO( CONCAT( t.pregunta.codigo,'-', t.descripcion ), COUNT(1) ) " +
            "from Revision revision inner join revision.pasoSeleccionado.referencias r LEFT JOIN r.topicos t " +
            "inner join r.evaluacionCalidad e " +
            "where revision.id = :id and e.atributoCalidad.id = :idAtributoCalidad " +
            "and e.evaluacionCualitativa = co.edu.utp.gia.sms.entidades.EvaluacionCualitativa.CUMPLE " +
            "GROUP BY t.id ORDER BY t.pregunta.id,t.descripcion";

    /**
     * Consulta que permite obtener el número de referencias que cumplem con un determinado atributo de calidad por Topico en una revision
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id            Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @param idAtributoCalidad Id del atributo de calidad
     * @return TypedQuery<DatoDTO> que representa la consulta de las {@link DatoDTO}
     */
    public static TypedQuery<DatoDTO> createQuery(EntityManager entityManager, Integer id, Integer idAtributoCalidad) {
        return entityManager.createNamedQuery(NAME, DatoDTO.class)
                .setParameter("id", id)
                .setParameter("idAtributoCalidad",idAtributoCalidad);
    }
}
