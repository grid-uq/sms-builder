package co.edu.utp.gia.sms.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.entidades.EvaluacionCualitativa;
import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.AtributoCalidadEJB;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;

@Named
@ViewScoped
public class TablaResumenEvaluacionReferenciasAtributoBean extends GenericBean<ReferenciaDTO>{


	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = -668848541409393797L;
	private List<ReferenciaDTO> referencias;
	@Inject
	private ReferenciaEJB referenciaEJB;
	@Inject
	private AtributoCalidadEJB atributoCalidadEJB;


	private List<AtributoCalidad> atributosCalidad;
	
	private AtributoCalidad atributoCalidad;
	private Integer idAtributo;
	
	public void inicializar() {
		if (revision != null) {
			referencias = referenciaEJB.obtenerTodasConEvaluacion(revision.getId(), 3);
			atributosCalidad = atributoCalidadEJB.obtenerAtributosCalidad( revision.getId() );
			atributoCalidad = atributosCalidad.stream().findFirst().orElse(null);
			idAtributo = atributoCalidad != null ? atributoCalidad.getId() : null;
		}
	}
	

	public boolean tieneRalacion(ReferenciaDTO referencia,Pregunta pregunta) {
		for (Topico topico : referencia.getTopicos()) {
			if( pregunta.getTopicos().contains(topico) ) {
				return true;
			}
		}
		return false;
	}
	
	
	public void atributoSeleccionado () {
		atributoCalidad = atributosCalidad.stream().filter(a->a.getId()==idAtributo).findFirst().orElse(atributoCalidad);
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
	 * Metodo que permite obtener el valor del atributo atributosCalidad
	 * @return El valor del atributo atributosCalidad
	 */
	public List<AtributoCalidad> getAtributosCalidad() {
		return atributosCalidad;
	}


	/**
	 * Metodo que permite asignar un valor al atributo atributosCalidad
	 * @param atributosCalidad Valor a ser asignado al atributo atributosCalidad
	 */
	public void setAtributosCalidad(List<AtributoCalidad> atributosCalidad) {
		this.atributosCalidad = atributosCalidad;
	}

	/**
	 * Permite obtener un arreglo con los valores de la {@link EvaluacionCualitativa}
	 * @return Arreglo de valores de la {@link EvaluacionCualitativa}
	 */
	public List<SelectItem> getListaValores() {
		List<SelectItem> valores = new ArrayList<SelectItem>();
		valores.add( new SelectItem("","","",false,false,true) );
		for (EvaluacionCualitativa evaluacion : EvaluacionCualitativa.values()) {
			valores.add( new SelectItem(evaluacion) );
		}
		return valores;
	}


	/**
	 * Metodo que permite obtener el valor del atributo atributoCalidad
	 * @return El valor del atributo atributoCalidad
	 */
	public AtributoCalidad getAtributoCalidad() {
		return atributoCalidad;
	}


	/**
	 * Metodo que permite asignar un valor al atributo atributoCalidad
	 * @param atributoCalidad Valor a ser asignado al atributo atributoCalidad
	 */
	public void setAtributoCalidad(AtributoCalidad atributoCalidad) {
		this.atributoCalidad = atributoCalidad;
	}


	/**
	 * Metodo que permite obtener el valor del atributo idAtributo
	 * @return El valor del atributo idAtributo
	 */
	public Integer getIdAtributo() {
		return idAtributo;
	}


	/**
	 * Metodo que permite asignar un valor al atributo idAtributo
	 * @param idAtributo Valor a ser asignado al atributo idAtributo
	 */
	public void setIdAtributo(Integer idAtributo) {
		this.idAtributo = idAtributo;
	}
	
	
}
