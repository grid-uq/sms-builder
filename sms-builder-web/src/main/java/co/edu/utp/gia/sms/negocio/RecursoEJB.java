package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.Recurso;
import co.edu.utp.gia.sms.query.seguridad.SeguridadRecursoFindAll;
import co.edu.utp.gia.sms.query.seguridad.SeguridadRecursoFindByUrl;
import co.edu.utp.gia.sms.query.seguridad.SeguridadRecursoFindUrlByPublic;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;


/**
 * Clase de negocio encargada de implementar las funciones correspondientes a la
 * gestion del {@link Recurso}.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
@Stateless
@LocalBean
public class RecursoEJB extends AbstractEJB<Recurso, Integer> {


    public RecursoEJB() {
        super(Recurso.class);
    }

    /**
     * Permite obtener un listado con todos los {@link Recurso}s registrados en
     * el sistema
     *
     * @return {@link List} de {@link Recurso}, con todos los {@link Recurso}
     * registrados en el sistema
     */
    @Override
    public List<Recurso> listar() {
        return SeguridadRecursoFindAll.createQuery(entityManager).getResultList();
    }

    /**
     * Metodo que permite buscar un {@link Recurso} basado en su url
     *
     * @param url Url del {@link Recurso} que se desea buscar
     * @return {@link Recurso} correspondiente al url dado, o null si no existe
     * un {@link Recurso} con dicho id registrado en el sistema
     */
    public Recurso buscarRecurso(String url) {
        return SeguridadRecursoFindByUrl.createQuery(entityManager,url)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    /**
     * Metodo que permite buscar los {@link Recurso} publicos
     *
     * @return Lista de las URL de los {@link Recurso} registrados en el sistema
     * como públicos
     */
    public List<String> buscarRecursosPublicos() {
        return SeguridadRecursoFindUrlByPublic.createQuery(entityManager,true).getResultList();
    }

}
