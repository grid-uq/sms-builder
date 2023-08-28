package co.edu.utp.gia.sms.entidades;

import co.edu.utp.gia.sms.util.ApplicationGeneralProducer;

import java.util.Locale;
import java.util.ResourceBundle;
/**
 * Define los tipos de fuente
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
public enum TipoFuente {
	BASE_DATOS,BOLA_NIEVE,INCLUSION_DIRECTA;

	@Override
	public String toString() {
		ApplicationGeneralProducer instancia = new ApplicationGeneralProducer();
		Locale locale = instancia.getDefaultLocale();
		ResourceBundle bundle = ResourceBundle.getBundle("mensajes",locale);
        return switch (this) {
            case BASE_DATOS -> bundle.getString("etiquetaBaseDatos");
            case BOLA_NIEVE -> bundle.getString("etiquetaBolaNieve");
            default -> bundle.getString("etiquetaInclusionDirecta");
        };

	}
}
