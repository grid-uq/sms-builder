package co.edu.utp.gia.sms.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import co.edu.utp.gia.sms.entidades.Termino;
import co.edu.utp.gia.sms.negocio.TerminoEJB;

@Named
@ViewScoped
public class RegistroTerminoBean extends GenericBean<Termino> {
	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 4369004470790305574L;
	private String descripcion;
	private List<Termino> terminos;
	@Inject
	private TerminoEJB terminoEJB;

	public void inicializar() {
		if (revision != null) {
			terminos = terminoEJB.obtenerTerminos(revision.getId());
		}
	}

	public void registrar() {
		Termino termino = terminoEJB.registrar( descripcion, revision.getId());
		terminos.add(termino);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro Adicionado"));
		descripcion = "";
	}

	@Override
	public void actualizar(Termino objeto) {
		terminoEJB.actualizar(objeto);
	}

	/**
	 * Permite eliminar una termino
	 * 
	 * @param termino termino a eliminar
	 */
	public void eliminar(Termino termino) {
		terminoEJB.eliminar(termino.getId());
		terminos.remove(termino);
		FacesMessage msg = new FacesMessage("Registro eliminado");
		FacesContext.getCurrentInstance().addMessage(null, msg);
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
	 * Metodo que permite obtener el valor del atributo terminos
	 * @return El valor del atributo terminos
	 */
	public List<Termino> getTerminos() {
		return terminos;
	}

	/**
	 * Metodo que permite asignar un valor al atributo terminos
	 * @param terminos Valor a ser asignado al atributo terminos
	 */
	public void setTerminos(List<Termino> terminos) {
		this.terminos = terminos;
	}



}
