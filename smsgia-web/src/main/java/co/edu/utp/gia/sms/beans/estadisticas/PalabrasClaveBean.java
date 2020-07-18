package co.edu.utp.gia.sms.beans.estadisticas;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudModel;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.negocio.EstadisticaEJB;

@ManagedBean
@ViewScoped
public class PalabrasClaveBean {

	private TagCloudModel modelo;
	@Inject
	private EstadisticaEJB estadisticaEJB;

	private Integer minimo;
	
	@ManagedProperty("#{registroInicialBean.revision}")
	private Revision revision;
	
	List<DatoDTO> palabras;

	@PostConstruct
	public void inicializar() {
		minimo = 2;
//		setTitulo("Referencias por Tipo");
		if (revision != null) {
			crearModelo();
		}
	}

	public void crearModelo() {
		palabras = estadisticaEJB.obtenerPalabrasClave(revision.getId(),minimo);
		modelo = new DefaultTagCloudModel();
		palabras.forEach( d->{ modelo.addTag( new DefaultTagCloudItem(d.getEtiqueta(), d.getValor().intValue()) ); } );
	}

	/**
	 * Metodo que permite obtener el valor del atributo modelo
	 * @return El valor del atributo modelo
	 */
	public TagCloudModel getModelo() {
		return modelo;
	}

	/**
	 * Metodo que permite asignar un valor al atributo modelo
	 * @param modelo Valor a ser asignado al atributo modelo
	 */
	public void setModelo(TagCloudModel modelo) {
		this.modelo = modelo;
	}

	/**
	 * Metodo que permite obtener el valor del atributo minimo
	 * @return El valor del atributo minimo
	 */
	public Integer getMinimo() {
		return minimo;
	}

	/**
	 * Metodo que permite asignar un valor al atributo minimo
	 * @param minimo Valor a ser asignado al atributo minimo
	 */
	public void setMinimo(Integer minimo) {
		this.minimo = minimo;
	}

	/**
	 * Metodo que permite obtener el valor del atributo revision
	 * @return El valor del atributo revision
	 */
	public Revision getRevision() {
		return revision;
	}

	/**
	 * Metodo que permite asignar un valor al atributo revision
	 * @param revision Valor a ser asignado al atributo revision
	 */
	public void setRevision(Revision revision) {
		this.revision = revision;
	}

	/**
	 * Metodo que permite obtener el valor del atributo palabras
	 * @return El valor del atributo palabras
	 */
	public List<DatoDTO> getPalabras() {
		return palabras;
	}

	/**
	 * Metodo que permite asignar un valor al atributo palabras
	 * @param palabras Valor a ser asignado al atributo palabras
	 */
	public void setPalabras(List<DatoDTO> palabras) {
		this.palabras = palabras;
	}

}
