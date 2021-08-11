package co.edu.utp.gia.sms.importutil;

import co.edu.utp.gia.sms.util.ApplicationGeneralProducer;

import java.util.Locale;
import java.util.ResourceBundle;

public enum TipoArchivo {
	RIS,BIBTEX;

//	@Override
//	public String toString() {
//		ApplicationGeneralProducer instancia = new ApplicationGeneralProducer();
//		Locale locale = instancia.getDefaultLocale();
//		ResourceBundle bundle = ResourceBundle.getBundle("mensajes",locale);
//		switch (this){
//			case RIS:
//				return bundle.getString("etiquetaBaseDatos");
//			case BIBTEX:
//			default:
//				return bundle.getString("etiquetaInclusionDirecta");
//		}
//	}
}
