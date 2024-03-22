package co.edu.utp.gia.sms.configuration;

import co.edu.utp.gia.sms.negocio.AtributoCalidadService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * Clase encargada de realizar la configuración inicial de los pasos del proceso en la aplicación
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@ApplicationScoped
public class AtributosCalidadSetup implements SetupInterface {
    @Inject
    private AtributoCalidadService atributoCalidadService;

    @Override
    public void setup() {
        if( atributoCalidadService.get().isEmpty() ) {
            atributoCalidadService.save(AtributoCalidadService.SCI, true);
            atributoCalidadService.save(AtributoCalidadService.CVI, false);
            atributoCalidadService.save(AtributoCalidadService.IRRQ, false);
        }
    }
}
