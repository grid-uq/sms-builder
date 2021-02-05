package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.entidades.Objetivo;
import co.edu.utp.gia.sms.entidades.Usuario;
import co.edu.utp.gia.sms.negocio.RevisoresEJB;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class RevisoresBean extends GenericBean<Objetivo> {
	/**
	 * Variable que representa el atributo serialVersionUID de la clase
	 */
	private static final long serialVersionUID = 9060626423979863537L;
	private List<Usuario> usuarios;

	@Inject
	private RevisoresEJB revisoresEJB;

	public void inicializar() {
		if (getRevision() != null) {
			usuarios = revisoresEJB.listar(getRevision().getId());
		}
	}

	public List<? extends Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void guardar(){
		try {
			revisoresEJB.guardar(getRevision().getId(),usuarios);
			mostrarMensajeGeneral("Datos guardados");
		}catch (Exception e){
			mostrarErrorGeneral(e.getMessage());
		}
	}

}
