package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Recurso;
import co.edu.utp.gia.sms.query.seguridad.SeguridadRecursoFindByUrl;
import co.edu.utp.gia.sms.query.seguridad.SeguridadRecursoFindUrlByPublic;
import jakarta.enterprise.context.ApplicationScoped;

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
@ApplicationScoped
public class RecursoService extends AbstractGenericService<Recurso, String> {


    public RecursoService() {
        super(DB.root.getProvider(Recurso.class));
    }

    /**
     * Metodo que permite buscar un {@link Recurso} basado en su url
     *
     * @param url Url del {@link Recurso} que se desea buscar
     * @return {@link Recurso} correspondiente al url dado, o null si no existe
     * un {@link Recurso} con dicho id registrado en el sistema
     */
    public Recurso findByUrl(String url) {
        return SeguridadRecursoFindByUrl.createQuery(url)
                .findFirst()
                .orElse(null);
    }

    /**
     * Metodo que permite buscar los {@link Recurso} publicos
     *
     * @return Lista de las URL de los {@link Recurso} registrados en el sistema
     * como públicos
     */
    public List<String> buscarRecursosPublicos(boolean publico) {
        return SeguridadRecursoFindUrlByPublic.createQuery(publico).toList();
    }

}
