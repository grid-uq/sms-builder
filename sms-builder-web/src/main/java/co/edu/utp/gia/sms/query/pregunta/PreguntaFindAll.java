package co.edu.utp.gia.sms.query.pregunta;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.dtos.PreguntaDTO;
import co.edu.utp.gia.sms.query.Queries;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;

/**
 * Consulta que permite obtener las preguntas registradas en el sistema para una revision <br />
 */
@Entity
@NamedQuery(name = PreguntaFindAll.NAME, query = PreguntaFindAll.QUERY)
public class PreguntaFindAll extends Queries{
    public static final String NAME = "Pregunta.findAll";
    public static final String QUERY = "select distinct new co.edu.utp.gia.sms.dtos.PreguntaDTO(p.id, p.codigo, p.descripcion) from Pregunta p, IN(p.objetivos) o where o.revision.id = :id order by p.codigo";

    /**
     * Consulta que permite obtener las preguntas registradas en el sistema para una revision <br />
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @return TypedQuery< DatoDTO > que representa la consulta de las {@link DatoDTO}
     */
    public static TypedQuery<PreguntaDTO> createQuery(EntityManager entityManager, Integer id){
        return entityManager.createNamedQuery(NAME,PreguntaDTO.class)
                .setParameter("id",id);
    }
}
