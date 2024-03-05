package co.edu.utp.gia.sms.api;

import co.edu.utp.gia.sms.entidades.CadenaBusqueda;
import co.edu.utp.gia.sms.negocio.CadenaBusquedaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/cadenasbusqueda")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CadenaBusquedaApi extends AbstractGenericApi<CadenaBusqueda,String> {

    public CadenaBusquedaApi() {
    }

    @Inject
    public CadenaBusquedaApi(CadenaBusquedaService service) {
        super(service);
    }

    @POST
    @Override
    public Response save(CadenaBusqueda entidad) {
        return super.save(entidad);
    }

    @PUT
    @Path("/{id}")
    @Override
    public Response update(@PathParam("id") String id, CadenaBusqueda entidad) {
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
        if( "__default__".equals(id)){
            var cadena = new CadenaBusqueda();
            cadena.setConsulta(((CadenaBusquedaService) service).sugerirConsulta());
            return Response.ok(cadena,MediaType.APPLICATION_JSON).build();
        }
        return super.find(id);
    }

    @GET
    @Override
    public Response get() {
        return super.get();
    }
}
