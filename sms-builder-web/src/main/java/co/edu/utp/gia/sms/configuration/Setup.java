package co.edu.utp.gia.sms.configuration;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@javax.ejb.Singleton
public class Setup implements SetupInterface {

    @Inject
    private RecursosSetup recursosSetup;
    @Inject
    private RolSetup rolSetup;
    @Inject
    private UsuariosSetup usuariosSetup;
    @Inject
    private FuenteSetup fuenteSetup;
    @Inject
    private PasosSetup pasosSetup;
    @Inject
    private RevisionSetup revisionSetup;

    @PostConstruct
    public void setup() {
        recursosSetup.setup();
        rolSetup.setup();
        usuariosSetup.setup();
        fuenteSetup.setup();
        pasosSetup.setup();
        revisionSetup.setup();
    }

}
