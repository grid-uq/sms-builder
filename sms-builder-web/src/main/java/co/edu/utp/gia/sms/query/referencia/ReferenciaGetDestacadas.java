package co.edu.utp.gia.sms.query.referencia;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * Consulta que permite obtener los Referencias que han recivido una valoración de su contenido
 */
@Entity
@NamedQuery(name = ReferenciaGetDestacadas.NAME, query = ReferenciaGetDestacadas.QUERY)
public class ReferenciaGetDestacadas extends Queries{
    public static final String NAME = "Referencia.getDestacadas";
    public static final String QUERY = "select new co.edu.utp.gia.sms.dtos.ReferenciaDTO( r ) " +
            "from Revision revision inner join revision.pasoSeleccionado.referencias r " +
            "where revision.id = :id and r.relevancia is not null ORDER BY r.spsid,r.nombre";

    /**
     * Consulta que permite obtener los Referencias que han recivido una valoración de su contenido
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id de la Revision
     * @return TypedQuery< ReferenciaDTO > que representa la consulta
     */
    public static TypedQuery<ReferenciaDTO> createQuery(EntityManager entityManager, Integer id){
        return entityManager.createNamedQuery(NAME, ReferenciaDTO.class)
                .setParameter("id",id);
    }
}
