package co.edu.utp.gia.sms.beans.estadisticas;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.EvaluacionCualitativa;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;
import co.edu.utp.gia.sms.negocio.RevisionEJB;

@ManagedBean
@ViewScoped
public class TablaReferenciasTopicosBean {

	private List<ReferenciaDTO> referencias;
	@Inject
	private ReferenciaEJB referenciaEJB;
	@Inject
	private RevisionEJB revisionEJB;

	@ManagedProperty("#{registroInicialBean.revision}")
	private Revision revision;

	private List<Topico> topicos;

	private Integer idAtributoCalidad;
	private String evaluacion;

	@PostConstruct
	public void inicializar() {

		if (revision != null) {
			referencias = referenciaEJB.obtenerTodas(revision.getId(), 3);
			topicos = revisionEJB.obtenerTopicos(revision.getId());
		}
	}

	public void consultarReferencias() {
		if (idAtributoCalidad == null) {
			referencias = referenciaEJB.obtenerTodas(revision.getId(), 3);
		} else if (evaluacion != null) {
			referencias = referenciaEJB.obtenerReferenciasAtributoCalidadEvaluacion(revision.getId(), idAtributoCalidad,
					EvaluacionCualitativa.valueOf(evaluacion), 3);
		} else {
			referencias = referenciaEJB.obtenerReferenciasAtributoCalidadEvaluacion(revision.getId(), idAtributoCalidad,
					3);
		}
	}

	/**
	 * Metodo que permite obtener el valor del atributo referencias
	 * 
	 * @return El valor del atributo referencias
	 */
	public List<ReferenciaDTO> getReferencias() {
		return referencias;
	}

	/**
	 * Metodo que permite asignar un valor al atributo referencias
	 * 
	 * @param referencias Valor a ser asignado al atributo referencias
	 */
	public void setReferencias(List<ReferenciaDTO> referencias) {
		this.referencias = referencias;
	}

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
	 * Metodo que permite obtener el valor del atributo topicos
	 * 
	 * @return El valor del atributo topicos
	 */
	public List<Topico> getTopicos() {
		return topicos;
	}

	/**
	 * Metodo que permite asignar un valor al atributo topicos
	 * 
	 * @param topicos Valor a ser asignado al atributo topicos
	 */
	public void setTopicos(List<Topico> topicos) {
		this.topicos = topicos;
	}

	/**
	 * Metodo que permite obtener el valor del atributo idAtributoCalidad
	 * 
	 * @return El valor del atributo idAtributoCalidad
	 */
	public Integer getIdAtributoCalidad() {
		return idAtributoCalidad;
	}

	/**
	 * Metodo que permite asignar un valor al atributo idAtributoCalidad
	 * 
	 * @param idAtributoCalidad Valor a ser asignado al atributo idAtributoCalidad
	 */
	public void setIdAtributoCalidad(Integer idAtributoCalidad) {
		this.idAtributoCalidad = idAtributoCalidad;
	}

	/**
	 * Metodo que permite obtener el valor del atributo evaluacion
	 * 
	 * @return El valor del atributo evaluacion
	 */
	public String getEvaluacion() {
		return evaluacion;
	}

	/**
	 * Metodo que permite asignar un valor al atributo evaluacion
	 * 
	 * @param evaluacion Valor a ser asignado al atributo evaluacion
	 */
	public void setEvaluacion(String evaluacion) {
		this.evaluacion = evaluacion;
	}

	/**
	 * Permite obtener un arreglo con los valores de la
	 * {@link EvaluacionCualitativa}
	 * 
	 * @return Arreglo de valores de la {@link EvaluacionCualitativa}
	 */
	public List<SelectItem> getListaValores() {
		List<SelectItem> valores = new ArrayList<>();
		valores.add(new SelectItem(null, "", "", false, false, true));
		for (EvaluacionCualitativa valor : EvaluacionCualitativa.values()) {
			valores.add(new SelectItem(valor));
		}
		return valores;
	}
}
