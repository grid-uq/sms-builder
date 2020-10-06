package co.edu.utp.gia.sms.beans.estadisticas;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import co.edu.utp.gia.sms.importutil.TipoFuente;

@Named
@ViewScoped
public class ReferenciasTipoFuenteBean extends EstaditicaDatoDTOBaseBean {

	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = -5273832765304254823L;
	private TipoFuente tipo;

	public void inicializar() {
		setTitulo("Referencias por Tipo de Fuente");
		setEjeX("Fuente");
		setEjeY("# Referencias");

//		setTiposGrafica(new String[] { "bar" });
//		setTipoGrafica("bar");

		if (getRevision() != null) {
			setDatos(getEstadisticaEJB().obtenerReferenciasTipoFuente(getRevision().getId()));

			crearModelo();
			
		}
	}


	public void onChangeTipoFuente() {
		if (tipo == null) {
			setDatos(getEstadisticaEJB().obtenerReferenciasTipoFuente(getRevision().getId()));
		} else {
			setDatos(getEstadisticaEJB().obtenerReferenciasTipoFuenteNombre(getRevision().getId(),tipo));
		} 
		crearModelo();
	}

	/**
	 * Metodo que permite obtener el valor del atributo tipo
	 * 
	 * @return El valor del atributo tipo
	 */
	public TipoFuente getTipo() {
		return tipo;
	}

	/**
	 * Metodo que permite asignar un valor al atributo tipo
	 * 
	 * @param tipo Valor a ser asignado al atributo tipo
	 */
	public void setTipo(TipoFuente tipo) {
		System.out.println("TIPO "+tipo);
		this.tipo = tipo;
	}

	public TipoFuente[] getTiposFuente() {
		return TipoFuente.values();
	}

}
