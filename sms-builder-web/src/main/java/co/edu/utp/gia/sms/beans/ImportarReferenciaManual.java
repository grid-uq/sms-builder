package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.importutil.TipoFuente;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ImportarReferenciaManual extends ImportarReferenciasBean{

    public ImportarReferenciaManual() {
        super();
        setTipoFuente(TipoFuente.INCLUSION_DIRECTA);
    }
}
