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
		case ACM:
			return new ACMFileMultipleRegisterParse();
		case IEEE:
			return new IEEEFileMultipleRegisterParse();
		case SCIENCEDIRECT:
			return new SDFileMultipleRegisterParse();
		case SCOPUS:
			return new ScopusFileMultipleRegisterParse();
		case SPRINGER:
			return new SpringerFileMultipleRegisterParse();
		case WOS:
			return new WOSFileMultipleRegisterParse();
		case ACM_MENDELEY:
			return new MendeleyRisFileMultipleRegisterParse(Fuente.ACM);
		case IEEE_MENDELEY:
			return new MendeleyRisFileMultipleRegisterParse(Fuente.IEEE);
		case SCIENCEDIRECT_MENDELEY:
			return new MendeleyRisFileMultipleRegisterParse(Fuente.SCIENCEDIRECT);
		case SCOPUS_MENDELEY:
			return new MendeleyRisFileMultipleRegisterParse(Fuente.SCOPUS);
		case SPRINGER_MENDELEY:
			return new MendeleyRisFileMultipleRegisterParse(Fuente.SPRINGER);
		case WOS_MENDELEY:
			return new MendeleyRisFileMultipleRegisterParse(Fuente.WOS);
		default:
			return null;
		}
	}
}
