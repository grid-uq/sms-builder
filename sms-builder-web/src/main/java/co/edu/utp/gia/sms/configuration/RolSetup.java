package co.edu.utp.gia.sms.configuration;

import co.edu.utp.gia.sms.entidades.Rol;
import co.edu.utp.gia.sms.negocio.RecursoEJB;
import co.edu.utp.gia.sms.negocio.RolEJB;

import javax.inject.Inject;

public class RolSetup implements SetupInterface{
    /**
     * Variable que representa el atributo rolBO de la clase
     */
    @Inject
    private RolEJB rolBO;
    /**
     * Variable que representa el atributo recursoBO de la clase
     */
    @Inject
    private RecursoEJB recursoBO;

    @Override
    public void setup() {
        registrarRecursosRol(1,"Administrador", RecursosConstants.RECURSOS_ADMINISTRADOR);
        registrarRecursosRol(2,"Usuario", RecursosConstants.RECURSOS_USUARIO);
    }

    private void registrarRecursosRol(Integer id,String nombre,String[] recursos){
        var rol = new Rol(id,nombre);
        for (String url : recursos) {
            var recurso = recursoBO.buscarRecurso(url);
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
