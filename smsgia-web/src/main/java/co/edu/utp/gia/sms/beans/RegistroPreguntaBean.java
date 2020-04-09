package co.edu.utp.gia.sms.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import co.edu.utp.gia.sms.dtos.PreguntaDTO;
import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.PreguntaEJB;

/**
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Julio Cesar Chavarro
 * @author Carlos Augusto Meneces
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0 
 * @since 9 abr. 2020
 *
 */
@ManagedBean
@ViewScoped

public class RegistroPreguntaBean implements Serializable {
	private String descripcion;
	@ManagedProperty(value = "#{registroInicialBean.revision}")
	private Revision revision;
	private String codigo;
	private List<PreguntaDTO> preguntas;
	
	private List<Integer> listaIdObjetivos;
	

	@Inject
	private PreguntaEJB preguntaEJB;

	@PostConstruct
	public void inicializar() {

		if (revision != null) {
			System.out.println("Buscando preguntas revision " + revision.getId());
			preguntas = preguntaEJB.obtenerPreguntas(revision.getId());
		}
	}

	public String registrar() {
		System.out.println("Se registraron los siguientes datos :");
		preguntaEJB.registrar(codigo, descripcion, listaIdObjetivos);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pregunta Adicionada"));
		codigo = "";
		descripcion = "";
		return "/revision/registroPregunta";
	}

	public void onRowEdit(RowEditEvent event) {
		Pregunta pregunta = ((Pregunta) event.getObject());
		preguntaEJB.actualizar(pregunta);
		FacesMessage msg = new FacesMessage("Pregunta editada");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
//    	Pregunta pregunta = ((Pregunta) event.getObject());
		FacesMessage msg = new FacesMessage("Edicion cancelada");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * Permite eliminar una pregunta
	 * 
	 * @param pregunta pregunta a eliminar
	 */
	public void eliminar(Pregunta pregunta) {
		preguntaEJB.eliminar(pregunta.getId());
		preguntas.remove(pregunta);
		FacesMessage msg = new FacesMessage("Pregunta eliminada");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * Permite eliminar un {@link Topico} de una pregunta 
	 * 
	 * @param topico Topico de la pregunta a eliminar
	 */
	public void eliminarTopico(PreguntaDTO pregunta,Topico topico) {
		preguntaEJB.eliminarTopico(topico.getId());
		FacesMessage msg = new FacesMessage("Topico eliminado");
		FacesContext.getCurrentInstance().addMessage(null, msg);
//		inicializar();
		pregunta.getTopicos().remove(topico);
	}
	
	public void adicionarTopico(Integer id) {
		System.out.println("Llamando Dialogo para pregunta "+id);
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idPregunta",id);
		PrimeFaces.current().dialog().openDynamic("/revision/registrarTopico", options, null);
	}
//	public void adicionarTopico(Pregunta pregunta) {
////		System.out.println("Llamando Dialogo para pregunta "+id);
//        Map<String,Object> options = new HashMap<String, Object>();
//        options.put("resizable", false);
//        options.put("draggable", false);
//        options.put("modal", true);
//        
//        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pregunta",pregunta);
//		PrimeFaces.current().dialog().openDynamic("/revision/registrarTopico", options, null);
//	}

	
    public void onTopicoCreado(SelectEvent event) {
    	System.out.println("OnTopicCreado");
        Topico topico = (Topico) event.getObject();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Topico Adicionado", "Id:" + topico.getId());
		FacesContext.getCurrentInstance().addMessage(null, message);
		inicializar();
    }

	
////////// ----- GET/SET ----- ////////////	
	/**
	 * Metodo que permite obtener el valor del atributo descripcion
	 * 
	 * @return El valor del atributo descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Metodo que permite asignar un valor al atributo descripcion
	 * 
	 * @param descripcion Valor a ser asignado al atributo descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	 * Metodo que permite obtener el valor del atributo codigo
	 * 
	 * @return El valor del atributo codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Metodo que permite asignar un valor al atributo codigo
	 * 
	 * @param codigo Valor a ser asignado al atributo codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Metodo que permite obtener el valor del atributo preguntas
	 * 
	 * @return El valor del atributo preguntas
	 */
	public List<PreguntaDTO> getPreguntas() {
		return preguntas;
	}

	/**
	 * Metodo que permite asignar un valor al atributo preguntas
	 * 
	 * @param preguntas Valor a ser asignado al atributo preguntas
	 */
	public void setPreguntas(List<PreguntaDTO> preguntas) {
		this.preguntas = preguntas;
	}

	/**
	 * Metodo que permite obtener el valor del atributo listaIdObjetivos
	 * @return El valor del atributo listaIdObjetivos
	 */
	public List<Integer> getListaIdObjetivos() {
		return listaIdObjetivos;
	}

	/**
	 * Metodo que permite asignar un valor al atributo listaIdObjetivos
	 * @param listaIdObjetivos Valor a ser asignado al atributo listaIdObjetivos
	 */
	public void setListaIdObjetivos(List<Integer> listaIdObjetivos) {
		this.listaIdObjetivos = listaIdObjetivos;
	}

	
	
	
}
