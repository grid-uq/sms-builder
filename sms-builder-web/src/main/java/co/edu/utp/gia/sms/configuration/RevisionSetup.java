package co.edu.utp.gia.sms.configuration;

import co.edu.utp.gia.sms.negocio.RevisionEJB;

import javax.inject.Inject;

public class RevisionSetup implements SetupInterface{


    @Inject
    private RevisionEJB revisionEJB;

    @Override
    public void setup() {
        if( revisionEJB.listar().size() == 0 ) {
            revisionEJB.registrar("", "", 1);
        }
    }
}
