package co.edu.utp.gia.sms.beans;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.PreguntaEJB;

@Named
@ViewScoped
public class RegistroTopicoBean extends GenericBean<Topico> {

	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 5103003688870607449L;
	private String descripcion;
	private Integer id;
	@Inject
	private PreguntaEJB preguntaEJB;
	
	
	/**
	 * Permite registrar un topico
	 */
	public void registrar() {
		Topico topico = null;
		id = (Integer) getAndRemoveFromSession("idPregunta");
		if (id != null) {
			topico = preguntaEJB.adicionarTopico(id, descripcion);
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

	@Override
	public void inicializar() {
		
	}

}
