package co.edu.utp.gia.sms.importutil.springer;

import co.edu.utp.gia.sms.importutil.FileMultipleRegisterParse;


public class SpringerFileMultipleRegisterParse
		extends FileMultipleRegisterParse {

	public SpringerFileMultipleRegisterParse() {
		super(new SpringerReferenceParcer());
	}
	
}
