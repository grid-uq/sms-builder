package co.edu.utp.gia.sms.query.referencia;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.EvaluacionCualitativa;
import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * Consulta que permite obtener las referencias con una determinada calificación de un atributo de calidad dado
 */
@Entity
@NamedQuery(name = ReferenciaGetAllByEvaluacionOfAtributoCalidad.NAME, query = ReferenciaGetAllByEvaluacionOfAtributoCalidad.QUERY)
public class ReferenciaGetAllByEvaluacionOfAtributoCalidad extends Queries{
    public static final String NAME = "Referencia.getAllByEvaluacionOfAtributoCalidad";
    public static final String QUERY = "select new co.edu.utp.gia.sms.dtos.ReferenciaDTO( r ) " +
            "from Revision revision inner join revision.pasoSeleccionado.referencias r inner join r.evaluacionCalidad e " +
            "where revision.id = :id and e.atributoCalidad.id = :idAtributoCalidad " +
            "and e.evaluacionCualitativa = :valorEvaluacion  ORDER BY r.spsid,r.nombre";

    /**
     * Consulta que permite obtener las referencias con una determinada calificación de un atributo de calidad dado
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @param idAtributoCalidad Id del atributo de calidad
     * @param valorEvaluacion Evaluación que deben cumplir las referencias seleccionadas
     * @return TypedQuery< Nota > que representa la consulta
     */
    public static TypedQuery<ReferenciaDTO> createQuery(EntityManager entityManager, Integer id,
                                                        Integer idAtributoCalidad, EvaluacionCualitativa valorEvaluacion) {
        return entityManager.createNamedQuery(NAME, ReferenciaDTO.class)
                .setParameter("id",id)
                .setParameter("idAtributoCalidad",idAtributoCalidad)
                .setParameter("valorEvaluacion",valorEvaluacion);
    }
}
