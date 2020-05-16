package co.edu.utp.gia.sms.importutil.acm;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.importutil.FileMultipleRegisterParse;


public class ACMFileMultipleRegisterParse
		extends FileMultipleRegisterParse {

	public ACMFileMultipleRegisterParse() {
		super(new ACMReferenceParcer());
	}
	

	@Override
	public List<Referencia> parse(InputStream input) {
		List<Referencia> references = new ArrayList<>();

		try (Scanner lector = new Scanner(input)) {

			while (lector.hasNextLine()) {
				String stringRerence = readReference(lector);
				if (stringRerence != null && !"".equals(stringRerence)) {
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
		while (lector.hasNextLine() && !"".equals(linea)) {
			linea = lector.nextLine().trim();
			if (!"".equals(linea)) {
				build.append(linea + "\n");
			}
		}
		return build.toString();
	}
	
	
	
	
	
	
}
