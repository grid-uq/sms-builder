package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.exportutil.ReferenceToRIS;
import co.edu.utp.gia.sms.negocio.ReferenciaService;
import lombok.Getter;
import lombok.Setter;

import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.extern.java.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;

/**
 * Clase controladora de interfaz web que se encarga de presentar un resumen de las referencias destacadas.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@Named
@ViewScoped
@Log
public class ResumenReferenciasDestacadasBean extends GenericBean<ReferenciaDTO> {
    @Getter @Setter
    private List<ReferenciaDTO> referencias;
    @Inject
    private ReferenciaService referenciaService;

    public void inicializar() {
        referencias = referenciaService.obtenerDestacadas();
    }


    public void exportToRIS() {

        String contentType = "text/plain";
        String fileName = "referenciasSeleccionadas.ris";
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        ec.responseReset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
        ec.setResponseContentType(contentType); // Check http://www.iana.org/assignments/media-types for all types. Use if necessary ExternalContext#getMimeType() for auto-detection based on filename.
//	    ec.setResponseContentLength(contentLength); // Set it with the file size. This header is optional. It will work if it's omitted, but the download progress will be unknown.
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\""); // The Save As popup magic is done here. You can give it any file name you want, this only won't work in MSIE, it will use current request URL as file name instead.

        try (OutputStream output = ec.getResponseOutputStream()) {
            ReferenceToRIS rtr = new ReferenceToRIS(output);
            rtr.procesarReferencias(referencias);
        } catch (IOException e) {
            log.log(Level.ALL,"Error al exportar las referencias",e);
        }
        // Now you can write the InputStream of the file to the above OutputStream the usual way.
        // ...

        fc.responseComplete(); // Important! Otherwise JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.

    }


}
