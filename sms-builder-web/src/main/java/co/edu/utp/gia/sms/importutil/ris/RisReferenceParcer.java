package co.edu.utp.gia.sms.importutil.ris;

import co.edu.utp.gia.sms.entidades.Fuente;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.importutil.ReferenceParser;

import java.io.StringReader;
import java.util.Scanner;

/**
 * Clase que interpreta elementos en formato .RIS
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
public class RisReferenceParcer extends ReferenceParser<String> {

	private static final String TITULO = "TI";
	private static final String TITULO_2 = "T1";
	private static final String KEYWORD = "KW";
	private static final String YEAR = "PY";
	private static final String YEAR_1 = "Y1";
	private static final String ABSTRACT = "AB";
	private static final String ABSTRACT_1 = "N2";
	private static final String AUTOR = "AU";
	private static final String AUTOR_2 = "A1";
	private static final String DOI = "DO";
	// private static final String ISBN = "SN";
	private static final String NOMBRE_PUBLICACION = "T2";
	private static final String NOMBRE_PUBLICACION_1 = "JF";
	private static final String TIPO_PUBLICACION = "TY";

	public RisReferenceParcer(Fuente fuente) {
		super(fuente);
	}

	protected Referencia procesar(String texto) {
		Referencia reference = new Referencia();
		try (Scanner lector = new Scanner(new StringReader(texto))) {
			while (lector.hasNextLine()) {
				String linea = lector.nextLine();
				procesarLinea(reference, linea);
			}
		}
		return reference;
	}

	/**
	 * Procesa una linea del formato .RIS exportado desde la BD SCOPUS
	 * 
	 * @param reference Referencia que se esta procesando
	 * @param nextLine  Linea a ser procesada
	 */
	private void procesarLinea(Referencia reference, String nextLine) {
		if (nextLine != null && !nextLine.isEmpty()) {
			if (nextLine.indexOf(" - ") != 3) {
				return;
			}
			String key = nextLine.substring(0, 2).toUpperCase();
			if (nextLine.length() < 6) {
				System.out.println(nextLine);
			}
			String value = nextLine.substring(5).trim();

			TipoMetadato tipo = identifierOf(key);

			if (TipoMetadato.YEAR.equals(tipo)) {
				value = value.substring(0,4).trim();
			}
			reference.addElement(tipo, value);
		}
	}

	private TipoMetadato identifierOf(String key) {
        return switch (key) {
			case TITULO, TITULO_2 -> TipoMetadato.TITLE;
			case AUTOR,AUTOR_2 ->  TipoMetadato.AUTOR;
			case NOMBRE_PUBLICACION, NOMBRE_PUBLICACION_1 -> TipoMetadato.PUBLISHER;
			case YEAR,YEAR_1 -> TipoMetadato.YEAR;
			case ABSTRACT, ABSTRACT_1 -> TipoMetadato.ABSTRACT;
//		case ISBN:
//			return TipoMetadato.ISBN;
            case DOI -> TipoMetadato.DOI;
            case KEYWORD -> TipoMetadato.KEYWORD;
            case TIPO_PUBLICACION -> TipoMetadato.TYPE;
            default -> TipoMetadato.NOT_SUPORT;
        };
	}

}
