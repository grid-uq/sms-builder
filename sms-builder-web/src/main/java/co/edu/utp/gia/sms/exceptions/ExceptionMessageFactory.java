package co.edu.utp.gia.sms.exceptions;

import co.edu.utp.gia.sms.util.message.GenericMessageFactory;


public class ExceptionMessageFactory extends GenericMessageFactory<ExceptionMessage> {

    private static final ExceptionMessageFactory instance = new ExceptionMessageFactory();

    private ExceptionMessageFactory() {
        super("/exception", ExceptionMessage.class);
    }

    public static ExceptionMessageFactory getInstance(){
        return instance;
    }

}
