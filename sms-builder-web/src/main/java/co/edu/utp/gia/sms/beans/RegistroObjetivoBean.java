package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.Objetivo;
import co.edu.utp.gia.sms.negocio.ObjetivoService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.Collection;
/**
 * Clase controladora de interfaz web que se encarga de la gestión de objetivos.
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
public class RegistroObjetivoBean extends GenericBean<Objetivo> {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    @Serial
    private static final long serialVersionUID = 9060626480979863537L;
    @Getter
    @Setter
    private String codigo;
    @Getter
    @Setter
    private String descripcion;
    @Getter
    @Setter
    private Collection<Objetivo> objetivos;

    @Inject
    private ObjetivoService objetivoService;

    public void inicializar() {
        if (getRevision() != null) {
            objetivos = objetivoService.get();
        }
    }


    public void registrar() {
        Objetivo objetivo = objetivoService.save(codigo, descripcion);
        objetivos.add(objetivo);
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        codigo = "";
        descripcion = "";
    }


    @Override
    public void actualizar(Objetivo objeto) {
        objetivoService.update(objeto);
    }


    /**
     * Permite eliminar un Objetivo
     *
     * @param objetivo Objetivo a eliminar
     */
    public void eliminar(Objetivo objetivo) {
        objetivoService.delete(objetivo.getId());
        objetivos.remove(objetivo);
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
    }

}
