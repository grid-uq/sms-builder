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
    @Inject
    private ProcesoSetup procesoSetup;
    @PostConstruct
    public void setup() {
        try {
            recursosSetup.setup();
        }catch ( Exception e ){
            e.printStackTrace();
        }
        try{
            rolSetup.setup();
        }catch ( Exception e ){
            e.printStackTrace();
        }
        try{
            usuariosSetup.setup();
        }catch ( Exception e ){
            e.printStackTrace();
        }
        try{
            fuenteSetup.setup();
        }catch ( Exception e ){
            e.printStackTrace();
        }
        try{
            pasosSetup.setup();
        }catch ( Exception e ){
            e.printStackTrace();
        }
        try{
            revisionSetup.setup();
        }catch ( Exception e ){
            e.printStackTrace();
        }
        try{
            procesoSetup.setup();
        }catch ( Exception e ){
            e.printStackTrace();
        }

    }

}
