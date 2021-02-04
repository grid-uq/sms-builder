package co.edu.utp.gia.sms.beans.estadisticas;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import co.edu.utp.gia.sms.beans.AbstractRevisionBean;
import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudModel;

import co.edu.utp.gia.sms.beans.AbstractBean;
import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.negocio.EstadisticaEJB;

@Named
@ViewScoped
public class PalabrasClaveBean extends AbstractRevisionBean {

	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = -2705046687022203958L;
	private TagCloudModel modelo;
	@Inject
	private EstadisticaEJB estadisticaEJB;

	private Integer minimo;
	
	
	private List<DatoDTO> palabras;

	
	public void inicializar() {
		minimo = 2;
//		setTitulo("Referencias por Tipo");
		if (getRevision() != null) {
			crearModelo();
		}
	}

	public void crearModelo() {
		palabras = estadisticaEJB.obtenerPalabrasClave(getRevision().getId(),minimo);
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
