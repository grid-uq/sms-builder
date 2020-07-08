package co.edu.utp.gia.sms.importutil;

import java.io.IOException;
import java.net.URLEncoder;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;

import co.edu.utp.gia.sms.entidades.Referencia;

/**
 * Clas utilitaria que permite obtener el número de citas de un determinado documento. 
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
	 * Metodo que permite obtener el numero de citas de una {@link Referencia}
	 * @param referencia
	 * @return
	 * @throws IOException
	 */
	public int findCitation(Referencia referencia) throws  IOException {
		HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
		HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(
				"https://scholar.google.com/scholar?hl=es&q=" + URLEncoder.encode(referencia.getNombre(), "UTF-8")

		));
		String rawResponse = request.execute().parseAsString();
		int i = rawResponse.indexOf("Citado por ");
		if (i >= 0) {
			String aux = rawResponse.substring(i + 11, i + 20);
			referencia.setCitas( Integer.parseInt(aux.substring(0, aux.indexOf('<'))) );
			return referencia.getCitas();
		}
		return 0;
	}

}
