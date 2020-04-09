package co.edu.utp.gia.sms.beans;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.SessionMap;
import javax.inject.Inject;

import org.primefaces.PrimeFaces;

import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.PreguntaEJB;

@ManagedBean
@ViewScoped
public class RegistroTopicoBean implements Serializable {

	private String descripcion;
	private Integer id;
	@Inject
	private PreguntaEJB preguntaEJB;
	
	@SessionMap
	private Map<String, Object> sessionMap;

	/**
	 * Permite registrar un topico
	 */
	public void registrar() {
		Topico topico = null;
		id = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idPregunta");
		if (id != null) {
			topico = preguntaEJB.adicionarTopico(id, descripcion);
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("pregunta");
		}
		PrimeFaces.current().dialog().closeDynamic(topico);
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
	 * Metodo que permite obtener el valor del atributo id
	 * 
	 * @return El valor del atributo id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Metodo que permite asignar un valor al atributo id
	 * 
	 * @param id Valor a ser asignado al atributo id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

}
