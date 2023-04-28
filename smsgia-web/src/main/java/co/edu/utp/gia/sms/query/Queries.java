package co.edu.utp.gia.sms.query;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({

//	@NamedQuery(name = Queries.PREGUNTA_GET_ALL, query = "select p from Pregunta p where p.revision.id = :id"),

//	@NamedQuery(name = Queries.PREGUNTA_GET_ALL, query = "select distinct p from Pregunta p, IN(p.objetivos) o where o.revision.id = :id order by p.codigo"),

		@NamedQuery(name = Queries.PREGUNTA_GET_ALL, query = "select distinct new co.edu.utp.gia.sms.dtos.PreguntaDTO(p.id, p.codigo, p.descripcion) from Pregunta p, IN(p.objetivos) o where o.revision.id = :id order by p.codigo"),

		@NamedQuery(name = Queries.PREGUNTA_GET_CANTIDAD, query = "select count(1)  from Pregunta p, IN(p.objetivos) o where o.revision.id = :id order by p.codigo"),

		@NamedQuery(name = Queries.PREGUNTA_OBJETIVO_GET_ALL, query = "select o from Pregunta p, IN(p.objetivos) o where p.id = :id"),

		@NamedQuery(name = Queries.TOPICO_PREGUNTA_GET_ALL, query = "select t from Topico t where t.pregunta.id = :id"),
//	@NamedQuery(name = Queries.TOPICO_REVISION_GET_ALL, query = "select t from Topico t where t.pregunta.revision.id = :id order by t.pregunta.codigo"),
		@NamedQuery(name = Queries.TOPICO_REVISION_GET_ALL, query = "select t from Topico t , IN(t.pregunta.objetivos) o where o.revision.id = :id order by t.pregunta.codigo"),
		@NamedQuery(name = Queries.TERMINO_GET_ALL, query = "select t from Termino t where t.revision.id = :id"),
		@NamedQuery(name = Queries.NOTA_GET_ALL, query = "select n from Nota n where n.referencia.id = :id"),

		@NamedQuery(name = Queries.OBJETIVO_GET_ALL, query = "select o from Objetivo o where o.revision.id = :id"),

		@NamedQuery(name = Queries.REVISION_GET_ALL, query = "select p from Revision p"),

		@NamedQuery(name = Queries.ESTADISTICA_YEAR, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( r.year, COUNT(1) ) from Referencia r where r.revision.id = :idRevision and r.filtro = 3 GROUP BY r.year ORDER BY r.year"),

		@NamedQuery(name = Queries.ESTADISTICA_TIPO, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( r.tipo, COUNT(1) ) from Referencia r where r.revision.id = :idRevision and r.filtro = 3 GROUP BY r.tipo ORDER BY r.tipo"),
		@NamedQuery(name = Queries.ESTADISTICA_CALIDAD_YEAR, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( r.year, AVG(r.totalEvaluacionCalidad) ) from Referencia r where r.revision.id = :idRevision and r.filtro = 3 GROUP BY r.year ORDER BY r.year"),
		@NamedQuery(name = Queries.ESTADISTICA_ATRIBUTO_CALIDAD_YEAR, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( r.year, COUNT(1) ) from Referencia r inner join r.evaluacionCalidad e where r.revision.id = :idRevision and r.filtro >= 3 and e.atributoCalidad.id = :idAtributoCalidad and e.evaluacionCualitativa = co.edu.utp.gia.sms.entidades.EvaluacionCualitativa.CUMPLE GROUP BY r.year ORDER BY r.year"),
		@NamedQuery(name = Queries.ESTADISTICA_REFERENCIA_TOPICO, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( CONCAT( t.pregunta.codigo,'-', t.descripcion ), COUNT(1) ) from Referencia r LEFT JOIN r.topicos t  where r.revision.id = :idRevision and r.filtro = 3 GROUP BY t.id ORDER BY t.descripcion"),
		@NamedQuery(name = Queries.ESTADISTICA_REFERENCIA_TOPICO_ATRIBUTO_CALIDAD, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( CONCAT( t.pregunta.codigo,'-', t.descripcion ), COUNT(1) ) from Referencia r LEFT JOIN r.topicos t inner join r.evaluacionCalidad e where r.revision.id = :idRevision and r.filtro >= 3 and e.atributoCalidad.id = :idAtributoCalidad and e.evaluacionCualitativa = co.edu.utp.gia.sms.entidades.EvaluacionCualitativa.CUMPLE GROUP BY t.id ORDER BY t.descripcion"),
		@NamedQuery(name = Queries.ESTADISTICA_REFERENCIA_TOPICO_PREGUNTA_ATRIBUTO_CALIDAD, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( t.descripcion , COUNT(1) ) from Referencia r LEFT JOIN r.topicos t inner join r.evaluacionCalidad e  where r.revision.id = :idRevision and r.filtro >= 3 and t.pregunta.codigo = :codigo and e.atributoCalidad.id = :idAtributoCalidad and e.evaluacionCualitativa = co.edu.utp.gia.sms.entidades.EvaluacionCualitativa.CUMPLE GROUP BY t.id ORDER BY t.descripcion"),

		@NamedQuery(name = Queries.ESTADISTICA_REFERENCIA_TOPICO_PREGUNTA, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( t.descripcion , COUNT(1) ) from Referencia r LEFT JOIN r.topicos t  where r.revision.id = :idRevision and r.filtro = 3 and t.pregunta.codigo = :codigo GROUP BY t.id ORDER BY t.descripcion"),
		@NamedQuery(name = Queries.ESTADISTICA_REFERENCIA_PREGUNTA, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( t.pregunta.codigo, COUNT(DISTINCT( r.id )) ) from Referencia r LEFT JOIN r.topicos t  where r.revision.id = :idRevision and r.filtro = 3 GROUP BY t.pregunta.id ORDER BY t.pregunta.codigo"),
		@NamedQuery(name = Queries.ESTADISTICA_REFERENCIA_TIPO_FUENTE, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( f.tipo , count(1)) from Metadato m,Fuente f where m.referencia.revision.id = :idRevision and m.referencia.filtro >= 3 and m.identifier = co.edu.utp.gia.sms.entidades.TipoMetadato.FUENTE and m.value = f.nombre group by f.tipo "),
		@NamedQuery(name = Queries.ESTADISTICA_PALABRAS_CLAVE, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( m.value , count(1)) from Metadato m where m.referencia.revision.id = :idRevision and m.referencia.filtro >= 3 and m.identifier = co.edu.utp.gia.sms.entidades.TipoMetadato.KEYWORD group by m.value having count(1) >= :minimo "),

		@NamedQuery(name = Queries.ESTADISTICA_REFERENCIA_TIPO_FUENTE_NOMBRE, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( f.nombre , count(1)) from Metadato m,Fuente f where m.referencia.revision.id = :idRevision and m.referencia.filtro >= 3 and m.identifier = co.edu.utp.gia.sms.entidades.TipoMetadato.FUENTE and m.value = f.nombre and f.tipo = :tipo group by f.nombre "),
		@NamedQuery(name = Queries.ESTADISTICA_REFERENCIA_PALABRAS_CLAVE, query = "select DISTINCT( m.referencia ) from Metadato m where m.referencia.revision.id = :idRevision and m.referencia.filtro >= 3 and m.identifier in :identifiers and m.value like :value"),

		@NamedQuery(name = Queries.METADATO_GET_ALL, query = "select m from Metadato m where m.referencia.id = :id and m.identifier = :tipo "),

		@NamedQuery(name = Queries.REFERENCIA_GET_ALL, query = "select new co.edu.utp.gia.sms.dtos.ReferenciaDTO( r ,  (:filtro + 0 )  ) from Referencia r where r.revision.id = :idRevision and MOD( r.filtro, (:filtro + 1 ) ) = :filtro ORDER BY r.spsid,r.nombre"),
		@NamedQuery(name = Queries.REFERENCIA_GET_ALL_DESTACADAS, query = "select new co.edu.utp.gia.sms.dtos.ReferenciaDTO( r ) from Referencia r where r.revision.id = :idRevision and r.relevancia is not null ORDER BY r.spsid,r.nombre"),
		@NamedQuery(name = Queries.REFERENCIA_GET_EVALUACION_ATRIBUTO_CALIDAD, query = "select new co.edu.utp.gia.sms.dtos.ReferenciaDTO( r ,  (:filtro + 0 )  ) from Referencia r inner join r.evaluacionCalidad e where r.revision.id = :idRevision and MOD( r.filtro, (:filtro + 1 ) ) = :filtro  and e.atributoCalidad.id = :idAtributoCalidad and e.evaluacionCualitativa = :valorEvaluacion  ORDER BY r.spsid,r.nombre"),
		@NamedQuery(name = Queries.REFERENCIA_GET_ATRIBUTO_CALIDAD, query = "select new co.edu.utp.gia.sms.dtos.ReferenciaDTO( r ,  (:filtro + 0 )  ) from Referencia r inner join r.evaluacionCalidad e where r.revision.id = :idRevision and MOD( r.filtro, (:filtro + 1 ) ) = :filtro  and e.atributoCalidad.id = :idAtributoCalidad  ORDER BY r.spsid,r.nombre"),

		@NamedQuery(name = Queries.REFERENCIA_METADATO_GET_ALL, query = "select m from Metadato m where m.referencia.id = :id"),
		@NamedQuery(name = Queries.REFERENCIA_CANTIDAD_RELACION_PREGUNTAS, query = "select count(distinct t.pregunta.id) from Referencia r LEFT JOIN r.topicos t  where r.id = :id"),
		@NamedQuery(name = Queries.REFERENCIA_NOTA_ETAPA_GET_ALL, query = "select n from Nota n where n.referencia.id = :id and n.etapa = :filtro"),
		@NamedQuery(name = Queries.REFERENCIA_SCIS, query = "select r.sci from Referencia r where r.revision.id = :idRevision and r.filtro >= 3"),

		@NamedQuery(name = Queries.EVALUACION_CALIDAD_GET_ALL, query = "select e from EvaluacionCalidad e where e.referencia.id = :id"),
		@NamedQuery(name = Queries.EVALUACION_TOTAL_CALIDAD, query = "select SUM(e.evaluacionCuantitativa) from EvaluacionCalidad e where e.referencia.id = :id"),

		@NamedQuery(name = Queries.ATRIBUTO_CALIDAD_GET_ALL, query = "select a from AtributoCalidad a where a.revision.id = :id"),
		@NamedQuery(name = Queries.ATRIBUTO_CALIDAD_GET_BY_DESCRIPTION_AND_REVISION, query = "select a from AtributoCalidad a where a.revision.id = :idRevision and a.descripcion = :descripcion")

})

public class Queries implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	private static final long serialVersionUID = -7643166662144090738L;

	/**
	 * Consulta que permite obtener las preguntas registradas en el sistema para una
	 * revision <br />
	 * <code>select p from Pregunta p where p.revision.id = :id </code>
	 * 
	 */
	public static final String PREGUNTA_GET_ALL = "Pregunta.getAll";

	// TODO Actualizar la documentación de estas constantes
	/**
	 * Consulta que permite obtener las preguntas registradas en el sistema para una
	 * revision <br />
	 * <code>select p from Pregunta p where p.revision.id = :id </code>
	 * 
	 */
	public static final String PREGUNTA_GET_CANTIDAD = "Pregunta.getCantidad";

	/**
	 * Consulta que permite obtener los topicos registradas en el sistema para una
	 * pregunta <br />
	 * <code>select t from Topico t where t.pregunta.id = :id </code>
	 * 
	 */

	public static final String TOPICO_PREGUNTA_GET_ALL = "Topico.PreguntaGetAll";

	/**
	 * Consulta que permite obtener los topicos registradas en el sistema para una
	 * pregunta <br />
	 * <code>select t from Topico t where t.pregunta.revision.id = :id  order by t.pregunta.codigo</code>
	 * 
	 */
	public static final String TOPICO_REVISION_GET_ALL = "Topico.RevisionGetAll";

	/**
	 * Consulta que permite obtener los terminos registradas en el sistema para una
	 * revision <br />
	 * <code>select t from Termino t where t.revision.id = :id </code>
	 * 
	 */
	public static final String TERMINO_GET_ALL = "Termino.getAll";

	/**
	 * Consulta que permite obtener los Objetivos registrados en el sistema para una
	 * revision <br />
	 * <code>select o from Objetivo o where o.revision.id = :id </code>
	 * 
	 */
	public static final String OBJETIVO_GET_ALL = "Objetivo.getAll";

	/**
	 * Consulta que permite obtener las revisiones registradas en el sistema <br />
	 * <code>select p from Revision p  </code>
	 * 
	 */
	public static final String REVISION_GET_ALL = "Revision.getAll";

	/**
	 * Consulta que permite obtener las preguntas registradas en el sistema para una
	 * revision <br />
	 * <code>select r from Referencia r where r.revision.id = :idRevision and r.filtro >= :filtro </code>
	 * 
	 */
	public static final String REFERENCIA_GET_ALL = "Referencia.getAll";
	/**
	 * Consulta que permite obtener las preguntas registradas en el sistema para una
	 * revision <br />
	 * <code>select new co.edu.utp.gia.sms.dtos.ReferenciaDTO( r ) from Referencia r where r.revision.id = :idRevision and r.relevancia is not null ORDER BY r.spsid,r.nombre</code>
	 * 
	 */
	public static final String REFERENCIA_GET_ALL_DESTACADAS = "Referencia.getAllDestacadas";

	/**
	 * Consulta que permite obtener las preguntas registradas en el sistema para una
	 * revision <br />
	 * <code>select r from Referencia r where r.revision.id = :idRevision and r.filtro >= :filtro </code>
	 * 
	 */

	public static final String REFERENCIA_GET_EVALUACION_ATRIBUTO_CALIDAD = "Referencia.getReferenciaByEvaluacionAtributoCalidad";
	/**
	 * Consulta que permite obtener las preguntas registradas en el sistema para una
	 * revision <br />
	 * <code>select r from Referencia r where r.revision.id = :idRevision and r.filtro >= :filtro </code>
	 * 
	 */

	public static final String REFERENCIA_GET_ATRIBUTO_CALIDAD = "Referencia.getReferenciaByAtributoCalidad";

	/**
	 * Consulta que permite obtener las preguntas registradas en el sistema para una
	 * revision <br />
	 *
	 * @param idRevision <code>select r.citas from Referencia r where r.revision.id = :idRevision and r.filtro >= 3 </code>
	 * 
	 */
	public static final String REFERENCIA_SCIS = "Referencia.getSCIs";

	/**
	 * Consulta que permite obtener las referecias por año <br />
	 * <code>select new co.edu.utp.gia.sms.dtos.DatoDTO( r.year, COUNT(1) ) from Referencia r where r.revision.id = :idRevision and r.filtro = 3 GROUP BY r.year ORDER BY r.year </code>
	 * 
	 */
	public static final String ESTADISTICA_YEAR = "Referencia.estadisticaYear";

	/**
	 * Consulta que permite obtener las referecias por año <br />
	 * <code>select new co.edu.utp.gia.sms.dtos.DatoDTO( r.year, COUNT(1) ) from Referencia r inner join r.evaluacionCalidad e where r.revision.id = :idRevision and r.filtro >= 3 and e.atributoCalidad.id = :idAtributoCalidad and e.evaluacionCualitativa = co.edu.utp.gia.sms.entidades.EvaluacionCualitativa.CUMPLE GROUP BY r.year ORDER BY r.year </code>
	 * 
	 */
	public static final String ESTADISTICA_ATRIBUTO_CALIDAD_YEAR = "Referencia.estadisticaAtributoCalidadYear";
	/**
	 * Consulta que permite obtener las referencias por tipo <br />
	 * <code>select new co.edu.utp.gia.sms.dtos.DatoDTO( r.tipo, COUNT(1) ) from Referencia r where r.revision.id = :idRevision and r.filtro = 3 GROUP BY r.year ORDER BY r.year </code>
	 * 
	 */
	public static final String ESTADISTICA_TIPO = "Referencia.estadisticaTipo";

	/**
	 * Consulta que permite obtener las referencias por tipo <br />
	 * <code>select new co.edu.utp.gia.sms.dtos.DatoDTO( r.year, SUM(r.totalEvaluacionCalidad) ) from Referencia r where r.revision.id = :idRevision and r.filtro = 3 GROUP BY r.year ORDER BY r.year </code>
	 * 
	 */
	public static final String ESTADISTICA_CALIDAD_YEAR = "Referencia.calidadYear";

	/**
	 * Consulta que permite obtener las referencias por tipo <br />
	 * <code>select new co.edu.utp.gia.sms.dtos.DatoDTO( t.descripcion, COUNT(1) ) from Referencia r LEFT JOIN r.topicos t  where r.revision.id = :idRevision and r.filtro = 3 GROUP BY t.id ORDER BY t.descripcion </code>
	 * 
	 */
	public static final String ESTADISTICA_REFERENCIA_TOPICO = "Referencia.referenciaTopico";
	/**
	 * Consulta que permite obtener las referencias por tipo <br />
	 * <code>select new co.edu.utp.gia.sms.dtos.DatoDTO( t.descripcion, COUNT(1) ) from Referencia r LEFT JOIN r.topicos t  where r.revision.id = :idRevision and r.filtro = 3 GROUP BY t.id ORDER BY t.descripcion </code>
	 * 
	 */
	public static final String ESTADISTICA_REFERENCIA_TOPICO_ATRIBUTO_CALIDAD = "Referencia.referenciaTopicoAtributoCaliad";

	/**
	 * Consulta que permite obtener las referencias por tipo <br />
	 * <code>select new co.edu.utp.gia.sms.dtos.DatoDTO( t.descripcion, COUNT(1) ) from Referencia r LEFT JOIN r.topicos t  where r.revision.id = :idRevision and r.filtro = 3 and t.pregunta.codigo = :codigo GROUP BY t.id ORDER BY t.descripcion </code>
	 * 
	 */
	public static final String ESTADISTICA_REFERENCIA_TOPICO_PREGUNTA = "Referencia.referenciaTopicoPregunta";

	/**
	 * Consulta que permite obtener las referencias por tipo <br />
	 * <code>select new co.edu.utp.gia.sms.dtos.DatoDTO( t.descripcion, COUNT(1) ) from Referencia r LEFT JOIN r.topicos t  where r.revision.id = :idRevision and r.filtro = 3 and t.pregunta.codigo = :codigo GROUP BY t.id ORDER BY t.descripcion </code>
	 * 
	 */
	public static final String ESTADISTICA_REFERENCIA_TOPICO_PREGUNTA_ATRIBUTO_CALIDAD = "Referencia.referenciaTopicoPreguntaAtributoCalidad";

	/**
	 * Consulta que permite obtener las referencias por tipo <br />
	 * <code>select new co.edu.utp.gia.sms.dtos.DatoDTO( t.pregunta, COUNT(1) ) from Referencia r LEFT JOIN r.topicos t  where r.revision.id = :idRevision and r.filtro = 3 GROUP BY t.pregunta.id ORDER BY t.pregunta.descripcion </code>
	 * 
	 */
	public static final String ESTADISTICA_REFERENCIA_PREGUNTA = "Referencia.referenciaPregunta";

	/**
	 * Consulta que permite obtener las referencias por tipo de fuente <br />
	 * <code>select new co.edu.utp.gia.sms.dtos.DatoDTO(f.tipo, count(1)) from Metadato m,Fuente f where m.referencia.filtro >= 3 and m.identifier = co.edu.utp.gia.sms.entidades.TipoMetadato.FUENTE and m.identifier = f.nombre group by f.tipo  </code>
	 * 
	 */
	public static final String ESTADISTICA_REFERENCIA_TIPO_FUENTE = "Referencia.referenciaTipoFuente";

	/**
	 * Consulta que permite obtener las referencias por tipo de fuente <br />
	 * <code>select new co.edu.utp.gia.sms.dtos.DatoDTO(f.tipo, count(1)) from Metadato m,Fuente f where m.referencia.filtro >= 3 and m.identifier = co.edu.utp.gia.sms.entidades.TipoMetadato.FUENTE and m.identifier = f.nombre group by f.tipo  </code>
	 * 
	 */
	public static final String ESTADISTICA_PALABRAS_CLAVE = "Referencia.palabrasClave";

	/**
	 * Consulta que permite obtener las referencias por tipo de fuente <br />
	 * <code>select DISTINCT( m.referencia ) from Metadato m where m.referencia.revision.id = :idRevision and m.referencia.filtro >= 3 and m.identifier in :identifiers and m.value like :value  </code>
	 *
	 */
	public static final String ESTADISTICA_REFERENCIA_PALABRAS_CLAVE = "Estadistica.referenciaPalabrasClave";

	/**
	 * Consulta que permite obtener las referencias por tipo de fuente <br />
	 * <code>select new co.edu.utp.gia.sms.dtos.DatoDTO( f.nombre , count(1)) from Metadato m,Fuente f where m.referencia.revision.id = :idRevision and m.referencia.filtro >= 3 and m.identifier = co.edu.utp.gia.sms.entidades.TipoMetadato.FUENTE and m.value = f.nombre and f.tipo = :tipo group by f.nombre  </code>
	 * 
	 */
	public static final String ESTADISTICA_REFERENCIA_TIPO_FUENTE_NOMBRE = "Referencia.referenciaTipoFuenteNombre";

	/**
	 * Consulta que permite obtener los metadatos de una referencia que pertenecen a
	 * un cierto tipo<br />
	 * <code>select m from Metadato m where m.referencia.id = :id and m.identifier = :tipo </code>
	 * 
	 */

	public static final String METADATO_GET_ALL = "Metadato.getAll";

	/**
	 * Consulta que permite obtener las revisiones de calidad registradas en el
	 * sistema para una referencia <br />
	 * <code>select e from EvaluacionCalidad e where e.referencia.id = :id </code>
	 * 
	 */
	public static final String EVALUACION_CALIDAD_GET_ALL = "EvaluacionCalidad.getAll";

	/**
	 * Consulta que permite obtener el total de las evaluaciones de calidad
	 * registradas en el sistema para una referencia <br />
	 * <code>select SUM(e.evaluacionCuantitativa) from EvaluacionCalidad e where e.referencia.id = :id </code>
	 * 
	 */
	public static final String EVALUACION_TOTAL_CALIDAD = "EvaluacionCalidad.totalCalidad";

	/**
	 * Consulta que permite obtener los atributos de calidad registradas en el
	 * sistema para una revision <br />
	 * <code>select a from AtributoCalidad a where a.revision.id = :id </code>
	 * 
	 */
	public static final String ATRIBUTO_CALIDAD_GET_ALL = "AtributoCalidad.getAll";

	/**
	 * Consulta que permite obtener un atributo de calidad revision basado en la
	 * descripcion <br />
	 *
	 * @param descripcion
	 * @param idRevision  <code>select a from AtributoCalidad a where a.revision.id = :idRevision and a.descripcion = :descripcion </code>
	 * 
	 */
	public static final String ATRIBUTO_CALIDAD_GET_BY_DESCRIPTION_AND_REVISION = "Revision.getAtributoCalidadByDescripcion";

	public static final String PREGUNTA_OBJETIVO_GET_ALL = "Pregunta.getObjetivos";

	public static final String NOTA_GET_ALL = "Nota.getAll";

	public static final String REFERENCIA_NOTA_ETAPA_GET_ALL = "Referencia.getNotasEpata.getAll";

	public static final String REFERENCIA_METADATO_GET_ALL = "Referencia.getMetadatos.getAll";

	public static final String REFERENCIA_CANTIDAD_RELACION_PREGUNTAS = "Referencia.getCantidadRelacionPreguntas";

}
