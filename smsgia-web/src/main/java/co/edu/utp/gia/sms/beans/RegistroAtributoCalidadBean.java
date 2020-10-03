package co.edu.utp.gia.sms.beans;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.negocio.AtributoCalidadEJB;


@Named
@ViewScoped
public class RegistroAtributoCalidadBean extends GenericBean<AtributoCalidad> {
	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 1755642135191615078L;
	private String descripcion;
	private List<AtributoCalidad> atributosCalidad;
	@Inject
	private AtributoCalidadEJB atributoCalidadEJB;

	public void inicializar() {
		if (revision != null) {
			atributosCalidad = atributoCalidadEJB.obtenerAtributosCalidad(revision.getId());
		}
	}

	public void registrar() {
		AtributoCalidad atributo = atributoCalidadEJB.registrar( descripcion, revision.getId());
		atributosCalidad.add(atributo);
		mostrarMensajeGeneral("Registro Adicionado");
		descripcion = "";
	}

	@Override
	public void actualizar(AtributoCalidad objeto) {
		atributoCalidadEJB.actualizar(objeto);
	}


	/**
	 * Permite eliminar una atributo de calidad
	 * 
	 * @param atributoCalidad Atributo de calidad a eliminar
	 */
	public void eliminar(AtributoCalidad atributoCalidad) {
		atributoCalidadEJB.eliminar(atributoCalidad.getId());
		atributosCalidad.remove(atributoCalidad);
		mostrarMensajeGeneral("Registro eliminado");
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
	 * Metodo que permite obtener el valor del atributo atributosCalidad
	 * @return El valor del atributo atributosCalidad
	 */
	public List<AtributoCalidad> getAtributosCalidad() {
		return atributosCalidad;
	}

	/**
	 * Metodo que permite asignar un valor al atributo atributosCalidad
	 * @param atributosCalidad Valor a ser asignado al atributo atributosCalidad
	 */
	public void setAtributosCalidad(List<AtributoCalidad> atributosCalidad) {
		this.atributosCalidad = atributosCalidad;
	}
}
