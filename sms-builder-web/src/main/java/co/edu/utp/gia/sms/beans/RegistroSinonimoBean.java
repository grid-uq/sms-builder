package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.TerminoService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
/**
 * Clase controladora de interfaz web que se encarga de la gestión de sinónimos.
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
public class RegistroSinonimoBean extends GenericBean<Topico> {

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
    private TerminoService terminoService;


    /**
     * Permite registrar un topico
     */
    public void registrar() {
        String sinonimo = null;
        id = (String) getAndRemoveFromSession("idTermino");
        if (id != null) {
            terminoService.adicionarSinonimo(id, descripcion);
        }
        PrimeFaces.current().dialog().closeDynamic(sinonimo);
    }


    @Override
    public void inicializar() {
        // No se requiere inicializar ningún dato
    }

}
