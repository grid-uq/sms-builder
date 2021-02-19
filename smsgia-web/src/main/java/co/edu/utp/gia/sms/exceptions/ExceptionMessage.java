package co.edu.utp.gia.sms.exceptions;

import org.aeonbits.owner.Config;

public interface ExceptionMessage extends Config {
    @Key("message.exception.defaultDetail")
    String getDefaultMessageDetail();
    @Key("message.exception.defaultSummary")
    String getDefaultMessageSumary();
    @Key("message.exception.loginFail")
    String getLoginFailMessage();
}
