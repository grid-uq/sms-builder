package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.*;
import co.edu.utp.gia.sms.exceptions.LogicException;
import co.edu.utp.gia.sms.query.referencia.ReferenciaGetAllSCI;
import co.edu.utp.gia.sms.query.referencia.ReferenciaGetPreguntaRelacionada;
import co.edu.utp.gia.sms.query.referencia.ReferenciaGetTotalEvaluacionCalidad;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.java.Log;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;

/**
 * Clase de negocio encargada de implementar las funciones correspondientes a la
 * gestion de la {@link EvaluacionCalidad}.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
@ApplicationScoped
@Log
public class EvaluacionCalidadService extends AbstractGenericService<EvaluacionCalidad, String> {
	@Inject
	private RevisionService revisionService;
	@Inject
	private AtributoCalidadService atributoCalidadService;
	@Inject
	private PreguntaService preguntaService;
	@Inject
	private ReferenciaService referenciaService;

	public EvaluacionCalidadService() {
		super();
	}

	/* (non-Javadoc)
	 * @see co.edu.utp.gia.sms.negocio.AbstractGenericService#save(co.edu.utp.gia.sms.entidades.Entidad)
	 */
	@Override
	public EvaluacionCalidad save(EvaluacionCalidad evaluacion) {
		return save(evaluacion.getReferencia()::getEvaluacionCalidad,evaluacion);
	}


	/* (non-Javadoc)
	 * @see co.edu.utp.gia.sms.negocio.AbstractGenericService#update(co.edu.utp.gia.sms.entidades.Entidad)
	 */
	@Override
	public void update(EvaluacionCalidad evaluacion) {
//		super.update(evaluacion.getReferencia()::getEvaluacionCalidad,evaluacion);
		evaluacion.calcularEvaluacionCualitativa();
		var referencia = evaluacion.getReferencia();
		if( evaluacion.getId() != null && find(referencia::getEvaluacionCalidad,evaluacion.getId()).isPresent() ) {
			super.update(referencia::getEvaluacionCalidad,evaluacion);
		} else {
			save(evaluacion);
		}
		referencia.setTotalEvaluacionCalidad(calcularTotalEvaluacionCalidad(referencia.getId()).floatValue());
		referenciaService.update(referencia);
	}

	/* (non-Javadoc)
	 * @see co.edu.utp.gia.sms.negocio.AbstractGenericService#delete(co.edu.utp.gia.sms.entidades.Entidad)
	 */
	@Override
	public void delete(EvaluacionCalidad evaluacion) {
		super.delete(evaluacion.getReferencia()::getEvaluacionCalidad,evaluacion);
	}


	public void evaluacionAcutomatica(){
		var referencias = revisionService.getPasoActual().getReferencias();
		referencias.forEach(this::evaluacionAutomatica);
	}

	private void evaluacionAutomatica(Referencia referencia) {
		try {
			evaluarSegunPreguntas(referencia);
			evaluarSegunCitas(referencia);
			evaluarSegunCVI(referencia);
		} catch (Exception e) {
			final String mensaje = exceptionMessage.getReferenciaErrorEvaluacion(referencia.getId(), referencia.getSpsid());
			log.log(Level.WARNING, mensaje, e);
			throw new LogicException(mensaje, e);
		}
	}

	private void evaluarSegunPreguntas(Referencia referencia) {
		var atributoCalidad = atributoCalidadService.findByDescripcion(AtributoCalidadService.IRRQ);
		if( !atributoCalidad.isEmpty() ) {
			EvaluacionCalidad evaluacionCalidad = determinarEvaluacionCalidad(referencia, atributoCalidad.stream().findFirst().get());

			int totalPreguntas = preguntaService.count();
			int totalPreguntasRelacionadas = (int) calcularTotalPreguntasRelacionadas(referencia.getId());
			float porcentaje = totalPreguntasRelacionadas * 100.0f / totalPreguntas;

			if (porcentaje >= 75) {
				evaluacionCalidad.setEvaluacionCualitativa(EvaluacionCualitativa.CUMPLE);
			} else if (porcentaje >= 50) {
				evaluacionCalidad.setEvaluacionCualitativa(EvaluacionCualitativa.PARCIALMENTE);

			} else {
				evaluacionCalidad.setEvaluacionCualitativa(EvaluacionCualitativa.NO_CUMPLE);
			}
			referencia.setSrrqi(porcentaje);
			update(evaluacionCalidad);
		}
	}

	private void evaluarSegunCitas(Referencia referencia) {

		var atributoCalidad = atributoCalidadService.findByDescripcion(AtributoCalidadService.SCI);
		if( !atributoCalidad.isEmpty() ) {
			EvaluacionCalidad evaluacionCalidad = determinarEvaluacionCalidad(referencia, atributoCalidad.stream().findFirst().get());

			float media = referencia.getCitas()
					/ (float) (1 + Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(referencia.getYear()));

			List<Float> scis = obtenerSCIs();

			Percentile p = new Percentile();
			double[] datos = new double[scis.size()];
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

			update(evaluacionCalidad);
		}
	}



	private void evaluarSegunCVI(Referencia referencia) {
		var atributoCalidad = atributoCalidadService.findByDescripcion(AtributoCalidadService.CVI);

		if( !atributoCalidad.isEmpty() ) {
			EvaluacionCalidad evaluacionCalidad = determinarEvaluacionCalidad(referencia, atributoCalidad.stream().findFirst().get());

			if (referencia.getRelevancia() == null || referencia.getRelevancia() < 3) {
				evaluacionCalidad.setEvaluacionCualitativa(EvaluacionCualitativa.NO_CUMPLE);
			} else if (referencia.getRelevancia() < 5) {
				evaluacionCalidad.setEvaluacionCualitativa(EvaluacionCualitativa.PARCIALMENTE);
			} else {
				evaluacionCalidad.setEvaluacionCualitativa(EvaluacionCualitativa.CUMPLE);
			}

			update(evaluacionCalidad);
		}
	}


	private EvaluacionCalidad determinarEvaluacionCalidad(Referencia referencia, AtributoCalidad atributoCalidad) {
		return referencia.getEvaluacionCalidad().stream()
				.filter( evaluacionCalidad -> evaluacionCalidad.getAtributoCalidad().equals(atributoCalidad) )
				.findFirst()
				.orElse( new EvaluacionCalidad(referencia, atributoCalidad) );
	}

	/**
	 * Consulta que permite obtener el número de preguntas relacionadas con una referencia
	 *
	 * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
	 * @return número de preguntas relacionadas con una referencia
	 */
	private long calcularTotalPreguntasRelacionadas(String id) {
		return ReferenciaGetPreguntaRelacionada.createQuery(id).count();
	}

	/**
	 * Consulta que permite obtener el total de las evaluaciones de calidad registradas en el sistema para una referencia
	 *
	 * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
	 * @return Double con el total de la evaluación de calidad de la referencia dada
	 */
	private Double calcularTotalEvaluacionCalidad(String id) {
		return ReferenciaGetTotalEvaluacionCalidad.createQuery(id).sum();
	}

	/**
	 * Consulta que permite obtener los SCI de las referencia de una revision
	 *
	 * @return List<Float> listado de los SCI de las referencias de una revision
	 */
	private List<Float> obtenerSCIs() {
		return ReferenciaGetAllSCI.createQuery(revisionService.getPasoActual()::getReferencias).toList();
	}
}
