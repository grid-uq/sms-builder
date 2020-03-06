package co.edu.utp.gia.sms.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.UploadedFile;

import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.importutil.FileMultipleRegisterParse;
import co.edu.utp.gia.sms.importutil.FileMultipleRegisterParseFactory;
import co.edu.utp.gia.sms.importutil.Fuente;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;

@ManagedBean
@ViewScoped

public class RegistroReferenciasBean implements Serializable {
	@ManagedProperty(value = "#{registroInicialBean.revision}")
	private Revision revision;
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
		try (Scanner reader = new Scanner( file.getInputstream() )){
			
			
			FileMultipleRegisterParse parser = FileMultipleRegisterParseFactory.getInstance(Fuente.ACM);
			List<Referencia> referencias = parser.parse(file.getInputstream());
			referenciaEJB.registrar(referencias, revision.getId());
			FacesMessage message = new FacesMessage("Se adicionaron ", referencias.size() + " referencias");
			FacesContext.getCurrentInstance().addMessage(null, message);
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

	/**
	 * Metodo que permite obtener el valor del atributo revision
	 * @return El valor del atributo revision
	 */
	public Revision getRevision() {
		return revision;
	}

	/**
	 * Metodo que permite asignar un valor al atributo revision
	 * @param revision Valor a ser asignado al atributo revision
	 */
	public void setRevision(Revision revision) {
		this.revision = revision;
	}


}
