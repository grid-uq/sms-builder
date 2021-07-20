package co.edu.utp.gia.sms.query;

import co.edu.utp.gia.sms.entidades.CadenaBusqueda;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

@Entity
@NamedQuery(name = CadenaBusquedaQuery.FindAll.NAME, query = CadenaBusquedaQuery.FindAll.QUERY)
public class CadenaBusquedaQuery extends Queries {
    private static final String BASE = "CadenaBusqueda.";
    /**
     * Permite encontrar todas las cadenas de busqueda registradas en una revision <br />
     *
     * Parametros: Id de la revision de la que se desean obtener las cadenas de busqueda.
     * <br />
     * <code>select c from CadenaBusqueda c where c.revision.id = :id</code>
     */
    public static final class FindAll{
        public static final String NAME = BASE +"findAll";
        public static final String QUERY = "select c from CadenaBusqueda c where c.revision.id = :id";

        /**
         * Permite encontrar todas las cadenas de busqueda registradas en una revision <br />
         * <code>select c from CadenaBusqueda c where c.revision.id = :id</code>
         * @param entityManager Para la ejecución de la consulta
         * @param id Id de la revision de la que se desean obtener las cadenas de busqueda.
         * @return TypedQuery< CadenaBusqueda > que representa la consulta de las {@link CadenaBusqueda}
         *
         */
        public static TypedQuery<CadenaBusqueda> createQuery(EntityManager entityManager, Integer id){
            return entityManager.createNamedQuery(NAME,CadenaBusqueda.class).setParameter("id",id);
        }
    }
}
