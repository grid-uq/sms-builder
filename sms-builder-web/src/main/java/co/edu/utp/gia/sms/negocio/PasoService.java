package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Paso;
/**
 * Clase de negocio encargada de implementar las funciones correspondientes a la
 * gestion del {@link Paso}.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
public class PasoService extends AbstractGenericService<Paso, String> {

    public PasoService() {
        super(DB.root.getProvider(Paso.class));
    }

    public Paso findByName(String nombre){
        return dataProvider.get().stream()
                .filter( paso -> paso.nombre().equals(nombre) )
                .findFirst().orElse(null);
    }
}
