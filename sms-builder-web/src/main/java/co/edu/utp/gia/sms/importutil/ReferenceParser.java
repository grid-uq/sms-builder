package co.edu.utp.gia.sms.importutil;

import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.TipoMetadato;

/**
 * Define de forma general un Parser que traduce convierte un texto a Referencia
 * 
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @version 1.0
 * @since 20/06/2019
 *
 */
public abstract class ReferenceParser {
	/**
	 * Variable que representa el atributo fuente de la clase
	 */
	private final Fuente fuente;

	/**
	 * Metodo que permite inicializar los elementos de la clase ReferenceParser
	 * 
	 * @param fuente
	 */
	public ReferenceParser(Fuente fuente) {
		this.fuente = fuente;
	}

	/**
	 * @param texto
	 * @return
	 */
	public final Referencia parse(String texto) {
		
		if ("".equals(texto.trim())) {
			return null;
		}
		
		Referencia referencia = new Referencia();
		
		if ( fuente.equals(Fuente.MANUAL) || fuente.equals(Fuente.SNOWBALL_BACKWARD ) || fuente.equals(Fuente.SNOWBALL_FORWARD ) )  {
			referencia.setFiltro(3);
		}
		
		referencia.addElement(TipoMetadato.FUENTE, fuente.toString());
		procesarTexto(referencia, texto);
		return referencia;
	}

	/**
	 * @param referencia
	 * @param texto
	 */
	protected abstract void procesarTexto(Referencia referencia, String texto);
}
