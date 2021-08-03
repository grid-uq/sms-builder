package co.edu.utp.gia.sms.query.revision;

import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.query.Queries;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 * Consulta que permite obtener un atributo de calidad de una revision basado en la descripcion
 */
@Entity
@NamedQuery(name = RevisionFindAtributoCalidadByDescription.NAME, query = RevisionFindAtributoCalidadByDescription.QUERY)
public class RevisionFindAtributoCalidadByDescription extends Queries{
    public static final String NAME = "Revision.findAtributoCalidadByDescription";
    public static final String QUERY = "select a from AtributoCalidad a where a.revision.id = :id and a.descripcion = :descripcion";

    /**
     * Consulta que permite obtener un atributo de calidad de una revision basado en la descripcion
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @param descripcion Descripcion del atributo de calidad que se desea obtener
     * @return TypedQuery< AtributoCalidad > que representa la consulta de las {@link AtributoCalidad}
     */
    public static TypedQuery<AtributoCalidad> createQuery(EntityManager entityManager, Integer id,String descripcion){
        return entityManager.createNamedQuery(NAME,AtributoCalidad.class)
                .setParameter("id",id)
                .setParameter("descripcion",descripcion);
    }
}
