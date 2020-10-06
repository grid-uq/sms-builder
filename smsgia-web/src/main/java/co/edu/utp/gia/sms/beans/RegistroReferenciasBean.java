package co.edu.utp.gia.sms.beans;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.file.UploadedFile;

import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.importutil.FileMultipleRegisterParse;
import co.edu.utp.gia.sms.importutil.FileMultipleRegisterParseFactory;
import co.edu.utp.gia.sms.importutil.Fuente;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;

@Named
@ViewScoped
public class RegistroReferenciasBean extends GenericBean<Referencia> {
	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 1107564281230780705L;

	@Inject
	private ReferenciaEJB referenciaEJB;

	private UploadedFile file;
	private Fuente fuente;


	public void upload() {
		if (file != null) {
			procesarArchivo();
		}
	}

	private void procesarArchivo() {
		
		try (Scanner reader = new Scanner( file.getInputStream() )){
			
			FileMultipleRegisterParse parser = FileMultipleRegisterParseFactory.getInstance(fuente);
			List<Referencia> referencias = parser.parse(file.getInputStream());
			referenciaEJB.registrar(referencias, getRevision().getId());
			mostrarMensajeGeneral("Se adicionaron "+ referencias.size() + " referencias" );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public void handleFileUpload(FileUploadEvent event) {
//		FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
//		FacesContext.getCurrentInstance().addMessage(null, msg);
//	}

////////// ----- GET/SET ----- ////////////	

	/**
	 * Metodo que permite obtener el valor del atributo file
	 * 
	 * @return El valor del atributo file
	 */
	public UploadedFile getFile() {
		return file;
	}

	/**
	 * Metodo que permite asignar un valor al atributo file
	 * 
	 * @param file Valor a ser asignado al atributo file
	 */
	public void setFile(UploadedFile file) {
		this.file = file;
	}

	/**
	 * Metodo que permite obtener el valor del atributo fuente
	 * 
	 * @return El valor del atributo fuente
	 */
	public Fuente getFuente() {
		return fuente;
	}

	/**
	 * Metodo que permite asignar un valor al atributo fuente
	 * 
	 * @param fuente Valor a ser asignado al atributo fuente
	 */
	public void setFuente(Fuente fuente) {
		this.fuente = fuente;
	}

	/**
	 * Arreglo de fuentes soportadas
	 * 
	 * @return Arreglo de fuentes soportadas
	 */
	public Fuente[] getFuentes() {
		return Fuente.values();
	}

	@Override
	public void inicializar() {
		
	}

}
