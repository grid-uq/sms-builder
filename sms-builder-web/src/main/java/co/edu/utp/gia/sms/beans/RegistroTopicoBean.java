package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.TopicoService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
/**
 * Clase controladora de interfaz web que se encarga de la gestión de tópicos.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@Named
@ViewScoped
public class RegistroTopicoBean extends GenericBean<Topico> {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 5103003688870607449L;
    @Getter
    @Setter
    private String descripcion;
    @Getter
    @Setter
    private String id;
    @Inject
    private TopicoService topicoService;


    /**
     * Permite registrar un topico
     */
    public void registrar() {
        Topico topico = null;
        id = getAndRemoveFromSession("idPregunta").toString();
        if (id != null) {
            topico = topicoService.save(id, descripcion);
        }
        PrimeFaces.current().dialog().closeDynamic(topico);
    }


    @Override
    public void inicializar() {
        // No se requiere inicializar ningún dato
    }

}
