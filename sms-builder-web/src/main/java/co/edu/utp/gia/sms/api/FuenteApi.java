package co.edu.utp.gia.sms.api;

import co.edu.utp.gia.sms.entidades.Fuente;
import co.edu.utp.gia.sms.entidades.TipoFuente;
import co.edu.utp.gia.sms.negocio.FuenteService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.function.Predicate;

@ApplicationScoped
@Path("/fuestes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FuenteApi extends AbstractGenericApi<Fuente,String> {

    public FuenteApi() {
    }

    @Inject
    public FuenteApi(FuenteService service) {
        super(service);
    }

    @POST
    @Override
    public Response save(Fuente entidad) {
        return super.save(entidad);
    }

    @PUT
    @Path("/{id}")
    @Override
    public Response update(@PathParam("id") String id, Fuente entidad) {
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
    public Response get(@QueryParam("type") TipoFuente tipoFuente,@QueryParam("name") String name) {
        Predicate<Fuente> filtroType = fuente -> tipoFuente == null || fuente.getTipo().equals(tipoFuente);
        Predicate<Fuente> filtroName = fuente -> name == null || fuente.getNombre().equalsIgnoreCase(name);
        var fuentes = service.get().stream().filter(filtroType.and(filtroName)).toList();
        return Response.ok(fuentes,MediaType.APPLICATION_JSON).build();
    }

}
