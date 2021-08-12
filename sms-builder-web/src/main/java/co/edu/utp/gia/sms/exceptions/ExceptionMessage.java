package co.edu.utp.gia.sms.exceptions;

import org.aeonbits.owner.Config;

public interface ExceptionMessage extends Config {
    @Key("message.exception.defaultDetail")
    String getDefaultMessageDetail();
    @Key("message.exception.defaultSummary")
    String getDefaultMessageSumary();
    @Key("message.exception.loginFail")
    String getLoginFailMessage();
    @Key("message.exception.registroExiste")
    String getRegistroExistente();
    @Key("message.exception.registroNoExiste")
    String getRegistroNoEncontrado();
    @Key("message.exception.operacionNosoportada")
    String getOperacionNoSoportada();
    @Key("message.exception.datosIncompletos")
    String getDatosIncompletos();
    @Key("message.exception.claveNoConincide")
    String getClaveNoCoincide();
    @Key("message.exception.referencia.sinFecha")
    String getReferenciaSinFecha(int id,String spsid);
    @Key("message.exception.referencia.errorEvaluacion")
    String getReferenciaErrorEvaluacion(int id,String spsid);
    @Key("message.error.conversion")
    String getConversionError();
}
