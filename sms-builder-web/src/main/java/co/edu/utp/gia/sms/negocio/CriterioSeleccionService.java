package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.CriterioSeleccion;
import co.edu.utp.gia.sms.entidades.Pregunta;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * Clase de negocio encargada de implementar las funciones correspondientes a la
 * gestion de la {@link Pregunta}.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
@ApplicationScoped
public class CriterioSeleccionService extends AbstractGenericService<CriterioSeleccion, String> {

    @Inject
    private TopicoService topicoService;
    public CriterioSeleccionService() {
        super(DB.root.getProvider(CriterioSeleccion.class));
    }

}
