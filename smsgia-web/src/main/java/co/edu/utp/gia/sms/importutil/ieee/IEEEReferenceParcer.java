package co.edu.utp.gia.sms.importutil.ieee;

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

// El archivo de la bases de datos IEEE está presentado con formato horizontlal y separados por comas.

public class IEEEReferenceParcer extends ReferenceParser {

	public IEEEReferenceParcer() {
		super(Fuente.IEEE);
	}

	protected void procesarTexto(Referencia reference, String texto) {

		String elementos[] = texto.split("\",\"");

		
		if (elementos != null && elementos.length >= 25) {

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
		String autors[] = value.split(";");
		for (String autor : autors) {
			reference.addElement(TipoMetadato.KEYWORD, autor);
		}
	}	
/*
0 	"Document Title",
1	Authors,
2	"Author Affiliations",
3	"Publication Title",
4	Date Added To Xplore,
5	"Publication Year",
6	"Volume",
7	"Issue",
8	"Start Page",
9	"End Page",
10	"Abstract",
11	"ISSN",
12	ISBNs,
13	"DOI",
14	Funding Information,
15	PDF Link,
16	"Author Keywords",
17	"IEEE Terms",
18	"INSPEC Controlled Terms",
19	"INSPEC Non-Controlled Terms",
20	"Mesh_Terms",
21	Article Citation Count,
22	Patent Citation Count,
23	"Reference Count",
24	"License",
25	"Online Date",
26	Issue Date,
27	"Meeting Date",
28	"Publisher",
29	Document Identifier

*/	
	
	
	private TipoMetadato identifierOf(int index) {
		switch (index) {
		case 0:
			return TipoMetadato.TITLE;
		case 1:
			return TipoMetadato.AUTOR;
		case 3:
		case 28:	
			return TipoMetadato.PUBLISHER;
		case 5:
			return TipoMetadato.YEAR;
		case 10:
			return TipoMetadato.ABSTRACT;
		case 11:
		case 12:
			return TipoMetadato.ISBN;
		case 13:	
			return TipoMetadato.DOI;
		case 16:
			return TipoMetadato.KEYWORD;
		case 29:
			return TipoMetadato.TYPE;
		default:
			return TipoMetadato.NOT_SUPORT;
		}

	}

}
