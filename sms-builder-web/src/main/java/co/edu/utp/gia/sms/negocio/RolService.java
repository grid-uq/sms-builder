package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Recurso;
import co.edu.utp.gia.sms.entidades.Rol;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Clase de negocio encargada de implementar las funciones correspondientes a la
 * gestion del {@link Rol}.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
@ApplicationScoped
public class RolService extends AbstractGenericService<Rol, String> {

    public RolService() {
        super(DB.root.revision()::getRoles);
    }

    /**
     * Permite adicionar un recurso a un rol
     * @param id Id del recurso al que se desea adicionar un recurso
     * @param recurso Recurso a ser adicionado
     */
    public void addRecurso(String id,Recurso recurso){
        Rol rol = findOrThrow(id);
        rol.getRecursos().add(recurso);
    }
}
