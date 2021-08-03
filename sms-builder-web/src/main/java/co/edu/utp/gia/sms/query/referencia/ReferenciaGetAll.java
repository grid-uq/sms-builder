package co.edu.utp.gia.sms.query.referencia;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * Consulta que permite obtener los Referencias seleccionadas de una revision
 */
@Entity
@NamedQuery(name = ReferenciaGetAll.NAME, query = ReferenciaGetAll.QUERY)
public class ReferenciaGetAll extends Queries{
    public static final String NAME = "Referencia.getAll";
    public static final String QUERY = "select new co.edu.utp.gia.sms.dtos.ReferenciaDTO( r , revision.pasoSeleccionado.orden ) " +
            "from Revision revision inner join revision.pasoSeleccionado.referencias r " +
            "where revision.id = :id ORDER BY r.spsid,r.nombre";

    /**
     * Consulta que permite obtener los Referencias seleccionadas de una revision
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
