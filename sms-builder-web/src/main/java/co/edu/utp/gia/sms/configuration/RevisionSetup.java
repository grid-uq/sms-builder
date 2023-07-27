package co.edu.utp.gia.sms.configuration;

import co.edu.utp.gia.sms.negocio.RevisionEJB;

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
    private RevisionEJB revisionEJB;

    @Override
    public void setup() {
        if( revisionEJB.listar().size() == 0 ) {
            revisionEJB.registrar("", "", 1);
        }
    }
}
