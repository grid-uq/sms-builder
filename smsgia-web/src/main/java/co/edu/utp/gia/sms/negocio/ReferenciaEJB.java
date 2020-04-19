package co.edu.utp.gia.sms.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.EvaluacionCalidad;
import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.entidades.Nota;
import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.importutil.Fuente;
import co.edu.utp.gia.sms.query.Queries;

@Stateless
public class ReferenciaEJB extends AbstractEJB<Referencia, Integer> {
	@Inject
	private RevisionEJB revisionEJB;
	@Inject
	private NotaEJB notaEJB;
	@Inject
	private TopicoEJB topicoEJB;
	@Inject
	private EvaluacionCalidadEJB evaluacionCalidadEJB;
	@Inject
	private MetadatoEJB metadatoEJB;
	
	public ReferenciaEJB() {
		super(Referencia.class);
	}

	public Referencia registrar(Referencia referencia, Integer idRevision) {
		Revision revision = revisionEJB.obtener(idRevision);
		referencia.setRevision(revision);
		for (Metadato metadato : referencia.getMetadatos()) {
			if (metadato.getIdentifier().equals(TipoMetadato.FUENTE)
					&& metadato.getValue().equalsIgnoreCase(Fuente.INCLUSION_DIRECTA.toString())) {
				referencia.setFiltro(3);
			}
		}
		registrar(referencia);
		return referencia;
	}

	public void registrar(List<Referencia> referencias, Integer idRevision) {
		for (Referencia referencia : referencias) {
			registrar(referencia, idRevision);
		}
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
				.createNamedQuery(Queries.REFERENCIA_GET_ALL, ReferenciaDTO.class)
				.setParameter("idRevision", idRevision).setParameter("filtro", filtro).getResultList();

		for (ReferenciaDTO referencia : referencias) {
			referencia.setAutores(obtenerAutores(referencia.getId()));
			referencia.setAbstracts(obtenerAbstract(referencia.getId()));
			referencia.setKeywords(obtenerKeywords(referencia.getId()));
			referencia.setFuente(obtenerFuente(referencia.getId()));
			referencia.setNota(notaEJB.obtener(referencia.getId(), filtro));
			referencia.setNotas(notaEJB.obtenerNotas(referencia.getId()));
			referencia.setMetadatos(metadatoEJB.obtenerMetadatos(referencia.getId())); 
		}
		return referencias;
	}

	/**
	 * Permite obtener la fuente a la que pertenece una {@link Referencia}
	 * 
	 * @param id Id de la referencia de la que se quiere obtener la fuente
	 * @return {@link Fuente} a la que pertenece la {@link Referencia} que
	 *         corresponde al id proporcionado
	 */
	private Fuente obtenerFuente(Integer id) {
		return Fuente.valueOf(metadatoEJB.obtenerStringMetadatoByTipo(id, TipoMetadato.FUENTE));
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
			referencia.setEvaluaciones(evaluacionCalidadEJB.obtenerEvaluaciones(referencia.getId()));
		}

		return referencias;
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
		return metadatoEJB.obtenerStringMetadatoByTipo(idReferencia, TipoMetadato.AUTOR);
	}

	public String obtenerKeywords(Integer idReferencia) {
		return metadatoEJB.obtenerStringMetadatoByTipo(idReferencia, TipoMetadato.KEYWORD);
	}

	public String obtenerAbstract(Integer idReferencia) {
		return metadatoEJB.obtenerStringMetadatoByTipo(idReferencia, TipoMetadato.ABSTRACT);
	}



	public void actualizarFiltro(Integer id, Integer filtro) {
		Referencia referencia = obtener(id);
//		System.out.println("Cambiando " + referencia.getFiltro() + " por " + filtro);
		referencia.setFiltro(filtro);
	}

//	public void actualizarFiltro(Integer id, Integer filtro, Integer idNota, String descripcionNota, Integer etapa) {
//		Referencia referencia = obtener(id);
//		Nota nota = notaEJB.obtener(id, filtro);
//
//		if (idNota == null && nota.getId() == null && descripcionNota != null && !descripcionNota.isEmpty()) {
//			notaEJB.registrar(etapa, descripcionNota, id);
//		} else if ( nota.getId() != null) {
//
//			if (descripcionNota == null || descripcionNota.isEmpty()) {
//				notaEJB.eliminar(nota.getId());
//			} else {
//				notaEJB.actualizar(nota.getId(), descripcionNota);
//
//			}
//		}
//
////		System.out.println("Cambiando " + referencia.getFiltro() + " por " + filtro);
//		referencia.setFiltro(filtro);
//
//	}

	public void guardarEvaluacion(EvaluacionCalidad evaluacion) {
		evaluacionCalidadEJB.actualizar(evaluacion);
		Referencia referencia = obtener(evaluacion.getReferencia().getId());
		referencia.setTotalEvaluacionCalidad(
				calcularTotalEvaluacionCalidad(referencia.getId()).floatValue());
	}

	private Double calcularTotalEvaluacionCalidad(Integer id) {
		return entityManager.createNamedQuery(Queries.EVALUACION_TOTAL_CALIDAD, Double.class).setParameter("id", id)
				.getSingleResult();
	}

	public void adicionarTopico(Integer id, Integer idTopico) {
		Referencia referencia = obtener(id);
		Topico topico = topicoEJB.obtener(idTopico);
		referencia.getTopicos().add(topico);
	}

	public void limpiarTopicos(Integer id) {
		Referencia referencia = obtener(id);
		referencia.getTopicos().clear();
	}

	public void actualizarRelevancia(Integer id, Integer relevancia) {
		Referencia referencia = obtener(id);
		if (referencia != null) {
			referencia.setRelevancia(relevancia);
		}
	}

}
