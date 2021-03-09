package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.TopicoEJB;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
    private Integer id;
    @Inject
    private TopicoEJB topicoEJB;


    /**
     * Permite registrar un topico
     */
    public void registrar() {
        Topico topico = null;
        id = (Integer) getAndRemoveFromSession("idPregunta");
        if (id != null) {
            topico = topicoEJB.registrar(id, descripcion);
        }
        PrimeFaces.current().dialog().closeDynamic(topico);
    }


    @Override
    public void inicializar() {
        // No se requiere inicializar ningún dato
    }

}
