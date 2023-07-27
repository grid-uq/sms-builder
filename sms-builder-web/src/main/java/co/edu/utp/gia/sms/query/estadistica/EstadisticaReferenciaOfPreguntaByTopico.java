package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.query.Queries;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;

/**
 * Consulta que permite obtener el número de referencias por Topico de una Pregunta en una revision
 */
@Entity
@NamedQuery(name = EstadisticaReferenciaOfPreguntaByTopico.NAME, query = EstadisticaReferenciaOfPreguntaByTopico.QUERY)
public class EstadisticaReferenciaOfPreguntaByTopico extends Queries {
    public static final String NAME = "Estadistica.referenciaOfPreguntaByTopico";
    public static final String QUERY = "select new co.edu.utp.gia.sms.dtos.DatoDTO( t.descripcion , COUNT(1) ) " +
            "from Revision revision inner join revision.pasoSeleccionado.referencias r LEFT JOIN r.topicos t  " +
            "where revision.id = :id and t.pregunta.codigo = :codigo " +
            "GROUP BY t.id ORDER BY t.pregunta.id,t.descripcion";

    /**
     * Consulta que permite obtener el número de referencias por Topico de una Pregunta en una revision
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id            Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @param codigo        Codigo de la pregunta de la que se desean obtener las estadisticas
     * @return TypedQuery<DatoDTO> que representa la consulta de las {@link DatoDTO}
     */
    public static TypedQuery<DatoDTO> createQuery(EntityManager entityManager, Integer id, String codigo) {
        return entityManager.createNamedQuery(NAME, DatoDTO.class)
                .setParameter("id", id)
                .setParameter("codigo",codigo);
    }
}
