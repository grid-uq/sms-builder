package co.edu.utp.gia.sms.beans;

import java.io.IOException;
import java.util.Arrays;

import javax.faces.context.FacesContext;
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
		String[] pattern = {"/seguridad","/administracion"};
		String path = getFacesContext().getExternalContext().getRequestServletPath();
		if (getRevision() == null && !Arrays.asList(urls).contains(path) && Arrays.stream(pattern).filter(p->path.startsWith(p)).count() == 0) {
			irInicio();
		}
	}

	@Override
	public void inicializar() {
		// TODO Auto-generated method stub
		
	}

	private void irInicio(){
		try {
			getFacesContext().getExternalContext()
					.redirect(getFacesContext().getExternalContext().getRequestContextPath() + "/");
			getFacesContext().responseComplete();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void onIdle()  {
		System.out.println("finalizar sesion");
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		irInicio();
	}

	public void onActive(){

	}

}
