package co.edu.utp.gia.sms.beans;

import java.io.IOException;
import java.util.Arrays;

import javax.inject.Named;

import co.edu.utp.gia.sms.entidades.Revision;

@Named
public class ValidarRevisionBean extends GenericBean<Revision>{

	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 9092392501937374230L;

	public void validar() {
//		System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
//		System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestPathInfo());
//		System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath());
		String[] urls = { "/revision/registroRevision.xhtml", "/revision/seleccionarRevision.xhtml",
				"/revision/registroInicial.xhtml" };
		String path = getFacesContext().getExternalContext().getRequestServletPath();
		if (revision == null && !Arrays.asList(urls).contains(path)) {
			try {

				getFacesContext().getExternalContext()
						.redirect(getFacesContext().getExternalContext().getRequestContextPath() + "/");
				getFacesContext().responseComplete();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void inicializar() {
		// TODO Auto-generated method stub
		
	}

}
