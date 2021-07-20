package co.edu.utp.gia.sms.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.importutil.Fuente;
import co.edu.utp.gia.sms.importutil.TipoFuente;
import co.edu.utp.gia.sms.query.EstadisticaQuery;
import co.edu.utp.gia.sms.query.Queries;

@Stateless
public class EstadisticaEJB {
	@PersistenceContext
	private EntityManager entityManager;

	public List<DatoDTO> obtenerReferenciasYear(Integer revisionId) {
		return entityManager.createNamedQuery(EstadisticaQuery.ESTADISTICA_YEAR, DatoDTO.class)
				.setParameter("idRevision", revisionId).getResultList();
	}

	public List<DatoDTO> obtenerReferenciasYear(Integer revisionId, Integer idAtributoCalidad) {
		return entityManager.createNamedQuery(EstadisticaQuery.ESTADISTICA_ATRIBUTO_CALIDAD_YEAR, DatoDTO.class)
				.setParameter("idRevision", revisionId).setParameter("idAtributoCalidad", idAtributoCalidad)
				.getResultList();
	}

	public List<DatoDTO> obtenerReferenciasTipo(Integer revisionId) {
		return entityManager.createNamedQuery(EstadisticaQuery.ESTADISTICA_TIPO, DatoDTO.class)
				.setParameter("idRevision", revisionId).getResultList();
	}

	public List<DatoDTO> obtenerReferenciasCalidadYear(Integer revisionId) {
		return entityManager.createNamedQuery(EstadisticaQuery.ESTADISTICA_CALIDAD_YEAR, DatoDTO.class)
				.setParameter("idRevision", revisionId).getResultList();
	}

	public List<DatoDTO> obtenerReferenciasCalidadYear(Integer revisionId, Integer idAtributoCalidad) {
		return entityManager.createNamedQuery(EstadisticaQuery.ESTADISTICA_ATRIBUTO_CALIDAD_CALIDAD_YEAR, DatoDTO.class)
				.setParameter("idRevision", revisionId).setParameter("idAtributoCalidad", idAtributoCalidad)
				.getResultList();
	}

	public List<DatoDTO> obtenerReferenciasPregunta(Integer revisionId) {
		return entityManager.createNamedQuery(EstadisticaQuery.ESTADISTICA_REFERENCIA_PREGUNTA, DatoDTO.class)
				.setParameter("idRevision", revisionId).getResultList();
	}

	public List<DatoDTO> obtenerReferenciasTipoFuente(Integer id) {
		List<DatoDTO> resultado = entityManager
				.createNamedQuery(EstadisticaQuery.ESTADISTICA_REFERENCIA_TIPO_FUENTE, DatoDTO.class)
				.setParameter("id", id).getResultList();

		for (TipoFuente fuente : TipoFuente.values()) {
			if (!resultado.stream().anyMatch(d -> d.getEtiqueta().equals(fuente.toString()))) {
				resultado.add(new DatoDTO(fuente, 0L));
			}
		}
		return resultado;
	}

	public List<DatoDTO> obtenerReferenciasTipoFuenteNombre(Integer revisionId, TipoFuente tipo) {
		List<DatoDTO> resultado = entityManager
				.createNamedQuery(EstadisticaQuery.ESTADISTICA_REFERENCIA_TIPO_FUENTE_NOMBRE, DatoDTO.class)
				.setParameter("idRevision", revisionId).setParameter("tipo", tipo).getResultList();
		for (Fuente fuente : Fuente.values()) {
			if (fuente.getTipo() == tipo
					&& !resultado.stream().anyMatch(d -> d.getEtiqueta().equals(fuente.toString()))) {
				resultado.add(new DatoDTO(fuente, 0L));
			}
		}
//		for (TipoFuente fuente : TipoFuente.values()) {
//			if( !resultado.stream().anyMatch( d->d.getEtiqueta().equals( fuente.toString() ) ) ) {
//				resultado.add( new DatoDTO(fuente, 0L));
//			}
//		} 

		return resultado;
	}

	public List<DatoDTO> obtenerReferenciasTopico(Integer revisionId) {
		return entityManager.createNamedQuery(EstadisticaQuery.ESTADISTICA_REFERENCIA_TOPICO, DatoDTO.class)
				.setParameter("idRevision", revisionId).getResultList();
	}

	public List<DatoDTO> obtenerReferenciasTopico(Integer revisionId, Integer idAtributoCalidad) {
		return entityManager.createNamedQuery(EstadisticaQuery.ESTADISTICA_REFERENCIA_TOPICO_ATRIBUTO_CALIDAD, DatoDTO.class)
				.setParameter("idRevision", revisionId).setParameter("idAtributoCalidad", idAtributoCalidad)
				.getResultList();
	}

	public List<DatoDTO> obtenerReferenciasTopico(Integer revisionId, String codigo) {
		return entityManager.createNamedQuery(EstadisticaQuery.ESTADISTICA_REFERENCIA_TOPICO_PREGUNTA, DatoDTO.class)
				.setParameter("idRevision", revisionId).setParameter("codigo", codigo).getResultList();
	}

	public List<DatoDTO> obtenerReferenciasTopico(Integer revisionId, String codigo, Integer idAtributoCalidad) {
		return entityManager
				.createNamedQuery(EstadisticaQuery.ESTADISTICA_REFERENCIA_TOPICO_PREGUNTA_ATRIBUTO_CALIDAD, DatoDTO.class)
				.setParameter("idRevision", revisionId).setParameter("codigo", codigo)
				.setParameter("idAtributoCalidad", idAtributoCalidad).getResultList();
	}

	public List<DatoDTO> obtenerPalabrasClave(Integer revisionId, int minimo) {
		return EstadisticaQuery.PalabrasClave.createQuery(entityManager,revisionId,minimo).getResultList();
	}

	public List<Referencia> obtenerReferencias(Integer revisionId, String keyword, List<TipoMetadato> metadatos) {
		return entityManager.createNamedQuery(EstadisticaQuery.ESTADISTICA_REFERENCIA_PALABRAS_CLAVE, Referencia.class)
				.setParameter("idRevision", revisionId).setParameter("value", String.format("%%%s%%", keyword))
				.setParameter("identifiers", metadatos).getResultList();
	}

}
