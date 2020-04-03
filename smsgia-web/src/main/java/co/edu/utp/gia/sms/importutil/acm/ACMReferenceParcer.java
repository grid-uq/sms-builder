package co.edu.utp.gia.sms.importutil.acm;

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
public class ACMReferenceParcer extends ReferenceParser {

	private static final String TITULO = "T"; 				
	private static final String KEYWORD = "K";				
	private static final String YEAR = "D";				
	//private static final String ABSTRACT = "";			
	private static final String AUTOR = "A";				
	private static final String DOI = "R";					
	private static final String ISBN = "@";				
	private static final String NOMBRE_PUBLICACION = "J";
	private static final String TIPO_PUBLICACION = "0";		
	
	
	public ACMReferenceParcer() {
		super(Fuente.ACM);
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
			return TipoMetadato.AUTOR;
		case NOMBRE_PUBLICACION:
			return TipoMetadato.PUBLISHER;
		case YEAR:
			return TipoMetadato.YEAR;
//		case ABSTRACT:
//			return TipoMetadato.ABSTRACT;
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
	 * Procesa una revisión de ACM en formato tipo EndNote (formato vertical)
	 * 
	 * @param reference Referencia que se esta procesando
	 * @param nextLine  Linea a ser procesada
	 */
	private void procesarLinea(Referencia reference, String nextLine) {
		
		
		
		if (nextLine != null && !nextLine.isEmpty()) {
			TipoMetadato key = identifierOf(nextLine.substring(1, 2).toUpperCase()) ;
			String value = nextLine.substring(2).trim().toUpperCase();
			
			if (TipoMetadato.KEYWORD.equals(key)) {
				addKeywords(reference, value);
			} else {
				reference.addElement(key, value);
			}			
		}
	}

	private void addKeywords(Referencia reference, String value) {
		String autors[] = value.split(", ");
		for (String autor : autors) {
			reference.addElement(TipoMetadato.KEYWORD, autor);
		}
	}

//	"type","id","author","editor","advisor","note","title","pages","article_no","num_pages","keywords","doi","journal","issue_date","volume","issue_no","description","month","year","issn","booktitle","acronym","edition","isbn","conf_loc","publisher","publisher_loc"
//	"article","2956643","Tom  Killalea","","","","The Hidden Dividends of Microservices","10:25--10:34","10","10","","10.1145/2956641.2956643","Queue","May-June 2016","14","3","","May","2016","1542-7730","","","","","","ACM","New York, NY, USA"

	private TipoMetadato identifierOf(int index) {
		switch (index) {
		case 6:
		case 20:
			return TipoMetadato.TITLE;
		case 10:
			return TipoMetadato.KEYWORD;
		case 18:
			return TipoMetadato.YEAR;
		case 16:
			return TipoMetadato.ABSTRACT;
		case 2:
			return TipoMetadato.AUTOR;
		case 11:
			return TipoMetadato.DOI;
		case 19:
		case 23:
			return TipoMetadato.ISBN;
		case 25:
			return TipoMetadato.PUBLISHER;
		case 0:
			return TipoMetadato.TYPE;
		default:
			return TipoMetadato.NOT_SUPORT;
		}

	}

}
