package co.edu.utp.gia.sms.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.entidades.EvaluacionCalidad;
import co.edu.utp.gia.sms.entidades.EvaluacionCualitativa;
import co.edu.utp.gia.sms.negocio.AtributoCalidadEJB;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;

@Named
@ViewScoped
public class EvaluarReferenciaBean extends GenericBean<ReferenciaDTO> {
	
	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 6788702926482127829L;
	private List<AtributoCalidad> atributosCalidad;
	private List<EvaluacionCalidad> evaluaciones;
	@Inject
	private AtributoCalidadEJB atributoCalidadEJB;
	@Inject
	private ReferenciaEJB referenciaEJB;
	private ReferenciaDTO referencia;

	public void inicializar() {
		if (getRevision() != null) {
			referencia = (ReferenciaDTO) getFromSession("referenciaDTO");
			atributosCalidad = atributoCalidadEJB.obtenerAtributosCalidad(getRevision().getId());
			if (referencia.getEvaluaciones() == null || referencia.getEvaluaciones().isEmpty() ) {
				evaluaciones = new ArrayList<EvaluacionCalidad>();
				for (AtributoCalidad atributoCalidad : atributosCalidad) {
					EvaluacionCalidad evaluacion = new EvaluacionCalidad(referencia.getReferencia(), atributoCalidad); 
					evaluaciones.add(evaluacion);
					referencia.addEvaluacion(evaluacion);
				}
			}else {
				evaluaciones = referencia.getEvaluaciones();
			}
		}
	}

	public void guardar() {
		mostrarMensajeGeneral("Registro Adicionado");
		for (EvaluacionCalidad evaluacion : evaluaciones) {
			referenciaEJB.guardarEvaluacion(evaluacion);
		}
		getAndRemoveFromSession("referenciaDTO");
		PrimeFaces.current().dialog().closeDynamic(referencia);
	}

////////// ----- GET/SET ----- ////////////	


	/**
	 * Metodo que permite obtener el valor del atributo atributosCalidad
	 * 
	 * @return El valor del atributo atributosCalidad
	 */
	public List<AtributoCalidad> getAtributosCalidad() {
		return atributosCalidad;
	}

	/**
	 * Metodo que permite asignar un valor al atributo atributosCalidad
	 * 
	 * @param atributosCalidad Valor a ser asignado al atributo atributosCalidad
	 */
	public void setAtributosCalidad(List<AtributoCalidad> atributosCalidad) {
		this.atributosCalidad = atributosCalidad;
	}

	/**
	 * Metodo que permite obtener el valor del atributo evaluaciones
	 * 
	 * @return El valor del atributo evaluaciones
	 */
	public List<EvaluacionCalidad> getEvaluaciones() {
		return evaluaciones;
	}

	/**
	 * Metodo que permite asignar un valor al atributo evaluaciones
	 * 
	 * @param evaluaciones Valor a ser asignado al atributo evaluaciones
	 */
	public void setEvaluaciones(List<EvaluacionCalidad> evaluaciones) {
		this.evaluaciones = evaluaciones;
	}

	/**
	 * Metodo que retorna los valores de la enumeracion
	 * {@link EvaluacionCualitativa}
	 * 
	 * @return Los valores de la {@link EvaluacionCualitativa}
	 */
	public EvaluacionCualitativa[] getEvaluacionCalitativaValues() {
		return EvaluacionCualitativa.values();
	}
}
