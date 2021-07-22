package co.edu.utp.gia.sms.query.topico;

import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * Consulta que permite obtener los objetivos de una pregunta <br />
 */
@Entity
@NamedQuery(name = TopicoFindAllByPregunta.NAME, query = TopicoFindAllByPregunta.QUERY)
public class TopicoFindAllByPregunta extends Queries{
    public static final String NAME = "Topico.findAllByPregunta";
    public static final String QUERY = "select t from Topico t where t.pregunta.id = :id";

    /**
     * Consulta que permite obtener los topicos registradas en el sistema para una pregunta <br />
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Pregunta}
     * @return TypedQuery< Topico > que representa la consulta de las {@link Topico}
     */
    public static TypedQuery<Topico> createQuery(EntityManager entityManager, Integer id){
        return entityManager.createNamedQuery(NAME,Topico.class)
                .setParameter("id",id);
    }
}
