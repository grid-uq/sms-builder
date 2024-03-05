package co.edu.utp.gia.sms.api;

import co.edu.utp.gia.sms.entidades.Paso;
import co.edu.utp.gia.sms.negocio.PasoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/pasos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PasoApi extends AbstractGenericApi<Paso,String> {

    public PasoApi() {
    }

    @Inject
    public PasoApi(PasoService service) {
        super(service);
    }

    @POST
    @Override
    public Response save(Paso entidad) {
        return super.save(entidad);
    }

    @PUT
    @Path("/{id}")
    @Override
    public Response update(@PathParam("id") String id, Paso entidad) {
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
    public Response get(@QueryParam("name") String name) {
        if( name != null ){
            return Response.ok(((PasoService)service).findByName(name)).build();
        }
        return super.get();
    }

}
