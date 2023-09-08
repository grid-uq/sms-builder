package co.edu.utp.gia.sms.beans.seguridad;

import co.edu.utp.gia.sms.beans.AbstractBean;
import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.EstadoUsuario;
import co.edu.utp.gia.sms.entidades.Usuario;
import co.edu.utp.gia.sms.negocio.UsuarioService;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.primefaces.event.RowEditEvent;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;

/**
 * Clase encargada la interacción del usuario con las funcionalidades
 * de gestion de los {@link Usuario}
 *
 * @author Christian A. Candela <christiancandela@grid.edu.co>
 * @author Luis E. Sepúlveda <lesepulveda@grid.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 2015-12-02
 */
@Log
public abstract class UsuarioBean extends AbstractBean {
    /**
     * Variable que representa el atributo usuarioAutenticado de la clase.
     * Instancia del usuario autenticado en el sistema
     */
    @Inject
    @ManagedProperty("#{seguridadBean.usuario}")
    @Getter
    @Setter
    private Usuario usuarioAutenticado;

    /**
     * Variable que representa el atributo verificacionClave de la clase. Es
     * usado como verificación de la clave dada por el usuario
     */
    @Getter
    @Setter
    private String verificacionClave;
    /**
     * Variable que representa el atributo nuevaClave de la clase. Es usado para
     * el ingreso de la nueva clave cuando la misma va a ser actualizada por el
     * usuario
     */
    @Getter
    @Setter
    private String nuevaClave;
    /**
     * Variable que representa el atributo claveActual de la clase. Es usada
     * para verificar la clave actual cuando el usuario está actualizando sus
     * datos
     */
    @Getter
    @Setter
    private String claveActual;
    /**
     * Variable que representa el atributo verificacionClaveEdit de la clase. Es
     * usado como verificación de la clave dada por el usuario. Esta variable es
     * usada cuando se esta editando el usuario;
     */
    @Getter
    @Setter
    private String verificacionClaveEdit;

    /**
     * Variable que representa el atributo usuarios de la clase. Este contiene
     * la lista de usuarios registrados en el sistema.
     */
    @Getter
    @Setter
    private Collection<Usuario> usuarios;

    /**
     * Variable que representa el atributo usuarioBO de la clase
     */
    @Inject
    private UsuarioService usuarioService;

    /**
     * Variable que representa el atributo editId de la clase. Id de la
     * {@link UsuarioBean} que será editada.
     */
    @Getter
    @Setter
    private String editId;

    /**
     * Variable que representa el listado de valores de tipo
     * {@link EstadoUsuario}
     */
    @Getter
    private List<EstadoUsuario> estados;

    /**
     * Metodo encargado de inicializar los datos de la clase
     */
    public void inicializar() {
        usuarios = usuarioService.get();
        setUsuario(newUsuario());
        estados = Arrays.asList(EstadoUsuario.values());
    }

    /**
     * Método que crear una instacia de Usuario
     *
     * @return nueva instancia de Usuario
     */
    public abstract Usuario newUsuario();

    /**
     * Metodo que permite registrar un usuario en el sistema
     */
    public String registrar() {
        try {
            usuarioService.create(getUsuario(), verificacionClave);
            setUsuario(newUsuario());
            verificacionClave = "";
            usuarios = usuarioService.get();
            mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        } catch (Exception e) {
            log.log(Level.INFO,"Error al registar un usuario "+e.getMessage(),e);
            mostrarErrorGeneral(e.getMessage());
        }
        return "/index.xhtml";
    }

    /**
     * Metodo que permite eliminar un usuario del sistema
     *
     * @param usuario Usuario a ser eliminado
     */
    public void eliminar(Usuario usuario) {
        try {
            usuarioService.delete(usuario);
            usuarios = usuarioService.get();
            mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        } catch (Exception e) {
            mostrarErrorGeneral(e.getMessage());
        }
    }

    /**
     * Metodo encargado de responder al evento de edicion de usuario
     *
     * @param event Evento generado al editar un usuario
     */
    public void onRowEdit(RowEditEvent<Usuario> event) {
        Usuario usuario = event.getObject();
        actualizar(usuario);
    }

    /**
     * Permite actualizar un usuario dado
     *
     * @param usuario Usuario a ser actualizado
     * @return Retorna true en caso de que la actualización de datos haya sido exitosa, en caso contrario retorna false
     */
    private boolean actualizar(Usuario usuario) {
        boolean exito = false;
        try {
            usuarioService.update(usuario, verificacionClaveEdit);
            exito = true;
            verificacionClaveEdit = "";
            mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        } catch (Exception e) {
            mostrarErrorGeneral(e.getMessage());
            usuarios = usuarioService.get();
        }
        return exito;
    }

    /**
     * Metodo encargado de responder al evento de cancelación de la edicion de
     * usuario
     *
     * @param event Evento generado al cancela la edición de un usuario
     */
    public void onRowCancel(RowEditEvent<Usuario> event) {
        verificacionClaveEdit = "";
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_CANCELADA));
    }


    /**
     * Metodo que permite obtener el valor del atributo usuario
     *
     * @return El valor del atributo usuario
     */
    public abstract Usuario getUsuario();

    /**
     * Metodo que permite asignar un valor al atributo usuario
     *
     * @param usuario Valor a ser asignado al atributo usuario
     */
    public abstract void setUsuario(Usuario usuario);


    /**
     * Metodo que guarda los cambios realizados a un {@link Usuario}
     *
     * @param usuario {@link Usuario} que se desea guardar
     */
    public void guardar(Usuario usuario) {
        try {
            usuarioService.update(usuario);
            setEditId(null);
            mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));

        } catch (Exception e) {
            mostrarErrorGeneral(e.getMessage());
        }
    }

    /**
     * Método que perite la actualización de los datos del usuario
     */
    public void actualizar() {
        if (!usuarioAutenticado.getClave().equals(claveActual)) {
            mostrarErrorGeneral(getMessage(MessageConstants.OPERACION_ERROR));
        } else {
            String anterior = usuarioAutenticado.getClave();
            usuarioAutenticado.setClave(nuevaClave);
            if (!actualizar(usuarioAutenticado)) {
                usuarioAutenticado.setClave(anterior);
            }
        }

    }


}
