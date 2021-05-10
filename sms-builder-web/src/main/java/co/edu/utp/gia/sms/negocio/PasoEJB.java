package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.Paso;
import co.edu.utp.gia.sms.query.PasoQuery;

import java.util.List;

public class PasoEJB extends AbstractEJB<Paso, Integer> {

    public PasoEJB() {
        super(Paso.class);
    }

    public Paso findByName(String nombre){
        return entityManager.createNamedQuery(PasoQuery.PASOS_FIND_BY_NAME,Paso.class)
                .setParameter("nombre",nombre)
                .getResultStream().findFirst().orElse(null);
    }

    @Override
    public List<Paso> listar() {
        return createQuery(PasoQuery.PASOS_FIND_ALL).getResultList();
    }
}
