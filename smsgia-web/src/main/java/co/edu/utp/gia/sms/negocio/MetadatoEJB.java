package co.edu.utp.gia.sms.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.query.Queries;

@Stateless
public class MetadatoEJB {
	@PersistenceContext
	private EntityManager entityManager;
	

	public List<Metadato> obtenerMetadatos(Integer idReferencia) {
		List<Metadato> listaMetadatos = entityManager.createNamedQuery(Queries.REFERENCIA_METADATO_GET_ALL, Metadato.class)
				.setParameter("id", idReferencia).getResultList();
			return listaMetadatos;
	}	
	
}
