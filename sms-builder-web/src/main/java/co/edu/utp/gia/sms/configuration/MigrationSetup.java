package co.edu.utp.gia.sms.configuration;

import co.edu.utp.gia.sms.negocio.ReferenciaService;
import jakarta.inject.Inject;

public class MigrationSetup implements SetupInterface {
    @Inject
    private ReferenciaService referenciaService;

    @Override
    public void setup() {
        referenciaService.findAll().stream()
                .filter(referencia->referencia.getTags() != null && ! referencia.getTags().isEmpty())
                .forEach(referencia -> referenciaService.updateTags(referencia.getId(),referencia.getTags()));
    }
}
