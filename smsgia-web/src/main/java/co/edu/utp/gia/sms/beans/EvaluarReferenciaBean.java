package co.edu.utp.gia.sms.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.PrimeFaces;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.entidades.EvaluacionCalidad;
import co.edu.utp.gia.sms.entidades.EvaluacionCualitativa;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.negocio.AtributoCalidadEJB;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;

@ManagedBean
@RequestScoped
public class EvaluarReferenciaBean implements Serializable {
	@ManagedProperty(value = "#{registroInicialBean.revision}")
	private Revision revision;
	private List<AtributoCalidad> atributosCalidad;
	private List<EvaluacionCalidad> evaluaciones;
	@Inject
	private AtributoCalidadEJB atributoCalidadEJB;
	@Inject
	private ReferenciaEJB referenciaEJB;
	private ReferenciaDTO referencia;

	@PostConstruct
	public void inicializar() {
		if (revision != null) {
			referencia = (ReferenciaDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get("referenciaDTO");
			atributosCalidad = atributoCalidadEJB.obtenerAtributosCalidad(revision.getId());
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

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro Adicionado"));
		for (EvaluacionCalidad evaluacion : evaluaciones) {
			referenciaEJB.guardarEvaluacion(evaluacion);
		}
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("referenciaDTO");
		PrimeFaces.current().dialog().closeDynamic(referencia);

	}

////////// ----- GET/SET ----- ////////////	

	/**
	 * Metodo que permite obtener el valor del atributo revision
	 * 
	 * @return El valor del atributo revision
	 */
	public Revision getRevision() {
		return revision;
	}

	/**
	 * Metodo que permite asignar un valor al atributo revision
	 * 
	 * @param revision Valor a ser asignado al atributo revision
	 */
	public void setRevision(Revision revision) {
		this.revision = revision;
	}

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
