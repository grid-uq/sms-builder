package co.edu.utp.gia.sms.api;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.negocio.ReferenciaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
@Path("/referencias")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReferenciaApi extends AbstractGenericApi<Referencia,String> {

    public ReferenciaApi() {
    }

    @Inject
    public ReferenciaApi(ReferenciaService service) {
        super(service);
    }

    @POST
    public Response save(Referencia entidad,@QueryParam("idPasoProceso") String idPasoProceso) {
        if( idPasoProceso == null ){
            return super.save(entidad);
        } else {
            return createResponse(((ReferenciaService)service).save(entidad,idPasoProceso));
        }
    }

    @PUT
    public Response asociciacionAutomatica() {
        ((ReferenciaService) service).asociciacionAutomatica();
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    @Override
    public Response update(@PathParam("id") String id, Referencia entidad) {
        return super.update(id, entidad);
    }

    @DELETE
    @Path("/{id}")
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
    public Response get(@QueryParam("paso") String paso,@QueryParam("evaluacion") boolean evaluacion) {
        List<ReferenciaDTO> referencias = switch (paso) {
            case "-1" -> ((ReferenciaService) service).findByPasoSeleccionado();
            case null -> ((ReferenciaService) service).findAll();
            default -> ((ReferenciaService) service).findByPaso(paso);
        };

        if(evaluacion){
            referencias.forEach(referencia->referencia.setEvaluaciones(referencia.getReferencia().getEvaluacionCalidad()));
        }

        return Response.ok(referencias,MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("/{id}/topicos/{idTopico}")
    public Response addTopico(@PathParam("id") String id, @PathParam("idTopico") String idTopico) {
        var referencia = ((ReferenciaService) service).addTopico(id,idTopico);
        return Response.ok(referencia,MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("/{id}/topicos/{idTopico}")
    public Response removeTopico(@PathParam("id") String id, @PathParam("idTopico") String idTopico) {
        var referencia = ((ReferenciaService) service).removeTopico(id,idTopico);
        return Response.ok(referencia,MediaType.APPLICATION_JSON).build();
    }

    @PATCH
    @Path("/{id}/relevancia")
    public Response updateRelevancia(@PathParam("id") String id,@NotNull Integer relevancia) {
        var referencia = ((ReferenciaService) service).updateRelevancia(id,relevancia);
        return Response.ok(referencia,MediaType.APPLICATION_JSON).build();
    }

    @PATCH
    @Path("/{id}/citas")
    public Response updateCitas(@PathParam("id") String id,@NotNull Integer citas) {
        var referencia = ((ReferenciaService) service).updateCita(id,citas);
        return Response.ok(referencia,MediaType.APPLICATION_JSON).build();
    }

    @PATCH
    @Path("/{id}/notas")
    public Response updateNotas(@PathParam("id") String id,@NotNull String notas) {
        var referencia = ((ReferenciaService) service).updateNota(id,notas);
        return Response.ok(referencia,MediaType.APPLICATION_JSON).build();
    }

    @PATCH
    @Path("/{id}/resumen")
    public Response updateResumen(@PathParam("id") String id,@NotNull String resumen) {
        var referencia = ((ReferenciaService) service).updateResumen(id,resumen);
        return Response.ok(referencia,MediaType.APPLICATION_JSON).build();
    }

    @PATCH
    @Path("/{id}/sps")
    public Response updateSps(@PathParam("id") String id,@NotNull String sps) {
        var referencia = ((ReferenciaService) service).updateSPS(id,sps);
        return Response.ok(referencia,MediaType.APPLICATION_JSON).build();
    }

    @PATCH
    @Path("/{id}/year")
    public Response updateYear(@PathParam("id") String id,@NotNull String year) {
        var referencia = ((ReferenciaService) service).updateYear(id,year);
        return Response.ok(referencia,MediaType.APPLICATION_JSON).build();
    }

    @PATCH
    @Path("/{id}/duplicada")
    public Response updateDuplicada(@PathParam("id") String id,@NotNull Boolean duplicada) {
        var referencia = ((ReferenciaService) service).updateDuplicada(id,duplicada);
        return Response.ok(referencia,MediaType.APPLICATION_JSON).build();
    }

    @PATCH
    @Path("/{id}/tags")
    public Response updateTags(@PathParam("id") String id,@NotNull List<String> tags) {
        var referencia = ((ReferenciaService) service).updateTags(id,tags);
        return Response.ok(referencia,MediaType.APPLICATION_JSON).build();
    }
}
