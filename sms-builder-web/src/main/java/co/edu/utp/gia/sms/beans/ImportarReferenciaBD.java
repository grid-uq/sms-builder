package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.importutil.TipoFuente;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ImportarReferenciaBD extends ImportarReferenciasBean{

    public ImportarReferenciaBD() {
        super();
        setTipoFuente(TipoFuente.BASE_DATOS);
    }
}
