package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.Paso;
import co.edu.utp.gia.sms.query.paso.PasoFindAll;
import co.edu.utp.gia.sms.query.paso.PasoFindByName;

import java.util.List;
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
public class PasoEJB extends AbstractEJB<Paso, Integer> {

    public PasoEJB() {
        super(Paso.class);
    }

    public Paso findByName(String nombre){
        return PasoFindByName.createQuery(entityManager,nombre)
                .getResultStream().findFirst().orElse(null);
    }

    @Override
    public List<Paso> listar() {
        return PasoFindAll.createQuery(entityManager).getResultList();
    }
}
