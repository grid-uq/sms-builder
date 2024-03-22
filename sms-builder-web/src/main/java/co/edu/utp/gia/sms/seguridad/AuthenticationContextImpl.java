package co.edu.utp.gia.sms.seguridad;

import co.edu.utp.gia.sms.entidades.Recurso;
import co.edu.utp.gia.sms.entidades.Rol;
import co.edu.utp.gia.sms.entidades.Usuario;
import co.edu.utp.gia.sms.query.seguridad.SeguridadRecursoFindUrlByPublic;
import co.edu.utp.gia.sms.query.seguridad.SeguridadUsuarioLogin;
import lombok.Getter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//@RequestScoped
@Getter
public class AuthenticationContextImpl implements AuthenticationContext {
    private final Usuario currentUser;
    private final Set<String> resources;

    public AuthenticationContextImpl(Usuario currentUser) {
        this.currentUser = currentUser;
        resources = loadResources();
    }

    private Set<String> loadResources() {
        var resources = SeguridadRecursoFindUrlByPublic.createQuery(true);

        if (isAuthenticated()) {
            var privateResources = getCurrentUser().getRoles().stream()
                    .map(Rol::getRecursos)
                    .flatMap(List::stream)
                    .map(Recurso::getUrl);
            resources = Stream.concat(resources,privateResources);
        }
        return resources.collect(Collectors.toSet());
    }

    public static AuthenticationContext of(String username){
        var user = SeguridadUsuarioLogin.createQuery(username).findFirst().orElse(null);
        return new AuthenticationContextImpl(user);
    }

    public boolean isAuthenticated(){
        return currentUser != null;
    }

    @Override
    public boolean hasPermision(String resource) {
        return resources.contains(resource);
    }

}