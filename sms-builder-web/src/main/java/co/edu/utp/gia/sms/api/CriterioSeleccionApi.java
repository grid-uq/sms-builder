package co.edu.utp.gia.sms.api;

import co.edu.utp.gia.sms.entidades.CriterioSeleccion;
import co.edu.utp.gia.sms.negocio.CriterioSeleccionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/criteriosseleccion")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CriterioSeleccionApi extends AbstractGenericApi<CriterioSeleccion,String> {

    public CriterioSeleccionApi() {
    }

    @Inject
    public CriterioSeleccionApi(CriterioSeleccionService service) {
        super(service);
    }

    @POST
    @Override
    public Response save(CriterioSeleccion entidad) {
        return super.save(entidad);
    }

    @PUT
    @Path("/{id}")
    @Override
    public Response update(@PathParam("id") String id, CriterioSeleccion entidad) {
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
    @Override
    public Response get() {
        return super.get();
    }
}
