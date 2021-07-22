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

	/**
	 * Consulta que permite obtener el número de referencias por tipo de fuente en una revision <br />
	 *
	 * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
	 * @return List< DatoDTO > con Estadisticas de los datos obtenidos
	 */
	public List<DatoDTO> obtenerReferenciasTipoFuente(Integer id) {
		List<DatoDTO> resultado = EstadisticaQuery.ReferenciaByTipoFuente.createQuery(entityManager,id).getResultList();

		for (TipoFuente fuente : TipoFuente.values()) {
			if (!resultado.stream().anyMatch(d -> d.getEtiqueta().equals(fuente.toString()))) {
				resultado.add(new DatoDTO(fuente, 0L));
			}
		}
		return resultado;
	}

	/**
	 * Permite obtener el número de referencias por cada fuente de un determinado tipo de fuente en una revision
	 * @param id Id de la revision
	 * @param tipo Tipo de fuente a consultar
	 * @return List< DatoDTO > Con Estadisticas de referencia por tipo de fuente.
	 */
	public List<DatoDTO> obtenerReferenciasTipoFuenteNombre(Integer id, TipoFuente tipo) {
		List<DatoDTO> resultado = EstadisticaQuery.ReferenciaByTipoFuenteAndNombre.createQuery(entityManager,id,tipo)
				.getResultList();
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

	/**
	 *
	 * Consulta que permite obtener las Referencias que contienen en uno de sus metadatos la palabra dada <br />
	 *
	 * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
	 * @param keyword Palabra a buscar
	 * @param metadatos Listado de tipos de metadatos a incluir en la búsqueda.
	 * @return List< Referencia > que contienen la keyword buscada en uno de sus metadatos
	 */
	public List<Referencia> obtenerReferencias(Integer id, String keyword, List<TipoMetadato> metadatos) {
		return EstadisticaQuery.ReferenciaByPalabrasClave.createQuery(entityManager,id,keyword,metadatos).getResultList();
	}

}
