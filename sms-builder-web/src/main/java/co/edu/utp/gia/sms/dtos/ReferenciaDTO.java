package co.edu.utp.gia.sms.dtos;

import co.edu.utp.gia.sms.entidades.*;
import co.edu.utp.gia.sms.importutil.Fuente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ReferenciaDTO implements Serializable {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = -2840554748252612956L;
    @Getter
    @Setter
    private Referencia referencia;

    private boolean seleccionada;
    @Getter
    @Setter
    private int etapa;
    @Getter
    @Setter
    private List<EvaluacionCalidad> evaluaciones;
    @Getter
    @Setter
    private String autores;
    @Getter
    @Setter
    private String keywords;
    @Getter
    @Setter
    private String abstracts;
    @Getter
    @Setter
    private Fuente fuente;
    @Getter
    @Setter
    private List<Metadato> metadatos;

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
		abstracts = referencia.getResumen();
        evaluarSeleccion();
    }

    private void evaluarSeleccion() {
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
     * @param id
     * @see co.edu.utp.gia.sms.entidades.Referencia#setId(java.lang.Integer)
     */
    public void setId(Integer id) {
        referencia.setId(id);
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
     * Metodo que permite asignar un valor al atributo seleccionada
     *
     * @param seleccionada Valor a ser asignado al atributo seleccionada
     */
    public void setSeleccionada(boolean seleccionada) {
//        if (this.seleccionada != seleccionada) {
//            int base = (etapa << 1) | 1;
//            if (seleccionada) {
//                setFiltro(getFiltro() | base);
//            } else {
//                setFiltro(getFiltro() & etapa);
//            }
//        }
        this.seleccionada = seleccionada;
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

    public EvaluacionCalidad getEvaluacionCalidad(AtributoCalidad atributo) {
        for (EvaluacionCalidad evaluacionCalidad : evaluaciones) {
            if (evaluacionCalidad.getAtributoCalidad().equals(atributo)) {
                return evaluacionCalidad;
            }
        }
        return null;
    }


    /**
     * @return
     * @see co.edu.utp.gia.sms.entidades.Referencia#getNota()
     */
    public String getNota() {
        return referencia.getNota();
    }

    /**
     * @param nota
     * @see co.edu.utp.gia.sms.entidades.Referencia#setNota(java.lang.String)
     */
    public void setNota(String nota) {
        referencia.setNota(nota);
    }


    /**
     * @return
     * @see co.edu.utp.gia.sms.entidades.Referencia#getRelevancia()
     */
    public Integer getRelevancia() {
        return referencia.getRelevancia();
    }

    /**
     * @param relevancia
     * @see co.edu.utp.gia.sms.entidades.Referencia#setRelevancia(java.lang.Integer)
     */
    public void setRelevancia(Integer relevancia) {
        referencia.setRelevancia(relevancia);
    }

    /**
     * @return
     * @see co.edu.utp.gia.sms.entidades.Referencia#getCitas()
     */

    public Integer getCitas() {
        return referencia.getCitas();
    }

    /**
     * @param citas
     * @see co.edu.utp.gia.sms.entidades.Referencia#setCitas(java.lang.Integer)
     */
    public void setCitas(Integer citas) {
        referencia.setCitas(citas);
    }

    /**
     * @return
     * @see co.edu.utp.gia.sms.entidades.Referencia#getPonderacionCitas()
     */
    public Float getPonderacionCitas() {
        return referencia.getPonderacionCitas();
    }

    /**
     * @param ponderacionCitas
     * @see co.edu.utp.gia.sms.entidades.Referencia#setPonderacionCitas(java.lang.Float)
     */
    public void setPonderacionCitas(Float ponderacionCitas) {
        referencia.setPonderacionCitas(ponderacionCitas);
    }

    /**
     * @return
     * @see co.edu.utp.gia.sms.entidades.Referencia#getSpsid()
     */
    public String getSpsid() {
        return referencia.getSpsid();
    }

    /**
     * @param spsid
     * @see co.edu.utp.gia.sms.entidades.Referencia#setSpsid(java.lang.String)
     */
    public void setSpsid(String spsid) {
        referencia.setSpsid(spsid);
    }

    /**
     * @return
     * @see co.edu.utp.gia.sms.entidades.Referencia#getSci()
     */
    public Float getSci() {
        return referencia.getSci();
    }

    /**
     * @param sci
     * @see co.edu.utp.gia.sms.entidades.Referencia#setSci(java.lang.Float)
     */
    public void setSci(Float sci) {
        referencia.setSci(sci);
    }

    /**
     * @return
     * @see co.edu.utp.gia.sms.entidades.Referencia#getSrrqi()
     */
    public Float getSrrqi() {
        return referencia.getSrrqi();
    }

    /**
     * @param srrqi
     * @see co.edu.utp.gia.sms.entidades.Referencia#setSrrqi(java.lang.Float)
     */
    public void setSrrqi(Float srrqi) {
        referencia.setSrrqi(srrqi);
    }

    public String getValorEvaluacion(AtributoCalidad atributo) {
        EvaluacionCalidad evaluacion = getEvaluacionCalidad(atributo);
        if (evaluacion == null) {
            return "";
        }
        return evaluacion.getEvaluacionCualitativa().toString();
    }
}
