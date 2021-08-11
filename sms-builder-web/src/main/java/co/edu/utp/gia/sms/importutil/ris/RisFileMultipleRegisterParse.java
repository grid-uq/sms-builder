package co.edu.utp.gia.sms.importutil.ris;

import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.importutil.FileMultipleRegisterParse;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RisFileMultipleRegisterParse extends FileMultipleRegisterParse {

	public RisFileMultipleRegisterParse(String fuente,String tipoFuente) {
		super(new RisReferenceParcer(fuente,tipoFuente));
	}

	@Override
	public List<Referencia> parse(InputStream input) {
		List<Referencia> references = new ArrayList<>();

		try (Scanner lector = new Scanner(input)) {

			while (lector.hasNextLine()) {
				String stringRerence = readReference(lector);
				if (!"".equals(stringRerence)) {
					Referencia reference = getReferenceParser().parse(stringRerence);
					if (reference != null) {
						references.add(reference);
					}
				}
			}
		}

		return references;
	}

	private String readReference(Scanner lector) {
		StringBuilder build = new StringBuilder();
		String linea = null;
		while (lector.hasNextLine() && !"ER  -".equals(linea)) {
			linea = lector.nextLine().trim();
			if (!"ER  -".equals(linea)) {
				build.append(linea).append("\n");
			}
		}
		return build.toString();
	}
}
