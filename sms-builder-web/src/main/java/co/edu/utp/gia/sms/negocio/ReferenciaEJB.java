package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.*;
import co.edu.utp.gia.sms.exceptions.LogicException;
import co.edu.utp.gia.sms.query.referencia.*;
import lombok.extern.java.Log;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;

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
@Stateless
@Log
public class ReferenciaEJB extends AbstractGenericService<Referencia, String> {
    @Inject
    private RevisionService revisionService;
    @Inject
    private TopicoService topicoService;
    @Inject
    private EvaluacionCalidadService evaluacionCalidadService;
    @Inject
    private MetadatoServices metadatoServices;
    @Inject
    private AtributoCalidadService atributoCalidadService;
    @Inject
    private PreguntaService preguntaService;
    @Inject
    private ProcesoService procesoService;

    public ReferenciaEJB() {
        super(DB.root.getProvider(Referencia.class));
    }

    public Referencia save(Referencia referencia, String idPasoProceso) {
        //referencia.setFiltro(idPasoProceso);
        save(referencia);
        procesoService.addReferencia(idPasoProceso, referencia);
        return referencia;
    }

    public void save(List<Referencia> referencias,  String idPasoProceso) {
        for (Referencia referencia : referencias) {
            save(referencia, idPasoProceso);
        }
    }

    public List<ReferenciaDTO> findByPaso(String idPaso) {
        PasoProceso paso = procesoService.findOrThrow(idPaso);
        return findByPaso(paso);
    }

    private List<ReferenciaDTO> findByPaso(PasoProceso paso) {
        return poblarReferenciaDTOS(paso.getReferencias().stream()
                .sorted(Comparator.comparing(Referencia::getNombre))
                .map(r -> new ReferenciaDTO(r, paso.getOrden())).toList());
    }

    private List<ReferenciaDTO> poblarReferenciaDTOS(List<ReferenciaDTO> referencias) {
        for (ReferenciaDTO referencia : referencias) {
            referencia.setAutores(getAutores(referencia.getId()));
            referencia.setAbstracts(getAbstract(referencia.getId()));
            referencia.setKeywords(getKeywords(referencia.getId()));
            referencia.setFuente(getFuente(referencia.getId()));
            referencia.setMetadatos(metadatoServices.obtenerMetadatos(referencia.getId()));
        }
        return referencias;
    }

    /**
     * Permite obtener la fuente a la que pertenece una {@link Referencia}
     *
     * @param id Id de la referencia de la que se quiere obtener la fuente
     * @return {@link Fuente} a la que pertenece la {@link Referencia} que
     * corresponde al id proporcionado
     */
    private Fuente getFuente(String id) {
        return ReferenciaGetFuentes.createQuery(id).findFirst().orElse(null);
    }

    /**
     * Permite obtener el listado de referencias de una revision que han pasado por
     * un determinado filtro
     *
     * @return Listado de {@link Pregunta} de la {@link Revision} identificada con
     * el id dado
     */
    public List<ReferenciaDTO> findWithEvaluacion() {
        List<ReferenciaDTO> referencias = findByPaso( revisionService.get().getPasoSeleccionado() );
        for (ReferenciaDTO referencia : referencias) {
            referencia.setEvaluaciones(referencia.getEvaluaciones());
        }
        return referencias;
    }

    public String getAutores(String idReferencia) {
        return metadatoServices.obtenerStringMetadatoByTipo(idReferencia, TipoMetadato.AUTOR);
    }

    public String getKeywords(String idReferencia) {
        return metadatoServices.obtenerStringMetadatoByTipo(idReferencia, TipoMetadato.KEYWORD);
    }

    public String getAbstract(String idReferencia) {
        return metadatoServices.obtenerStringMetadatoByTipo(idReferencia, TipoMetadato.ABSTRACT);
    }

//    public void actualizarFiltro(String id, Integer filtro) {
//        Referencia referencia = obtener(id);
//        Integer filtroActual = referencia.getFiltro();
//        if (filtroActual < filtro) {
//            while (filtroActual < filtro) {
//                filtroActual++;
//                procesoService.addReferencia(""+filtroActual, referencia);
//            }
//        } else {
//            while (filtroActual > filtro) {
//                procesoService.removeReferencia(filtroActual, referencia);
//                filtroActual--;
//            }
//        }
//        referencia.setFiltro(filtro);
//    }

    public void saveEvaluacion(EvaluacionCalidad evaluacion) {
        evaluacionCalidadService.update(evaluacion);
        Referencia referencia = findOrThrow(evaluacion.getReferencia().getId());
        referencia.setTotalEvaluacionCalidad(calcularTotalEvaluacionCalidad(referencia.getId()).floatValue());
    }

