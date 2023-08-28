package co.edu.utp.gia.sms.configuration;

import co.edu.utp.gia.sms.negocio.RevisionService;
import jakarta.inject.Inject;
/**
 * Clase encargada de realizar la configuración inicial de la revision en la aplicación
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
public class RevisionSetup implements SetupInterface{
    @Inject
    private RevisionService revisionService;
    @Override
    public void setup() {
        if( revisionService.get().getNombre() == null ) {
            revisionService.save("", "");
        }
    }
}
