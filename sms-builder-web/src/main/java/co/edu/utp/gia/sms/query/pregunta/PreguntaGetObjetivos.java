package co.edu.utp.gia.sms.query.pregunta;

import co.edu.utp.gia.sms.entidades.Objetivo;
import co.edu.utp.gia.sms.query.Queries;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;

/**
 * Consulta que permite obtener los objetivos de una pregunta <br />
 */
@Entity
@NamedQuery(name = PreguntaGetObjetivos.NAME, query = PreguntaGetObjetivos.QUERY)
public class PreguntaGetObjetivos extends Queries{
    public static final String NAME = "Pregunta.getObjetivos";
    public static final String QUERY = "select o from Pregunta p, IN(p.objetivos) o where p.id = :id";

    /**
     * Consulta que permite obtener los objetivos de una pregunta <br />
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Pregunta}
     * @return TypedQuery< Objetivo > que representa la consulta de las {@link Objetivo}
     */
    public static TypedQuery<Objetivo> createQuery(EntityManager entityManager, Integer id){
        return entityManager.createNamedQuery(NAME,Objetivo.class)
                .setParameter("id",id);
    }
}
