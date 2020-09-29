package co.edu.utp.gia.sms.beans;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.exportutil.ReferenceToRIS;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;

@Named
@ViewScoped
public class ResumenReferenciasDestacadasBean extends GenericBean<ReferenciaDTO> {
	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = -4192800052066233993L;
	private List<ReferenciaDTO> referencias;
	@Inject
	private ReferenciaEJB referenciaEJB;


	public void inicializar() {

		if (revision != null) {
			referencias = referenciaEJB.obtenerDestacadas(revision.getId());
		}
	}
	

	/**
	 * Metodo que permite obtener el valor del atributo referencias
	 * 
	 * @return El valor del atributo referencias
	 */
	public List<ReferenciaDTO> getReferencias() {
		return referencias;
	}

	/**
	 * Metodo que permite asignar un valor al atributo referencias
	 * 
	 * @param referencias Valor a ser asignado al atributo referencias
	 */
	public void setReferencias(List<ReferenciaDTO> referencias) {
		this.referencias = referencias;
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
			
			e.printStackTrace();
		}
	    // Now you can write the InputStream of the file to the above OutputStream the usual way.
	    // ...

	    fc.responseComplete(); // Important! Otherwise JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.
		
	}


}
