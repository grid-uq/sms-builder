package co.edu.utp.gia.sms.negocio;

import java.util.List;

import javax.ejb.Stateless;

import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.query.Queries;

@Stateless
public class RevisionEJB extends AbstractEJB<Revision, Integer> {

	public RevisionEJB() {
		super(Revision.class);
	}

	public Revision registrar(String nombre, String descripcion) {
		Revision revision = new Revision(nombre, descripcion);
		return registrar(revision);
	}

	/**
	 * Permite obtener el listado de preguntas de una revision
	 * 
	 * @param id Identificador de la revision
	 * @return Listado de {@link Pregunta} de la {@link Revision} identificada con
	 *         el id dado
	 */
	public List<Pregunta> obtenerPreguntas(Integer id) {
		return entityManager.createNamedQuery(Queries.PREGUNTA_GET_ALL, Pregunta.class).setParameter("id", id)
				.getResultList();
	}


	/**
	 * Permite obtener el listado de preguntas de una revision
	 * 
	 * @return Listado de las {@link Revision} registradas
	 */
	public List<Revision> obtenerTodas() {
		return entityManager.createNamedQuery(Queries.REVISION_GET_ALL, Revision.class).getResultList();
	}

	/**
	 * Permite actualizar una revision
	 * 
	 * @param id          Id de la {@link Pregunta} a ser actualizada
	 * @param nombre      Codigo de la pregunta a actualizar
	 * @param descripcion Descripcion de la pregunta a actulizar
	 */
	public void actualizar(Integer id, String nombre, String descripcion) {
		Revision revision = obtener(id);
		if (revision != null) {
			revision.setNombre(nombre);
			revision.setDescripcion(descripcion);
		}
	}

	/**
	 * Permite obtener el listado de topicos de la revision
	 * 
	 * @param id Identificador de la revision
	 * @return Listado de {@link Topcios} de la {@link Revision} identificada con el
	 *         id dado
	 */
	public List<Topico> obtenerTopicos(Integer id) {
		return entityManager.createNamedQuery(Queries.TOPICO_REVISION_GET_ALL, Topico.class).setParameter("id", id)
				.getResultList();
	}
	
}
