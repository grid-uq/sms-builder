package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.negocio.RevisionService;
import co.edu.utp.gia.sms.util.ExportFile;
import co.edu.utp.gia.sms.util.json.JsonExporter;
import co.edu.utp.gia.sms.util.json.JsonImporter;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.primefaces.model.file.UploadedFile;

import java.io.IOException;
import java.util.logging.Level;

@Named
@RequestScoped
@Log
public class BackupBean extends AbstractBean{
    @Inject
    private RevisionService revisionService;
    private Revision revision;
    @Getter @Setter
    private UploadedFile file;

    @Override
    public void inicializar() {
        revision = revisionService.get();
    }

    public void export(){
        ExportFile.INSTANCE.export("application/zip","sms-builder.zip",(os)->JsonExporter.INSTANCE.toJsonFile(revision,os));
    }

    public void restoreBackup(){
        if (file != null && file.getFileName() != null) {
            procesarArchivo();
        } else {
            mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        }
    }

    private void procesarArchivo() {
        try {
            var revisionRestored = JsonImporter.INSTANCE.importFromJson( file.getInputStream() );
            revisionService.restore(revisionRestored);
            mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA) );
        } catch (IOException e) {
            log.log(Level.WARNING, "Error al procesar un archivo", e);
        }
    }
}
