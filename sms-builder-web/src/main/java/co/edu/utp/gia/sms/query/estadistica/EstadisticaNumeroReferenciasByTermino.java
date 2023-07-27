package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.query.Queries;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.TypedQuery;

/**
 * Consulta que permite obtener el número de referencias por termino
 */
@Entity
//@NamedQuery(name = EstadisticaNumeroReferenciasByKeyword.NAME, query = EstadisticaNumeroReferenciasByKeyword.QUERY)
@NamedNativeQuery( name = EstadisticaNumeroReferenciasByTermino.NAME, query = EstadisticaNumeroReferenciasByTermino.QUERY,resultSetMapping = Queries.RESULT_MAPPING_DATODTO)
public class EstadisticaNumeroReferenciasByTermino extends Queries{
    public static final String NAME = "Estadistica.numeroReferenciasByTermino";
    //public static final String QUERY = "select new co.edu.utp.gia.sms.dtos.DatoDTO( t.descripcion , COUNT(1) ) from Termino t join t.sinonimos sinonimo inner join t.revision.pasoSeleccionado.referencias r inner join r.metadatos m where t.revision.id = :id AND ( m.identifier = co.edu.utp.gia.sms.entidades.TipoMetadato.KEYWORD OR m.identifier = co.edu.utp.gia.sms.entidades.TipoMetadato.TITLE OR m.identifier = co.edu.utp.gia.sms.entidades.TipoMetadato.ABSTRACT ) and ( m.value like concat('%',t.descripcion,'%') OR m.value like concat('%',sinonimo,'%')  ) GROUP BY t.id ORDER BY t.descripcion";
    public static final String QUERY = "select t.descripcion as etiqueta, COUNT( distinct(pr.REFERENCIAS_ID) ) as valor " +
            "from TERMINO t " +
            "inner join TERMINO_SINONIMO s ON t.id = Termino_ID " +
            "inner join REVISION r ON t.REVISION_ID = r.ID " +
            "inner join PASOPROCESO_REFERENCIA pr ON r.PASOSELECCIONADO_ID = pr.PASOPROCESO_ID " +
            "inner join METADATO m on pr.REFERENCIAS_ID = m.REFERENCIA_ID " +
            "where t.REVISION_ID = ?1 " +
            "and m.identifier IN ('KEYWORD','TITLE', 'ABSTRACT' ) " +
            "and ( m.value like concat('%',t.descripcion,'%') OR m.value like concat('%',s.sinonimo,'%')  ) GROUP BY t.id ORDER BY t.descripcion";


    /**
     * Consulta que permite obtener el número de referencias por termino
     *
     * @param entityManager Para la ejecución de la consulta
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @return TypedQuery< DatoDTO > que representa la consulta
     */
    public static TypedQuery<DatoDTO> createQuery(EntityManager entityManager, Integer id){
        return entityManager.createNamedQuery(NAME,DatoDTO.class)
                .setParameter(1,id);
    }
}
