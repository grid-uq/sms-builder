package co.edu.utp.gia.sms.util.json;

import co.edu.utp.gia.sms.entidades.Revision;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipInputStream;

public enum JsonImporter {
    INSTANCE;

    public Revision importFromJson(File file) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException("El archivo no existe: ");
        }

        FileInputStream fis = new FileInputStream(file);
        return importFromJson(fis);
    }

    public Revision importFromJson(String filePath) throws IOException {
        if (!Files.exists(Paths.get(filePath))) {
            throw new IOException("La ruta no existe o no es accesible");
        }
        FileInputStream fis = new FileInputStream(filePath);
        return importFromJson(fis);
    }

    public Revision importFromJson(Path path) throws IOException {
        if (!Files.exists(path)) {
            throw new FileNotFoundException("El archivo no existe: ");
        }
        InputStream fis = Files.newInputStream(path);
        return importFromJson(fis);
    }

    public Revision importFromJson(InputStream fis) throws IOException {
        try(ZipInputStream zipIS = new ZipInputStream(fis)){
            zipIS.getNextEntry();
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(zipIS, Revision.class);
        }
    }
}
