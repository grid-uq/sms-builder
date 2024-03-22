package co.edu.utp.gia.sms.beans.seguridad;

import co.edu.utp.gia.sms.beans.AbstractBean;
import co.edu.utp.gia.sms.entidades.Recurso;
import co.edu.utp.gia.sms.entidades.Rol;
import co.edu.utp.gia.sms.entidades.Usuario;
import co.edu.utp.gia.sms.exceptions.ExceptionMessage;
import co.edu.utp.gia.sms.exceptions.LogicException;
import co.edu.utp.gia.sms.negocio.RecursoService;
import co.edu.utp.gia.sms.negocio.UsuarioService;
import io.quarkus.security.credential.PasswordCredential;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.request.UsernamePasswordAuthenticationRequest;
import io.undertow.security.api.SecurityContext;
import jakarta.annotation.PostConstruct;
import jakarta.faces.component.FacesComponent;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
//import jakarta.security.enterprise.AuthenticationStatus;
//import jakarta.security.enterprise.SecurityContext;
//import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
//import jakarta.security.enterprise.credential.Credential;
//import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import jakarta.ws.rs.core.SecurityContext;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import io.quarkus.security.identity.IdentityProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

//import static jakarta.security.enterprise.AuthenticationStatus.SEND_FAILURE;


/**
 * Clase que se encarga de verificar y mantener los datos de autenticación del
 * {@link Usuario} que interactua con la aplicación.
 *
 * @author Christian A. Candela <christiancandela@grid.edu.co>
 * @author Luis E. Sepúlveda <lesepulveda@grid.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 2015-12-02
 */
@Log
public abstract class SeguridadBean extends AbstractBean {
//	/**
//	 * Variable que representa el {@link Usuario} que esta autenticado
//	 */
//	private Usuario usuario = null;
    /**
     * Variable que representa el atributo autenticado de la clase. Permite
     * determinar si un {@link Usuario} esta o no autenticado
     */
    @Getter
    @Setter
    private boolean autenticado = false;
    /**
     * Variable que representa el atributo nombreUsario del {@link Usuario} que se esta
     * autenticando.
     */
    @Getter
    @Setter
    private String nombreUsuario;
    /**
     * Variable que representa el atributo clave del {@link Usuario} que se esta
     * autenticando
     */
    @Getter
    @Setter
    private String clave;

    /**
     * Lista de recursos a los que puede acceder el usuario
     */
    @Getter
    @Setter
    private List<String> urlRecursos;
    /**
     * Instancia del objeto de negocio {@link UsuarioService} usadao para la gestion
     * del {@link Usuario}
     */
    @Inject
    private UsuarioService usuarioService;

    /**
     * Instancia del objeto de negocio {@link RecursoService} usadao para la gestion
     * del {@link Recurso}
     */
    @Inject
    private RecursoService recursoService;

    /**
     * Instancia que perite obtener los mensajes de las excepciones generadas.
     */
    @Inject
    private ExceptionMessage exceptionMessage;



    @Inject
    private IdentityProvider<UsernamePasswordAuthenticationRequest> identityProvider;

    @ConfigProperty(name = "quarkus.http.auth.form.cookie-name")
    String cookieName;


    /**
     * Método que inicializa los elementos básicos del sistema
     */
    @PostConstruct
    public void inicializar() {
        cargarRecursos();
    }

    /**
     * Realiza la verificación de los datos de autenticación proporcioandos por
     * el {@link Usuario}
     */
    public void ingresar() throws IOException {
//        AuthenticationStatus status;
        boolean status = false;
        try {
            var r = identityProvider.authenticate(new UsernamePasswordAuthenticationRequest(nombreUsuario,new PasswordCredential(clave.toCharArray())),null);
            r.attachContext();

//            Credential credential = new UsernamePasswordCredential(nombreUsuario, clave);
//            status = securityContext
//                    .authenticate(
//                            (HttpServletRequest) getFacesContext().getExternalContext().getRequest(),
//                            (HttpServletResponse) getFacesContext().getExternalContext().getResponse(),
//                            AuthenticationParameters.withParams().credential(credential));

            // TODO verifciar modficación de usuario
            //setUsuario(usuarioService.login(nombreUsuario, clave));
            if (getUsuario() == null) {
                throw new LogicException(exceptionMessage.getLoginFailMessage());
            }
            cargarRecursos();
            autenticado = true;
            mostrarMensajeGeneral("");
        } catch (Throwable t) {
            log.log(Level.WARNING,"Problemas al autenticar",t);
            mostrarErrorGeneral(String.format("ERROR: %s", t.getMessage()));
//            status = SEND_FAILURE;
        }
//        switch (status) {
//            case SEND_CONTINUE:
//                getFacesContext().responseComplete();
//                break;
//            case SEND_FAILURE:
////                    getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username and password", null));
//                break;
//            case SUCCESS:
////                getFacesContext().getExternalContext().redirect(getFacesContext().getExternalContext().getRequestContextPath() + "/");
//                break;
//            case NOT_DONE:
//        }

        //return null;
    }

    /**
     * Permite inicializar el listado de URLs a las cuales el usuario tiene
     * acceso
     */
    private void cargarRecursos() {
        urlRecursos = new ArrayList<>(recursoService.buscarRecursosPublicos(true));
        if (getUsuario() != null) {
            for (Rol rol : getUsuario().getRoles()) {
                for (Recurso recurso : rol.getRecursos()) {
                    urlRecursos.add(recurso.getUrl());
                }
            }
        }
    }

    /**
     * Permite establecer si un usuario tiene o no permisos para acceder a un
     * determinado recurso
     *
     * @return True si el usuario tiene permiso para acceder al recurso, en caso
     * contrario retorna false
     */
    public boolean getTienePermiso() {
        String path = FacesContext.getCurrentInstance().getViewRoot()
                .getViewId();
        return verifivarAcceso(path);
    }

    /**
     * Permite determinar si un recurso es o no publico
     *
     * @param path Url del recurso
     * @return True si el recurso es publico, en caso contrario retorna false
     */
    public boolean verificarRecursoPublico(String path) {
        Recurso recurso = recursoService.findByUrl(path);
        return recurso != null && recurso.isPublico();
    }

    /**
     * Permite verificar si un usuario tiene o no acceso a un recurso
     *
     * @param path URL del recurso del cual se desea saber si se tiene o no
     *             acceso
     * @return True si se tiene acceso, en caso contrario retorna false;
     */
    public boolean verifivarAcceso(String path) {
        return urlRecursos.contains(path);
    }

    public boolean verificarPermiso(FacesComponent component) {
        return verifivarAcceso(component.value());
    }

    /**
     * Metodo que permite cerrar la sesion del usuario
     *
     * @return La url de redirección despues del cierre de sesion
     */
    public String cerrarSesion() {
        autenticado = false;
//        setUsuario(null);
        cargarRecursos();
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();
        var cookie = new Cookie(cookieName,"");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        HttpServletResponse response = (HttpServletResponse) getFacesContext().getExternalContext().getResponse();
        response.addCookie(cookie);
        return "/administracion/index";
    }

    /**
     * Metodo que permite obtener el valor del atributo usuario
     *
     * @return El valor del atributo usuario
     */
    public Usuario getUsuario(){
        return getCurrentUser();
    }

//    /**
//     * Metodo que permite asignar un valor al atributo usuario
//     *
//     * @param usuario Valor a ser asignado al atributo usuario
//     */
//    public abstract void setUsuario(Usuario usuario);


}
