package co.edu.utp.gia.sms.importutil;

import co.edu.utp.gia.sms.entidades.Referencia;

import java.io.IOException;

/**
 * Clas utilitaria que permite obtener el número de citas de un determinado
 * documento.
 * 
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Julio Cesar Chavarro
 * @author Carlos Augusto Meneces
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 7 jul. 2020
 *
 */
public class FindReferenceCitation {

	/**
	 * Variable que representa el atributo instans de la clase
	 */
	private static final FindReferenceCitation instans = new FindReferenceCitation();

	/**
	 * Metodo que permite obtener el valor del atributo instans
	 * 
	 * @return El valor del atributo instans
	 */
	public static FindReferenceCitation getInstans() {
		return instans;
	}

	/**
	 * Metodo que permite obtener la traduccion del resumen de una
	 * {@link Referencia}
	 * 
	 * @param referencia
	 * @return
	 * @throws IOException
	 */
	public String findTranslate(Referencia referencia) throws IOException {
//			referencia.setCitas( Integer.parseInt(aux.substring(0, aux.indexOf('<'))) );
		return findTranslate(referencia.getResumen());
	}

	/**
	 * Metodo que permite obtener la traduccion del resumen de una
	 * {@link Referencia}
	 * 
	 * @param texto texto a traducir
	 * @return El texto
	 */
	public String findTranslate(String texto){
		return texto;
	}

}
