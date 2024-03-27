package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.*;
import co.edu.utp.gia.sms.exceptions.LogicException;
import co.edu.utp.gia.sms.query.referencia.ReferenciaGetAllByEvaluacionOfAtributoCalidad;
import co.edu.utp.gia.sms.query.referencia.ReferenciaGetAllWithEvaluacionOfAtributoCalidad;
import co.edu.utp.gia.sms.query.referencia.ReferenciaGetDestacadas;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.java.Log;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

// TODO Pendiente la indicación de las revistas con mayor frecuencia dentro del SMS.
//      importante para la toma de decisión sobre el destino de publicación.
/**
 * Clase de negocio encargada de implementar las funciones correspondientes a la
 * gestion de la {@link Referencia}.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
@ApplicationScoped
@Log
public class ReferenciaService extends AbstractGenericService<Referencia, String> {
    @Inject
    private RevisionService revisionService;
    @Inject
    private TopicoService topicoService;
    @Inject
    private ProcesoService procesoService;

    public ReferenciaService() {
        super(DB.root.getProvider(Referencia.class));
    }

    public Referencia save(Referencia referencia, String idPasoProceso) {
        //referencia.setFiltro(idPasoProceso);
        save(referencia);
        DB.storageManager.store(referencia.getMetadatos());
        procesoService.addReferencia(idPasoProceso, referencia);
        return referencia;
    }

    @Override
    public void delete(Referencia referencia) {
        var primerPaso = procesoService.findByOrden(1);
        if( primerPaso != null ){
            procesoService.removeReferencia(primerPaso.getId(), referencia.getId());
        }
        super.delete(referencia);
    }

    public void save(List<Referencia> referencias,  String idPasoProceso) {
        referencias.forEach(referencia -> save(referencia,idPasoProceso));
    }

    public List<ReferenciaDTO> findByPasoSeleccionado() {
        return findByPaso( revisionService.getPasoActual() );
    }
    public List<ReferenciaDTO> findByPaso(String idPaso) {
        PasoProceso paso = procesoService.findOrThrow(idPaso);
        return findByPaso(paso);
    }

    public List<ReferenciaDTO> findAll() {
        return get().stream()
                .sorted(Comparator.comparing(Referencia::getNombre))
                .map(r -> new ReferenciaDTO(r, 0)).toList();
    }

    private List<ReferenciaDTO> findByPaso(PasoProceso paso) {
        return paso.getReferencias().stream()
                .sorted(Comparator.comparing(Referencia::getNombre))
                .map(r -> new ReferenciaDTO(r, paso.getOrden())).toList();
    }

    /**
     * Permite obtener el listado de referencias de una revision que han pasado por
     * un determinado filtro
     *
     * @return Listado de {@link Pregunta} de la {@link Revision} identificada con
     * el id dado
     */
    public List<ReferenciaDTO> findWithEvaluacion() {
        List<ReferenciaDTO> referencias = findByPasoSeleccionado();
        for (ReferenciaDTO referencia : referencias) {
            referencia.setEvaluaciones(referencia.getReferencia().getEvaluacionCalidad());
        }
        return referencias;
    }

    public Referencia addTopico(String id, String idTopico) {
        Referencia referencia = findOrThrow(id);
        Topico topico = topicoService.findOrThrow(idTopico);
        if( !referencia.getTopicos().contains(topico)  ) {
            referencia.getTopicos().add(topico);
            DB.storageManager.store(referencia.getTopicos());
        }
        return referencia;
    }

    public void cleanTopicos(String id) {
        Referencia referencia = findOrThrow(id);
        referencia.getTopicos().clear();
        DB.storageManager.store(referencia.getTopicos());
    }

    public Referencia updateRelevancia(String id, Integer relevancia) {
        Referencia referencia = findOrThrow(id);
        if (referencia != null) {
            referencia.setRelevancia(relevancia);
        }
        update(referencia);
        return referencia;
    }

    public Referencia updateCita(String id, Integer citas) {

        Referencia referencia = findOrThrow(id);
        if (referencia != null) {
            referencia.setCitas(citas);
            if (referencia.getYear() == null) {
                throw new LogicException(
                        getExceptionMessage().getReferenciaSinFecha(referencia.getId(), referencia.getSpsid())
                );
            } else if (referencia.getCitas() != null) {
                float media = referencia.getCitas() / (float) (1 + Calendar.getInstance().get(Calendar.YEAR)
                        - Integer.parseInt(referencia.getYear()));
                referencia.setSci(media);
            } else {
                referencia.setSci(null);
            }
        }
        update(referencia);
        return referencia;
    }

    public Referencia updateNota(String id, String nota) {

        Referencia referencia = findOrThrow(id);
        if (referencia != null) {
            referencia.setNota(nota);
        }
        update(referencia);
        return referencia;
    }

    public Referencia updateResumen(String id, String resumen) {
        Referencia referencia = findOrThrow(id);
        if (referencia != null) {
            referencia.setResumen(resumen);
        }
        update(referencia);
        return referencia;
    }

    public Referencia removeTopico(String id, String idTopico) {
        Referencia referencia = findOrThrow(id);
        Topico topico =  topicoService.findOrThrow(idTopico);
        referencia.getTopicos().remove(topico);
        DB.storageManager.store(referencia.getTopicos());
        return referencia;
    }

    public Referencia updateSPS(String id, String spsid) {
        Referencia referencia = findOrThrow(id);
        referencia.setSpsid(spsid);
        return referencia;
    }

    /**
     * Consulta que permite obtener las referencias con una determinada calificación de un atributo de calidad dado
     *
     * @param paso Paso del que se desean obtener las referencias.
     * @param idAtributoCalidad Id del atributo de calidad
     * @param valorEvaluacion   Evaluación que deben cumplir las referencias seleccionadas
     * @return List<ReferenciaDTO> listado de las referencias que cumplen con la evaluación solicitada en el atributo de calidad dado
     */
    public List<ReferenciaDTO> obtenerReferenciasAtributoCalidadEvaluacion(PasoProceso paso,
                                                                           String idAtributoCalidad, EvaluacionCualitativa valorEvaluacion) {
        return ReferenciaGetAllByEvaluacionOfAtributoCalidad
                .createQuery(paso::getReferencias,idAtributoCalidad, valorEvaluacion)
                .map(r -> new ReferenciaDTO(r, 0)).toList();
    }


    /**
     * Consulta que permite obtener las referencias con una determinada calificación de un atributo de calidad dado
     *
     * @param idAtributoCalidad Id del atributo de calidad
     * @param valorEvaluacion   Evaluación que deben cumplir las referencias seleccionadas
     * @return List<ReferenciaDTO> listado de las referencias que cumplen con la evaluación solicitada en el atributo de calidad dado
     */
    public List<ReferenciaDTO> obtenerReferenciasAtributoCalidadEvaluacion(String idPaso,
            String idAtributoCalidad, EvaluacionCualitativa valorEvaluacion) {
        var paso = this.procesoService.findOrThrow(idPaso);
        return obtenerReferenciasAtributoCalidadEvaluacion(paso,idAtributoCalidad,valorEvaluacion);
    }


    /**
     * Consulta que permite obtener las referencias con una determinada calificación de un atributo de calidad dado
     *
     * @param idAtributoCalidad Id del atributo de calidad
     * @param valorEvaluacion   Evaluación que deben cumplir las referencias seleccionadas
     * @return List<ReferenciaDTO> listado de las referencias que cumplen con la evaluación solicitada en el atributo de calidad dado
     */
    public List<ReferenciaDTO> obtenerReferenciasAtributoCalidadEvaluacion(
            String idAtributoCalidad, EvaluacionCualitativa valorEvaluacion) {
        return obtenerReferenciasAtributoCalidadEvaluacion(revisionService.getPasoActual(),idAtributoCalidad,valorEvaluacion);
    }

    /**
     * Consulta que permite obtener las referencias con evaluación de un atributo de calidad dado
     * @param paso Paso del que se desean obtener las referencias.
     * @param idAtributoCalidad Id del atributo de calidad
     * @return List<ReferenciaDTO> Listado de las con evaluación de un atributo de calidad del id dado
     */
    public List<ReferenciaDTO> obtenerReferenciasAtributoCalidadEvaluacion(PasoProceso paso,String idAtributoCalidad) {
        return ReferenciaGetAllWithEvaluacionOfAtributoCalidad.createQuery(paso::getReferencias,idAtributoCalidad)
                .map(r -> new ReferenciaDTO(r, 0)).toList();
    }

    /**
     * Consulta que permite obtener las referencias con evaluación de un atributo de calidad dado
     *
     * @param idAtributoCalidad Id del atributo de calidad
     * @return List<ReferenciaDTO> Listado de las con evaluación de un atributo de calidad del id dado
     */
    public List<ReferenciaDTO> obtenerReferenciasAtributoCalidadEvaluacion(String idAtributoCalidad) {
        return obtenerReferenciasAtributoCalidadEvaluacion(revisionService.getPasoActual(),idAtributoCalidad);
    }

    public Referencia updateYear(String id, String year) {
        Referencia referencia = findOrThrow(id);
        referencia.setYear(year);
        if (referencia.getCitas() != null && referencia.getYear() != null) {
            float media = referencia.getCitas() / (float) (1 + Calendar.getInstance().get(Calendar.YEAR)
                    - Integer.parseInt(referencia.getYear()));
            referencia.setSci(media);
        }
        return referencia;
    }

    /**
     * Consulta que permite obtener los Referencias que han recivido una valoración de su contenido
     *
     * @return List<ReferenciaDTO> listado de las referencias destacadas, en caso de no haber ninguna el listado sera vacio
     */
    public List<ReferenciaDTO> obtenerDestacadas() {
        return ReferenciaGetDestacadas.createQuery(revisionService.getPasoActual()::getReferencias).map(r -> new ReferenciaDTO(r, 0)).toList();
    }

    public Referencia updateDuplicada(String id, Boolean duplicada) {
        Referencia referencia = findOrThrow(id);
        referencia.setDuplicada(duplicada);
        update(referencia);
        return referencia;
    }

    public Referencia updateTags(String id, List<String> tags) {
        Referencia referencia = findOrThrow(id);
        referencia.setTags(tags);
        update(referencia);
        DB.storageManager.store(referencia.getTags());
        return referencia;
    }

    public void asociciacionAutomatica() {
        dataProvider.get().forEach(this::asociciacionAutomaticamente);
    }

    private void asociciacionAutomaticamente(Referencia referencia) {
        Predicate<Topico> condicion = topico -> referencia.getTags().stream()
                .anyMatch(topico.getDescripcion()::equalsIgnoreCase);
        revisionService.get().getTopicos().stream()
                .filter( condicion )
                .forEach(topico -> addTopico(referencia.getId(), topico.getId()));
    }
}
