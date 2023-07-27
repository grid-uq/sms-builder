package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.entidades.Revision;
import lombok.extern.java.Log;

import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
/**
 * Clase controladora de interfaz web que se encarga de validar una revisión.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@Named
@Log
public class ValidarRevisionBean extends GenericBean<Revision> {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 9092392501937374230L;

    public void validar() {
//		System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
//		System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestPathInfo());
//		System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath());

        String[] urls = {"/revision/registroRevision.xhtml", "/revision/seleccionarRevision.xhtml",
                "/revision/registroInicial.xhtml"};
        String[] pattern = {"/seguridad", "/administracion"};
        String path = getFacesContext().getExternalContext().getRequestServletPath();

        log.info("Validando path: "+path);
        if (getRevision() == null && !Arrays.asList(urls).contains(path) && Arrays.stream(pattern).noneMatch(path::startsWith)) {
            irInicio();
        }
    }

    @Override
    public void inicializar() {
        // No require inicializar ningún valor

    }

    private void irInicio() {
        try {
            log.info("REDIRECCIONANDO A INICIO");
            getFacesContext().getExternalContext()
                    .redirect(getFacesContext().getExternalContext().getRequestContextPath() + "/");
            getFacesContext().responseComplete();
        } catch (IOException e) {
            log.log(Level.WARNING, "Error inesperado", e);
        }
    }

    public void onIdle() {
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();
        irInicio();
    }

    public void onActive() {
        // No requiere realizar tarea cuando se detecta presencia del usuario
    }

}
