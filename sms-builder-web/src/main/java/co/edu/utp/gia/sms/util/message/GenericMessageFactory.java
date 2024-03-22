package co.edu.utp.gia.sms.util.message;

import lombok.NoArgsConstructor;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

import java.util.*;
/**
 * Clase encargada de cargar los mensajes desde archivos de propiedades para ser mostrados en excepciones.
 */
@NoArgsConstructor
public class GenericMessageFactory<T extends Config>{
    private String propertiesName;
    private Class<T> tclass;
    private Map<Locale,T> instances;
    private Locale defaultLocale;

    public GenericMessageFactory(String propertiesName, Class<T> tclass) {
        this(propertiesName,tclass,Locale.ROOT);
    }

    public GenericMessageFactory(String propertiesName, Class<T> tclass, Locale defaultLocale) {
        this.propertiesName = propertiesName;
        this.tclass = tclass;
        this.defaultLocale = defaultLocale;
        instances = new HashMap<>();
    }

    public T getMessageInstance(Locale locale)  {
        if(! instances.containsKey(locale) ){
            instances.put(locale,
                    ConfigFactory.create(tclass,
                            getPropertiesBase(locale)
                    )
            );
        }
        return instances.get(locale);
    }

    public T getMessageInstance()  {
        return getMessageInstance(defaultLocale);
    }

    private Properties getPropertiesBase(Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(propertiesName,locale);
//        ResourceBundle resourceBundle = ResourceBundle.getBundle(propertiesName, locale, Thread.currentThread().getContextClassLoader());
        return resourcesBundleToProperties(resourceBundle);
    }

    private Properties resourcesBundleToProperties(ResourceBundle resourceBundle) {
        Properties properties = new Properties();
        resourceBundle.keySet().forEach(key->properties.setProperty(key,resourceBundle.getString(key)) );
        return properties;
    }
}
