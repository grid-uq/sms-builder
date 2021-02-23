package co.edu.utp.gia.sms.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.edu.utp.gia.sms.entidades.Objetivo;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.query.Queries;

@Stateless
public class ObjetivoEJB extends AbstractEJB<Objetivo, Integer> {
	@Inject
	private RevisionEJB revisionEJB;

	public ObjetivoEJB() {
		super(Objetivo.class);
	}

	/**
	 * Permite registrar una objetivo
	 * 
	 * @param codigo      Código del Objetivo
	 * @param descripcion Descripción del Objetivo
	 * @param idRevision  Id de la {@link Revision} a la que se desea adicionar el
	 *                    Objetivo
	 * @return El Objetivo registrado
	 */
	public Objetivo registrar(String codigo, String descripcion, Integer idRevision) {
		Objetivo objetivo = null;
		Revision revision = revisionEJB.obtener(idRevision);
		if (revision != null) {
			objetivo = new Objetivo( codigo, descripcion,revision);
			entityManager.persist(objetivo);
		}
		return objetivo;
	}

	/**
	 * Permite obtener el listado de Objetivos de una revision
	 * 
	 * @param id Identificador de la revision
	 * @return Listado de {@link Objetivo} de la {@link Revision} identificada con
	 *         el id indicado
	 */
	public List<Objetivo> obtenerObjetivo(Integer id) {
		return entityManager.createNamedQuery(Queries.OBJETIVO_GET_ALL, Objetivo.class).setParameter("id", id)
				.getResultList();
	}


	/**
	 * Permite actualizar un Objetivo
	 * 
	 * @param id          Id del {@link Objetivo} a ser actualizado
	 * @param codigo      Código del {@link Objetivo} a ser actualizado
	 * @param descripcion Descripción del {@link Objetivo} a ser actualizado
	 */
	public void actualizar(Integer id, String codigo, String descripcion) {
		Objetivo objetivo = obtener(id);
		if (objetivo != null) {
			objetivo.setCodigo(codigo);
			objetivo.setDescripcion(descripcion);
		}
	}

	public List<Objetivo> obtenerObjetivo(List<Integer> listaIdObjetivos) {
		List<Objetivo> objetivos = new ArrayList<Objetivo>();
		for (Integer id : listaIdObjetivos) {
			objetivos.add(obtener(id));
		}

		return objetivos;
	}

	public List<Objetivo> obtenerObjetivosPregunta(Integer id) {
		return entityManager.createNamedQuery(Queries.PREGUNTA_OBJETIVO_GET_ALL, Objetivo.class).setParameter("id", id)
				.getResultList();
	}

}