    /**
     * Consulta que permite obtener el total de las evaluaciones de calidad registradas en el sistema para una referencia
     *
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @return Double con el total de la evaluación de calidad de la referencia dada
     */
    private Double calcularTotalEvaluacionCalidad(String id) {
        return ReferenciaGetTotalEvaluacionCalidad.createQuery(id).sum();
    }

    public void addTopico(String id, String idTopico) {
        Referencia referencia = findOrThrow(id);
        Topico topico = topicoService.findOrThrow(idTopico);
        referencia.getTopicos().add(topico);
    }

    public void cleanTopicos(String id) {
        Referencia referencia = findOrThrow(id);
        referencia.getTopicos().clear();
    }

    public void updateRelevancia(String id, Integer relevancia) {
        Referencia referencia = findOrThrow(id);
        if (referencia != null) {
            referencia.setRelevancia(relevancia);
        }
    }

    public void updateCita(String id, Integer citas) {

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

    }

    public void updateNota(String id, String nota) {

        Referencia referencia = findOrThrow(id);
        if (referencia != null) {
            referencia.setNota(nota);
        }
    }

    public void updateResumen(String id, String resumen) {
        Referencia referencia = findOrThrow(id);
        if (referencia != null) {
            referencia.setResumen(resumen);
        }
    }

    public void updateTopico(String id, String idTopico) {
        Referencia referencia = findOrThrow(id);
        Topico topico =  topicoService.findOrThrow(idTopico);
        referencia.getTopicos().remove(topico);
    }

    public void evaluacionAutomatica(String id) {
        Referencia referencia = findOrThrow(id);
        if (referencia == null) {
            throw new LogicException(exceptionMessage.getRegistroNoEncontrado());
        }
        try {
            evaluarSegunPreguntas(referencia);
            evaluarSegunCitas(referencia);
            evaluarSegunCVI(referencia);
        } catch (Exception e) {
            final String mensaje = exceptionMessage.getReferenciaErrorEvaluacion(id, referencia.getSpsid());
            log.log(Level.WARNING, mensaje, e);
            throw new LogicException(mensaje, e);
        }
    }

    private void evaluarSegunCVI(Referencia referencia) {
        AtributoCalidad atributoCalidad = atributoCalidadService.findByDescripcion(AtributoCalidadService.CVI);
        EvaluacionCalidad evaluacionCalidad = determinarEvaluacionCalidad(referencia, atributoCalidad);

        if (referencia.getRelevancia() == null || referencia.getRelevancia() < 3) {
            evaluacionCalidad.setEvaluacionCualitativa(EvaluacionCualitativa.NO_CUMPLE);
        } else if (referencia.getRelevancia() < 5) {
            evaluacionCalidad.setEvaluacionCualitativa(EvaluacionCualitativa.PARCIALMENTE);
        } else {
            evaluacionCalidad.setEvaluacionCualitativa(EvaluacionCualitativa.CUMPLE);
        }

        saveEvaluacion(evaluacionCalidad);
    }

    private void evaluarSegunCitas(Referencia referencia) {

        AtributoCalidad atributoCalidad = atributoCalidadService.findByDescripcion(AtributoCalidadService.SCI);
        EvaluacionCalidad evaluacionCalidad = determinarEvaluacionCalidad(referencia, atributoCalidad);

        float media = referencia.getCitas()
                / (float) (1 + Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(referencia.getYear()));

        List<Float> scis = obtenerSCIs();

        Percentile p = new Percentile();
        double[] datos = new double[scis.size()];
        for (int i = 0; i < datos.length; i++) {
            datos[i] = scis.get(i);
        }

        double q1 = p.evaluate(datos, 75);
        double q2 = p.evaluate(datos, 50);
        if (media > q1) {
            evaluacionCalidad.setEvaluacionCualitativa(EvaluacionCualitativa.CUMPLE);
        } else if (media > q2) {
            evaluacionCalidad.setEvaluacionCualitativa(EvaluacionCualitativa.PARCIALMENTE);
        } else {
            evaluacionCalidad.setEvaluacionCualitativa(EvaluacionCualitativa.NO_CUMPLE);
        }

        saveEvaluacion(evaluacionCalidad);

    }

    private EvaluacionCalidad determinarEvaluacionCalidad(Referencia referencia, AtributoCalidad atributoCalidad) {
        EvaluacionCalidad evaluacionCalidad = null;

        for (EvaluacionCalidad evaluacion : referencia.getEvaluacionCalidad()) {
            if (evaluacion.getAtributoCalidad().equals(atributoCalidad)) {
                evaluacionCalidad = evaluacion;
            }
        }

        if (evaluacionCalidad == null) {

            evaluacionCalidad = new EvaluacionCalidad(referencia, atributoCalidad);
        }

        return evaluacionCalidad;
    }

