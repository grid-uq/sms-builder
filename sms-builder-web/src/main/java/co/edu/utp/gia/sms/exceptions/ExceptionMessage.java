package co.edu.utp.gia.sms.exceptions;

import org.aeonbits.owner.Config;
/**
 * Clase utilitaria que representa un mensaje de excepcion.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
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
    String getReferenciaSinFecha(String id,String spsid);
    @Key("message.exception.referencia.errorEvaluacion")
    String getReferenciaErrorEvaluacion(String id,String spsid);
    @Key("message.error.conversion")
    String getConversionError();
}
