package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.Objetivo;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.query.objetivo.ObjetivoFindAll;
import co.edu.utp.gia.sms.query.pregunta.PreguntaGetObjetivos;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;
/**
 * Clase de negocio encargada de implementar las funciones correspondientes a la
 * gestion del {@link Objetivo}.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
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
		return ObjetivoFindAll.createQuery(entityManager,id).getResultList();
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

	/**
	 * Permite obtener los objetivos relacionados a una pregunta
	 * @param id Id de la pregunta
	 * @return List< Objetivo > relacionados con la preguna
	 */
	public List<Objetivo> obtenerObjetivosPregunta(Integer id) {
		return PreguntaGetObjetivos.createQuery(entityManager,id).getResultList();
	}

}
