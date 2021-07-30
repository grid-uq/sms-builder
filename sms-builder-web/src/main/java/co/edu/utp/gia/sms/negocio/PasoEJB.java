package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.Paso;
import co.edu.utp.gia.sms.query.paso.PasoFindAll;
import co.edu.utp.gia.sms.query.paso.PasoFindByName;

import java.util.List;

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
