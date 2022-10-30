package co.edu.utp.gia.sms.configuration;

import co.edu.utp.gia.sms.entidades.Recurso;
import co.edu.utp.gia.sms.negocio.RecursoEJB;

import javax.inject.Inject;
/**
 * Clase encargada de realizar la configuración inicial de los recursos en la aplicación
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
public class RecursosSetup implements SetupInterface{

    /**
     * Variable que representa el atributo recursoBO de la clase
     */
    @Inject
    private RecursoEJB recursoBO;



    @Override
    public void setup() {
        for (var recurso : RecursosConstants.RECURSOS_ADMINISTRADOR) {
            setupRecurso(recurso, false);
        }
        for (var recurso : RecursosConstants.RECURSOS_USUARIO) {
            setupRecurso(recurso, false);
        }
        for (var recurso : RecursosConstants.RECURSOS_PUBLICOS) {
            setupRecurso(recurso, true);
        }
    }

    /**
     * Método encargado de registrar un recurso en el sistema
     *
     * @param url
     *            Url del recurso a ser registrado
     * @param publico
     *            Indica si el registro es público o no
     */
    private void setupRecurso(String url, boolean publico) {
        if (recursoBO.buscarRecurso(url) == null) {
            var recurso = new Recurso(getNombreRecurso(url),url,publico);
            recursoBO.registrar(recurso);
        }
    }

    private String getNombreRecurso(String url) {
        String[] nombre = url.split("/");
        var nombreRecurso = new StringBuilder(nombre[1]);
        for (var i = 2; i < nombre.length && !"index.xhtml".equals(nombre[i]); i++) {
            nombreRecurso.append("_").append(nombre[i]);
        }
        return nombreRecurso.toString().replaceAll(".xhtml", "");
    }
}
