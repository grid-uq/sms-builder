package co.edu.utp.gia.sms.importutil.sd;

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
public class SDReferenceParcer extends ReferenceParser {
	private static final String TITULO = "TI"; 				
	private static final String KEYWORD = "KW";				
	private static final String YEAR = "PY";				
	private static final String ABSTRACT = "AB";			
	private static final String AUTOR = "A1";				
	private static final String AUTOR2 = "A2";				
	private static final String AUTOR3 = "A3";				
	private static final String AUTOR4 = "A4";				
	private static final String DOI = "DO";					
	private static final String ISBN = "SN";				
	private static final String NOMBRE_PUBLICACION = "PP";	//revisar
	private static final String TIPO_PUBLICACION = "TY";	

	public SDReferenceParcer() {
		super(Fuente.SD);
	}

	protected void procesarTexto(Referencia reference, String texto) {

		try (Scanner lector = new Scanner(new StringReader(texto))) {
			while (lector.hasNextLine()) {
				String linea = lector.nextLine();
				procesarLinea(reference, linea);
			}
		}
	}

	private TipoMetadato identifierOf(String key) {
		switch (key) {
		case TITULO:
			return TipoMetadato.TITLE;
		case AUTOR:
		case AUTOR2:
		case AUTOR3:
		case AUTOR4:
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

	/**
	 * Procesa una linea del formato SD
	 * 
	 * @param reference Referencia que se esta procesando
	 * @param nextLine  Linea a ser procesada
	 */
	private void procesarLinea(Referencia reference, String nextLine) {
		if (nextLine != null && !nextLine.isEmpty()) {
			String key = nextLine.substring(0, 2).toUpperCase();
			String value = nextLine.substring(5).trim().toUpperCase();
			reference.addElement(identifierOf(key), value);
		}
	}

}
