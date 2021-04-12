package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.Recurso;
import co.edu.utp.gia.sms.entidades.Rol;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

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
@Stateless
@LocalBean
public class RolEJB extends AbstractEJB<Rol, Integer> {

    public RolEJB() {
        super(Rol.class);
    }


    /**
     * Permite obtener un listado con todos los {@link Rol}s registrados en
     * el sistema
     *
     * @return {@link List} de {@link Rol}, con todos los {@link Rol}
     * registrados en el sistema
     */
    @Override
    public List<Rol> listar() {
        return entityManager.createNamedQuery(Rol.GET_ALL, Rol.class).getResultList();
    }

    /**
     * Permite adicionar un recurso a un rol
     * @param id Id del recurso al que se desea adicionar un recurso
     * @param recurso Recurso a ser adicionado
     */
    public void addRecurso(Integer id,Recurso recurso){
        Rol rol = obtenerOrThrow(id);
        rol.getRecursos().add(recurso);
    }
}
