package co.edu.utp.gia.sms.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.entidades.EvaluacionCalidad;
import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.entidades.Topico;

public class ReferenciaDTO implements Serializable {
	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = -2840554748252612956L;
	private Referencia referencia;
	private boolean seleccionada;
	private int etapa;
	private List<EvaluacionCalidad> evaluaciones;
	private String autores;
	private String keywords;
	private String abstracts;

	public ReferenciaDTO(Referencia referencia) {
		this(referencia, 0);
		System.out.println("Construyento ReferenciaDTO SIN PARAMETRO");
	}

	/**
	 * Metodo que permite inicializar los elementos de la clase ReferenciaDTO
	 * 
	 * @param referencia
	 * @param etapa      0 para todas las referencias seleccinadas, 1 para las que
	 *                   pasaron el filtro de repetidos, 3 para el filtro de
	 *                   seleccion en pantalla filtro % (etapa+1) = etapa
	 */
	public ReferenciaDTO(Referencia referencia, Integer etapa) {
		this.etapa = etapa;
		this.referencia = referencia;
		evaluarSeleccion();
	}

	private void evaluarSeleccion() {
//		System.out.println("Seleccionada =  " + referencia.getFiltro().intValue() + " | " + etapa + " = "
//				+ (referencia.getFiltro().intValue() | etapa) + " > " + etapa + " = "
//				+ ( (referencia.getFiltro().intValue() | etapa) > etapa ) );
		seleccionada = (referencia.getFiltro().intValue() | etapa) > etapa;
	}

	/**
	 * @return
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return referencia.hashCode();
	}

	/**
	 * @return
	 * @see co.edu.utp.gia.sms.entidades.Referencia#getId()
	 */
	public Integer getId() {
		return referencia.getId();
	}

	/**
	 * @param obj
	 * @return
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return referencia.equals(obj);
	}

	/**
	 * @param id
	 * @see co.edu.utp.gia.sms.entidades.Referencia#setId(java.lang.Integer)
	 */
	public void setId(Integer id) {
		referencia.setId(id);
	}

	/**
	 * @return
	 * @see co.edu.utp.gia.sms.entidades.Referencia#getNombre()
	 */
	public String getNombre() {
		return referencia.getNombre();
	}

	/**
	 * @param nombre
	 * @see co.edu.utp.gia.sms.entidades.Referencia#setNombre(java.lang.String)
	 */
	public void setNombre(String nombre) {
		referencia.setNombre(nombre);
	}

	/**
	 * @return
	 * @see co.edu.utp.gia.sms.entidades.Referencia#getYear()
	 */
	public String getYear() {
		return referencia.getYear();
	}

	/**
	 * @param year
	 * @see co.edu.utp.gia.sms.entidades.Referencia#setYear(java.lang.String)
	 */
	public void setYear(String year) {
		referencia.setYear(year);
	}

	/**
	 * @return
	 * @see co.edu.utp.gia.sms.entidades.Referencia#getResumen()
	 */
	public String getResumen() {
		return referencia.getResumen();
	}

	/**
	 * @param resumen
	 * @see co.edu.utp.gia.sms.entidades.Referencia#setResumen(java.lang.String)
	 */
	public void setResumen(String resumen) {
		referencia.setResumen(resumen);
	}

	/**
	 * @return
	 * @see co.edu.utp.gia.sms.entidades.Referencia#getMetadatos()
	 */
	public List<Metadato> getMetadatos() {
		return referencia.getMetadatos();
	}

	/**
	 * @param metadatos
	 * @see co.edu.utp.gia.sms.entidades.Referencia#setMetadatos(java.util.List)
	 */
	public void setMetadatos(List<Metadato> metadatos) {
		referencia.setMetadatos(metadatos);
	}

	/**
	 * @return
	 * @see co.edu.utp.gia.sms.entidades.Referencia#getFiltro()
	 */
	public Integer getFiltro() {
		return referencia.getFiltro();
	}

	/**
	 * @param filtro
	 * @see co.edu.utp.gia.sms.entidades.Referencia#setFiltro(java.lang.Integer)
	 */
	public void setFiltro(Integer filtro) {
		referencia.setFiltro(filtro);
	}

	/**
	 * @return
	 * @see co.edu.utp.gia.sms.entidades.Referencia#getTipo()
	 */
	public String getTipo() {
		return referencia.getTipo();
	}

	/**
	 * @param tipo
	 * @see co.edu.utp.gia.sms.entidades.Referencia#setTipo(java.lang.String)
	 */
	public void setTipo(String tipo) {
		referencia.setTipo(tipo);
	}

	/**
	 * @return
	 * @see co.edu.utp.gia.sms.entidades.Referencia#getRevision()
	 */
	public Revision getRevision() {
		return referencia.getRevision();
	}

