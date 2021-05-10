package co.edu.utp.gia.sms.configuration;

import co.edu.utp.gia.sms.importutil.Fuente;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class FuenteSetup implements SetupInterface{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void setup() {
        for (var fuente : Fuente.values()) {
            co.edu.utp.gia.sms.entidades.Fuente f = entityManager.find(co.edu.utp.gia.sms.entidades.Fuente.class, fuente);
            if( f == null ) {
                entityManager.persist( new co.edu.utp.gia.sms.entidades.Fuente(fuente) );
            }
        }
    }
}
