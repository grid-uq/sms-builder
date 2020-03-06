package co.edu.utp.gia.sms.importutil;

import co.edu.utp.gia.sms.importutil.acm.ACMFileMultipleRegisterParse;
import co.edu.utp.gia.sms.importutil.ieee.IEEEFileMultipleRegisterParse;
import co.edu.utp.gia.sms.importutil.wos.WOSFileMultipleRegisterParse;

public class FileMultipleRegisterParseFactory {
	public static final FileMultipleRegisterParse getInstance(Fuente fuente) {
		switch (fuente) {
		case ACM:
			return new ACMFileMultipleRegisterParse();
		case IEEE:
			return new IEEEFileMultipleRegisterParse();
		case WOS:
			return new WOSFileMultipleRegisterParse();
		default:
			return null;
		}
	}
}
