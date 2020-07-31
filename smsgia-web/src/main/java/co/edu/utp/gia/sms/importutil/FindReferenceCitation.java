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
				"https://scholar.google.com/scholar?hl=es&q=" + URLEncoder.encode(referencia.getNombre(), "UTF-8")

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
	 * @param referencia
	 * @return
	 * @throws IOException
	 */
	public String findTranslate(String texto) throws IOException {
		HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
		String url = "https://microsoft-translator-text.p.rapidapi.com/translate?profanityAction=NoAction&textType=plain&to=es&api-version=3.0";
		HttpRequest postRequest = requestFactory.buildPostRequest(new GenericUrl(url),
				ByteArrayContent.fromString("application/json", "[ {  \"Text\": \"" + texto + "\" }]"));
//		postRequest.set
		JacksonFactory jsonFactory = new JacksonFactory();
		postRequest.setParser(new JsonObjectParser(jsonFactory));
		
		postRequest.getHeaders().put("x-rapidapi-host", "microsoft-translator-text.p.rapidapi.com");

		postRequest.getHeaders().put("x-rapidapi-key", "4df3d75ac5msh32f12134161df56p19e4d4jsn387ae08ca2f9");
		postRequest.getHeaders().setAccept("application/json");
//		postRequest.getHeaders().setAcceptEncoding("gzip;q=0,deflate,sdch");
		postRequest.getHeaders().setAcceptEncoding("gzip;q=0");
//		Accept-Encoding: gzip;q=0,deflate,sdch
//		postRequest.getHeaders().setContentType("gzip;q=0,deflate,sdch");

		try {
			HttpResponse response = postRequest.execute();
			
			InputStreamReader reader = new InputStreamReader(response.getContent());
			BufferedReader in = new BufferedReader(reader);

			String readed ;
			String respuesta = "";
			while ((readed = in.readLine()) != null) {
				respuesta = respuesta + readed;
			}
//			String respuesta =  new String(  IOUtils.toByteArray(response.getContent()) );
			System.out.println(respuesta);
			return new JSONArray(respuesta).getJSONObject(0).getJSONArray("translations").getJSONObject(0).getString("text");
//			return ((JSONObject) ((JSONArray) ((JSONObject) new JSONArray(postRequest.execute().parseAsString()).get(0))
//					.get("translations")).get(0)).get("text").toString();
		} catch (Exception e) {
			
			e.printStackTrace();
			return "";
		}

	}

}
