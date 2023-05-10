package co.edu.utp.gia.sms.importutil;

import co.edu.utp.gia.sms.importutil.acm.ACMFileMultipleRegisterParse;
import co.edu.utp.gia.sms.importutil.ieee.IEEEFileMultipleRegisterParse;
import co.edu.utp.gia.sms.importutil.mendeleyris.MendeleyRisFileMultipleRegisterParse;
import co.edu.utp.gia.sms.importutil.scopus.ScopusFileMultipleRegisterParse;
import co.edu.utp.gia.sms.importutil.sd.SDFileMultipleRegisterParse;
import co.edu.utp.gia.sms.importutil.springer.SpringerFileMultipleRegisterParse;
import co.edu.utp.gia.sms.importutil.wos.WOSFileMultipleRegisterParse;

public class FileMultipleRegisterParseFactory {
	private FileMultipleRegisterParseFactory() {
	}
	
	public static final FileMultipleRegisterParse getInstance(Fuente fuente) {
		switch (fuente) {
		case INCLUSION_DIRECTA:
			return new MendeleyRisFileMultipleRegisterParse(Fuente.INCLUSION_DIRECTA);
		case SNOWBALL_BACKWARD:
			return new MendeleyRisFileMultipleRegisterParse(Fuente.SNOWBALL_BACKWARD);
		case SNOWBALL_FORWARD:
			return new MendeleyRisFileMultipleRegisterParse(Fuente.SNOWBALL_FORWARD);
		default:
			return new MendeleyRisFileMultipleRegisterParse(fuente);
		}
	}
}
