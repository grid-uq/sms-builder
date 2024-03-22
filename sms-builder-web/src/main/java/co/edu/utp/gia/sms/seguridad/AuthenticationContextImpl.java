package co.edu.utp.gia.sms.seguridad;

import co.edu.utp.gia.sms.entidades.Usuario;
import co.edu.utp.gia.sms.query.seguridad.SeguridadUsuarioLogin;
import jakarta.enterprise.context.RequestScoped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

//@RequestScoped
@Getter
@AllArgsConstructor
public class AuthenticationContextImpl implements AuthenticationContext {
    private final Usuario currentUser;

    public static AuthenticationContext of(String username){
        var user = SeguridadUsuarioLogin.createQuery(username).findFirst().orElse(null);
        return new AuthenticationContextImpl(user);
    }

    public boolean isAuthenticated(){
        return currentUser == null;
    }
}