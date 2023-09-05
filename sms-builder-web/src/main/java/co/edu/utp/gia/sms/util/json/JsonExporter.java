package co.edu.utp.gia.sms.util.json;

import co.edu.utp.gia.sms.entidades.Revision;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Log
public enum JsonExporter {
    INSTANCE;

    public void toJsonFile(Revision revision, String outputFilePath) throws IOException {
        FileOutputStream fos = new FileOutputStream(outputFilePath);
        toJsonFile(revision, fos);
    }

    public void toJsonFile(Revision revision, File outputFile) throws IOException {

        if (!outputFile.exists()) {
            throw new FileNotFoundException("El archivo no existe: ");
        }

        FileOutputStream fos = new FileOutputStream(outputFile);
        toJsonFile(revision, fos);
    }

    public void toJsonFile(Revision revision, Path outputPath) throws IOException {

        if (!Files.exists(outputPath)) {
            throw new FileNotFoundException("El archivo no existe: ");
        }

        FileOutputStream fos = new FileOutputStream(outputPath.toString());
        toJsonFile(revision, fos);
    }

    public void toJsonFile(Revision revision, OutputStream fos)  {
        try(ZipOutputStream zipOS = new ZipOutputStream(fos)) {
            ZipEntry entry = new ZipEntry("sms-builder.json");
            ObjectMapper objectMapper = new ObjectMapper();
            zipOS.putNextEntry(entry);

            objectMapper.writeValue(zipOS, revision);
        } catch (IOException e) {
            log.log(Level.SEVERE,"No se pudo exportar el backup",e);
            throw new RuntimeException(e);
        }
    }
}
