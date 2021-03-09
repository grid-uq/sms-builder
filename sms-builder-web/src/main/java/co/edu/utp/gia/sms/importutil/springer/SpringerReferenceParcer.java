package co.edu.utp.gia.sms.importutil.springer;

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

// El archivo de la bases de datos SPRINGER está presentado con formato horizontlal y separados por comas.

public class SpringerReferenceParcer extends ReferenceParser {

	public SpringerReferenceParcer() {
		super(Fuente.SPRINGER);
	}

	protected void procesarTexto(Referencia reference, String texto) {

		String elementos[] = texto.split("\",\"");

		if (elementos != null && elementos.length >= 9) { //revisar porque 25

			for (int i = 0; i < elementos.length; i++) {
				if (elementos[i] != null && !elementos[i].isEmpty()) {
					
					String value = elementos[i].indexOf(",") == 0 ? elementos[i].substring(1) : elementos[i];
					if (!"".equals(value)) {
						TipoMetadato identifier = identifierOf(i);
						if (TipoMetadato.AUTOR.equals(identifier)) {
							addAutors(reference, value);
						} else if (TipoMetadato.KEYWORD.equals(identifier)) {
							addKeywords(reference, value);
						} else {
							reference.addElement(identifier, value);
						}
					}
				}
			}
		}

	}

	private void addAutors(Referencia reference, String value) {
		String autors[] = value.split("; ");
		for (String autor : autors) {
			reference.addElement(TipoMetadato.AUTOR, autor);
		}
	}

	private void addKeywords(Referencia reference, String value) {
		String keywords[] = value.split(";");
		for (String keyword : keywords) {
			reference.addElement(TipoMetadato.KEYWORD, keyword);
		}
	}


/*	

Para Springer 

0 	Item Title,
1 	Publication Title,
2	Book Series Title,
3	Journal Volume,
4	Journal Issue,
5	Item DOI,
6	Authors,
7	Publication Year,
8	URL,
9	Content Type

*/
	
	private TipoMetadato identifierOf(int index) {
		switch (index) {
		case 0:
			return TipoMetadato.TITLE;
		case 6:
			return TipoMetadato.AUTOR;
		case 1:
		case 2:	
			return TipoMetadato.PUBLISHER;
		case 7:
			return TipoMetadato.YEAR;
//		case 10:
//			return TipoMetadato.ABSTRACT;
//		case 12:
//			return TipoMetadato.ISBN;
		case 5:	
			return TipoMetadato.DOI;
//		case 16:
//			return TipoMetadato.KEYWORD;
		case 9:
			return TipoMetadato.TYPE;
		default:
			return TipoMetadato.NOT_SUPORT;
		}

	}
	
	
	
}
