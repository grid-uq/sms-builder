package co.edu.utp.gia.sms.query.objetivo;

import co.edu.utp.gia.sms.entidades.Objetivo;
import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * Consulta que permite obtener los Objetivos registrados en el sistema para una revision
 */
@Entity
@NamedQuery(name = ObjetivoFindAll.NAME, query = ObjetivoFindAll.QUERY)
public class ObjetivoFindAll extends Queries{
    public static final String NAME = "Objetivo.findAll";
    public static final String QUERY = "select o from Objetivo o where o.revision.id = :id";

    /**
     * Consulta que permite obtener los Objetivos registrados en el sistema para una revision
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @return TypedQuery< Objetivo > que representa la consulta de las {@link co.edu.utp.gia.sms.entidades.Objetivo}
     */
    public static TypedQuery<Objetivo> createQuery(EntityManager entityManager, Integer id){
        return entityManager.createNamedQuery(NAME,Objetivo.class)
                .setParameter("id",id);
    }
}
