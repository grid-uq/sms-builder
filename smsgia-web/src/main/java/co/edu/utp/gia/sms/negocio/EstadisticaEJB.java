package co.edu.utp.gia.sms.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.entidades.Referencia;

@Stateless
public class EstadisticaEJB {
	@PersistenceContext
	private EntityManager entityManager;

	public List<DatoDTO> obtenerReferenciasYear(Integer revisionId) {
		return entityManager.createNamedQuery(Referencia.ESTADISTICA_YEAR, DatoDTO.class)
				.setParameter("idRevision", revisionId).getResultList();
	}

	public List<DatoDTO> obtenerReferenciasTipo(Integer revisionId) {
		return entityManager.createNamedQuery(Referencia.ESTADISTICA_TIPO, DatoDTO.class)
				.setParameter("idRevision", revisionId).getResultList();
	}

	public List<DatoDTO> obtenerReferenciasCalidadYear(Integer revisionId) {
		return entityManager.createNamedQuery(Referencia.ESTADISTICA_CALIDAD_YEAR, DatoDTO.class)
				.setParameter("idRevision", revisionId).getResultList();
	}

	public List<DatoDTO> obtenerReferenciasTopico(Integer revisionId) {
		return entityManager.createNamedQuery(Referencia.ESTADISTICA_REFERENCIA_TOPICO, DatoDTO.class)
				.setParameter("idRevision", revisionId).getResultList();
	}

	public List<DatoDTO> obtenerReferenciasPregunta(Integer revisionId) {
		return entityManager.createNamedQuery(Referencia.ESTADISTICA_REFERENCIA_PREGUNTA, DatoDTO.class)
				.setParameter("idRevision", revisionId).getResultList();
	}

}
