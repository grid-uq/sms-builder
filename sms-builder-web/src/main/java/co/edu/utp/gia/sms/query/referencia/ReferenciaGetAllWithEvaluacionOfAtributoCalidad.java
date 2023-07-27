package co.edu.utp.gia.sms.query.referencia;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.query.Queries;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;

/**
 * Consulta que permite obtener las referencias con evaliacón de un atributo de calidad dado
 */
@Entity
@NamedQuery(name = ReferenciaGetAllWithEvaluacionOfAtributoCalidad.NAME, query = ReferenciaGetAllWithEvaluacionOfAtributoCalidad.QUERY)
public class ReferenciaGetAllWithEvaluacionOfAtributoCalidad extends Queries{
    public static final String NAME = "Referencia.getAllWithEvaluacionOfAtributoCalidad";
    public static final String QUERY = "select new co.edu.utp.gia.sms.dtos.ReferenciaDTO( r ) " +
            "from Revision revision inner join revision.pasoSeleccionado.referencias r inner join r.evaluacionCalidad e " +
            "where revision.id = :id and e.atributoCalidad.id = :idAtributoCalidad  ORDER BY r.spsid,r.nombre";

    /**
     * Consulta que permite obtener las referencias con evaliacón de un atributo de calidad dado
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @param idAtributoCalidad Id del atributo de calidad
     * @return TypedQuery< Nota > que representa la consulta
     */
    public static TypedQuery<ReferenciaDTO> createQuery(EntityManager entityManager, Integer id, Integer idAtributoCalidad){
        return entityManager.createNamedQuery(NAME, ReferenciaDTO.class)
                .setParameter("id",id)
                .setParameter("idAtributoCalidad",idAtributoCalidad);
    }
}
