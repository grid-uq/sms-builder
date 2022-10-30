package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.query.referencia.ReferenciaGetMetadatos;
import co.edu.utp.gia.sms.query.referencia.ReferenciaGetMetadatosByTipo;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Clase de negocio encargada de implementar las funciones correspondientes a la
 * gestion del {@link Metadato}.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
@Stateless
public class MetadatoEJB extends AbstractEJB<Metadato, Integer> {

    public MetadatoEJB() {
        super(Metadato.class);
    }

    /**
     * Consulta que permite obtener los metadatos de una referencia que pertenecen a un cierto tipo
     *
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @param tipo Tipo de metadato que se desea obtener
     * @return List< Metadato > de la referencia que son del tipo indicado
     */
    public List<Metadato> obtenerListMetadatoByTipo(Integer id, TipoMetadato tipo) {
        return ReferenciaGetMetadatosByTipo.createQuery(entityManager,id,tipo).getResultList();
    }

    public String obtenerStringMetadatoByTipo(Integer idReferencia, TipoMetadato tipoMetadato) {
        List<Metadato> listMetadatos = obtenerListMetadatoByTipo(idReferencia, tipoMetadato);


        return listMetadatos.stream().map(Metadato::getValue).collect(Collectors.joining(" ; "));
    }

    /**
     * Consulta que permite obtener los Metadatos de una Referencia
     *
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @return List<Metadato> listado de los metadatos de la referencia del id dado
     */
    public List<Metadato> obtenerMetadatos(Integer id) {
        return ReferenciaGetMetadatos.createQuery(entityManager,id).getResultList();
    }

}
