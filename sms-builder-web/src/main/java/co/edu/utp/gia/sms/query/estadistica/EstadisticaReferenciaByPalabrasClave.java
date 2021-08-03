package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Consulta que permite obtener las Referencias que contienen en uno de sus metadatos la palabra dada<br />
 */
@Entity
@NamedQuery(name = EstadisticaReferenciaByPalabrasClave.NAME, query = EstadisticaReferenciaByPalabrasClave.QUERY)
public class EstadisticaReferenciaByPalabrasClave extends Queries {
    public static final String NAME = "Estadistica.referenciaPalabrasClave";
    public static final String QUERY = "select DISTINCT( m.referencia ) from Revision revision " +
            "inner join revision.pasoSeleccionado.referencias r LEFT JOIN r.metadatos m " +
            "where revision.id = :id and m.identifier in :identifiers and UPPER(m.value) like UPPER(:value) ";

    /**
     * Consulta que permite obtener las Referencias que contienen en uno de sus metadatos la palabra dada <br />
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id            Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @param keyword       Palabra a buscar
     * @param metadatos     Listado de tipos de metadatos a incluir en la búsqueda.
     * @return TypedQuery<Referencia> que representa la consulta
     */
    public static TypedQuery<Referencia> createQuery(EntityManager entityManager, Integer id, String keyword, List<TipoMetadato> metadatos) {
        return entityManager.createNamedQuery(NAME, Referencia.class)
                .setParameter("id", id)
                .setParameter("value", String.format("%%%s%%", keyword))
                .setParameter("identifiers", metadatos);
    }
}
