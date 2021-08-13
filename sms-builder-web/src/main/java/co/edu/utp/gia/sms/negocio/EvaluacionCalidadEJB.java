package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.EvaluacionCalidad;
import co.edu.utp.gia.sms.entidades.EvaluacionCalidadPK;
import co.edu.utp.gia.sms.query.referencia.ReferenciaGetEvaluacionesCalidad;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class EvaluacionCalidadEJB extends AbstractEJB<EvaluacionCalidad, EvaluacionCalidadPK> {

	public EvaluacionCalidadEJB() {
		super(EvaluacionCalidad.class);
	}

	/* (non-Javadoc)
	 * @see co.edu.utp.gia.sms.negocio.AbstractEJB#actualizar(co.edu.utp.gia.sms.entidades.Entidad)
	 */
	@Override
	public void actualizar(EvaluacionCalidad evaluacion) {
		evaluacion.calcularEvaluacionCualitativa();
		if( evaluacion.getId() != null && obtener(evaluacion.getId()) != null ) {
			super.actualizar(evaluacion);
		} else {
			registrar(evaluacion);
		}
	}

	/**
	 * Consulta que permite obtener las evaluaciones de calidad registradas en el sistema para una referencia
	 *
	 * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
	 * @return List< EvaluacionCalidad > Lista de las {@link EvaluacionCalidad} de la {@link co.edu.utp.gia.sms.entidades.Referencia} dada
	 */
	public List<EvaluacionCalidad> obtenerEvaluaciones(Integer id) {
		return ReferenciaGetEvaluacionesCalidad.createQuery(entityManager,id).getResultList();
	}

}
