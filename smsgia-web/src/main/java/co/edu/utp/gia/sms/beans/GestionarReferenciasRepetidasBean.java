package co.edu.utp.gia.sms.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;

@ManagedBean
@ViewScoped
public class GestionarReferenciasRepetidasBean {

	private List<Item> items;

	private List<ReferenciaDTO> referencias;
	@Inject
	private ReferenciaEJB referenciaEJB;

	@ManagedProperty("#{registroInicialBean.revision}")
	private Revision revision;

	@PostConstruct
	public void inicializar() {
		items = new ArrayList<GestionarReferenciasRepetidasBean.Item>();
		items.add(new Item(true, "item 1"));
		items.add(new Item(false, "item 2"));
		items.add(new Item(true, "item 3"));
		items.add(new Item(false, "item 4"));
		items.add(new Item(true, "item 5"));

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
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se guardaron los registro"));
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
	 * Metodo que permite obtener el valor del atributo items
	 * 
	 * @return El valor del atributo items
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * Metodo que permite asignar un valor al atributo items
	 * 
	 * @param items Valor a ser asignado al atributo items
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}

	public class Item {
		boolean seleccionado;
		String texto;

		public Item(boolean s, String t) {
			seleccionado = s;
			texto = t;
		}

		/**
		 * Metodo que permite obtener el valor del atributo seleccionado
		 * 
		 * @return El valor del atributo seleccionado
		 */
		public boolean isSeleccionado() {
			return seleccionado;
		}

		/**
		 * Metodo que permite asignar un valor al atributo seleccionado
		 * 
		 * @param seleccionado Valor a ser asignado al atributo seleccionado
		 */
		public void setSeleccionado(boolean seleccionado) {
			this.seleccionado = seleccionado;
		}

		/**
		 * Metodo que permite obtener el valor del atributo texto
		 * 
		 * @return El valor del atributo texto
		 */
		public String getTexto() {
			return texto;
		}

		/**
		 * Metodo que permite asignar un valor al atributo texto
		 * 
		 * @param texto Valor a ser asignado al atributo texto
		 */
		public void setTexto(String texto) {
			this.texto = texto;
		}

	}
}
