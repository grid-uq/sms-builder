package co.edu.utp.gia.sms.api;

import co.edu.utp.gia.sms.entidades.Entidad;
import co.edu.utp.gia.sms.exceptions.ExceptionMessage;
import co.edu.utp.gia.sms.negocio.AbstractGenericService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;

import java.io.Serializable;
import java.net.URI;

/**
 * Clase abstracta que define los elementos de logica generales asociados al
 * CRUD de una entidad
 */
public abstract class AbstractGenericApi<E extends Entidad<TipoId>, TipoId> implements Serializable {

    /**
     * Instancia que perite obtener los mensajes de las excepciones generadas.
     */
//    @Inject
//    @Getter
    protected ExceptionMessage exceptionMessage;

    protected AbstractGenericService<E,TipoId> service;

    public AbstractGenericApi() {
        exceptionMessage = ConfigFactory.create(ExceptionMessage.class);
    }

    public AbstractGenericApi(AbstractGenericService<E, TipoId> service) {
        this();
        this.service = service;
    }


    @Produces(MediaType.APPLICATION_JSON)
    public Response save(E entidad) {
        validateBeforeSave(entidad);

        var registro = service.save(entidad);
        if( registro == null ){
            throw new WebApplicationException(exceptionMessage.getDefaultMessageSumary(), Response.Status.INTERNAL_SERVER_ERROR);
        }
        URI uri = UriBuilder.fromPath("/{id}").build(registro.getId());
        return Response.created(uri)
                .entity(registro).type(MediaType.APPLICATION_JSON).build() ;
    }

    /**
     * Verificaciones a realizar antes de que la entidad sea almacenada
     * @param entidad Entidad a almacenar
     */
    protected void validateBeforeSave(E entidad){
        if( entidad == null ){
            throw new WebApplicationException(exceptionMessage.getDatosIncompletos(), Response.Status.BAD_REQUEST);
        }
        var stored = service.find(entidad.getId());
        if (stored.isPresent()) {
            throw new WebApplicationException(exceptionMessage.getRegistroExistente(), Response.Status.CONFLICT);
        }
    }

    public Response update(TipoId id,E entidad) {
        validateBeforeUpdate(entidad);
        var entidadStored = findOrThrow(id);
        if( entidadStored != entidad) {
            service.update(entidad);
        }
        return find(entidad.getId());
    }

    /**
     * Verificaciones a realizar antes de que la entidad sea actualizada
     * @param entidad Entidad a actualizar
     */
    protected void validateBeforeUpdate(E entidad){

    }

    public Response delete(TipoId id) {
        var entidad = findOrThrow(id);
        validateBeforeDelete(entidad);
        service.delete(id);
        return Response.noContent().build();
    }


    /**
     * Verificaciones a realizar antes de que la entidad sea borrada
     * @param entidad Entidad a borrar
     */
    protected void validateBeforeDelete(E entidad){
        findOrThrow(entidad.getId());
    }

    public Response find(TipoId id) {
        return Response.ok(service.find(id),MediaType.APPLICATION_JSON).build();
    }

    protected E findOrThrow(TipoId id) {
        return service.find(id).orElseThrow(
                () -> new WebApplicationException(exceptionMessage.getRegistroNoEncontrado(), Response.Status.NOT_FOUND));
    }

    public Response get() {
        return Response.ok(service.get(),MediaType.APPLICATION_JSON).build();
    }

    public int count(){
        return service.count();
    }

}
