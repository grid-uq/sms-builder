package co.edu.utp.gia.sms.negocio;

import java.util.List;

import javax.ejb.Stateless;

import co.edu.utp.gia.sms.entidades.EvaluacionCalidad;
import co.edu.utp.gia.sms.entidades.EvaluacionCalidadPK;
import co.edu.utp.gia.sms.query.Queries;

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
		if( evaluacion.getId() != null ) {
			super.actualizar(evaluacion);
		} else {
			registrar(evaluacion);
		}
	}	
	
	public List<EvaluacionCalidad> obtenerEvaluaciones(Integer id) {
		return entityManager.createNamedQuery(Queries.EVALUACION_CALIDAD_GET_ALL, EvaluacionCalidad.class)
				.setParameter("id", id).getResultList();
	}

}
