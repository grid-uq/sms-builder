package co.edu.utp.gia.sms.negocio;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.math3.stat.descriptive.rank.Percentile;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.entidades.EvaluacionCalidad;
import co.edu.utp.gia.sms.entidades.EvaluacionCualitativa;
import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.importutil.Fuente;
import co.edu.utp.gia.sms.query.Queries;

// TODO Pendiente la indicación de las revistas con mayor frecuencia dentro del SMS.
//      importante para la toma de decisión sobre el destino de publicación.

@Stateless
public class ReferenciaEJB extends AbstractEJB<Referencia, Integer> {
	@Inject
	private RevisionEJB revisionEJB;
	@Inject
	private TopicoEJB topicoEJB;
	@Inject
	private EvaluacionCalidadEJB evaluacionCalidadEJB;
	@Inject
	private MetadatoEJB metadatoEJB;
	@Inject
	private AtributoCalidadEJB atributoCalidadEJB;
	@Inject
	private PreguntaEJB preguntaEJB;

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
		referencia.setFiltro(filtro);
	}

	public void guardarEvaluacion(EvaluacionCalidad evaluacion) {
		evaluacionCalidadEJB.actualizar(evaluacion);
		Referencia referencia = obtener(evaluacion.getReferencia().getId());
		referencia.setTotalEvaluacionCalidad(calcularTotalEvaluacionCalidad(referencia.getId()).floatValue());
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

	public void actualizarCita(Integer id, Integer citas) {

		Referencia referencia = obtener(id);
		if (referencia != null) {
			referencia.setCitas(citas);
			if (referencia.getYear() == null) {
				throw new RuntimeException(
						String.format("La referencia %d-%s no tiene año ", referencia.getId(), referencia.getSpsid()));
			} else if (referencia.getCitas() != null) {
				float media = referencia.getCitas() / (float) (1 + Calendar.getInstance().get(Calendar.YEAR)
						- Integer.parseInt(referencia.getYear()));
				referencia.setSci(media);
			} else {
				referencia.setSci(null);
			}
		}

	}

	public void actualizarNota(Integer id, String nota) {

		Referencia referencia = obtener(id);
		if (referencia != null) {
			referencia.setNota(nota);
		}
	}
	
	public void actualizarResumen(Integer id, String resumen) {
		// TODO Auto-generated method stub
		
		Referencia referencia = obtener(id);
		if (referencia != null) {
			referencia.setResumen(resumen);
		}
	}

	public void removerTopico(Integer id, Integer idTopico) {
		Referencia referencia = obtener(id);
		Topico topico = entityManager.find(Topico.class, idTopico);
		referencia.getTopicos().remove(topico);

	}
	
	public void evaluacionAutomatica(Integer id) {
		Referencia referencia = obtener(id);
		if (referencia == null) {
			throw new RuntimeException(String.format("No se encontró la referencia %d", id));
		}
		try {
			evaluarSegunPreguntas(referencia);
			evaluarSegunCitas(referencia);
			evaluarSegunCVI(referencia);
		} catch (Exception e) {
			System.err.println("Error evaluando referencia " + id);
			e.printStackTrace();
			throw new RuntimeException(String.format("Error evaluando referencia %d-%s", id, referencia.getSpsid()), e);
		}
	}

	private void evaluarSegunCVI(Referencia referencia) {
		AtributoCalidad atributoCalidad = atributoCalidadEJB.obtener(AtributoCalidadEJB.CVI,
				referencia.getRevision().getId());
		EvaluacionCalidad evaluacionCalidad = determinarEvaluacionCalidad(referencia, atributoCalidad);

		if (referencia.getRelevancia() == null || referencia.getRelevancia() < 3) {
			evaluacionCalidad.setEvaluacionCualitativa(EvaluacionCualitativa.NO_CUMPLE);
		} else if (referencia.getRelevancia() < 5) {
			evaluacionCalidad.setEvaluacionCualitativa(EvaluacionCualitativa.PARCIALMENTE);
		} else {
			evaluacionCalidad.setEvaluacionCualitativa(EvaluacionCualitativa.CUMPLE);
		}

		guardarEvaluacion(evaluacionCalidad);
	}

	private void evaluarSegunCitas(Referencia referencia) {
		// TODO: Se debe agregar un parámetro de configuración que determine el número
		// de citas
		// promedio para la evaluación de calidad.
		// 2 = Citas de los estudios

		AtributoCalidad atributoCalidad = atributoCalidadEJB.obtener(AtributoCalidadEJB.SCI,
				referencia.getRevision().getId());
		EvaluacionCalidad evaluacionCalidad = determinarEvaluacionCalidad(referencia, atributoCalidad);

		float media = referencia.getCitas()
				/ (float) (1 + Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(referencia.getYear()));

		List<Float> scis = obtenerSCIs(referencia.getRevision().getId());

		Percentile p = new Percentile();
		double datos[] = new double[scis.size()];
		for (int i = 0; i < datos.length; i++) {
			datos[i] = scis.get(i);
		}

		double q1 = p.evaluate(datos, 75);
		double q2 = p.evaluate(datos, 50);
		if (media > q1) {
			evaluacionCalidad.setEvaluacionCualitativa(EvaluacionCualitativa.CUMPLE);
		} else if (media > q2) {
			evaluacionCalidad.setEvaluacionCualitativa(EvaluacionCualitativa.PARCIALMENTE);
		} else {
			evaluacionCalidad.setEvaluacionCualitativa(EvaluacionCualitativa.NO_CUMPLE);
		}

		guardarEvaluacion(evaluacionCalidad);

	}

	private EvaluacionCalidad determinarEvaluacionCalidad(Referencia referencia, AtributoCalidad atributoCalidad) {
		EvaluacionCalidad evaluacionCalidad = null;

		for (EvaluacionCalidad evaluacion : referencia.getEvaluacionCalidad()) {
			if (evaluacion.getAtributoCalidad().equals(atributoCalidad)) {
				evaluacionCalidad = evaluacion;
			}
		}

		if (evaluacionCalidad == null) {

			evaluacionCalidad = new EvaluacionCalidad(referencia, atributoCalidad);
		}

		return evaluacionCalidad;
	}

	private void evaluarSegunPreguntas(Referencia referencia) {
		AtributoCalidad atributoCalidad = atributoCalidadEJB.obtener(AtributoCalidadEJB.RRQI,
				referencia.getRevision().getId());
		EvaluacionCalidad evaluacionCalidad = determinarEvaluacionCalidad(referencia, atributoCalidad);

		int totalPreguntas = (int) preguntaEJB.totalPreguntas(referencia.getRevision().getId());
		int totalPreguntasRelacionadas = (int) calcularTotalPreguntasRelacionadas(referencia.getId());
		float porcentaje = totalPreguntasRelacionadas * 100.0f / totalPreguntas;

		if (porcentaje >= 80) {
			evaluacionCalidad.setEvaluacionCualitativa(EvaluacionCualitativa.CUMPLE);
		} else if (porcentaje >= 50) {
			evaluacionCalidad.setEvaluacionCualitativa(EvaluacionCualitativa.PARCIALMENTE);

		} else {
			evaluacionCalidad.setEvaluacionCualitativa(EvaluacionCualitativa.NO_CUMPLE);
		}
		referencia.setSrrqi(porcentaje);
		guardarEvaluacion(evaluacionCalidad);

	}

	private long calcularTotalPreguntasRelacionadas(Integer id) {
		return entityManager.createNamedQuery(Queries.REFERENCIA_CANTIDAD_RELACION_PREGUNTAS, Long.class)
				.setParameter("id", id).getSingleResult();

	}

	private List<Float> obtenerSCIs(Integer idRevision) {
		return entityManager.createNamedQuery(Queries.REFERENCIA_SCIS, Float.class)
				.setParameter("idRevision", idRevision).getResultList();
	}

	public void actualizarSPS(Integer id, String spsid) {
		Referencia referencia = obtener(id);
		if (referencia != null) {
			referencia.setSpsid(spsid);
		}
	}	

	public List<ReferenciaDTO> obtenerReferenciasAtributoCalidadEvaluacion(Integer idRevision,
			Integer idAtributoCalidad, EvaluacionCualitativa valorEvaluacion, int filtro) {

		List<ReferenciaDTO> referencias = entityManager
				.createNamedQuery(Queries.REFERENCIA_GET_EVALUACION_ATRIBUTO_CALIDAD, ReferenciaDTO.class)
				.setParameter("idRevision", idRevision).setParameter("filtro", filtro)
				.setParameter("idAtributoCalidad", idAtributoCalidad).setParameter("valorEvaluacion", valorEvaluacion)
				.getResultList();

		for (ReferenciaDTO referencia : referencias) {
			referencia.setAutores(obtenerAutores(referencia.getId()));
			referencia.setAbstracts(obtenerAbstract(referencia.getId()));
			referencia.setKeywords(obtenerKeywords(referencia.getId()));
			referencia.setFuente(obtenerFuente(referencia.getId()));
			referencia.setMetadatos(metadatoEJB.obtenerMetadatos(referencia.getId()));
		}
		return referencias;
	}

	public List<ReferenciaDTO> obtenerReferenciasAtributoCalidadEvaluacion(Integer idRevision,
			Integer idAtributoCalidad, int filtro) {

		List<ReferenciaDTO> referencias = entityManager
				.createNamedQuery(Queries.REFERENCIA_GET_ATRIBUTO_CALIDAD, ReferenciaDTO.class)
				.setParameter("idRevision", idRevision).setParameter("filtro", filtro)
				.setParameter("idAtributoCalidad", idAtributoCalidad).getResultList();

		for (ReferenciaDTO referencia : referencias) {
			referencia.setAutores(obtenerAutores(referencia.getId()));
			referencia.setAbstracts(obtenerAbstract(referencia.getId()));
			referencia.setKeywords(obtenerKeywords(referencia.getId()));
			referencia.setFuente(obtenerFuente(referencia.getId()));
			referencia.setMetadatos(metadatoEJB.obtenerMetadatos(referencia.getId()));
		}
		return referencias;
	}

	public void actualizarYear(Integer id, String year) {
		Referencia referencia = obtener(id);
		if (referencia != null) {
			referencia.setYear(year);
			if (referencia.getCitas() != null && referencia.getYear() != null) {
				float media = referencia.getCitas() / (float) (1 + Calendar.getInstance().get(Calendar.YEAR)
						- Integer.parseInt(referencia.getYear()));
				referencia.setSci(media);
			}
		}
	}

	public List<ReferenciaDTO> obtenerDestacadas(Integer idRevision) {
		List<ReferenciaDTO> referencias = entityManager
				.createNamedQuery(Queries.REFERENCIA_GET_ALL_DESTACADAS, ReferenciaDTO.class)
				.setParameter("idRevision", idRevision).getResultList();

		for (ReferenciaDTO referencia : referencias) {
			referencia.setAutores(obtenerAutores(referencia.getId()));
			referencia.setAbstracts(obtenerAbstract(referencia.getId()));
			referencia.setKeywords(obtenerKeywords(referencia.getId()));
			referencia.setFuente(obtenerFuente(referencia.getId()));
			referencia.setMetadatos(metadatoEJB.obtenerMetadatos(referencia.getId()));
		}
		return referencias;
	}	
}
