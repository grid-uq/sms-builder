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
	
	@NamedQuery(name = Queries.PREGUNTA_OBJETIVO_GET_ALL, query = "select o from Pregunta p, IN(p.objetivos) o where p.id = :id"),
	
	@NamedQuery(name = Queries.TOPICO_PREGUNTA_GET_ALL, query = "select t from Topico t where t.pregunta.id = :id"),
//	@NamedQuery(name = Queries.TOPICO_REVISION_GET_ALL, query = "select t from Topico t where t.pregunta.revision.id = :id order by t.pregunta.codigo"),
	@NamedQuery(name = Queries.TOPICO_REVISION_GET_ALL, query = "select t from Topico t , IN(t.pregunta.objetivos) o where o.revision.id = :id order by t.pregunta.codigo"),
	@NamedQuery(name = Queries.TERMINO_GET_ALL, query = "select t from Termino t where t.revision.id = :id"),
	@NamedQuery(name = Queries.OBJETIVO_GET_ALL, query = "select o from Objetivo o where o.revision.id = :id"),
	
	@NamedQuery(name = Queries.REVISION_GET_ALL, query = "select p from Revision p"),
	@NamedQuery(name = Queries.REFERENCIA_GET_ALL, query = "select new co.edu.utp.gia.sms.dtos.ReferenciaDTO( r ,  (:filtro + 0 )  ) from Referencia r where r.revision.id = :idRevision and MOD( r.filtro, (:filtro + 1 ) ) = :filtro ORDER BY r.nombre"),
	@NamedQuery(name = Queries.ESTADISTICA_YEAR, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( r.year, COUNT(1) ) from Referencia r where r.revision.id = :idRevision and r.filtro = 3 GROUP BY r.year ORDER BY r.year"),
	@NamedQuery(name = Queries.ESTADISTICA_TIPO, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( r.tipo, COUNT(1) ) from Referencia r where r.revision.id = :idRevision and r.filtro = 3 GROUP BY r.tipo ORDER BY r.tipo"),
	@NamedQuery(name = Queries.ESTADISTICA_CALIDAD_YEAR, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( r.year, AVG(r.totalEvaluacionCalidad) ) from Referencia r where r.revision.id = :idRevision and r.filtro = 3 GROUP BY r.year ORDER BY r.year"),
	@NamedQuery(name = Queries.ESTADISTICA_REFERENCIA_TOPICO, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( t.descripcion, COUNT(1) ) from Referencia r LEFT JOIN r.topicos t  where r.revision.id = :idRevision and r.filtro = 3 GROUP BY t.id ORDER BY t.descripcion"),
	@NamedQuery(name = Queries.ESTADISTICA_REFERENCIA_PREGUNTA, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( t.pregunta.codigo, COUNT(1) ) from Referencia r LEFT JOIN r.topicos t  where r.revision.id = :idRevision and r.filtro = 3 GROUP BY t.pregunta.id ORDER BY t.pregunta.codigo"),
	
	@NamedQuery(name = Queries.METADATO_GET_ALL, query = "select m from Metadato m where m.referencia.id = :id and m.identifier = :tipo "),

	@NamedQuery(name = Queries.EVALUACION_CALIDAD_GET_ALL, query = "select e from EvaluacionCalidad e where e.referencia.id = :id"),
	@NamedQuery(name = Queries.EVALUACION_TOTAL_CALIDAD, query = "select SUM(e.evaluacionCuantitativa) from EvaluacionCalidad e where e.referencia.id = :id"),

	@NamedQuery(name = Queries.ATRIBUTO_CALIDAD_GET_ALL, query = "select a from AtributoCalidad a where a.revision.id = :id")

})

public class Queries implements Serializable{
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	
	
	/**
	 * Consulta que permite obtener las preguntas registradas en el sistema para una
	 * revision <br />
	 * <code>select p from Pregunta p where p.revision.id = :id </code>
	 * 
	 */
	public static final String PREGUNTA_GET_ALL = "Pregunta.getAll";
	
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
		
	private static final long serialVersionUID = -7643166662144090738L;
	
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
	 * Consulta que permite obtener las referecias por año <br />
	 * <code>select new co.edu.utp.gia.sms.dtos.DatoDTO( r.year, COUNT(1) ) from Referencia r where r.revision.id = :idRevision and r.filtro = 3 GROUP BY r.year ORDER BY r.year </code>
	 * 
	 */
	public static final String ESTADISTICA_YEAR = "Referencia.estadisticaYear";
	
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
	 * <code>select new co.edu.utp.gia.sms.dtos.DatoDTO( t.pregunta, COUNT(1) ) from Referencia r LEFT JOIN r.topicos t  where r.revision.id = :idRevision and r.filtro = 3 GROUP BY t.pregunta.id ORDER BY t.pregunta.descripcion </code>
	 * 
	 */
	public static final String ESTADISTICA_REFERENCIA_PREGUNTA = "Referencia.referenciaPregunta";
	
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
	 * Consulta que permite obtener el total de las evaluaciones de calidad registradas en el
	 * sistema para una referencia <br />
	 * <code>select SUM(e.evaluacionCuantitativa) from EvaluacionCalidad e where e.referencia.id = :id </code>
	 * 
	 */
	public static final String EVALUACION_TOTAL_CALIDAD = "EvaluacionCalidad.totalCalidad";
	
	/**
	 * Consulta que permite obtener los atributos de calidad registradas en el sistema para una
	 * revision <br />
	 * <code>select a from AtributoCalidad a where a.revision.id = :id </code>
	 * 
	 */
	public static final String ATRIBUTO_CALIDAD_GET_ALL = "AtributoCalidad.getAll";


	public static final String PREGUNTA_OBJETIVO_GET_ALL = "Pregunta.getObjetivos";

}
