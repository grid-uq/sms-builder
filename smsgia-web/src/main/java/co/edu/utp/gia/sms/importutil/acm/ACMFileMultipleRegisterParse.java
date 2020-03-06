package co.edu.utp.gia.sms.importutil.acm;

import co.edu.utp.gia.sms.importutil.FileMultipleRegisterParse;


public class ACMFileMultipleRegisterParse
		extends FileMultipleRegisterParse {

	public ACMFileMultipleRegisterParse() {
		super(new ACMReferenceParcer());
	}
	
}
