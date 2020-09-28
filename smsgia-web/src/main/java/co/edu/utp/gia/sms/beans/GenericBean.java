package co.edu.utp.gia.sms.beans;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.annotation.SessionMap;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;

import co.edu.utp.gia.sms.entidades.Revision;

public abstract class GenericBean<Objeto> implements Serializable {
	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 9060626480979863537L;
	@Inject
	private RegistroInicialBean registroInicialBean;
	protected Revision revision;
	@Inject
	private FacesContext facesContext;
	@Inject @SessionMap
	private Map<String,Object> sessionMap;

	
//	@Inject
//	private ObjetivoEJB objetivoEJB;

	@PostConstruct
	public void init() {
		if (registroInicialBean != null) {
			revision = registroInicialBean.getRevision();
		}
		inicializar();
//		if (revision != null) {
//			objetivos = objetivoEJB.obtenerObjetivo(revision.getId());
//		}
	}
	
	public abstract void inicializar();
//	public void registrar() {
//		Objetivo objetivo = objetivoEJB.registrar(codigo, descripcion, revision.getId());
//		objetivos.add(objetivo);
//		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Objetivo Adicionado"));
//		codigo = "";
//		descripcion = "";
//	}

	public void onRowEdit(RowEditEvent<Objeto> event) {
		Objeto objeto = event.getObject();
		try {
			actualizar(objeto);
			mostrarMensajeGeneral("Registro actualizado");
		} catch (Exception e) {
			mostrarErrorGeneral(e.getMessage());
		}
	}

	public void actualizar(Objeto objeto) {
		
	}

	public void onRowCancel(RowEditEvent<Objeto> event) {
		FacesMessage msg = new FacesMessage("Edicion cancelada");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

//	/**
//	 * Permite eliminar un Objetivo
//	 * 
//	 * @param objetivo Objetivo a eliminar
//	 */
//	public void eliminar(Objetivo objetivo) {
//		objetivoEJB.eliminar(objetivo.getId());
//		objetivos.remove(objetivo);
//		FacesMessage msg = new FacesMessage("Registro eliminado");
//		FacesContext.getCurrentInstance().addMessage(null, msg);
//	}

	protected void mostrarErrorGeneral(String mensaje) {
		mostrarErrorEspecifico(null, mensaje);
	}

	protected void mostrarErrorEspecifico(String idComponente, String mensaje) {
		mostrarMensaje(idComponente, mensaje, FacesMessage.SEVERITY_ERROR);
	}

	protected void mostrarAdvertenciaGeneral(String mensaje) {
		mostrarAdvertenciaEspecifico(null, mensaje);
	}

	protected void mostrarAdvertenciaEspecifico(String idComponente, String mensaje) {
		mostrarMensaje(idComponente, mensaje, FacesMessage.SEVERITY_WARN);
	}

	protected void mostrarMensajeGeneral(String mensaje) {
		mostrarMensajeEspecifico(null, mensaje);
	}

	protected void mostrarMensajeEspecifico(String idComponente, String mensaje) {
		mostrarMensaje(idComponente, mensaje, FacesMessage.SEVERITY_INFO);
	}

	protected void mostrarMensaje(String idComponente, String mensaje, Severity severidad) {
		FacesMessage facesMessage = new FacesMessage(severidad, mensaje, mensaje);
		getFacesContext().addMessage(idComponente, facesMessage);
	}
	
	protected FacesContext getFacesContext() {
		return facesContext;
	}
	
	protected void addToSession(String key,Object value) {
		getSessionMap().put(key, value);
	}

	protected Object getFromSession(String key) {
		return getSessionMap().get(key);
	}
	
	protected Object getAndRemoveFromSession(String key) {
		return getSessionMap().remove(key);
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
	 * Metodo que permite obtener el valor del atributo sessionMap
	 * @return El valor del atributo sessionMap
	 */
	protected Map<String, Object> getSessionMap() {
		return sessionMap;
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

}
