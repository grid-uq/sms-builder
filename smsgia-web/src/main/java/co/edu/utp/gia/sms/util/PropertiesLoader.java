package co.edu.utp.gia.sms.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Clase encargada de permitir leer las propiedades desde los archivos de
 * propiedades acordes con el locale establecido
 */
@NoArgsConstructor
public class PropertiesLoader {
    /**
     * Variable que representa el atributo locale de la clase. Identifica la region
     * e idioma a manejar
     */
    @Getter
    @Setter
    private Locale locale;

    /**
     * Variable que representa el atributo properties de la clase. Identifica el
     * nombre del archivo de propiedades a ser usado
     */
    @Getter
    private String properties;

    /**
     * Variable que representa el atributo bundle de la clase. Identifica el
     * {@link ResourceBundle} que manejara el archivo de propiedades cargado
     */
    @Getter
    private ResourceBundle bundle;

    /**
     * Metodo que permite inicializar los elementos de la clase ${className}
     *
     * @param locale     Region - Idioma a ser usado para cargar el archivo de
     *                   propiedades
     * @param properties Nombre del archivo de propiedades a cargar
     */
    public PropertiesLoader(Locale locale, String properties) {
        this.locale = locale;
        this.properties = properties;
        try {
            bundle = locale != null ? ResourceBundle.getBundle(properties, locale) : ResourceBundle.getBundle(properties);
//		bundle = ResourceBundle.getBundle(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método encargado de obtener el nombre de la variable del archivo de
     * properties y retornar el valor de la variable buscada con los parámetros
     * asignados.
     *
     * @param nombrePropiedad , cadena con el nombre de la variable a cargar.
     * @param parametros      ,Objetos para reemplazar en la cadena.
     * @return String, cadena con el valor de la variable buscada.
     */
    public String getFormatProperties(String nombrePropiedad, Object[] parametros) {
        MessageFormat form = new MessageFormat(bundle.getString(nombrePropiedad));
        return form.format(parametros);
    }

    /**
     * Método encargado de obtener el nombre de la variable del archivo de
     * properties y retornar el valor de la variable buscada.
     *
     * @param nombrePropiedad , cadena con el nombre de la variable a cargar.
     * @return String, cadena con el valor de la variable buscada.
     */
    public String getProperties(String nombrePropiedad) {
        return bundle.getString(nombrePropiedad);
    }


}
