package co.edu.utp.gia.sms.util;

import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import lombok.extern.java.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.util.function.Consumer;
import java.util.logging.Level;

@Log
public enum ExportFile {
    INSTANCE;
    public void export(String contentType,String fileName,Consumer<OutputStream> consumer) {

        FacesContext fc = FacesContext.getCurrentInstance();
        final ExternalContext ec = getExternalContext(contentType, fileName, fc);

        try (OutputStream output = ec.getResponseOutputStream()) {
            consumer.accept(output);
        } catch (IOException e) {
            log.log(Level.ALL,"Error al exportar las referencias",e);
        }
        // Now you can write the InputStream of the file to the above OutputStream the usual way.
        // ...

        fc.responseComplete(); // Important! Otherwise JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.
    }

    private static ExternalContext getExternalContext(String contentType, String fileName, FacesContext fc) {
        ExternalContext ec = fc.getExternalContext();

        ec.responseReset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
        ec.setResponseContentType(contentType); // Check http://www.iana.org/assignments/media-types for all types. Use if necessary ExternalContext#getMimeType() for auto-detection based on filename.
//	    ec.setResponseContentLength(contentLength); // Set it with the file size. This header is optional. It will work if it's omitted, but the download progress will be unknown.
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\""); // The Save As popup magic is done here. You can give it any file name you want, this only won't work in MSIE, it will use current request URL as file name instead.
        return ec;
    }
}
