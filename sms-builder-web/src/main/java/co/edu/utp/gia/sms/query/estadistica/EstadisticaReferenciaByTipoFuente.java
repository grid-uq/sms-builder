package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * Consulta que permite obtener el número de referencias por tipo de fuente en una revision <br />
 */
@Entity
@NamedQuery(name = EstadisticaReferenciaByTipoFuente.NAME, query = EstadisticaReferenciaByTipoFuente.QUERY)
public class EstadisticaReferenciaByTipoFuente extends Queries {
    public static final String NAME = "Estadistica.referenciaTipoFuente";
    public static final String QUERY = "select new co.edu.utp.gia.sms.dtos.DatoDTO( f.tipo , count(1)) " +
            "from Revision revision inner join revision.pasoSeleccionado.referencias r inner join r.metadatos m,Fuente f " +
            "where revision.id = :id and m.identifier = co.edu.utp.gia.sms.entidades.TipoMetadato.FUENTE " +
            "and m.value = f.nombre group by f.tipo ";

    /**
     * Consulta que permite obtener el número de referencias por tipo de fuente en una revision <br />
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id            Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @return TypedQuery<DatoDTO> que representa la consulta de las {@link DatoDTO}
     */
    public static TypedQuery<DatoDTO> createQuery(EntityManager entityManager, Integer id) {
        return entityManager.createNamedQuery(NAME, DatoDTO.class)
                .setParameter("id", id);
    }
}
