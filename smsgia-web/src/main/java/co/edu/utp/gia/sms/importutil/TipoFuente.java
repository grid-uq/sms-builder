package co.edu.utp.gia.sms.importutil;

import co.edu.utp.gia.sms.util.ApplicationGeneralProducer;

import java.util.Locale;
import java.util.ResourceBundle;

public enum TipoFuente {
	BASE_DATOS,BOLA_NIEVE,INCLUSION_DIRECTA;

	@Override
	public String toString() {
		ApplicationGeneralProducer instancia = new ApplicationGeneralProducer();
		Locale locale = instancia.getDefaultLocale();
		ResourceBundle bundle = ResourceBundle.getBundle("mensajes",locale);
		switch (this){
			case BASE_DATOS:
				return bundle.getString("etiquetaBaseDatos");
			case BOLA_NIEVE:
				return bundle.getString("etiquetaBolaNieve");
			case INCLUSION_DIRECTA:
			default:
				return bundle.getString("etiquetaInclusionDirecta");
		}

	}
}
