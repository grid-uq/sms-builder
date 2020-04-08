package co.edu.utp.gia.sms.importutil.wos;

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

// Formato horizontal clave valor WoS

public class WOSReferenceParcer extends ReferenceParser {
	private static final String TITULO = "TI";
	private static final String KEYWORD = "DE";
	private static final String YEAR = "PY";
	private static final String YEAR2 = "EA";
	private static final String ABSTRACT = "AB";
	private static final String AUTOR = "AF";
	private static final String DOI = "DI";
	private static final String ISBN = "SN";
	private static final String NOMBRE_PUBLICACION = "PU";
	private static final String TIPO_PUBLICACION = "DT";

	public WOSReferenceParcer() {
		super(Fuente.WOS);
	}

	protected void procesarTexto(Referencia reference, String texto) {

		try (Scanner lector = new Scanner(new StringReader(texto))) {
			String key = null;
			while (lector.hasNextLine()) {
				String linea = lector.nextLine();
				// Cuando la línea no inicia con una lleve, se concatena con la llave anterior
				if ("  ".equals(linea.substring(0, 2))) {
					linea = key + linea.substring(2);
				} else {
					key = linea.substring(0, 2);
				}

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
		case YEAR2:
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
	 * Procesa una linea del formato WOS
	 * 
	 * @param reference Referencia que se esta procesando
	 * @param nextLine  Linea a ser procesada
	 */
	private void procesarLinea(Referencia reference, String nextLine) {
		if (nextLine != null && !nextLine.isEmpty()) {
			String key = nextLine.substring(0, 2).toUpperCase();
			String value = nextLine.substring(2).trim().toUpperCase();

			TipoMetadato tipo = identifierOf(key);

			if (TipoMetadato.KEYWORD.equals(tipo)) {
				addKeywords(reference, value);
			} else {
				if(TipoMetadato.YEAR.equals(tipo)) {
					value = value.length() > 4 ? value.substring(4).trim() : value;
				}
				reference.addElement(tipo, value);
			}
		}
	}

	private void addKeywords(Referencia reference, String value) {
		String keywords[] = value.split("; ");
		for (String keyword : keywords) {
			reference.addElement(TipoMetadato.KEYWORD, keyword);
		}
	}

}
