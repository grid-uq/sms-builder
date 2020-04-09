package co.edu.utp.gia.sms.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;

import co.edu.utp.gia.sms.entidades.Objetivo;
import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.negocio.ObjetivoEJB;

@ManagedBean
@ViewScoped

public class RegistroObjetivoBean implements Serializable {
	private String codigo;
	private String descripcion;
	@ManagedProperty(value = "#{registroInicialBean.revision}")
	private Revision revision;
	private List<Objetivo> objetivos;
	
	@Inject
	private ObjetivoEJB objetivoEJB;
	

	@PostConstruct
	public void inicializar() {
		if (revision != null) {
			objetivos = objetivoEJB.obtenerObjetivo(revision.getId());
		}
	}

	public void registrar() {
		Objetivo objetivo = objetivoEJB.registrar(codigo, descripcion, revision.getId());
		objetivos.add(objetivo);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Objetivo Adicionado"));
		codigo = "";
		descripcion = "";		
	}

	public void onRowEdit(RowEditEvent event) {
		Objetivo objetivo = ((Objetivo) event.getObject());
		objetivoEJB.actualizar(objetivo);
		FacesMessage msg = new FacesMessage("Registro editado");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edicion cancelada");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * Permite eliminar un Objetivo
	 * 
	 * @param objetivo		Objetivo a eliminar
	 */
	public void eliminar(Objetivo objetivo) {
		objetivoEJB.eliminar(objetivo.getId());
		objetivos.remove(objetivo);
		FacesMessage msg = new FacesMessage("Registro eliminado");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	

	/**
	 * Permite eliminar una {@link Pregunta} de un Objetivo 
	 * 
	 * @param pregunta Pregunta del Objetivo a eliminar
	 */

	public void eliminarPregunta(Objetivo objetivo, Pregunta pregunta) {
		objetivoEJB.eliminarPregunta(pregunta.getId());
		FacesMessage msg = new FacesMessage("Pregunta eliminada");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		objetivo.getPreguntas().remove(pregunta);
	}
	
	
	
//	public void adicionarTopico(Integer id) {
//		System.out.println("Llamando Dialogo para pregunta "+id);
//        Map<String,Object> options = new HashMap<String, Object>();
//        options.put("resizable", false);
//        options.put("draggable", false);
//        options.put("modal", true);
//        
//        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idPregunta",id);
//		PrimeFaces.current().dialog().openDynamic("/revision/registrarTopico", options, null);
//	}

	
//    public void onTopicoCreado(SelectEvent event) {
//        Topico topico = (Topico) event.getObject();
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Topico Adicionado", "Id:" + topico.getId());
//		FacesContext.getCurrentInstance().addMessage(null, message);
//		inicializar();
//    }
	
	
	
	
	
	
	
////////// ----- GET/SET ----- ////////////	
	/**
	 * Metodo que permite obtener el valor del atributo codigo
	 * 
	 * @return 		El valor del atributo codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Metodo que permite asignar un valor al atributo codigo
	 * 
	 * @param codigo 		Valor a ser asignado al atributo codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
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
	 * Metodo que permite obtener el listado de objetivos
	 * @return 			El listado de los objetivos
	 */
	public List<Objetivo> getObjetivos() {
		return objetivos;
	}

	/**
	 * Metodo que permite asignar un valor al atributo objetivos
	 * @param terminos 	Valor a ser asignado al atributo objetivos
	 */
	public void setObjetivos(List<Objetivo> objetivos) {
		this.objetivos = objetivos;
	}
}
