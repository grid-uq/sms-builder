package co.edu.utp.gia.sms.importutil.ieee;

import co.edu.utp.gia.sms.importutil.FileMultipleRegisterParse;


public class IEEEFileMultipleRegisterParse
		extends FileMultipleRegisterParse {

	public IEEEFileMultipleRegisterParse() {
		super(new IEEEReferenceParcer());
	}
	
}
