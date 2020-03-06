package co.edu.utp.gia.sms.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.EvaluacionCalidad;
import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.importutil.Fuente;

@Stateless
public class ReferenciaEJB {
	@PersistenceContext
	private EntityManager entityManager;
	@Inject
	private RevisionEJB revisionEJB;

	public Referencia registrar(Referencia referencia, Integer idRevision) {
		Revision revision = revisionEJB.obtener(idRevision);
		referencia.setRevision(revision);
		for (Metadato metadato : referencia.getMetadatos()) {
			if (metadato.getIdentifier().equals(TipoMetadato.FUENTE)
					&& metadato.getValue().equalsIgnoreCase(Fuente.INCLUSION_DIRECTA.toString())) {
				referencia.setFiltro(3);
			}
		}
		entityManager.persist(referencia);
		return referencia;
	}

	public void registrar(List<Referencia> referencias, Integer idRevision) {
		for (Referencia referencia : referencias) {
			registrar(referencia, idRevision);
		}
	}

	/**
	 * Permite obtener una referencia por medio del Identificador
	 * 
	 * @param idReferencia Identificador de la referencia que se desea obtener
	 * @return Retorna la referencia que corresponde con el identificador
	 *         proporcionado
	 */
	public Referencia obtener(Integer idReferencia) {
		return entityManager.find(Referencia.class, idReferencia);
	}

	/**
	 * Permite obtener el listado de referencias de una revision que han pasado por
	 * un determinado filtro
	 * 
	 * @param idRevision Identificador de la revision
	 * @param filtro     Filtro que se debe haber pasado 0 indica ningun filtro se
	 *                   mostrarian todas referencias de la revision
	 * @return Listado de {@link Pregunta} de la {@link Revision} identificada con
	 *         el id dado
	 */
	public List<ReferenciaDTO> obtenerTodas(int idRevision, int filtro) {
		List<ReferenciaDTO> referencias = entityManager
				.createNamedQuery(Referencia.REFERENCIA_GET_ALL, ReferenciaDTO.class)
				.setParameter("idRevision", idRevision).setParameter("filtro", filtro).getResultList();
		referencias.parallelStream().forEach((referencia) -> {
			referencia.setAutores(obtenerAutores(referencia.getId()));
			referencia.setAbstracts(obtenerAbstract(referencia.getId()));
			referencia.setKeywords(obtenerKeywords(referencia.getId()));
		});
		return referencias;
	}

	/**
	 * Permite obtener el listado de referencias de una revision que han pasado por
	 * un determinado filtro
	 * 
	 * @param idRevision Identificador de la revision
	 * @param filtro     Filtro que se debe haber pasado 0 indica ningun filtro se
	 *                   mostrarian todas referencias de la revision
	 * @return Listado de {@link Pregunta} de la {@link Revision} identificada con
	 *         el id dado
	 */
	public List<ReferenciaDTO> obtenerTodasConEvaluacion(int idRevision, int filtro) {
		List<ReferenciaDTO> referencias = obtenerTodas(idRevision, filtro);
		for (ReferenciaDTO referencia : referencias) {
			referencia.setEvaluaciones(obtenerEvaluaciones(referencia.getId()));
		}
		return referencias;
	}

	public List<EvaluacionCalidad> obtenerEvaluaciones(Integer id) {
		return entityManager.createNamedQuery(EvaluacionCalidad.EVALUACION_CALIDAD_GET_ALL, EvaluacionCalidad.class)
				.setParameter("id", id).getResultList();
	}

//	public List<ReferenciaDTO> obtenerTodas(int idRevision, int filtro) {
//		List<ReferenciaDTO> lista = new ArrayList<ReferenciaDTO>();
//
//		System.out.println("USANDO FILTRO "+filtro);
//		entityManager.createNamedQuery(Referencia.REFERENCIA_GET_ALL, Referencia.class)
//				.setParameter("idRevision", idRevision).setParameter("filtro", filtro).getResultList().stream()
//				.forEach((r) -> {
//					lista.add(new ReferenciaDTO(r, filtro));
//				});
//
//		return lista;
//	}

	public String obtenerAutores(Integer idReferencia) {
		return obtenerStringMetadatoByTipo(idReferencia, TipoMetadato.AUTOR);
	}

	public String obtenerKeywords(Integer idReferencia) {
		return obtenerStringMetadatoByTipo(idReferencia, TipoMetadato.KEYWORD);
	}

	public String obtenerAbstract(Integer idReferencia) {
		return obtenerStringMetadatoByTipo(idReferencia, TipoMetadato.ABSTRACT);
	}

	public String obtenerStringMetadatoByTipo(Integer idReferencia, TipoMetadato tipoMetadato) {
		List<Metadato> listMetadatos = obtenerListMetadatoByTipo(idReferencia, tipoMetadato);
		String valor = "";
		String separador = "";
		for (Metadato metadato : listMetadatos) {
			valor = valor + separador + metadato.getValue();
			separador = " ; ";
		}
		return valor;
	}

	public List<Metadato> obtenerListMetadatoByTipo(Integer idReferencia, TipoMetadato tipoMetadato) {
		return entityManager.createNamedQuery(Metadato.METADATO_GET_ALL, Metadato.class)
				.setParameter("id", idReferencia).setParameter("tipo", tipoMetadato).getResultList();
	}

	public void actualizarFiltro(Integer id, Integer filtro) {
		Referencia referencia = obtener(id);
		System.out.println("Cambiando " + referencia.getFiltro() + " por " + filtro);
		referencia.setFiltro(filtro);
	}

	public void guardarEvaluacion(EvaluacionCalidad evaluacion) {
		evaluacion.calcularEvaluacionCualitativa();
		entityManager.merge(evaluacion);
		Referencia referencia = obtener(evaluacion.getReferencia().getId());
		referencia.setTotalEvaluacionCalidad(
				calcularTotalEvaluacionCalidad(evaluacion.getReferencia().getId()).floatValue());
	}

	private Double calcularTotalEvaluacionCalidad(Integer id) {
		return entityManager.createNamedQuery(EvaluacionCalidad.EVALUACION_TOTAL_CALIDAD, Double.class)
				.setParameter("id", id).getSingleResult();
	}

	public void adicionarTopico(Integer id, Integer idTopico) {
		Referencia referencia = obtener(id);
		Topico topico = entityManager.find(Topico.class, idTopico);
		referencia.getTopicos().add(topico);
	}

	public void limpiarTopicos(Integer id) {
		Referencia referencia = obtener(id);
		referencia.getTopicos().clear();
	}

}
