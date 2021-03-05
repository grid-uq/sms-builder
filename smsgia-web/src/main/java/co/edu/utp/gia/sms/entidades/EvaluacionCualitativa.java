package co.edu.utp.gia.sms.entidades;

import co.edu.utp.gia.sms.util.ApplicationGeneralProducer;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Representa los valores que pueden ser asignados a una {@link EvaluacionCualitativa} en una {@link EvaluacionCalidad}
 */
public enum EvaluacionCualitativa {
    NO_CUMPLE, PARCIALMENTE, CUMPLE;

    @Override
    public String toString() {
        ApplicationGeneralProducer instancia = new ApplicationGeneralProducer();
        Locale locale = instancia.getDefaultLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("mensajes",locale);
        switch (this){
            case NO_CUMPLE:
                return bundle.getString("etiquetaNoCumple");
            case PARCIALMENTE:
                return bundle.getString("etiquetaParcialmente");
            case CUMPLE:
            default:
                return bundle.getString("etiquetaCumple");
        }

    }
}
