package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.Termino;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.TerminoService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
/**
 * Clase controladora de interfaz web que se encarga de la gestión de terminos.
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
public class RegistroTerminoBean extends GenericBean<Termino> {
    @Getter @Setter
    private String descripcion;
    @Getter @Setter
    private Collection<Termino> terminos;
    @Inject
    private TerminoService terminoService;

    public void inicializar() {
        terminos = terminoService.get();
    }

    public void registrar() {
        Termino termino = terminoService.save(descripcion);
        terminos.add(termino);
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        descripcion = "";
    }

    @Override
    public void actualizar(Termino objeto) {
        terminoService.update(objeto);
    }

    /**
     * Permite eliminar una termino
     *
     * @param termino termino a eliminar
     */
    public void eliminar(Termino termino) {
        terminoService.delete(termino.getId());
        terminos.remove(termino);
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
    }

    public void adicionarSinonimo(Integer id) {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        addToSession("idTermino", id);
        PrimeFaces.current().dialog().openDynamic("/revision/registrarSinonimo", options, null);
    }

    public void eliminarSinonimo(Termino termino,String sinonimo){
        terminoService.removeSinonimo(termino.getId(),sinonimo);
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        termino.removerSinonimo(sinonimo);
    }

    public void onTopicoCreado(SelectEvent<Topico> event) {
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        inicializar();
    }
}

