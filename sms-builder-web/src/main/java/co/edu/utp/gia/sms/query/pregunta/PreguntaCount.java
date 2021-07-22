package co.edu.utp.gia.sms.query.pregunta;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.dtos.PreguntaDTO;
import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * Consulta que permite obtener el número de preguntas registradas en el sistema para una revision <br />
 */
@Entity
@NamedQuery(name = PreguntaCount.NAME, query = PreguntaCount.QUERY)
public class PreguntaCount extends Queries{
    public static final String NAME = "Pregunta.count";
    public static final String QUERY = "select count(1)  from Pregunta p, IN(p.objetivos) o where o.revision.id = :id order by p.codigo";

    /**
     * Consulta que permite obtener el número de preguntas registradas en el sistema para una revision <br />
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @return TypedQuery< DatoDTO > que representa la consulta de las {@link DatoDTO}
     */
    public static TypedQuery<Long> createQuery(EntityManager entityManager, Integer id){
        return entityManager.createNamedQuery(NAME,Long.class)
                .setParameter("id",id);
    }
}