	/**
	 * @param revision
	 * @see co.edu.utp.gia.sms.entidades.Referencia#setRevision(co.edu.utp.gia.sms.entidades.Revision)
	 */
	public void setRevision(Revision revision) {
		referencia.setRevision(revision);
	}

	/**
	 * @return
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return referencia.toString();
	}

	/**
	 * Metodo que permite obtener el valor del atributo seleccionada
	 * 
	 * @return El valor del atributo seleccionada
	 */
	public boolean isSeleccionada() {
		return seleccionada;
	}

	/**
	 * @return
	 * @see co.edu.utp.gia.sms.entidades.Referencia#getTopicos()
	 */
	public List<Topico> getTopicos() {
		return referencia.getTopicos();
	}

	/**
	 * @param topicos
	 * @see co.edu.utp.gia.sms.entidades.Referencia#setTopicos(java.util.List)
	 */
	public void setTopicos(List<Topico> topicos) {
		referencia.setTopicos(topicos);
	}

	/**
	 * Metodo que permite asignar un valor al atributo seleccionada
	 * 
	 * @param seleccionada Valor a ser asignado al atributo seleccionada
	 */
	public void setSeleccionada(boolean seleccionada) {
		if (this.seleccionada != seleccionada) {
			int base = (etapa << 1) | 1;
			System.out.println("Base : " + base);
			if (seleccionada) {
//				System.out.println("filtro = " + getFiltro() + " | " + base + " = " + (getFiltro() | base));
				setFiltro(getFiltro() | base);

			} else {
//				System.out.println("filtro = " + getFiltro() + " ^ " + base + " = " + (getFiltro() ^ base));
				setFiltro(getFiltro() ^ base);
			}
		}
		this.seleccionada = seleccionada;
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
	 * Metodo que permite obtener el valor del atributo referencia
	 * 
	 * @return El valor del atributo referencia
	 */
	public Referencia getReferencia() {
		return referencia;
	}

	/**
	 * Metodo que permite asignar un valor al atributo referencia
	 * 
	 * @param referencia Valor a ser asignado al atributo referencia
	 */
	public void setReferencia(Referencia referencia) {
		this.referencia = referencia;
	}

	/**
	 * Permimte adicionar una evaluacion a la referencia
	 * 
	 * @param evaluacion Evaluacion a ser adicionada
	 */
	public void addEvaluacion(EvaluacionCalidad evaluacion) {
		if (evaluaciones == null) {
			evaluaciones = new ArrayList<EvaluacionCalidad>();
		}
		evaluaciones.add(evaluacion);
	}

	/**
	 * Metodo que permite obtener el valor del atributo autores
	 * @return El valor del atributo autores
	 */
	public String getAutores() {
		return autores;
	}

	/**
	 * Metodo que permite asignar un valor al atributo autores
	 * @param autores Valor a ser asignado al atributo autores
	 */
	public void setAutores(String autores) {
		this.autores = autores;
	}

	/**
	 * Metodo que permite obtener el valor del atributo etapa
	 * @return El valor del atributo etapa
	 */
	public int getEtapa() {
		return etapa;
	}

	/**
	 * Metodo que permite asignar un valor al atributo etapa
	 * @param etapa Valor a ser asignado al atributo etapa
	 */
	public void setEtapa(int etapa) {
		this.etapa = etapa;
	}

	/**
	 * Metodo que permite obtener el valor del atributo keywords
	 * @return El valor del atributo keywords
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * Metodo que permite asignar un valor al atributo keywords
	 * @param keywords Valor a ser asignado al atributo keywords
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * Metodo que permite obtener el valor del atributo abstracts
	 * @return El valor del atributo abstracts
	 */
	public String getAbstracts() {
		return abstracts;
	}

	/**
	 * Metodo que permite asignar un valor al atributo abstracts
	 * @param abstracts Valor a ser asignado al atributo abstracts
	 */
	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}

	/**
	 * @return
	 * @see co.edu.utp.gia.sms.entidades.Referencia#getTotalEvaluacionCalidad()
	 */
	public Float getTotalEvaluacionCalidad() {
		return referencia.getTotalEvaluacionCalidad();
	}

	/**
	 * @param totalEvaluacionCalidad
	 * @see co.edu.utp.gia.sms.entidades.Referencia#setTotalEvaluacionCalidad(java.lang.Float)
	 */
	public void setTotalEvaluacionCalidad(Float totalEvaluacionCalidad) {
		referencia.setTotalEvaluacionCalidad(totalEvaluacionCalidad);
	}

	public EvaluacionCalidad getEvaluacionCalidad(AtributoCalidad atributo ) {
		for (EvaluacionCalidad evaluacionCalidad : evaluaciones) {
			if( evaluacionCalidad.getAtributoCalidad().equals(atributo) ) {
				return evaluacionCalidad;
			}
		}
		return null;
	}
	
}
