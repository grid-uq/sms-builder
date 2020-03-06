package co.edu.utp.gia.sms.importutil.acm;

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

	public ACMReferenceParcer() {
		super(Fuente.ACM);
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
		String autors[] = value.split(" and ");
		for (String autor : autors) {
			reference.addElement(TipoMetadato.AUTOR, autor);
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
