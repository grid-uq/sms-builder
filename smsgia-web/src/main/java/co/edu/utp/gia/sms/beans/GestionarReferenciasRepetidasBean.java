package co.edu.utp.gia.sms.beans;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;

@Named
@ViewScoped
public class GestionarReferenciasRepetidasBean extends GenericBean<ReferenciaDTO>{

	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 8275908838815243233L;
	private List<ReferenciaDTO> referencias;
	@Inject
	private ReferenciaEJB referenciaEJB;

	public void inicializar() {
		if (revision != null) {
			referencias = referenciaEJB.obtenerTodas(revision.getId(), 0);
		}
	}
	
	public void sugerir() {
		sugerirSeleccion();
	}

	private void sugerirSeleccion() {
		ReferenciaDTO anterior = null;
		for (ReferenciaDTO actual : referencias) {
			if( anterior == null || anterior.getNombre() == null || actual.getNombre() == null || !anterior.getNombre().equalsIgnoreCase( actual.getNombre()  ) ) {
				actual.setSeleccionada(true);
			}
			anterior = actual;
		}
		
	}

	public void guardar() {
		for (ReferenciaDTO referencia : referencias) {
			referenciaEJB.actualizarFiltro(referencia.getId(),referencia.getFiltro());
		}
		mostrarMensajeGeneral("Se guardaron los registro");
	}

//	public void onSelect(ReferenciaDTO referencia) {
//		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
//	}

	/**
	 * Metodo que permite obtener el valor del atributo referencias
	 * 
	 * @return El valor del atributo referencias
	 */
	public List<ReferenciaDTO> getReferencias() {
		return referencias;
	}

	/**
	 * Metodo que permite asignar un valor al atributo referencias
	 * 
	 * @param referencias Valor a ser asignado al atributo referencias
	 */
	public void setReferencias(List<ReferenciaDTO> referencias) {
		this.referencias = referencias;
	}

}
