package co.edu.utp.gia.sms.importutil.bibtex;

import co.edu.utp.gia.sms.entidades.Fuente;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.importutil.ReferenceParser;
import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;
import org.jbibtex.Value;

import java.util.List;

/**
 * Clase utilitaria encargada de procesar referencias de tipo bibtex
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
public class BibtexReferenceParcer extends ReferenceParser<BibTeXEntry> {

	private static final String ABSTRACT = "abstract";

	public BibtexReferenceParcer(Fuente fuente) {
		super(fuente);
	}

	/**
	 * Permite procesar una referencia en formato Bibtex y al macena su información en la {@link Referencia}
	 * @param source Elemento base para obtener los datos de la referencia
	 */
	protected Referencia procesar(BibTeXEntry source) {
		Referencia reference = new Referencia();
		source.getFields().forEach((key, value) -> this.procesarField(key, value, reference));
		if( reference.getMetadatos().stream().noneMatch( m->TipoMetadato.TITLE.equals(m.getIdentifier()) ) ){
			reference.addElement(TipoMetadato.TITLE,BibtexStringUtil.parse(source.getType().getValue()));
		}
		if( reference.getMetadatos().stream().noneMatch( m->TipoMetadato.TYPE.equals(m.getIdentifier()) ) ){
			reference.addElement(TipoMetadato.TYPE,BibtexStringUtil.parse(source.getType().getValue()));
		}
		return reference;
	}

	/**
	 * Permite procesar un campo del archivo de referencias
	 * @param key	Llave que identifica el tipo de campo
	 * @param value	Valor del campo
	 * @param reference Referencia en la que se va a almacenar el valor del campo
	 */
	protected void procesarField( Key key,Value value,Referencia reference){
		TipoMetadato tipo = identifierOf(key);
		if( TipoMetadato.AUTOR.equals(tipo) ){
			adicionarAutores(reference,BibtexStringUtil.parse(value.toUserString()));
		} else {
			reference.addElement(tipo, BibtexStringUtil.parse(value.toUserString()));
		}
	}

	/**
	 * Permite adicionar los autores a una referencia a parit de un texto dado separados por " and "
	 * @param reference Referencia a la que se le desean adicionar los autores
	 * @param autores Texto que contiene los autores a ser adicionados
	 */
	private void adicionarAutores(Referencia reference, String autores) {
		for (String autor:autores.split(" and ")) {
			reference.addElement(TipoMetadato.AUTOR, BibtexStringUtil.parse(autor));
		}
	}

	/**
	 * Permite establecer una equivalencia entre una llave dada y un tipo de metadato a almacenar
	 * @param key Llave a la que se desea obtener la equivalencia
	 * @return El tipo de metadato correspondiente a la llave dada.
	 */
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
		if( isType(key)  ){
			return TipoMetadato.TYPE;
		}
		if( key.getValue().equals(ABSTRACT)  ) {
			return TipoMetadato.ABSTRACT;
		}
		return TipoMetadato.NOT_SUPORT;
	}

	private boolean isType(Key key){
		var list = List.of( BibTeXEntry.KEY_TYPE,BibTeXEntry.TYPE_ARTICLE,BibTeXEntry.TYPE_CONFERENCE,
				BibTeXEntry.TYPE_BOOK,BibTeXEntry.TYPE_BOOKLET,BibTeXEntry.TYPE_INBOOK,BibTeXEntry.TYPE_INCOLLECTION,
				BibTeXEntry.TYPE_INPROCEEDINGS,BibTeXEntry.TYPE_MANUAL,BibTeXEntry.TYPE_MASTERSTHESIS,BibTeXEntry.TYPE_MISC,
				BibTeXEntry.TYPE_PHDTHESIS,BibTeXEntry.TYPE_PROCEEDINGS,BibTeXEntry.TYPE_TECHREPORT,BibTeXEntry.TYPE_UNPUBLISHED);
		return list.stream().anyMatch( key::equals );
	}
}

