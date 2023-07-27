package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.query.Queries;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;

/**
 * Consulta que permite obtener las palabras claves y su número de apariciones en las referencias seleccionadas <br />
 */
@Entity
@NamedQuery(name = EstadisticaPalabrasClave.NAME, query = EstadisticaPalabrasClave.QUERY)
public class EstadisticaPalabrasClave extends Queries {
    public static final String NAME = "Estadistica.palabrasClave";
    public static final String QUERY = "select new co.edu.utp.gia.sms.dtos.DatoDTO( m.value , count(1)) " +
            "from Revision revision inner join revision.pasoSeleccionado.referencias r inner join r.metadatos m " +
            "where revision.id = :id and m.identifier = co.edu.utp.gia.sms.entidades.TipoMetadato.KEYWORD " +
            "group by m.value having count(1) >= :minimo ";

    /**
     * Consulta que permite obtener las palabras claves y su número de apariciones en las referencias seleccionadas
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id            Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @param minimo        Cantidad minima de apariciones que debe tener una palabra clave para ser considerada
     * @return TypedQuery<DatoDTO> que representa la consulta de las {@link DatoDTO}
     */
    public static TypedQuery<DatoDTO> createQuery(EntityManager entityManager, Integer id, Integer minimo) {
        return entityManager.createNamedQuery(NAME, DatoDTO.class)
                .setParameter("id", id)
                .setParameter("minimo", minimo);
    }
}