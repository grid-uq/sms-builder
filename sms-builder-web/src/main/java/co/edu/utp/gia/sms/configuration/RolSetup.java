package co.edu.utp.gia.sms.configuration;

import co.edu.utp.gia.sms.entidades.Rol;
import co.edu.utp.gia.sms.negocio.RecursoService;
import co.edu.utp.gia.sms.negocio.RolService;

import jakarta.inject.Inject;
/**
 * Clase encargada de realizar la configuración inicial de los roles en la aplicación
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */

public class RolSetup implements SetupInterface{
    /**
     * Variable que representa el atributo rolBO de la clase
     */
    @Inject
    private RolService rolBO;
    /**
     * Variable que representa el atributo recursoBO de la clase
     */
    @Inject
    private RecursoService recursoBO;

    @Override
    public void setup() {
        registrarRecursosRol(1,"Administrador", RecursosConstants.RECURSOS_ADMINISTRADOR);
        registrarRecursosRol(2,"Usuario", RecursosConstants.RECURSOS_USUARIO);
    }

    private void registrarRecursosRol(Integer id,String nombre,String[] recursos){
        var rol = new Rol(id,nombre);
        for (String url : recursos) {
            var recurso = recursoBO.findByUrl(url);
            if (recurso != null) {
                rol.getRecursos().add(recurso);
            }
        }
        if( rolBO.obtener(id) == null ){
            rolBO.registrar(rol);
        } else {
            rolBO.actualizar(rol);
        }
    }

}
