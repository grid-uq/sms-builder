package co.edu.utp.gia.sms.seguridad;

import io.quarkus.security.UnauthorizedException;
import io.quarkus.security.identity.CurrentIdentityAssociation;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.Instant;
import java.util.Date;

@Path("/api/logout")
public class LogoutResource {

    @ConfigProperty(name = "quarkus.http.auth.form.cookie-name")
    String cookieName;

    @Inject
    CurrentIdentityAssociation identity;


    @POST
    public Response logout() {
        if (identity.getIdentity().isAnonymous()) {
            throw new UnauthorizedException("Not authenticated");
        }
        final NewCookie removeCookie = new NewCookie.Builder(cookieName)
                .maxAge(0)
                .expiry(Date.from(Instant.EPOCH))
                .path("/")
                .build();
        return Response.noContent().cookie(removeCookie).build();
    }
}