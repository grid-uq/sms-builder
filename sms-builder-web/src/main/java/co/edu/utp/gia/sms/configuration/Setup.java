package co.edu.utp.gia.sms.configuration;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Inject;
/**
 * Clase encargada de realizar la configuración inicial de la aplicación
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
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
