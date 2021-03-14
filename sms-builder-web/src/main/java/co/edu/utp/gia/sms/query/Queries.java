package co.edu.utp.gia.sms.query;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity

//	@NamedQuery(name = Queries.PREGUNTA_GET_ALL, query = "select p from Pregunta p where p.revision.id = :id"),

//	@NamedQuery(name = Queries.PREGUNTA_GET_ALL, query = "select distinct p from Pregunta p, IN(p.objetivos) o where o.revision.id = :id order by p.codigo"),

@NamedQuery(name = Queries.PREGUNTA_GET_ALL, query = "select distinct new co.edu.utp.gia.sms.dtos.PreguntaDTO(p.id, p.codigo, p.descripcion) from Pregunta p, IN(p.objetivos) o where o.revision.id = :id order by p.codigo")

@NamedQuery(name = Queries.PREGUNTA_GET_CANTIDAD, query = "select count(1)  from Pregunta p, IN(p.objetivos) o where o.revision.id = :id order by p.codigo")

@NamedQuery(name = Queries.PREGUNTA_OBJETIVO_GET_ALL, query = "select o from Pregunta p, IN(p.objetivos) o where p.id = :id")

@NamedQuery(name = Queries.TOPICO_PREGUNTA_GET_ALL, query = "select t from Topico t where t.pregunta.id = :id")
//	@NamedQuery(name = Queries.TOPICO_REVISION_GET_ALL, query = "select t from Topico t where t.pregunta.revision.id = :id order by t.pregunta.codigo"),
@NamedQuery(name = Queries.TOPICO_REVISION_GET_ALL, query = "select t from Topico t , IN(t.pregunta.objetivos) o where o.revision.id = :id order by t.pregunta.codigo")
@NamedQuery(name = Queries.TERMINO_GET_ALL, query = "select t from Termino t where t.revision.id = :id")
@NamedQuery(name = Queries.NOTA_GET_ALL, query = "select n from Nota n where n.referencia.id = :id")

@NamedQuery(name = Queries.OBJETIVO_GET_ALL, query = "select o from Objetivo o where o.revision.id = :id")


@NamedQuery(name = Queries.METADATO_GET_ALL, query = "select m from Metadato m where m.referencia.id = :id and m.identifier = :tipo ")




@NamedQuery(name = Queries.EVALUACION_CALIDAD_GET_ALL, query = "select e from EvaluacionCalidad e where e.referencia.id = :id")
@NamedQuery(name = Queries.EVALUACION_TOTAL_CALIDAD, query = "select SUM(e.evaluacionCuantitativa) from EvaluacionCalidad e where e.referencia.id = :id")

@NamedQuery(name = Queries.ATRIBUTO_CALIDAD_GET_ALL, query = "select a from AtributoCalidad a where a.revision.id = :id")
@NamedQuery(name = Queries.ATRIBUTO_CALIDAD_GET_BY_DESCRIPTION_AND_REVISION, query = "select a from AtributoCalidad a where a.revision.id = :idRevision and a.descripcion = :descripcion")


public class Queries implements Serializable {

    /**
     * Consulta que permite obtener las preguntas registradas en el sistema para una
     * revision <br />
     * <code>select p from Pregunta p where p.revision.id = :id </code>
     */
    public static final String PREGUNTA_GET_ALL = "Pregunta.getAll";
    /**
     * Consulta que permite obtener las preguntas registradas en el sistema para una
     * revision <br />
     * <code>select p from Pregunta p where p.revision.id = :id </code>
     */
    public static final String PREGUNTA_GET_CANTIDAD = "Pregunta.getCantidad";
    /**
     * Consulta que permite obtener los topicos registradas en el sistema para una
     * pregunta <br />
     * <code>select t from Topico t where t.pregunta.id = :id </code>
     */

    public static final String TOPICO_PREGUNTA_GET_ALL = "Topico.PreguntaGetAll";

    // TODO Actualizar la documentación de estas constantes
    /**
     * Consulta que permite obtener los topicos registradas en el sistema para una
     * pregunta <br />
     * <code>select t from Topico t where t.pregunta.revision.id = :id  order by t.pregunta.codigo</code>
     */
    public static final String TOPICO_REVISION_GET_ALL = "Topico.RevisionGetAll";
    /**
     * Consulta que permite obtener los terminos registradas en el sistema para una
     * revision <br />
     * <code>select t from Termino t where t.revision.id = :id </code>
     */
    public static final String TERMINO_GET_ALL = "Termino.getAll";
    /**
     * Consulta que permite obtener los Objetivos registrados en el sistema para una
     * revision <br />
     * <code>select o from Objetivo o where o.revision.id = :id </code>
     */
    public static final String OBJETIVO_GET_ALL = "Objetivo.getAll";

    /**
     * Consulta que permite obtener los metadatos de una referencia que pertenecen a
     * un cierto tipo<br />
     * <code>select m from Metadato m where m.referencia.id = :id and m.identifier = :tipo </code>
     */

    public static final String METADATO_GET_ALL = "Metadato.getAll";
    /**
     * Consulta que permite obtener las revisiones de calidad registradas en el
     * sistema para una referencia <br />
     * <code>select e from EvaluacionCalidad e where e.referencia.id = :id </code>
     */
    public static final String EVALUACION_CALIDAD_GET_ALL = "EvaluacionCalidad.getAll";
    /**
     * Consulta que permite obtener el total de las evaluaciones de calidad
     * registradas en el sistema para una referencia <br />
     * <code>select SUM(e.evaluacionCuantitativa) from EvaluacionCalidad e where e.referencia.id = :id </code>
     */
    public static final String EVALUACION_TOTAL_CALIDAD = "EvaluacionCalidad.totalCalidad";
    /**
     * Consulta que permite obtener los atributos de calidad registradas en el
     * sistema para una revision <br />
     * <code>select a from AtributoCalidad a where a.revision.id = :id </code>
     */
    public static final String ATRIBUTO_CALIDAD_GET_ALL = "AtributoCalidad.getAll";
    /**
     * Consulta que permite obtener un atributo de calidad revision basado en la
     * descripcion <br />
     *
     * @param descripcion
     * @param idRevision  <code>select a from AtributoCalidad a where a.revision.id = :idRevision and a.descripcion = :descripcion </code>
     */
    public static final String ATRIBUTO_CALIDAD_GET_BY_DESCRIPTION_AND_REVISION = "Revision.getAtributoCalidadByDescripcion";
    public static final String PREGUNTA_OBJETIVO_GET_ALL = "Pregunta.getObjetivos";
    public static final String NOTA_GET_ALL = "Nota.getAll";



    private static final long serialVersionUID = -7643166662144090738L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

}
