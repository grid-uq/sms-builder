package co.edu.utp.gia.sms.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

public abstract class GenericBean<Objeto> extends AbstractBean {
	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 9060626480979863537L;

	
//	@Inject
//	private ObjetivoEJB objetivoEJB;

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


}
