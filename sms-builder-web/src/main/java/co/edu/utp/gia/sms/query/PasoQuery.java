package co.edu.utp.gia.sms.query;

import co.edu.utp.gia.sms.entidades.Paso;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

@Entity
@NamedQuery(name = PasoQuery.FindByName.NAME, query = PasoQuery.FindByName.QUERY)
@NamedQuery(name = PasoQuery.FindAll.NAME, query = PasoQuery.FindAll.QUERY)
public class PasoQuery extends Queries {
    private static final String BASE = "Paso.";

    /**
     * Permite encontrar un paso a través de su nombre <br />
     * Parametros:  <b>nombre</b> Nombre del paso que se desea encontrar <br />
     * <br />
     * <code>select paso from Paso paso where paso.nombre = :nombre</code>
     */
    public static final class FindByName{
        public static final String NAME = BASE +"findByName";
        public static final String QUERY = "select paso from Paso paso where paso.nombre = :nombre";

        /**
         * Permite encontrar un paso a través de su nombre <br />
         * <code>select paso from Paso paso where paso.nombre = :nombre</code>
         * @param entityManager Para la ejecución de la consulta
         * @param nombre Nombre del paso que se desea encontrar
         * @return TypedQuery< Paso > que representa la consulta de las {@link Paso}
         *
         */
        public static TypedQuery<Paso> createQuery(EntityManager entityManager,String nombre){
            return entityManager.createNamedQuery(NAME, Paso.class).setParameter("nombre",nombre);
        }
    }

    /**
     * Permite encontrar todos los paso registrados <br />
     *
     * <br />
     * <code>select paso from Paso paso</code>
     */
    public static final class FindAll{
        public static final String NAME = BASE +"findAll";
        public static final String QUERY = "select paso from Paso paso";

        /**
         * Permite encontrar todos los paso registrados <br />
         * <code>select paso from Paso paso</code>
         * @param entityManager Para la ejecución de la consulta
         * @return TypedQuery< Paso > que representa la consulta de las {@link Paso}
         *
         */
        public static TypedQuery<Paso> createQuery(EntityManager entityManager){
            return entityManager.createNamedQuery(NAME, Paso.class);
        }
    }

}
