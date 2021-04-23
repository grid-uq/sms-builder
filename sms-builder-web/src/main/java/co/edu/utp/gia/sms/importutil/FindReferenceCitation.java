package co.edu.utp.gia.sms.importutil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLEncoder;

import org.apache.poi.util.IOUtils;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpEncoding;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.StreamingContent;

import co.edu.utp.gia.sms.entidades.Referencia;

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
	 * Metodo que permite obtener el numero de citas de una {@link Referencia}
	 * 
	 * @param referencia
	 * @return
	 * @throws IOException
	 */
	public int findCitation(Referencia referencia) throws IOException {
		// TODO incluir doi en la consulta
		HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
		HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(
				String.format("https://scholar.google.com/scholar?hl=es&q=%s", URLEncoder.encode(referencia.getNombre(), "UTF-8"))
		));
		String rawResponse = request.execute().parseAsString();
		int i = rawResponse.indexOf("Citado por ");
		if (i >= 0) {
			String aux = rawResponse.substring(i + 11, i + 20);
			referencia.setCitas(Integer.parseInt(aux.substring(0, aux.indexOf('<'))));
			return referencia.getCitas();
		}
		return 0;
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
	 * @param texto
	 * @return
	 * @throws IOException
	 */
	public String findTranslate(String texto) throws IOException {
		return texto;

	}

}
