package co.edu.utp.gia.sms.query.topico;

import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.query.Queries;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;

/**
 * Consulta que permite obtener los topicos registradas en el sistema para una revision <br />
 */
@Entity
@NamedQuery(name = TopicoFindAll.NAME, query = TopicoFindAll.QUERY)
public class TopicoFindAll extends Queries{
    public static final String NAME = "Topico.findAll";
    public static final String QUERY = "select t from Topico t , IN(t.pregunta.objetivos) o " +
            "where o.revision.id = :id order by t.pregunta.codigo,t.descripcion";

    /**
     * Consulta que permite obtener los topicos registradas en el sistema para una revision <br />
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @return TypedQuery< Topico > que representa la consulta de las {@link Topico}
     */
    public static TypedQuery<Topico> createQuery(EntityManager entityManager, Integer id){
        return entityManager.createNamedQuery(NAME,Topico.class)
                .setParameter("id",id);
    }
}
