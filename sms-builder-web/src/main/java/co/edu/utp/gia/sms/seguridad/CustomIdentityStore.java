package co.edu.utp.gia.sms.seguridad;

import co.edu.utp.gia.sms.entidades.Rol;
import co.edu.utp.gia.sms.query.seguridad.SeguridadUsuarioLogin;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

import java.util.HashSet;

import static jakarta.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;

@ApplicationScoped
public class CustomIdentityStore implements IdentityStore {


    @Override
    public CredentialValidationResult validate(Credential credential) {
        UsernamePasswordCredential userCredential = (UsernamePasswordCredential) credential;

        var user = SeguridadUsuarioLogin.createQuery(userCredential.getCaller()).findFirst().orElse(null);
        if( user != null && user.getClave().equals( userCredential.getPasswordAsString() ) ){
            return new CredentialValidationResult(user.getNombreUsuario(),
                    new HashSet<>(user.getRoles().stream().map(Rol::getNombre).toList()));
        }
        return INVALID_RESULT;
    }
}