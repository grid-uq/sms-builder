package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.entidades.TipoFuente;
import co.edu.utp.gia.sms.query.Queries;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;

/**
 * Consulta que permite obtener el número de referencias por cada fuente de un determinado tipo de fuente en una revision <br />
 */
@Entity
@NamedQuery(name = EstadisticaReferenciaByTipoFuenteAndNombre.NAME, query = EstadisticaReferenciaByTipoFuenteAndNombre.QUERY)
public class EstadisticaReferenciaByTipoFuenteAndNombre extends Queries {
    public static final String NAME = "Estadistica.referenciaTipoFuenteNombre";
    public static final String QUERY = "select new co.edu.utp.gia.sms.dtos.DatoDTO( f.nombre , count(1)) " +
            "from Revision revision inner join revision.pasoSeleccionado.referencias r LEFT JOIN r.metadatos m,Fuente f " +
            "where revision.id = :id " +
            "and m.identifier = co.edu.utp.gia.sms.entidades.TipoMetadato.FUENTE " +
            "and m.value = f.nombre " +
            "and f.tipo = :tipo group by f.nombre ";

    /**
     * Consulta que permite obtener el número de referencias por cada fuente de un determinado tipo de fuente en una revision <br />
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id            Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @param tipoFuente    Tipo de fuente de las referencias a tener en cuenta
     * @return TypedQuery<DatoDTO> que representa la consulta de las {@link DatoDTO}
     */
    public static TypedQuery<DatoDTO> createQuery(EntityManager entityManager, Integer id, TipoFuente tipoFuente) {
        return entityManager.createNamedQuery(NAME, DatoDTO.class)
                .setParameter("id", id)
                .setParameter("tipo", tipoFuente);
    }
}