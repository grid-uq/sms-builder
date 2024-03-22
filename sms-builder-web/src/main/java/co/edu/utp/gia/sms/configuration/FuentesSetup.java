package co.edu.utp.gia.sms.configuration;

import co.edu.utp.gia.sms.entidades.TipoFuente;
import co.edu.utp.gia.sms.negocio.FuenteService;
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
public class FuentesSetup implements SetupInterface {
    @Inject
    private FuenteService fuenteService;

    @Override
    public void setup() {
        if( fuenteService.get().isEmpty() ) {
            fuenteService.save(FuenteService.INCLUSION_DIRECTA, TipoFuente.INCLUSION_DIRECTA);
            fuenteService.save(FuenteService.SNOWBALL_BACKWARD, TipoFuente.BOLA_NIEVE);
            fuenteService.save(FuenteService.SNOWBALL_FORWARD, TipoFuente.BOLA_NIEVE);
        }
    }
}
