package co.edu.utp.gia.sms.api;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.EvaluacionCualitativa;
import co.edu.utp.gia.sms.entidades.PasoProceso;
import co.edu.utp.gia.sms.negocio.ProcesoService;
import co.edu.utp.gia.sms.negocio.ReferenciaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
@Path("/pasosproceso")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProcesoApi extends AbstractGenericApi<PasoProceso,String> {

    private ReferenciaService referenciaService;

    public ProcesoApi() {
    }

    @Inject
    public ProcesoApi(ProcesoService service,ReferenciaService referenciaService) {
        super(service);
        this.referenciaService = referenciaService;
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

    @GET
    @Path("/{id}/referencias")
    public Response obtenerReferencias(@PathParam("id") String id,
                                       @QueryParam("atributoCalidad") String idAtributoCalidad,
                                       @QueryParam("evaluacion") EvaluacionCualitativa valorEvaluacion,
                                       @QueryParam("destacadas") Boolean destacadas) {
        var paso = ((ProcesoService)service).findOrThrow(id);
        List<ReferenciaDTO> result;
        if( idAtributoCalidad != null ){
            if( valorEvaluacion != null ){
                result = referenciaService.obtenerReferenciasAtributoCalidadEvaluacion(paso, idAtributoCalidad, valorEvaluacion);
            } else {
                result = referenciaService.obtenerReferenciasAtributoCalidadEvaluacion(paso,idAtributoCalidad);
            }
        } else if (destacadas!=null&& destacadas) {
            result = referenciaService.obtenerDestacadas();
        } else {
            result = paso.getReferencias().stream().map(ReferenciaDTO::new).toList();
        }

        return Response.ok(result,MediaType.APPLICATION_JSON).build();
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

    @POST
    @Path("/{id}/evaluaciones")
    public Response evaluarReferencias(@PathParam("id") String id) {
        ((ProcesoService)service).avanzarReferecias(id);
        return Response.ok().build();
    }

}
