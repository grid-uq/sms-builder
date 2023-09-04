package co.edu.utp.gia.sms.dtos;

import co.edu.utp.gia.sms.entidades.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase usada para encapsular datos de una referencia que serán mostrados al usuario
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@Getter
@NoArgsConstructor
public class ReferenciaDTO implements Serializable {
    @Setter
    private Referencia referencia;
    /**
     *  Determina si una referencia está o no incluida en un determinado paso
     */
    private boolean seleccionada;
    @Setter
    private int etapa;
    @Setter
    private List<EvaluacionCalidad> evaluaciones;
    private String keywords;
    private String autores;
    @Setter
    private AtributoCalidad atributoCalidad;

    public ReferenciaDTO(Referencia referencia) {
        this(referencia, 0);
    }

    /**
     * Metodo que permite inicializar los elementos de la clase ReferenciaDTO
     *
     * @param referencia Referencia a ser usada
     * @param etapa      0 para todas las referencias seleccinadas, 1 para las que
     *                   pasaron el filtro de repetidos, 3 para el filtro de
     *                   seleccion en pantalla filtro % (etapa+1) = etapa
     */
    public ReferenciaDTO(Referencia referencia, Integer etapa) {
        this.etapa = etapa;
        this.referencia = referencia;
        this.keywords = referencia.keyWordsAsString();
        this.autores = referencia.autoresAsString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReferenciaDTO that)) return false;

        return referencia.equals(that.referencia);
    }

    @Override
    public int hashCode() {
        return referencia.hashCode();
    }

    /*
     * @see co.edu.utp.gia.sms.entidades.Referencia#getId()
     */
    public String getId() {
        return referencia.getId();
    }



    /*
     * @see co.edu.utp.gia.sms.entidades.Referencia#getNombre()
     */
    public String getNombre() {
        return referencia.getNombre();
    }

    /*
     * @see co.edu.utp.gia.sms.entidades.Referencia#setNombre(java.lang.String)
     */
    public void setNombre(String nombre) {
        referencia.setNombre(nombre);
    }

    /*
     * @see co.edu.utp.gia.sms.entidades.Referencia#getYear()
     */
    public String getYear() {
        return referencia.getYear();
    }

    /*
     * @see co.edu.utp.gia.sms.entidades.Referencia#setYear(java.lang.String)
     */
    public void setYear(String year) {
        referencia.setYear(year);
    }

    /*
     * @see co.edu.utp.gia.sms.entidades.Referencia#getResumen()
     */
    public String getResumen() {
        return referencia.getResumen();
    }

    /*
     * @see co.edu.utp.gia.sms.entidades.Referencia#setResumen(java.lang.String)
     */
    public void setResumen(String resumen) {
        referencia.setResumen(resumen);
    }

    /*
     * @see co.edu.utp.gia.sms.entidades.Referencia#getTipo()
     */
    public String getTipo() {
        return referencia.getTipo();
    }

    /*
     * @see co.edu.utp.gia.sms.entidades.Referencia#setTipo(java.lang.String)
     */
    public void setTipo(String tipo) {
        referencia.setTipo(tipo);
    }

    /*
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return referencia.toString();
    }

    /**
     * Metodo que permite asignar un valor al atributo seleccionada
     *
     * @param seleccionada Valor a ser asignado al atributo seleccionada
     */
    public void setSeleccionada(boolean seleccionada) {
        this.seleccionada = seleccionada;
    }

    /*
     * @see co.edu.utp.gia.sms.entidades.Referencia#getTopicos()
     */
    public List<Topico> getTopicos() {
        return referencia.getTopicos();
    }

    /**
     * Permimte adicionar una evaluacion a la referencia
     *
     * @param evaluacion Evaluacion a ser adicionada
     */
    public void addEvaluacion(EvaluacionCalidad evaluacion) {
        if (evaluaciones == null) {
            evaluaciones = new ArrayList<>();
        }
        evaluaciones.add(evaluacion);
    }


    /*
     * @see co.edu.utp.gia.sms.entidades.Referencia#getTotalEvaluacionCalidad()
     */
    public Float getTotalEvaluacionCalidad() {
        return referencia.getTotalEvaluacionCalidad();
    }

    public EvaluacionCalidad getEvaluacionCalidad(AtributoCalidad atributo) {
        return getEvaluaciones().stream()
                .filter(evaluacionCalidad -> evaluacionCalidad.getAtributoCalidad().equals(atributo))
                .findFirst()
                .orElse(null);
    }


    /*
     * @see co.edu.utp.gia.sms.entidades.Referencia#getNota()
     */
    public String getNota() {
        return referencia.getNota();
    }

    /*
     * @see co.edu.utp.gia.sms.entidades.Referencia#setNota(java.lang.String)
     */
    public void setNota(String nota) {
        referencia.setNota(nota);
    }


    /*
     * @see co.edu.utp.gia.sms.entidades.Referencia#getRelevancia()
     */
    public Integer getRelevancia() {
        return referencia.getRelevancia();
    }

    /*
     * @see co.edu.utp.gia.sms.entidades.Referencia#setRelevancia(java.lang.Integer)
     */
    public void setRelevancia(Integer relevancia) {
        referencia.setRelevancia(relevancia);
    }

    /*
     * @see co.edu.utp.gia.sms.entidades.Referencia#getCitas()
     */
    public Integer getCitas() {
        return referencia.getCitas();
    }

    /*
     * @see co.edu.utp.gia.sms.entidades.Referencia#setCitas(java.lang.Integer)
     */
    public void setCitas(Integer citas) {
        referencia.setCitas(citas);
    }


    /*
     * @see co.edu.utp.gia.sms.entidades.Referencia#getSpsid()
     */
    public String getSpsid() {
        return referencia.getSpsid();
    }

    /*
     * @see co.edu.utp.gia.sms.entidades.Referencia#setSpsid(java.lang.String)
     */
    public void setSpsid(String spsid) {
        referencia.setSpsid(spsid);
    }

    /*
     * @see co.edu.utp.gia.sms.entidades.Referencia#getDuplicada()
     */
    public Boolean getDuplicada() {
        return referencia.getDuplicada();
    }

    /*
     * @see co.edu.utp.gia.sms.entidades.Referencia#setDuplicada(java.lang.Boolean)
     */
    public void setDuplicada(Boolean duplicada) {
        referencia.setDuplicada(duplicada);
    }

    public String getValorEvaluacion(AtributoCalidad atributo) {
        EvaluacionCalidad evaluacion = getEvaluacionCalidad(atributo);
        if (evaluacion == null) {
            return "";
        }
        return evaluacion.getEvaluacionCualitativa().toString();
    }

    public Fuente getFuente() {
        return referencia.getFuente();
    }

    /*
     * @see co.edu.utp.gia.sms.entidades.Referencia#getMetadatos()
     */
    public List<Metadato> getMetadatos() {
        return referencia.getMetadatos();
    }

    public String getEvaluacion(){
        return evaluaciones.stream()
                .filter( evaluacion->evaluacion.getAtributoCalidad().equals(atributoCalidad) )
                .map(EvaluacionCalidad::getEvaluacionCualitativa)
                .map(EvaluacionCualitativa::toString)
                .findFirst()
                .orElse(null);
    }
}
