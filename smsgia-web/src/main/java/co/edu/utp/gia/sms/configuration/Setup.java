package co.edu.utp.gia.sms.configuration;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.utp.gia.sms.importutil.Fuente;

@Startup
@javax.ejb.Singleton
public class Setup {

	@PersistenceContext
	private EntityManager entityManager;

	@PostConstruct
	public void inicializar() {
//		Fuente fuentes[] = { ACM, IEEE, SCIENCEDIRECT, SCOPUS, SPRINGER, WOS, MANUAL, SNOWBALL_BACKWARD, INCLUSION_DIRECTA };
		
		for (Fuente fuente : Fuente.values()) {
			co.edu.utp.gia.sms.entidades.Fuente f = entityManager.find(co.edu.utp.gia.sms.entidades.Fuente.class, fuente);
			if( f == null ) {
				entityManager.persist( new co.edu.utp.gia.sms.entidades.Fuente(fuente) );
			}
		}
	}

}
