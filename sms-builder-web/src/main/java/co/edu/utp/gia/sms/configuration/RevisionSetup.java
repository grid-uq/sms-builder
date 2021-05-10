package co.edu.utp.gia.sms.configuration;

import co.edu.utp.gia.sms.negocio.RevisionEJB;

import javax.inject.Inject;

public class RevisionSetup implements SetupInterface{


    @Inject
    private RevisionEJB revisionEJB;

    @Override
    public void setup() {
        revisionEJB.registrar("","",1);
    }
}
