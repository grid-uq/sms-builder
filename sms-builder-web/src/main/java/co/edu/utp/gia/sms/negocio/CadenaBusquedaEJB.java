package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.CadenaBusqueda;
import co.edu.utp.gia.sms.entidades.Revision;
import co.edu.utp.gia.sms.entidades.Termino;
import co.edu.utp.gia.sms.query.CadenaBusquedaQuery;
import co.edu.utp.gia.sms.query.Queries;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class CadenaBusquedaEJB extends AbstractEJB<CadenaBusqueda, Integer>{
	@Inject
	private RevisionEJB revisionEJB;
	@Inject
	private TerminoEJB terminoEJB;

	public CadenaBusquedaEJB() {
		super(CadenaBusqueda.class);
	}
	/**
	 * Permite registrar una cadena de busqueda
	 * @param baseDatos Nombre de la base de datos a la que pernece la cadena de busqueda
	 * @param consulta que representa la cadena de busqueda.
	 * @param idRevision  Id de la {@link Revision} a la que se desea adicionar la
	 *                    Cadena de busqueda
	 * @return La {@link CadenaBusqueda} registrada
	 */
	public CadenaBusqueda registrar(String baseDatos,String consulta, Integer idRevision) {
		CadenaBusqueda cadenaBusqueda = null;
		Revision revision = revisionEJB.obtener(idRevision);
		if (revision != null) {
			cadenaBusqueda = new CadenaBusqueda(baseDatos,consulta,revision);
			registrar(cadenaBusqueda);
		}
		return cadenaBusqueda;
	}

	/**
	 * Permite obtener el listado de {@link CadenaBusqueda} de una revision
	 * 
	 * @param id Identificador de la revision
	 * @return Listado de {@link CadenaBusqueda} de la {@link Revision} identificada con
	 *         el id dado
	 */
	public List<CadenaBusqueda> obtenerCadenasBusqueda(Integer id) {
		return CadenaBusquedaQuery.FindAll.createQuery(entityManager,id).getResultList();
	}

	/**
	 * Permite actualizar una {@link CadenaBusqueda}
	 * 
	 * @param id          Id de la {@link CadenaBusqueda} a ser actualizada
	 * @param baseDatos Nuevo nombre de la base de datos de la Cande de Busqueda
	 * @param consulta Nueva consulta de la Cande de Busqueda
	 */
	public void actualizar(Integer id, String baseDatos,String consulta) {
		CadenaBusqueda cadenaBusqueda = obtenerOrThrow(id);
		if (cadenaBusqueda != null) {
			cadenaBusqueda.setBaseDatos(baseDatos);
			cadenaBusqueda.setConsulta(consulta);
		}
	}

	public String sugerirConsulta(Integer id){
		List<Termino> terminos = terminoEJB.obtenerTerminos(id);

		 return terminos.stream().map(
				termino->{
					return construirQueryFromTermino(termino);
				}
		).collect(Collectors.joining(" AND "));
	}

	private String construirQueryFromTermino(Termino termino){
		StringBuilder stringBuilder = new StringBuilder("(");
		stringBuilder.append(termino.getDescripcion());

		if( termino.getSinonimos().size() > 0 ){
			stringBuilder.append(" OR ");
			stringBuilder.append(
				termino.getSinonimos().stream().collect(Collectors.joining(" OR "))
			);
		}

		stringBuilder.append(")");
		return stringBuilder.toString();
	}
}
