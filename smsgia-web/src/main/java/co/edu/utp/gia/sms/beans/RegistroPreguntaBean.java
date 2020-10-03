package co.edu.utp.gia.sms.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import co.edu.utp.gia.sms.dtos.PreguntaDTO;
import co.edu.utp.gia.sms.entidades.Objetivo;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.PreguntaEJB;
import co.edu.utp.gia.sms.negocio.TopicoEJB;

/**
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Julio Cesar Chavarro
 * @author Carlos Augusto Meneces
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0 
 * @since 9 abr. 2020
 *
 */
@Named
@ViewScoped
public class RegistroPreguntaBean extends GenericBean<PreguntaDTO> {
	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 1L;
	private String descripcion;
	private String codigo;
	private List<PreguntaDTO> preguntas;
	
	private List<Objetivo> listaObjetivos;
	

	@Inject
	private PreguntaEJB preguntaEJB;
	@Inject
	private TopicoEJB topicoEJB;

	public void inicializar() {
		if (revision != null) {
			System.out.println("Buscando preguntas revision " + revision.getId());
			preguntas = preguntaEJB.obtenerPreguntas(revision.getId());
		}
		listaObjetivos = new ArrayList<Objetivo>();
	}

	public String registrar() {
//		preguntaEJB.registrar(codigo, descripcion, listaIdObjetivos);
		preguntaEJB.registrar(codigo, descripcion, listaObjetivos);
		mostrarMensajeGeneral("Pregunta Adicionada" );
		codigo = "";
		descripcion = "";
		return "/revision/registroPregunta";
	}

	@Override
	public void actualizar(PreguntaDTO objeto) {
		preguntaEJB.actualizar(objeto);
	}
	

	/**
	 * Permite eliminar una pregunta
	 * 
	 * @param pregunta pregunta a eliminar
	 */
	public void eliminar(PreguntaDTO pregunta) {
		preguntaEJB.eliminar(pregunta.getId());
		preguntas.remove(pregunta);
		mostrarMensajeGeneral("Pregunta eliminada");
	}

	/**
	 * Permite eliminar un {@link Topico} de una pregunta 
	 * 
	 * @param topico Topico de la pregunta a eliminar
	 */
	public void eliminarTopico(PreguntaDTO pregunta,Topico topico) {
		topicoEJB.eliminar(topico.getId());
		mostrarMensajeGeneral("Topico eliminado");
//		inicializar();
		pregunta.getTopicos().remove(topico);
	}
	
	public void adicionarTopico(Integer id) {
		System.out.println("Llamando Dialogo para pregunta "+id);
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        addToSession("idPregunta",id);
		PrimeFaces.current().dialog().openDynamic("/revision/registrarTopico", options, null);
	}
//	public void adicionarTopico(Pregunta pregunta) {
////		System.out.println("Llamando Dialogo para pregunta "+id);
//        Map<String,Object> options = new HashMap<String, Object>();
//        options.put("resizable", false);
//        options.put("draggable", false);
//        options.put("modal", true);
//        
//        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pregunta",pregunta);
//		PrimeFaces.current().dialog().openDynamic("/revision/registrarTopico", options, null);
//	}

	
    public void onTopicoCreado(SelectEvent<Topico> event) {
//        Topico topico = event.getObject();
        mostrarMensajeGeneral("Topico Adicionado");
		inicializar();
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
	 * Metodo que permite obtener el valor del atributo preguntas
	 * 
	 * @return El valor del atributo preguntas
	 */
	public List<PreguntaDTO> getPreguntas() {
		return preguntas;
	}

	/**
	 * Metodo que permite asignar un valor al atributo preguntas
	 * 
	 * @param preguntas Valor a ser asignado al atributo preguntas
	 */
	public void setPreguntas(List<PreguntaDTO> preguntas) {
		this.preguntas = preguntas;
	}

	/**
	 * Metodo que permite obtener el valor del atributo listaObjetivos
	 * @return El valor del atributo listaObjetivos
	 */
	public List<Objetivo> getListaObjetivos() {
		return listaObjetivos;
	}

	/**
	 * Metodo que permite asignar un valor al atributo listaObjetivos
	 * @param listaObjetivos Valor a ser asignado al atributo listaObjetivos
	 */
	public void setListaObjetivos(List<Objetivo> listaObjetivos) {
		this.listaObjetivos = listaObjetivos;
	}

	
	
	
}
