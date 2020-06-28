package co.edu.utp.gia.sms.importutil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;

import co.edu.utp.gia.sms.entidades.Referencia;

public class FindReferenceCitation {
	private int numeroCitation;
	private Referencia referencia;

	public FindReferenceCitation(Referencia referencia) {
		this.referencia = referencia;
		numeroCitation = 0;
	}

	public void findCitation() throws IOException {
		URL url = new URL("https://scholar.google.com/scholar?hl=es&q=" + URLEncoder.encode(referencia.getNombre(), "UTF-8"));
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setInstanceFollowRedirects(true);
		
		String contentType = con.getHeaderField("Content-Type");
		System.out.println(contentType);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
			if( inputLine.indexOf("Citado por ") >= 0 ) {
				int i = inputLine.indexOf("Citado por ");
				System.out.println( inputLine.substring(i, 20) );
			}
		}
		in.close();
		con.disconnect();
		
	}
	
//	public void findCitation2() {
//		HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("http://webcode.me"))
//                .GET() // GET is default
//                .build();
//
//        HttpResponse<Void> response = client.send(request,
//                HttpResponse.BodyHandlers.discarding());
//
//        System.out.println(response.statusCode());
//	}

	public void findCitation3() throws UnsupportedEncodingException, IOException {
		HttpRequestFactory requestFactory
		  = new NetHttpTransport().createRequestFactory();
		HttpRequest request = requestFactory.buildGetRequest(
		  new GenericUrl(
				  "https://scholar.google.com/scholar?hl=es&q=" + URLEncoder.encode(referencia.getNombre(), "UTF-8")
				  
				  ));
		String rawResponse = request.execute().parseAsString();
		int i = rawResponse.indexOf("Citado por ");
		if( i >= 0 ) {
			String aux = rawResponse.substring(i+11,i+20);
			
			System.out.println( aux.substring(0, aux.indexOf("<")) );
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		Referencia r = new Referencia();
		r.setNombre("An Open-Source Benchmark Suite for Microservices and Their Hardware-Software Implications for Cloud & Edge Systems");
		FindReferenceCitation c = new FindReferenceCitation(r);
		c.findCitation3();
	}
	
	
	private static class ParameterStringBuilder {
	    public static String getParamsString(Map<String, String> params) 
	      throws UnsupportedEncodingException{
	        StringBuilder result = new StringBuilder();
	 
	        for (Map.Entry<String, String> entry : params.entrySet()) {
	          result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
	          result.append("=");
	          result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
	          result.append("&");
	        }
	 
	        String resultString = result.toString();
	        return resultString.length() > 0
	          ? resultString.substring(0, resultString.length() - 1)
	          : resultString;
	    }
	}
}