    private void evaluarSegunPreguntas(Referencia referencia) {
        AtributoCalidad atributoCalidad = atributoCalidadService.findByDescripcion(AtributoCalidadService.IRRQ);
        EvaluacionCalidad evaluacionCalidad = determinarEvaluacionCalidad(referencia, atributoCalidad);

        int totalPreguntas = (int) preguntaService.count();
        int totalPreguntasRelacionadas = (int) calcularTotalPreguntasRelacionadas(referencia.getId());
        float porcentaje = totalPreguntasRelacionadas * 100.0f / totalPreguntas;

        if (porcentaje >= 75) {
            evaluacionCalidad.setEvaluacionCualitativa(EvaluacionCualitativa.CUMPLE);
        } else if (porcentaje >= 50) {
            evaluacionCalidad.setEvaluacionCualitativa(EvaluacionCualitativa.PARCIALMENTE);

        } else {
            evaluacionCalidad.setEvaluacionCualitativa(EvaluacionCualitativa.NO_CUMPLE);
        }
        referencia.setSrrqi(porcentaje);
        saveEvaluacion(evaluacionCalidad);

    }

    /**
     * Consulta que permite obtener el número de preguntas relacionadas con una referencia
     *
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @return número de preguntas relacionadas con una referencia
     */
    private long calcularTotalPreguntasRelacionadas(String id) {
        return ReferenciaGetPreguntaRelacionada.createQuery(id).count();
    }

    /**
     * Consulta que permite obtener los SCI de las referencia de una revision
     *
     * @return List<Float> listado de los SCI de las referencias de una revision
     */
    private List<Float> obtenerSCIs() {
        return ReferenciaGetAllSCI.createQuery().toList();
    }

    public void updateSPS(String id, String spsid) {
        Referencia referencia = findOrThrow(id);
        if (referencia != null) {
            referencia.setSpsid(spsid);
        }
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
        return poblarReferenciaDTOS(
                ReferenciaGetAllByEvaluacionOfAtributoCalidad
                        .createQuery(idAtributoCalidad, valorEvaluacion)
                        .map(r -> new ReferenciaDTO(r, 0)).toList());
    }

    /**
     * Consulta que permite obtener las referencias con evaliacón de un atributo de calidad dado
     *
     * @param idAtributoCalidad Id del atributo de calidad
     * @return List<ReferenciaDTO> Listado de las con evaliacón de un atributo de calidad del id dado
     */
    public List<ReferenciaDTO> obtenerReferenciasAtributoCalidadEvaluacion(
                                                                           String idAtributoCalidad) {
        return poblarReferenciaDTOS(ReferenciaGetAllWithEvaluacionOfAtributoCalidad.createQuery(idAtributoCalidad)
                .map(r -> new ReferenciaDTO(r, 0)).toList());
    }

    public void actualizarYear(String id, String year) {
        Referencia referencia = findOrThrow(id);
        if (referencia != null) {
            referencia.setYear(year);
            if (referencia.getCitas() != null && referencia.getYear() != null) {
                float media = referencia.getCitas() / (float) (1 + Calendar.getInstance().get(Calendar.YEAR)
                        - Integer.parseInt(referencia.getYear()));
                referencia.setSci(media);
            }
        }
    }

    /**
     * Consulta que permite obtener los Referencias que han recivido una valoración de su contenido
     *
     * @return List<ReferenciaDTO> listado de las referencias destacadas, en caso de no haber ninguna el listado sera vacio
     */
    public List<ReferenciaDTO> obtenerDestacadas() {
        return poblarReferenciaDTOS( ReferenciaGetDestacadas.createQuery().map(r -> new ReferenciaDTO(r, 0)).toList());
    }

    public void avanzarReferecias(String idPaso) {
//        if (idPaso >= 1) {
            PasoProceso paso = procesoService.findOrThrow(idPaso);
            PasoProceso pasoSiguiente = procesoService.findByOrden(paso.getOrden()+1);
            paso.getReferencias().forEach(
                    r -> {
                            if (!pasoSiguiente.getReferencias().contains(r)) {
                                pasoSiguiente.getReferencias().add(r);
                            }
                        }
//                        if (r.getFiltro() == null || r.getFiltro() < (idPaso + 1)) {
//                            r.setFiltro(idPaso + 1);
//                            if (!pasoSiguiente.getReferencias().contains(r)) {
//                                pasoSiguiente.getReferencias().add(r);
//                            }
//                        }
            );
//        }
    }

    public void updateDuplicada(String id, Boolean duplicada) {
        Referencia referencia = findOrThrow(id);
        referencia.setDuplicada(duplicada);
    }
}
