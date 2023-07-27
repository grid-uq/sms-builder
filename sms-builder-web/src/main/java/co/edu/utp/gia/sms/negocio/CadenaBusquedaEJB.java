package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.*;
import co.edu.utp.gia.sms.query.revision.RevisionGetCadenaBusquedaQuery;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Clase de negocio encargada de implementar las funciones correspondientes a la
 * gestion de la {@link CadenaBusqueda}.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
@Stateless
@LocalBean
public class CadenaBusquedaEJB extends AbstractEJB<CadenaBusqueda, Integer>{
	@Inject
	private RevisionEJB revisionEJB;
	@Inject
	private TerminoEJB terminoEJB;
	@Inject
	private FuenteEJB fuenteEJB;

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
	public CadenaBusqueda registrar(String baseDatos, String consulta, Date fecha,Integer resultadoPreliminar,Integer resultadoFinal, Integer idRevision) {
		CadenaBusqueda cadenaBusqueda = null;
		Revision revision = revisionEJB.obtener(idRevision);
		if (revision != null) {
			cadenaBusqueda = new CadenaBusqueda(baseDatos,consulta,fecha,resultadoPreliminar,resultadoFinal,revision);
			registrar(cadenaBusqueda);
			//TODO hacer interface para registrar las bases de datos y que al registrar una cadena se use la base de datos ya creada
			fuenteEJB.registrar(baseDatos, TipoFuente.BASE_DATOS,revision);
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
		return RevisionGetCadenaBusquedaQuery.createQuery(entityManager,id).getResultList();
	}

	/**
	 * Permite actualizar una {@link CadenaBusqueda}
	 * 
	 * @param id          Id de la {@link CadenaBusqueda} a ser actualizada
	 * @param baseDatos Nuevo nombre de la base de datos de la Cande de Busqueda
	 * @param consulta Nueva consulta de la Cande de Busqueda
	 */
	public void actualizar(Integer id, String baseDatos,String consulta, Date fecha,Integer resultadoPreliminar,Integer resultadoFinal) {
		CadenaBusqueda cadenaBusqueda = obtenerOrThrow(id);
		if (cadenaBusqueda != null) {
			cadenaBusqueda.setBaseDatos(baseDatos);
			cadenaBusqueda.setConsulta(consulta);
			cadenaBusqueda.setFechaConsulta(fecha);
			cadenaBusqueda.setResultadoPreliminar(resultadoPreliminar);
			cadenaBusqueda.setResultadoFinal(resultadoFinal);
		}
	}

	public String sugerirConsulta(Integer id){
		List<Termino> terminos = terminoEJB.obtenerTerminos(id);

		 return terminos.stream().map(
				 this::construirQueryFromTermino
		).collect(Collectors.joining(" AND "));
	}

	private String construirQueryFromTermino(Termino termino){
		StringBuilder stringBuilder = new StringBuilder("(");
		stringBuilder.append(termino.getDescripcion());

		if( termino.getSinonimos().size() > 0 ){
			stringBuilder.append(" OR ");
			stringBuilder.append(
					String.join(" OR ", termino.getSinonimos())
			);
		}

		stringBuilder.append(")");
		return stringBuilder.toString();
	}
}
