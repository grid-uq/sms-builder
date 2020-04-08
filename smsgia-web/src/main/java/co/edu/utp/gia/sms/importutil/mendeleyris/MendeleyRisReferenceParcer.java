package co.edu.utp.gia.sms.importutil.mendeleyris;

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

/*
 * Esta bases de datos se está tratando para archivos exportados en formato .RIS
 */
public class MendeleyRisReferenceParcer extends ReferenceParser {

	private static final String TITULO = "T1";
	private static final String KEYWORD = "KW";
	private static final String YEAR = "Y1";
	private static final String ABSTRACT = "N2";
	private static final String AUTOR = "A1";
	private static final String DOI = "DO";
	// private static final String ISBN = "SN";
	private static final String NOMBRE_PUBLICACION = "JF";
	private static final String TIPO_PUBLICACION = "TY";

	public MendeleyRisReferenceParcer(Fuente fuente) {
		super(fuente);

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
//		case ISBN:
//			return TipoMetadato.ISBN;
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
