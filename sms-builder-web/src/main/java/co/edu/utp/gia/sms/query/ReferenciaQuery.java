package co.edu.utp.gia.sms.query;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name = ReferenciaQuery.REFERENCIA_GET_ALL, query = "select new co.edu.utp.gia.sms.dtos.ReferenciaDTO( r ,  (:filtro + 0 )  ) from Referencia r where r.revision.id = :idRevision and MOD( r.filtro, (:filtro + 1 ) ) = :filtro ORDER BY r.spsid,r.nombre")
@NamedQuery(name = ReferenciaQuery.REFERENCIA_GET_ALL_DESTACADAS, query = "select new co.edu.utp.gia.sms.dtos.ReferenciaDTO( r ) from Referencia r where r.revision.id = :idRevision and r.relevancia is not null ORDER BY r.spsid,r.nombre")
@NamedQuery(name = ReferenciaQuery.REFERENCIA_GET_EVALUACION_ATRIBUTO_CALIDAD, query = "select new co.edu.utp.gia.sms.dtos.ReferenciaDTO( r ,  (:filtro + 0 )  ) from Referencia r inner join r.evaluacionCalidad e where r.revision.id = :idRevision and MOD( r.filtro, (:filtro + 1 ) ) = :filtro  and e.atributoCalidad.id = :idAtributoCalidad and e.evaluacionCualitativa = :valorEvaluacion  ORDER BY r.spsid,r.nombre")
@NamedQuery(name = ReferenciaQuery.REFERENCIA_GET_ATRIBUTO_CALIDAD, query = "select new co.edu.utp.gia.sms.dtos.ReferenciaDTO( r ,  (:filtro + 0 )  ) from Referencia r inner join r.evaluacionCalidad e where r.revision.id = :idRevision and MOD( r.filtro, (:filtro + 1 ) ) = :filtro  and e.atributoCalidad.id = :idAtributoCalidad  ORDER BY r.spsid,r.nombre")
@NamedQuery(name = ReferenciaQuery.REFERENCIA_METADATO_GET_ALL, query = "select m from Metadato m where m.referencia.id = :id")
@NamedQuery(name = ReferenciaQuery.REFERENCIA_CANTIDAD_RELACION_PREGUNTAS, query = "select count(distinct t.pregunta.id) from Referencia r LEFT JOIN r.topicos t  where r.id = :id")
@NamedQuery(name = ReferenciaQuery.REFERENCIA_NOTA_ETAPA_GET_ALL, query = "select n from Nota n where n.referencia.id = :id and n.etapa = :filtro")
@NamedQuery(name = ReferenciaQuery.REFERENCIA_SCIS, query = "select r.sci from Referencia r where r.revision.id = :idRevision and r.filtro >= 3")
public class ReferenciaQuery extends Queries{
    /**
     * Consulta que permite obtener las preguntas registradas en el sistema para una
     * revision <br />
     * <code>select r from Referencia r where r.revision.id = :idRevision and r.filtro >= :filtro </code>
     */
    public static final String REFERENCIA_GET_ALL = "Referencia.getAll";
    /**
     * Consulta que permite obtener las preguntas registradas en el sistema para una
     * revision <br />
     * <code>select new co.edu.utp.gia.sms.dtos.ReferenciaDTO( r ) from Referencia r where r.revision.id = :idRevision and r.relevancia is not null ORDER BY r.spsid,r.nombre</code>
     */
    public static final String REFERENCIA_GET_ALL_DESTACADAS = "Referencia.getAllDestacadas";
    /**
     * Consulta que permite obtener las preguntas registradas en el sistema para una
     * revision <br />
     * <code>select r from Referencia r where r.revision.id = :idRevision and r.filtro >= :filtro </code>
     */

    public static final String REFERENCIA_GET_EVALUACION_ATRIBUTO_CALIDAD = "Referencia.getReferenciaByEvaluacionAtributoCalidad";
    /**
     * Consulta que permite obtener las preguntas registradas en el sistema para una
     * revision <br />
     * <code>select r from Referencia r where r.revision.id = :idRevision and r.filtro >= :filtro </code>
     */

    public static final String REFERENCIA_GET_ATRIBUTO_CALIDAD = "Referencia.getReferenciaByAtributoCalidad";
    /**
     * Consulta que permite obtener las preguntas registradas en el sistema para una
     * revision <br />
     *
     * @param idRevision <code>select r.citas from Referencia r where r.revision.id = :idRevision and r.filtro >= 3 </code>
     */
    public static final String REFERENCIA_SCIS = "Referencia.getSCIs";
    public static final String REFERENCIA_NOTA_ETAPA_GET_ALL = "Referencia.getNotasEpata.getAll";
    public static final String REFERENCIA_METADATO_GET_ALL = "Referencia.getMetadatos.getAll";
    public static final String REFERENCIA_CANTIDAD_RELACION_PREGUNTAS = "Referencia.getCantidadRelacionPreguntas";

}
