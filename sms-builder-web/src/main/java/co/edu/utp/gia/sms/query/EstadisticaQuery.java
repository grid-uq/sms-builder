package co.edu.utp.gia.sms.query;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.entidades.CadenaBusqueda;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;


@Entity

@NamedQuery(name = EstadisticaQuery.ESTADISTICA_YEAR, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( r.year, COUNT(1) ) from Revision revision inner join revision.pasoSeleccionado.referencias r where revision.id = :idRevision GROUP BY r.year ORDER BY r.year")
@NamedQuery(name = EstadisticaQuery.ESTADISTICA_TIPO, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( r.tipo, COUNT(1) ) from Revision revision inner join revision.pasoSeleccionado.referencias r where revision.id = :idRevision GROUP BY r.tipo ORDER BY r.tipo")
@NamedQuery(name = EstadisticaQuery.ESTADISTICA_CALIDAD_YEAR, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( r.year, AVG(r.totalEvaluacionCalidad) ) from Revision revision inner join revision.pasoSeleccionado.referencias r where revision.id = :idRevision GROUP BY r.year ORDER BY r.year")
@NamedQuery(name = EstadisticaQuery.ESTADISTICA_ATRIBUTO_CALIDAD_CALIDAD_YEAR, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( r.year, AVG(e.evaluacionCuantitativa) ) from Revision revision inner join revision.pasoSeleccionado.referencias r inner join r.evaluacionCalidad e where revision.id = :idRevision and e.atributoCalidad.id = :idAtributoCalidad GROUP BY r.year ORDER BY r.year")
@NamedQuery(name = EstadisticaQuery.ESTADISTICA_ATRIBUTO_CALIDAD_YEAR, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( r.year, COUNT(1) ) from Revision revision inner join revision.pasoSeleccionado.referencias r inner join r.evaluacionCalidad e where revision.id = :idRevision and e.atributoCalidad.id = :idAtributoCalidad and e.evaluacionCualitativa = co.edu.utp.gia.sms.entidades.EvaluacionCualitativa.CUMPLE GROUP BY r.year ORDER BY r.year")
@NamedQuery(name = EstadisticaQuery.ESTADISTICA_REFERENCIA_TOPICO, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( CONCAT( t.pregunta.codigo,'-', t.descripcion ), COUNT(1) ) from Revision revision inner join revision.pasoSeleccionado.referencias r LEFT JOIN r.topicos t  where revision.id = :idRevision GROUP BY t.id ORDER BY t.pregunta.id, t.descripcion")
@NamedQuery(name = EstadisticaQuery.ESTADISTICA_REFERENCIA_TOPICO_ATRIBUTO_CALIDAD, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( CONCAT( t.pregunta.codigo,'-', t.descripcion ), COUNT(1) ) from Revision revision inner join revision.pasoSeleccionado.referencias r LEFT JOIN r.topicos t inner join r.evaluacionCalidad e where revision.id = :idRevision and e.atributoCalidad.id = :idAtributoCalidad and e.evaluacionCualitativa = co.edu.utp.gia.sms.entidades.EvaluacionCualitativa.CUMPLE GROUP BY t.id ORDER BY t.pregunta.id,t.descripcion")
@NamedQuery(name = EstadisticaQuery.ESTADISTICA_REFERENCIA_TOPICO_PREGUNTA_ATRIBUTO_CALIDAD, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( t.descripcion , COUNT(1) ) from Revision revision inner join revision.pasoSeleccionado.referencias r LEFT JOIN r.topicos t inner join r.evaluacionCalidad e where r.revision.id = :idRevision and t.pregunta.codigo = :codigo and e.atributoCalidad.id = :idAtributoCalidad and e.evaluacionCualitativa = co.edu.utp.gia.sms.entidades.EvaluacionCualitativa.CUMPLE GROUP BY t.id ORDER BY t.pregunta.id,t.descripcion")
@NamedQuery(name = EstadisticaQuery.ESTADISTICA_REFERENCIA_TOPICO_PREGUNTA, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( t.descripcion , COUNT(1) ) from Revision revision inner join revision.pasoSeleccionado.referencias r LEFT JOIN r.topicos t  where revision.id = :idRevision and t.pregunta.codigo = :codigo GROUP BY t.id ORDER BY t.pregunta.id,t.descripcion")
@NamedQuery(name = EstadisticaQuery.ESTADISTICA_REFERENCIA_PREGUNTA, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( t.pregunta.codigo, COUNT(DISTINCT( r.id )) ) from Revision revision inner join revision.pasoSeleccionado.referencias r LEFT JOIN r.topicos t where revision.id = :idRevision GROUP BY t.pregunta.id ORDER BY t.pregunta.codigo")
@NamedQuery(name = EstadisticaQuery.ESTADISTICA_REFERENCIA_TIPO_FUENTE, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( f.tipo , count(1)) from Revision revision inner join revision.pasoSeleccionado.referencias r inner join r.metadatos m,Fuente f where revision.id = :id and m.identifier = co.edu.utp.gia.sms.entidades.TipoMetadato.FUENTE and m.value = f.nombre group by f.tipo ")
@NamedQuery(name = EstadisticaQuery.PalabrasClave.NAME, query = EstadisticaQuery.PalabrasClave.QUERY)
@NamedQuery(name = EstadisticaQuery.ESTADISTICA_REFERENCIA_TIPO_FUENTE_NOMBRE, query = "select new co.edu.utp.gia.sms.dtos.DatoDTO( f.nombre , count(1)) from Revision revision inner join revision.pasoSeleccionado.referencias r LEFT JOIN r.metadatos m,Fuente f where revision.id = :idRevision and m.identifier = co.edu.utp.gia.sms.entidades.TipoMetadato.FUENTE and m.value = f.nombre and f.tipo = :tipo group by f.nombre ")
@NamedQuery(name = EstadisticaQuery.ESTADISTICA_REFERENCIA_PALABRAS_CLAVE, query = "select DISTINCT( m.referencia ) from Revision revision inner join revision.pasoSeleccionado.referencias r LEFT JOIN r.metadatos m where revision.id = :idRevision and m.identifier in :identifiers and UPPER(m.value) like UPPER(:value) ")
public class EstadisticaQuery extends Queries{
    /**
     * Consulta que permite obtener las referecias por año <br />
     * <code>select new co.edu.utp.gia.sms.dtos.DatoDTO( r.year, COUNT(1) ) from Referencia r where r.revision.id = :idRevision and r.filtro = 3 GROUP BY r.year ORDER BY r.year </code>
     */
    public static final String ESTADISTICA_YEAR = "Referencia.estadisticaYear";
    /**
     * Consulta que permite obtener las referecias por año <br />
     * <code>select new co.edu.utp.gia.sms.dtos.DatoDTO( r.year, COUNT(1) ) from Referencia r inner join r.evaluacionCalidad e where r.revision.id = :idRevision and r.filtro >= 3 and e.atributoCalidad.id = :idAtributoCalidad and e.evaluacionCualitativa = co.edu.utp.gia.sms.entidades.EvaluacionCualitativa.CUMPLE GROUP BY r.year ORDER BY r.year </code>
     */
    public static final String ESTADISTICA_ATRIBUTO_CALIDAD_YEAR = "Referencia.estadisticaAtributoCalidadYear";
    /**
     * Consulta que permite obtener las referencias por tipo <br />
     * <code>select new co.edu.utp.gia.sms.dtos.DatoDTO( r.tipo, COUNT(1) ) from Referencia r where r.revision.id = :idRevision and r.filtro = 3 GROUP BY r.year ORDER BY r.year </code>
     */
    public static final String ESTADISTICA_TIPO = "Referencia.estadisticaTipo";
    /**
     * Consulta que permite obtener las referencias por tipo <br />
     * <code>select new co.edu.utp.gia.sms.dtos.DatoDTO( r.year, SUM(r.totalEvaluacionCalidad) ) from Referencia r where r.revision.id = :idRevision and r.filtro = 3 GROUP BY r.year ORDER BY r.year </code>
     */
    public static final String ESTADISTICA_CALIDAD_YEAR = "Referencia.calidadYear";
    /**
     * Consulta el promedio de la evaluación cualitativa de un atributo de calidad por año <br />
     * @param idRevision Id de la revision
     * @param idAtributoCalidad Id del atributo de calidad evaluado
     *                          <br />
     * <code>select new co.edu.utp.gia.sms.dtos.DatoDTO( e.referencia.year, AVG(e.evaluacionCuantitativa) ) from EvaluacionCalidad e where e.referencia.revision.id = :idRevision and e.referencia.filtro >= 3 and e.atributoCalidad.id = :idAtributoCalidad GROUP BY e.referencia.year ORDER BY e.referencia.year </code>
     */
    public static final String ESTADISTICA_ATRIBUTO_CALIDAD_CALIDAD_YEAR = "Referencia.atributoCalidadCalidadYear"; /**
     * Consulta que permite obtener las referencias por tipo <br />
     * <code>select new co.edu.utp.gia.sms.dtos.DatoDTO( t.descripcion, COUNT(1) ) from Referencia r LEFT JOIN r.topicos t  where r.revision.id = :idRevision and r.filtro = 3 GROUP BY t.id ORDER BY t.descripcion </code>
     */
    public static final String ESTADISTICA_REFERENCIA_TOPICO = "Referencia.referenciaTopico";
    /**
     * Consulta que permite obtener las referencias por tipo <br />
     * <code>select new co.edu.utp.gia.sms.dtos.DatoDTO( t.descripcion, COUNT(1) ) from Referencia r LEFT JOIN r.topicos t  where r.revision.id = :idRevision and r.filtro = 3 GROUP BY t.id ORDER BY t.descripcion </code>
     */
    public static final String ESTADISTICA_REFERENCIA_TOPICO_ATRIBUTO_CALIDAD = "Referencia.referenciaTopicoAtributoCaliad";
    /**
     * Consulta que permite obtener las referencias por tipo <br />
     * <code>select new co.edu.utp.gia.sms.dtos.DatoDTO( t.descripcion, COUNT(1) ) from Referencia r LEFT JOIN r.topicos t  where r.revision.id = :idRevision and r.filtro = 3 and t.pregunta.codigo = :codigo GROUP BY t.id ORDER BY t.descripcion </code>
     */
    public static final String ESTADISTICA_REFERENCIA_TOPICO_PREGUNTA = "Referencia.referenciaTopicoPregunta";
    /**
     * Consulta que permite obtener las referencias por tipo <br />
     * <code>select new co.edu.utp.gia.sms.dtos.DatoDTO( t.descripcion, COUNT(1) ) from Referencia r LEFT JOIN r.topicos t  where r.revision.id = :idRevision and r.filtro = 3 and t.pregunta.codigo = :codigo GROUP BY t.id ORDER BY t.descripcion </code>
     */
    public static final String ESTADISTICA_REFERENCIA_TOPICO_PREGUNTA_ATRIBUTO_CALIDAD = "Referencia.referenciaTopicoPreguntaAtributoCalidad";
    /**
     * Consulta que permite obtener las referencias por tipo <br />
     * <code>select new co.edu.utp.gia.sms.dtos.DatoDTO( t.pregunta, COUNT(1) ) from Referencia r LEFT JOIN r.topicos t  where r.revision.id = :idRevision and r.filtro = 3 GROUP BY t.pregunta.id ORDER BY t.pregunta.descripcion </code>
     */
    public static final String ESTADISTICA_REFERENCIA_PREGUNTA = "Referencia.referenciaPregunta";
    /**
     * Consulta que permite obtener las referencias por tipo de fuente <br />
     * @param idPaso
     * <code>select new co.edu.utp.gia.sms.dtos.DatoDTO( f.tipo , count(1)) from PasoProceso p inner join p.referencias r inner join r.metadatos m,Fuente f where p.id = :idPaso and m.identifier = co.edu.utp.gia.sms.entidades.TipoMetadato.FUENTE and m.value = f.nombre group by f.tipo  </code>
     */
    public static final String ESTADISTICA_REFERENCIA_TIPO_FUENTE = "Referencia.referenciaTipoFuente";

    public static class PalabrasClave{
        public static final String NAME = "Referencia.palabrasClave";
        public static final String QUERY = "select new co.edu.utp.gia.sms.dtos.DatoDTO( m.value , count(1)) from Revision revision inner join revision.pasoSeleccionado.referencias r inner join r.metadatos m where revision.id = :id and m.identifier = co.edu.utp.gia.sms.entidades.TipoMetadato.KEYWORD group by m.value having count(1) >= :minimo ";

        /**
         * Consulta que permite obtener las palabras claves y su número de apariciones en las referencias seleccionadas <br />
         * <code>select new co.edu.utp.gia.sms.dtos.DatoDTO(f.tipo, count(1)) from Metadato m,Fuente f where m.referencia.filtro >= 3 and m.identifier = co.edu.utp.gia.sms.entidades.TipoMetadato.FUENTE and m.identifier = f.nombre group by f.tipo  </code>
         *
         * @param entityManager Para la ejecución de la consulta
         * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
         * @param minimo Cantidad minima de apariciones que debe tener una palabra clave para ser considerada
         * @return TypedQuery< DatoDTO > que representa la consulta de las {@link DatoDTO}
         */
        public static TypedQuery<DatoDTO> createQuery(EntityManager entityManager, Integer id, Integer minimo){
            return entityManager.createNamedQuery(NAME,DatoDTO.class)
                    .setParameter("id",id)
                    .setParameter("minimo",minimo);
        }
    }
    /**
     * Consulta que permite obtener las referencias por tipo de fuente <br />
     * <code>select DISTINCT( m.referencia ) from Metadato m where m.referencia.revision.id = :idRevision and m.referencia.filtro >= 3 and m.identifier in :identifiers and m.value like :value  </code>
     */
    public static final String ESTADISTICA_REFERENCIA_PALABRAS_CLAVE = "Estadistica.referenciaPalabrasClave";
    /**
     * Consulta que permite obtener las referencias por tipo de fuente <br />
     * <code>select new co.edu.utp.gia.sms.dtos.DatoDTO( f.nombre , count(1)) from Metadato m,Fuente f where m.referencia.revision.id = :idRevision and m.referencia.filtro >= 3 and m.identifier = co.edu.utp.gia.sms.entidades.TipoMetadato.FUENTE and m.value = f.nombre and f.tipo = :tipo group by f.nombre  </code>
     */
    public static final String ESTADISTICA_REFERENCIA_TIPO_FUENTE_NOMBRE = "Referencia.referenciaTipoFuenteNombre";

}
