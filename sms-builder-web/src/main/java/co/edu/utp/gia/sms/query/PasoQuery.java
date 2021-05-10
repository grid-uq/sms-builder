package co.edu.utp.gia.sms.query;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = PasoQuery.PASOS_FIND_BY_NAME, query = "select paso from Paso paso where paso.nombre = :nombre")
@NamedQuery(name = PasoQuery.PASOS_FIND_ALL, query = "select paso from Paso paso")
public class PasoQuery extends Queries {
    /**
     * Permite encontrar un paso a través de su nombre <br />
     *
     * @param nombre Nombre del paso que se desea encontrar <br />
     * <code>select paso from Paso paso where paso.nombre = :nombre </code>
     */
    public static final String PASOS_FIND_BY_NAME = "Paso.findByName";
    /**
     * Permite encontrar todos los paso registrados <br />
     *
     * <code>select paso from Paso paso </code>
     */
    public static final String PASOS_FIND_ALL = "Paso.findAll";
}
