package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.CadenaBusqueda;
import co.edu.utp.gia.sms.entidades.Fuente;
import co.edu.utp.gia.sms.entidades.Termino;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Date;
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
@ApplicationScoped
public class CadenaBusquedaService extends AbstractGenericService<CadenaBusqueda, String>{
	@Inject
	private TerminoService terminoService;
	@Inject
	private FuenteService fuenteService;

	public CadenaBusquedaService() {
		super(DB.root.getProvider(CadenaBusqueda.class));
	}

	/**
	 * Permite registrar una cadena de busqueda
	 * @param baseDatos Nombre de la base de datos a la que pernece la cadena de busqueda
	 * @param consulta que representa la cadena de busqueda.
	 * @param fecha Fecha en la que se realiza la consulta
	 * @param resultadoPreliminar número de resultado encontrados antes de aplicar los criterios de inclusión/exclusión
	 * @param resultadoFinal número de resultados encontrados después de aplicar los criterios de inclusión/exclusión
	 * @return La {@link CadenaBusqueda} registrada
	 */
	public CadenaBusqueda save(Fuente baseDatos, String consulta, Date fecha, Integer resultadoPreliminar, Integer resultadoFinal) {
		baseDatos = fuenteService.findOrThrow(baseDatos.getId());
		CadenaBusqueda cadenaBusqueda = new CadenaBusqueda(baseDatos,consulta,fecha,resultadoPreliminar,resultadoFinal);
		save(cadenaBusqueda);
		//TODO hacer interface para registrar las bases de datos y que al registrar una cadena se use la base de datos ya creada
		//fuenteService.registrar(baseDatos, TipoFuente.BASE_DATOS,revision);
		return cadenaBusqueda;
	}

	/**
	 * Permite actualizar una {@link CadenaBusqueda}
	 * 
	 * @param id          Id de la {@link CadenaBusqueda} a ser actualizada
	 * @param baseDatos Nombre de la base de datos a la que pernece la cadena de busqueda
	 * @param consulta que representa la cadena de busqueda.
	 * @param fecha Fecha en la que se realiza la consulta
	 * @param resultadoPreliminar número de resultado encontrados antes de aplicar los criterios de inclusión/exclusión
	 * @param resultadoFinal número de resultados encontrados después de aplicar los criterios de inclusión/exclusión
	 */
	public void actualizar(String id, Fuente baseDatos,String consulta, Date fecha,Integer resultadoPreliminar,Integer resultadoFinal) {
		CadenaBusqueda cadenaBusqueda = findOrThrow(id);
		if (cadenaBusqueda != null) {
			cadenaBusqueda.setBaseDatos(baseDatos);
			cadenaBusqueda.setConsulta(consulta);
			cadenaBusqueda.setFechaConsulta(fecha);
			cadenaBusqueda.setResultadoPreliminar(resultadoPreliminar);
			cadenaBusqueda.setResultadoFinal(resultadoFinal);
		}
	}

	/**
	 * Sugiere la estructura de la consulta a partir de los términos y los sinónimos registrados
	 * @return Un String con la consulta sugerida
	 */
	public String sugerirConsulta(){

		 return terminoService.get()
				 .stream()
				 .map(this::construirQueryFromTermino)
				 .collect(Collectors.joining(" AND "));
	}

	/**
	 * Construye el segmento de la consulta conformado por un término y sus sinónimos
	 * @return Un String con el segmento de la consulta
	 */
	private String construirQueryFromTermino(Termino termino){
		StringBuilder stringBuilder = new StringBuilder("(");
		stringBuilder.append(termino.getDescripcion());

		if(!termino.getSinonimos().isEmpty()){
			stringBuilder.append(" OR ");
			stringBuilder.append(
					String.join(" OR ", termino.getSinonimos())
			);
		}

		stringBuilder.append(")");
		return stringBuilder.toString();
	}
}
