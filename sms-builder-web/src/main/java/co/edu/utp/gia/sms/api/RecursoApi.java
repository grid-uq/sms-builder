package co.edu.utp.gia.sms.api;

import co.edu.utp.gia.sms.entidades.Recurso;
import co.edu.utp.gia.sms.negocio.RecursoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Collections;
import java.util.List;

@ApplicationScoped
@Path("/recursos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RecursoApi extends AbstractGenericApi<Recurso,String> {


    public RecursoApi() {
    }

    @Inject
    public RecursoApi(RecursoService service) {
        super(service);
    }

    @POST
    @Override
    public Response save(Recurso entidad) {
        return super.save(entidad);
    }

    @PUT
    @Path("/{id}")
    @Override
    public Response update(@PathParam("id") String id, Recurso entidad) {
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

    @GET
    public Response get(@QueryParam("url") String url,@QueryParam("publico") Boolean publico) {
        if( url != null ){
            var recurso = ((RecursoService)service).findByUrl(url);
            var result = recurso != null && recurso.getPublico().equals(publico) ? List.of( recurso ) : Collections.emptyList();
            return Response.ok(result,MediaType.APPLICATION_JSON).build();
        } else if( publico != null ){
            return Response.ok(((RecursoService)service).buscarRecursosPublicos(publico),MediaType.APPLICATION_JSON).build();
        }
        return super.get();
    }


}
