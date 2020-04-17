package co.edu.utp.gia.sms.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.exceptions.LogicException;
import co.edu.utp.gia.sms.query.Queries;

@Stateless
public class AtributoCalidadEJB extends AbstractEJB<AtributoCalidad, Integer>{

	@Inject
	private RevisionEJB revisionEJB;

	public AtributoCalidadEJB() {
		super(AtributoCalidad.class);
	}
	
	/**
	 * Permite registrar un atributo de calidad
	 * 
	 * @param descripcion Descripcion del atributo de calidad
	 * @param idRevision  Id de la {@link Revision} a la que se desea adicionar el atributo de calidad
	 * @return El atributo de calidad registrado
	 */
	public AtributoCalidad registrar(String descripcion, Integer idRevision) {
		AtributoCalidad atributoCalidad = null;
		Revision revision = revisionEJB.obtener(idRevision);
		if (revision != null) {
			atributoCalidad = new AtributoCalidad(descripcion, revision);
			registrar(atributoCalidad);
		}
		return atributoCalidad;
	}

	/**
	 * Permite obtener el listado de atributos de calidad de una revision
	 * 
	 * @param id Identificador de la revision
	 * @return Listado de {@link AtributoCalidad} de la {@link Revision} identificada con
	 *         el id dado
	 */
	public List<AtributoCalidad> obtenerAtributosCalidad(Integer id) {
		return entityManager.createNamedQuery(Queries.ATRIBUTO_CALIDAD_GET_ALL, AtributoCalidad.class).setParameter("id", id)
				.getResultList();
	}


	/**
	 * Premite actualizar un {@link AtributoCalidad}
	 * 
	 * @param atributoCalidad Atributo de calidad a ser actualizada
	 */
	public void actualizar(AtributoCalidad atributoCalidad) {
		actualizar(atributoCalidad.getId(), atributoCalidad.getDescripcion());
	}

	/**
	 * Permite actualizar un atributo de calidad
	 * 
	 * @param id          Id de la {@link AtributoCalidad} a ser actualizada
	 * @param descripcion Descripcion del atributo de calidad a actulizar
	 */
	public void actualizar(Integer id, String descripcion) {
		AtributoCalidad atributoCalidad = obtener(id);
		if (atributoCalidad != null) {
			atributoCalidad.setDescripcion(descripcion);
		}
	}

	@Override
	public List<AtributoCalidad> listar() {
		throw new LogicException("Operacion no soportada");
	}

}
