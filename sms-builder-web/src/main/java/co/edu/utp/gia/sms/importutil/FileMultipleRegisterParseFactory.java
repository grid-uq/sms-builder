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
//		case ACM:
//			return new ACMFileMultipleRegisterParse();
//		case IEEE:
//			return new IEEEFileMultipleRegisterParse();
//		case SCIENCEDIRECT:
//			return new SDFileMultipleRegisterParse();
//		case SCOPUS:
//			return new ScopusFileMultipleRegisterParse();
//		case SPRINGER:
//			return new SpringerFileMultipleRegisterParse();
//		case WOS:
//			return new WOSFileMultipleRegisterParse();
		case ACM:
			return new MendeleyRisFileMultipleRegisterParse(Fuente.ACM);
		case IEEE:
			return new MendeleyRisFileMultipleRegisterParse(Fuente.IEEE);
		case SCIENCEDIRECT:
			return new MendeleyRisFileMultipleRegisterParse(Fuente.SCIENCEDIRECT);
		case SCOPUS:
			return new MendeleyRisFileMultipleRegisterParse(Fuente.SCOPUS);
		case SPRINGER:
			return new MendeleyRisFileMultipleRegisterParse(Fuente.SPRINGER);
		case WOS:
			return new MendeleyRisFileMultipleRegisterParse(Fuente.WOS);
		
//		case ACM_MENDELEY:
//			return new MendeleyRisFileMultipleRegisterParse(Fuente.ACM);
//		case IEEE_MENDELEY:
//			return new MendeleyRisFileMultipleRegisterParse(Fuente.IEEE);
//		case SCIENCEDIRECT_MENDELEY:
//			return new MendeleyRisFileMultipleRegisterParse(Fuente.SCIENCEDIRECT);
//		case SCOPUS_MENDELEY:
//			return new MendeleyRisFileMultipleRegisterParse(Fuente.SCOPUS);
//		case SPRINGER_MENDELEY:
//			return new MendeleyRisFileMultipleRegisterParse(Fuente.SPRINGER);
//		case WOS_MENDELEY:
//			return new MendeleyRisFileMultipleRegisterParse(Fuente.WOS);
//		case MANUAL:
		case INCLUSION_DIRECTA:
			return new MendeleyRisFileMultipleRegisterParse(Fuente.INCLUSION_DIRECTA);
		case SNOWBALL_BACKWARD:
			return new MendeleyRisFileMultipleRegisterParse(Fuente.SNOWBALL_BACKWARD);
		case SNOWBALL_FORWARD:
			return new MendeleyRisFileMultipleRegisterParse(Fuente.SNOWBALL_FORWARD);
		default:
			return null;
		}
	}
}
