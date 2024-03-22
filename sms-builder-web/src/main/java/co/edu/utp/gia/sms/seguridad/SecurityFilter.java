package co.edu.utp.gia.sms.seguridad;


import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;

import java.io.IOException;
import java.util.logging.Logger;

//@Provider
//@PreMatching
public class SecurityFilter implements ContainerRequestFilter {

    private static final Logger log = Logger.getLogger(SecurityFilter.class.getName());
    @Inject
    AuthenticationContextImpl authCtx; // Injecting the implementation,
                                               // not the interface!!!

    @Context
    SecurityContext securityCtx;


    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        log.info("APLICANDO FILTRO "+securityCtx.isSecure()+" --> "+securityCtx.getAuthenticationScheme());
        try {
            var user = securityCtx.getUserPrincipal() != null ? securityCtx.getUserPrincipal().getName() : null;
//            authCtx.setCurrentUser(user);
            log.info("ASIGNANDO USUARIO "+authCtx.getCurrentUser());
        }catch (Throwable t){
            t.printStackTrace();
//            authCtx.setCurrentUser(null);
        }
    }
}