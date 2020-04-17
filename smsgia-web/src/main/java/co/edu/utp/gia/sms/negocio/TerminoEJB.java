package co.edu.utp.gia.sms.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.entidades.Termino;
import co.edu.utp.gia.sms.query.Queries;

@Stateless
public class TerminoEJB extends AbstractEJB<Termino, Integer>{
	@Inject
	private RevisionEJB revisionEJB;

	public TerminoEJB() {
		super(Termino.class);
	}
	/**
	 * Permite registrar una termino
	 * 
	 * @param descripcion Descripcion de la Termino
	 * @param idRevision  Id de la {@link Revision} a la que se desea adicionar la
	 *                    Termino
	 * @return La Termino registrada
	 */
	public Termino registrar(String descripcion, Integer idRevision) {
		Termino termino = null;
		Revision revision = revisionEJB.obtener(idRevision);
		if (revision != null) {
			termino = new Termino(descripcion, revision);
			registrar(termino);
		}
		return termino;
	}

	/**
	 * Permite obtener el listado de Terminos de una revision
	 * 
	 * @param id Identificador de la revision
	 * @return Listado de {@link Termino} de la {@link Revision} identificada con
	 *         el id dado
	 */
	public List<Termino> obtenerTerminos(Integer id) {
		return entityManager.createNamedQuery(Queries.TERMINO_GET_ALL, Termino.class).setParameter("id", id)
				.getResultList();
	}

	/**
	 * Permite actualizar una Termino
	 * 
	 * @param id          Id de la {@link Termino} a ser actualizada
	 * @param descripcion Descripcion de la Termino a actulizar
	 */
	public void actualizar(Integer id, String descripcion) {
		Termino termino = obtener(id);
		if (termino != null) {
			termino.setDescripcion(descripcion);
		}
	}

}
