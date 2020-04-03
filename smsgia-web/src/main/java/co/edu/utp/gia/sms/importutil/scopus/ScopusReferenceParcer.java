package co.edu.utp.gia.sms.importutil.scopus;

import java.io.StringReader;
import java.util.Scanner;

import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.importutil.Fuente;
import co.edu.utp.gia.sms.importutil.ReferenceParser;

/**
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

/* Esta bases de datos se está tratando para archivos exportados en formato .RIS
 * */
public class ScopusReferenceParcer extends ReferenceParser {
	private static final String TITULO = "TI"; 				
	private static final String KEYWORD = "KW";				
	private static final String YEAR = "PY";				
	private static final String ABSTRACT = "AB";			
	private static final String AUTOR = "AU";				
	private static final String DOI = "DO";					
	private static final String ISBN = "SN";				
	private static final String NOMBRE_PUBLICACION = "PP"; // PB DB		
	private static final String TIPO_PUBLICACION = "TY";

	public ScopusReferenceParcer() {
		super(Fuente.SCOPUS);
	}

	protected void procesarTexto(Referencia reference, String texto) {

		try (Scanner lector = new Scanner(new StringReader(texto))) {
			while (lector.hasNextLine()) {
				String linea = lector.nextLine();
				procesarLinea(reference, linea);
			}
		}
	}

	/**
	 * Procesa una linea del formato .RIS exportado desde la BD SCOPUS
	 * 
	 * @param reference Referencia que se esta procesando
	 * @param nextLine  Linea a ser procesada
	 */
	private void procesarLinea(Referencia reference, String nextLine) {
		if (nextLine != null && !nextLine.isEmpty()) {
			String key = nextLine.substring(0, 2).toUpperCase();
			String value = nextLine.substring(4).trim().toUpperCase();
			System.out.println(value);
			reference.addElement(identifierOf(key), value);
		}
	}
	
	
	private TipoMetadato identifierOf(String key) {
		switch (key) {
		case TITULO:
			return TipoMetadato.TITLE;
		case AUTOR:
			return TipoMetadato.AUTOR;
		case NOMBRE_PUBLICACION:
			return TipoMetadato.PUBLISHER;
		case YEAR:
			return TipoMetadato.YEAR;
		case ABSTRACT:
			return TipoMetadato.ABSTRACT;
		case ISBN:
			return TipoMetadato.ISBN;
		case DOI:
			return TipoMetadato.DOI;
		case KEYWORD:
			return TipoMetadato.KEYWORD;
		case TIPO_PUBLICACION:
			return TipoMetadato.TYPE;
		default:
			return TipoMetadato.NOT_SUPORT;
		}
	}

}
