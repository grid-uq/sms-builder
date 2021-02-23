package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.entidades.Objetivo;
import co.edu.utp.gia.sms.negocio.ObjetivoEJB;
import lombok.Getter;
import lombok.Setter;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class RegistroObjetivoBean extends GenericBean<Objetivo> {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 9060626480979863537L;
    @Getter
    @Setter
    private String codigo;
    @Getter
    @Setter
    private String descripcion;
    @Getter
    @Setter
    private List<Objetivo> objetivos;

    @Inject
    private ObjetivoEJB objetivoEJB;

    public void inicializar() {
        if (getRevision() != null) {
            objetivos = objetivoEJB.obtenerObjetivo(getRevision().getId());
        }
    }


    public void registrar() {
        Objetivo objetivo = objetivoEJB.registrar(codigo, descripcion, getRevision().getId());
        objetivos.add(objetivo);
        mostrarMensajeGeneral("Objetivo Adicionado");
        codigo = "";
        descripcion = "";
    }


    @Override
    public void actualizar(Objetivo objeto) {
        objetivoEJB.actualizar(objeto);
    }


    /**
     * Permite eliminar un Objetivo
     *
     * @param objetivo Objetivo a eliminar
     */
    public void eliminar(Objetivo objetivo) {
        objetivoEJB.eliminar(objetivo.getId());
        objetivos.remove(objetivo);
        mostrarMensajeGeneral("Registro eliminado");
    }

}
