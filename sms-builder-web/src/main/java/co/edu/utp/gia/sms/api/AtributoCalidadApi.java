package co.edu.utp.gia.sms.api;

import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.negocio.AtributoCalidadService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/atributoscalidad")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AtributoCalidadApi extends AbstractGenericApi<AtributoCalidad,String> {

    public AtributoCalidadApi() {
    }

    @Inject
    public AtributoCalidadApi(AtributoCalidadService service) {
        super(service);
    }

    @POST
    @Override
    public Response save(AtributoCalidad entidad) {
        return super.save(entidad);
    }

    @PUT
    @Path("/{id}")
    @Override
    public Response update(@PathParam("id") String id, AtributoCalidad entidad) {
        return super.update(id, entidad);
    }

    @DELETE
    @Path("/{id}")
    @Override
    public Response delete(@PathParam("id") String id) {
        return super.delete(id);
    }

    @GET
    @Path("/{id}")
    @Override
    public Response find(@PathParam("id") String id) {
        return super.find(id);
    }
//
//    @GET
//    @Override
//    public Response get() {
//        return super.get();
//    }

    @GET
    public Response get(@QueryParam("descripcion") String descripcion) {
        if( descripcion == null ){
            return super.get();
        }
        return Response.ok(((AtributoCalidadService)service).findByDescripcion(descripcion), MediaType.APPLICATION_JSON).build();
    }
}
