package co.edu.utp.gia.sms.importutil;

import co.edu.utp.gia.sms.importutil.bibtex.BibtexFileMultipleRegisterParse;
import co.edu.utp.gia.sms.importutil.ris.RisFileMultipleRegisterParse;

public class FileMultipleRegisterParseFactory {
	private FileMultipleRegisterParseFactory() {
	}

	public static final FileMultipleRegisterParse getInstance(TipoArchivo tipoArchivo, String fuente,String tipoFuente) {
		switch (tipoArchivo) {
			case RIS:
				return new RisFileMultipleRegisterParse(fuente,tipoFuente);
			case BIBTEX:
			default:
				return new BibtexFileMultipleRegisterParse(fuente,tipoFuente);
		}
	}

}