package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.EvaluacionCalidad;
import co.edu.utp.gia.sms.entidades.Referencia;
import jakarta.enterprise.context.ApplicationScoped;

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
public class EvaluacionCalidadService extends AbstractGenericService<EvaluacionCalidad, String> {

	public EvaluacionCalidadService() {
		super();
	}

	public EvaluacionCalidadService(Referencia referencia){
		super(referencia::getEvaluacionCalidad);
	}

	@Override
	public EvaluacionCalidad save(EvaluacionCalidad evaluacion) {
		return save(evaluacion.getReferencia()::getEvaluacionCalidad,evaluacion);
	}

	/* (non-Javadoc)
	 * @see co.edu.utp.gia.sms.negocio.AbstractGenericService#update(co.edu.utp.gia.sms.entidades.Entidad)
	 */
	@Override
	public void update(EvaluacionCalidad evaluacion) {
		super.update(evaluacion.getReferencia()::getEvaluacionCalidad,evaluacion);
		evaluacion.calcularEvaluacionCualitativa();
		if( evaluacion.getId() != null && find(evaluacion.getId()).isPresent() ) {
			super.update(evaluacion.getReferencia()::getEvaluacionCalidad,evaluacion);
		} else {
			save(evaluacion);
		}
	}

	/* (non-Javadoc)
	 * @see co.edu.utp.gia.sms.negocio.AbstractGenericService#delete(co.edu.utp.gia.sms.entidades.Entidad)
	 */
	@Override
	public void delete(EvaluacionCalidad evaluacion) {
		super.delete(evaluacion.getReferencia()::getEvaluacionCalidad,evaluacion.getId());
	}
}
