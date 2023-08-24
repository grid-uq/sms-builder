package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.query.referencia.ReferenciaGetMetadatos;
import co.edu.utp.gia.sms.query.referencia.ReferenciaGetMetadatosByTipo;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
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
public class MetadatoServices extends AbstractGenericService<Metadato, String> {

    @Inject
    private ReferenciaEJB referenciaEJB;

    public MetadatoServices() {
        super();
    }

    public MetadatoServices(Referencia referencia) {
        super(referencia::getMetadatos);
    }

    @Override
    public Metadato save(Metadato entidad) {
        return save(entidad.getReferencia()::getMetadatos,entidad);
    }

    @Override
    public void update(Metadato entidad) {
        super.update(entidad.getReferencia()::getMetadatos,entidad);
    }

    @Override
    public void delete(Metadato entidad) {
        super.delete(entidad.getReferencia()::getMetadatos,entidad.getId());
    }

    public void delete(String idReferencia,String idMetadato) {
        var referencia = referenciaEJB.obtener(idReferencia);
        delete(referencia,idMetadato);
    }

    public void delete(Referencia referencia, String idMetado) {
        delete(referencia::getMetadatos,idMetado);
    }

    public Optional<Metadato> find(String idReferencia,String idMetadato) {
        var referencia = referenciaEJB.obtener(idReferencia);
        return find(referencia,idMetadato);
    }
    public Optional<Metadato> find(Referencia referencia, String idMetado) {
        return super.find(referencia::getMetadatos,idMetado);
    }

    public Metadato findOrThrow(String idReferencia,String idMetadato) {
        var referencia = referenciaEJB.obtener(idReferencia);
        return findOrThrow(referencia,idMetadato);
    }

    public Metadato findOrThrow(Referencia referencia, String idMetado) {
        return super.findOrThrow(referencia::getMetadatos,idMetado);
    }

    @Override
    public Collection<Metadato> get() {
        return super.get();
    }

    /**
     * Consulta que permite obtener los metadatos de una referencia que pertenecen a un cierto tipo
     *
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @param tipo Tipo de metadato que se desea obtener
     * @return List< Metadato > de la referencia que son del tipo indicado
     */
    public List<Metadato> obtenerListMetadatoByTipo(Integer id, TipoMetadato tipo) {
        return ReferenciaGetMetadatosByTipo.createQuery(id,tipo).toList();
    }

    /**
     * Consulta que permite obtener los metadatos de una referencia que pertenecen a un cierto tipo en forma de cadena separados por ;
     *
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @param tipo Tipo de metadato que se desea obtener
     * @return String de los metadatos de la referencia que pertenecen al tipo indicado en forma de cadena separados por ;
     */
    public String obtenerStringMetadatoByTipo(Integer id, TipoMetadato tipo) {
        return ReferenciaGetMetadatosByTipo.createQuery(id,tipo)
                .map(Metadato::getValue)
                .collect(Collectors.joining(" ; "));
    }

    /**
     * Consulta que permite obtener los Metadatos de una Referencia
     *
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @return List<Metadato> listado de los metadatos de la referencia del id dado
     */
    public List<Metadato> obtenerMetadatos(Integer id) {
        return ReferenciaGetMetadatos.createQuery(id).toList();
    }

}
