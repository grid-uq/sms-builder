package co.edu.utp.gia.sms.beans;

import lombok.extern.java.Log;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
@Log
public class RegistroReferenciasBean extends ImportarReferenciasBean{
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 1107564281230780705L;

    public RegistroReferenciasBean() {
        super();
        setTipoFuente(null);
    }
}
