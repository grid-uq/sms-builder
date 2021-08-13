package co.edu.utp.gia.sms.importutil.bibtex;

import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.importutil.ReferenceParser;
import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;
import org.jbibtex.Value;

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
public class BibtexReferenceParcer extends ReferenceParser<BibTeXEntry> {

	private static final String TITULO = "T1";
	private static final String KEYWORD = "KW";
	private static final String YEAR = "Y1";
	private static final String ABSTRACT = "abstract";
	private static final String AUTOR = "A1";
	private static final String DOI = "DO";
	// private static final String ISBN = "SN";
	private static final String NOMBRE_PUBLICACION = "JF";
	private static final String TIPO_PUBLICACION = "TY";

	public BibtexReferenceParcer(String fuente, String tipoFuente) {
		super(fuente,tipoFuente);
	}

	protected void procesar(Referencia reference, BibTeXEntry source) {
		source.getFields().entrySet( ).forEach( field->this.procesarField(field.getKey(), field.getValue(), reference) );
		if( reference.getMetadatos().stream().noneMatch( m->TipoMetadato.TITLE.equals(m.getIdentifier()) ) ){
			reference.addElement(TipoMetadato.TITLE,source.getType().getValue());
		}
	}

	protected void procesarField( Key key,Value value,Referencia reference){
		TipoMetadato tipo = identifierOf(key);
		if( TipoMetadato.AUTOR.equals(tipo) ){
			adicionarAutores(reference,value.toUserString());
		} else {
			reference.addElement(tipo, value.toUserString());
		}
	}

	private void adicionarAutores(Referencia reference, String autores) {
		for (String autor:autores.split(" and ")) {
			reference.addElement(TipoMetadato.AUTOR, autor);
		}
	}

	private TipoMetadato identifierOf(Key key) {
		if( BibTeXEntry.KEY_TITLE.equals(key) ){
			return TipoMetadato.TITLE;
		}
		if( BibTeXEntry.KEY_AUTHOR.equals(key) ){
			return TipoMetadato.AUTOR;
		}
		if( BibTeXEntry.KEY_JOURNAL.equals(key) ){
			return TipoMetadato.PUBLISHER;
		}
		if( BibTeXEntry.KEY_YEAR.equals(key) ){
			return TipoMetadato.YEAR;
		}
		if( BibTeXEntry.KEY_DOI.equals(key) ){
			return TipoMetadato.DOI;
		}
		if( BibTeXEntry.KEY_KEY.equals(key) ){
			return TipoMetadato.KEYWORD;
		}
		if( BibTeXEntry.KEY_TYPE.equals(key) ){
			return TipoMetadato.TYPE;
		}
		if( key.getValue().equals(ABSTRACT)  ) {
			return TipoMetadato.ABSTRACT;
		}

//		case TIPO_PUBLICACION:
//			return TipoMetadato.TYPE;
			return TipoMetadato.NOT_SUPORT;
	}

}
