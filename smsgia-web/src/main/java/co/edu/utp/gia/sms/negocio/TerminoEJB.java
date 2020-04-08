package co.edu.utp.gia.sms.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.entidades.Termino;
import co.edu.utp.gia.sms.query.Queries;

@Stateless
public class TerminoEJB {
	@PersistenceContext
	private EntityManager entityManager;
	@Inject
	private RevisionEJB revisionEJB;

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
			entityManager.persist(termino);
		}
		return termino;
	}

	/**
	 * Permite obtener una Termino basado en su Id
	 * 
	 * @param idTermino Identificador de la Termino que se desea obtener
	 * @return La {@link Termino} que se corresponde con el Identificador dado
	 */
	public Termino obtener(Integer idTermino) {
		return entityManager.find(Termino.class, idTermino);
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
	 * Premite actualizar una {@link Termino}
	 * 
	 * @param termino Termino a ser actualizada
	 */
	public void actualizar(Termino termino) {
		actualizar(termino.getId(), termino.getDescripcion());
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

	/**
	 * Permite eliminar una Termino basado en su id
	 * 
	 * @param id Id de la Termino a eliminar
	 */
	public void eliminar(Integer id) {
		Termino termino = obtener(id);
		if (termino != null) {
			entityManager.remove(termino);
		}
	}

}
