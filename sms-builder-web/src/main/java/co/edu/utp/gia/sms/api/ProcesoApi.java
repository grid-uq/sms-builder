package co.edu.utp.gia.sms.api;

import co.edu.utp.gia.sms.entidades.PasoProceso;
import co.edu.utp.gia.sms.negocio.ProcesoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/pasosproceso")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProcesoApi extends AbstractGenericApi<PasoProceso,String> {


    public ProcesoApi() {
    }

    @Inject
    public ProcesoApi(ProcesoService service) {
        super(service);
    }

    @POST
    @Override
    public Response save(PasoProceso entidad) {
        return super.save(entidad);
    }

    @PUT
    @Path("/{id}")
    @Override
    public Response update(@PathParam("id") String id, PasoProceso entidad) {
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
    public Response get(@QueryParam("orden") Integer orden) {
        if( orden != null ){
            return Response.ok(((ProcesoService)service).findByOrden(orden),MediaType.APPLICATION_JSON).build();
        }
        return super.get();
    }

    @POST
    @Path("/{id}/referencias")
    public Response avanzarReferencias(@PathParam("id") String id) {
        ((ProcesoService)service).avanzarReferecias(id);
        return Response.ok().build();
    }
    @POST
    @Path("/{id}/referencias/{idReferencia}")
    public Response add(@PathParam("id") String id, @PathParam("idReferencia")  String idReferencia) {
        var pasoProceso = ((ProcesoService)service).addReferencia(id,idReferencia);
        return Response.ok(pasoProceso,MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("/{id}/referencias/{idReferencia}")
    public Response remove(@PathParam("id") String id,@PathParam("idReferencia")  String idReferencia) {
        var pasoProceso = ((ProcesoService)service).removeReferencia(id,idReferencia);
        return Response.ok(pasoProceso,MediaType.APPLICATION_JSON).build();
    }



}
