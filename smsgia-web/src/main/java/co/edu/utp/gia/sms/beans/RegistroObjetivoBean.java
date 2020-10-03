package co.edu.utp.gia.sms.beans;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import co.edu.utp.gia.sms.entidades.Objetivo;
import co.edu.utp.gia.sms.negocio.ObjetivoEJB;

@Named
@ViewScoped
public class RegistroObjetivoBean extends GenericBean<Objetivo> {
	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 9060626480979863537L;
	private String codigo;
	private String descripcion;
	private List<Objetivo> objetivos;

	@Inject
	private ObjetivoEJB objetivoEJB;

	public void inicializar() {
		if (revision != null) {
			objetivos = objetivoEJB.obtenerObjetivo(revision.getId());
		}
	}
	
	

	public void registrar() {
		Objetivo objetivo = objetivoEJB.registrar(codigo, descripcion, revision.getId());
		objetivos.add(objetivo);
		mostrarMensajeGeneral("Objetivo Adicionado");
		codigo = "";
		descripcion = "";
	}


	@Override
	public void actualizar(Objetivo objeto) {
		objetivoEJB.actualizar(objeto);
	}


	/**
	 * Permite eliminar un Objetivo
	 * 
	 * @param objetivo Objetivo a eliminar
	 */
	public void eliminar(Objetivo objetivo) {
		objetivoEJB.eliminar(objetivo.getId());
		objetivos.remove(objetivo);
		mostrarMensajeGeneral("Registro eliminado");
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
	 * Metodo que permite obtener el listado de objetivos
	 * 
	 * @return El listado de los objetivos
	 */
	public List<Objetivo> getObjetivos() {
		return objetivos;
	}

	/**
	 * Metodo que permite asignar un valor al atributo objetivos
	 * 
	 * @param terminos Valor a ser asignado al atributo objetivos
	 */
	public void setObjetivos(List<Objetivo> objetivos) {
		this.objetivos = objetivos;
	}


}
