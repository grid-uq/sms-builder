package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.importutil.TipoFuente;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ImportarReferenciaBolaNieve extends ImportarReferenciasBean{

    public ImportarReferenciaBolaNieve() {
        super();
        setTipoFuente(TipoFuente.BOLA_NIEVE);
    }
}
