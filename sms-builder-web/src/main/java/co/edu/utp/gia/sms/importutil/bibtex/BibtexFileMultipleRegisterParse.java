package co.edu.utp.gia.sms.importutil.bibtex;

import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.importutil.FileMultipleRegisterParse;
import org.jbibtex.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BibtexFileMultipleRegisterParse extends FileMultipleRegisterParse<BibtexReferenceParcer> {

	public BibtexFileMultipleRegisterParse(String fuente, String tipoFuente) {
		super(new BibtexReferenceParcer(fuente,tipoFuente));
	}

	@Override
	public List<Referencia> parse(InputStream input) {
		List<Referencia> references ;

		try (InputStreamReader reader = new InputStreamReader(input)) {
			BibTeXParser bibtexParser = new BibTeXParser();
			BibTeXDatabase database = bibtexParser.parse(reader);
			Map<Key, BibTeXEntry> entryMap = database.getEntries();

			references = entryMap.values().stream().map( getReferenceParser()::parse ).collect(Collectors.toList());


		}catch (ParseException | IOException e) {
			e.printStackTrace();
			references = Collections.emptyList();
		}

		return references;
	}

}
