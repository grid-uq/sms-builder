package co.edu.utp.gia.sms.beans;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import co.edu.utp.gia.sms.entidades.Revision;

@ManagedBean
public class ValidarRevisionBean {
	@ManagedProperty("#{registroInicialBean.revision}")
	private Revision revision;

	public void validar() {
//		System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
//		System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestPathInfo());
//		System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath());
		String[] urls = { "/revision/registroRevision.xhtml", "/revision/seleccionarRevision.xhtml",
				"/revision/registroInicial.xhtml" };
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath();
		if (revision == null && !Arrays.asList(urls).contains(path)) {
			try {

				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/");
				FacesContext.getCurrentInstance().responseComplete();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Metodo que permite obtener el valor del atributo revision
	 * 
	 * @return El valor del atributo revision
	 */
	public Revision getRevision() {
		return revision;
	}

	/**
	 * Metodo que permite asignar un valor al atributo revision
	 * 
	 * @param revision Valor a ser asignado al atributo revision
	 */
	public void setRevision(Revision revision) {
		this.revision = revision;
	}

}
