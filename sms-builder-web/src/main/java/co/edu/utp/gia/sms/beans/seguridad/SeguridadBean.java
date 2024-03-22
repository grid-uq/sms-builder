package co.edu.utp.gia.sms.beans.seguridad;

import co.edu.utp.gia.sms.beans.AbstractBean;
import co.edu.utp.gia.sms.entidades.Recurso;
import co.edu.utp.gia.sms.entidades.Usuario;
import co.edu.utp.gia.sms.exceptions.ExceptionMessage;
import co.edu.utp.gia.sms.negocio.RecursoService;
import co.edu.utp.gia.sms.negocio.UsuarioService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.component.FacesComponent;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;



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

//    /**
//     * Lista de recursos a los que puede acceder el usuario
//     */
//    @Getter
//    @Setter
//    private List<String> urlRecursos;
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



//    @Inject
//    private IdentityProvider<UsernamePasswordAuthenticationRequest> identityProvider;

    @ConfigProperty(name = "quarkus.http.auth.form.cookie-name")
    String cookieName;


    /**
     * Método que inicializa los elementos básicos del sistema
     */
    @PostConstruct
    public void inicializar() {
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
        return hasPermision(path);
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
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();
        var cookie = new Cookie(cookieName,"");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        HttpServletResponse response = (HttpServletResponse) getFacesContext().getExternalContext().getResponse();
        response.addCookie(cookie);
        return "/index.xhtml?faces-redirect=true";
    }

    /**
     * Metodo que permite obtener el valor del atributo usuario
     *
     * @return El valor del atributo usuario
     */
    public Usuario getUsuario(){
        return getCurrentUser();
    }

    public boolean isAutenticado() {
        return isAuthenticated();
    }
}
