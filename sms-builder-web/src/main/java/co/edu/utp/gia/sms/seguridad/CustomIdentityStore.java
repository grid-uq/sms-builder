package co.edu.utp.gia.sms.seguridad;

import co.edu.utp.gia.sms.entidades.Rol;
import co.edu.utp.gia.sms.entidades.Usuario;
import co.edu.utp.gia.sms.query.seguridad.SeguridadUsuarioLogin;
import io.quarkus.security.AuthenticationFailedException;
import io.quarkus.security.identity.AuthenticationRequestContext;
import io.quarkus.security.identity.IdentityProvider;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.request.UsernamePasswordAuthenticationRequest;
import io.quarkus.security.runtime.QuarkusPrincipal;
import io.quarkus.security.runtime.QuarkusSecurityIdentity;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.security.enterprise.credential.Credential;
//import jakarta.security.enterprise.credential.UsernamePasswordCredential;
//import jakarta.security.enterprise.identitystore.CredentialValidationResult;
//import jakarta.security.enterprise.identitystore.IdentityStore;

import java.util.HashSet;
import java.util.Optional;

//import static jakarta.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;

@ApplicationScoped
public class CustomIdentityStore implements IdentityProvider<UsernamePasswordAuthenticationRequest> { //implements IdentityStore {
    @Override
    public Class<UsernamePasswordAuthenticationRequest> getRequestType() {
        return UsernamePasswordAuthenticationRequest.class;
    }

    @Override
    public Uni<SecurityIdentity> authenticate(UsernamePasswordAuthenticationRequest credential, AuthenticationRequestContext authenticationRequestContext) {
        var result = validate(credential.getUsername(),new String(credential.getPassword().getPassword()));
        var usuario = result.orElseThrow(()->new AuthenticationFailedException("password invalid or user not found"));
        return Uni.createFrom().item(QuarkusSecurityIdentity.builder()
                .setPrincipal(new QuarkusPrincipal(usuario.getNombreUsuario()))
                .addCredential(credential.getPassword())
                .setAnonymous(false)
                .addRoles(new HashSet<>(usuario.getRoles().stream().map(Rol::getNombre).toList()))
                .build());
    }


//    @Override
//    public CredentialValidationResult validate(Credential credential) {
//        UsernamePasswordCredential userCredential = (UsernamePasswordCredential) credential;
//
//        var user = SeguridadUsuarioLogin.createQuery(userCredential.getCaller()).findFirst().orElse(null);
//        if( user != null && user.getClave().equals( userCredential.getPasswordAsString() ) ){
//            return new CredentialValidationResult(user.getNombreUsuario(),
//                    new HashSet<>(user.getRoles().stream().map(Rol::getNombre).toList()));
//        }
//        return INVALID_RESULT;
//    }

    private Optional<Usuario> validate(String username, String password){
        var user = SeguridadUsuarioLogin.createQuery(username).findFirst().orElse(null);
        if( user != null && user.getClave().equals( password ) ){
            return Optional.of(user);
        }
        return Optional.empty();
    }
}