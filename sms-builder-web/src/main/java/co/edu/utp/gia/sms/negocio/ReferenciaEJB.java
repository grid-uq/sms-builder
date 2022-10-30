package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.*;
import co.edu.utp.gia.sms.exceptions.LogicException;
import co.edu.utp.gia.sms.query.referencia.*;
import lombok.extern.java.Log;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

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
public class ReferenciaEJB extends AbstractEJB<Referencia, Integer> {
    @Inject
    private RevisionEJB revisionEJB;
    @Inject
    private TopicoEJB topicoEJB;
    @Inject
    private EvaluacionCalidadEJB evaluacionCalidadEJB;
    @Inject
    private MetadatoEJB metadatoEJB;
    @Inject
    private AtributoCalidadEJB atributoCalidadEJB;
    @Inject
    private PreguntaEJB preguntaEJB;
    @Inject
    private ProcesoEJB procesoEJB;

    public ReferenciaEJB() {
        super(Referencia.class);
    }

    public Referencia registrar(Referencia referencia, Integer idRevision, Integer idPasoProceso) {
        Revision revision = revisionEJB.obtener(idRevision);

        referencia.setRevision(revision);
        referencia.setFiltro(idPasoProceso);
//        for (Metadato metadato : referencia.getMetadatos()) {
//            if (metadato.getIdentifier().equals(TipoMetadato.FUENTE)
//                    && metadato.getValue().equalsIgnoreCase(Fuente.INCLUSION_DIRECTA.toString())) {
//                referencia.setFiltro(3);
//            }
//        }
        registrar(referencia);
        procesoEJB.addReferencia(idPasoProceso, referencia);
        return referencia;
    }

    public void registrar(List<Referencia> referencias, Integer idRevision, Integer idPasoProceso) {
        for (Referencia referencia : referencias) {
            registrar(referencia, idRevision, idPasoProceso);
        }
    }

    /**
     * Permite obtener el listado de referencias seleccionadas de una revision
     *
     * @param id Identificador de la revision
     * @return Listado de {@link Referencia} seleccionadas de la {@link Revision} identificada con
     * el id dado
     */
    public List<ReferenciaDTO> obtenerSeleccionadas(int id) {
        return poblarReferenciaDTOS(ReferenciaGetAll.createQuery(entityManager,id).getResultList());
    }

    public List<ReferenciaDTO> obtenerTodas(int idPaso) {
        PasoProceso paso = procesoEJB.obtenerOrThrow(idPaso);
        return obtenerTodas(paso);
    }

    private List<ReferenciaDTO> obtenerTodas(PasoProceso paso) {
        return poblarReferenciaDTOS(paso.getReferencias().stream()
                .sorted(Comparator.comparing(Referencia::getNombre))
                .map(r -> new ReferenciaDTO(r, paso.getId())).collect(Collectors.toList()));
    }

    private List<ReferenciaDTO> poblarReferenciaDTOS(List<ReferenciaDTO> referencias) {
        for (ReferenciaDTO referencia : referencias) {
            referencia.setAutores(obtenerAutores(referencia.getId()));
            referencia.setAbstracts(obtenerAbstract(referencia.getId()));
            referencia.setKeywords(obtenerKeywords(referencia.getId()));
            referencia.setFuente(obtenerFuente(referencia.getId()));
            referencia.setMetadatos(metadatoEJB.obtenerMetadatos(referencia.getId()));
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
    private Fuente obtenerFuente(Integer id) {
        return ReferenciaGetFuentes.createQuery(entityManager,id).getResultList().stream().findFirst().orElse(null);
        //return Fuente.valueOf(metadatoEJB.obtenerStringMetadatoByTipo(id, TipoMetadato.FUENTE));
    }

    /**
     * Permite obtener el listado de referencias de una revision que han pasado por
     * un determinado filtro
     *
     * @param idRevision Identificador de la revision
     * @return Listado de {@link Pregunta} de la {@link Revision} identificada con
     * el id dado
     */
    public List<ReferenciaDTO> obtenerTodasConEvaluacion(int idRevision) {
        Revision revision = revisionEJB.obtenerOrThrow(idRevision);
        List<ReferenciaDTO> referencias = obtenerTodas(revision.getPasoSeleccionado());
        for (ReferenciaDTO referencia : referencias) {
            referencia.setEvaluaciones(evaluacionCalidadEJB.obtenerEvaluaciones(referencia.getId()));
        }
        return referencias;
    }

//	public List<ReferenciaDTO> obtenerTodas(int idRevision, int filtro) {
//		List<ReferenciaDTO> lista = new ArrayList<ReferenciaDTO>();
//
//		System.out.println("USANDO FILTRO "+filtro);
//		entityManager.createNamedQuery(Referencia.REFERENCIA_GET_ALL, Referencia.class)
//				.setParameter("idRevision", idRevision).setParameter("filtro", filtro).getResultList().stream()
//				.forEach((r) -> {
//					lista.add(new ReferenciaDTO(r, filtro));
//				});
//
//		return lista;
//	}

    public String obtenerAutores(Integer idReferencia) {
        return metadatoEJB.obtenerStringMetadatoByTipo(idReferencia, TipoMetadato.AUTOR);
    }

    public String obtenerKeywords(Integer idReferencia) {
        return metadatoEJB.obtenerStringMetadatoByTipo(idReferencia, TipoMetadato.KEYWORD);
    }

    public String obtenerAbstract(Integer idReferencia) {
        return metadatoEJB.obtenerStringMetadatoByTipo(idReferencia, TipoMetadato.ABSTRACT);
    }

    public void actualizarFiltro(Integer id, Integer filtro) {
        Referencia referencia = obtener(id);
        Integer filtroActual = referencia.getFiltro();
        if (filtroActual < filtro) {
            while (filtroActual < filtro) {
                filtroActual++;
                procesoEJB.addReferencia(filtroActual, referencia);
            }
        } else {
            while (filtroActual > filtro) {
                procesoEJB.removeReferencia(filtroActual, referencia);
                filtroActual--;
            }
        }
        referencia.setFiltro(filtro);
    }

    public void guardarEvaluacion(EvaluacionCalidad evaluacion) {
        evaluacionCalidadEJB.actualizar(evaluacion);
        Referencia referencia = obtener(evaluacion.getReferencia().getId());
        referencia.setTotalEvaluacionCalidad(calcularTotalEvaluacionCalidad(referencia.getId()).floatValue());
    }

    /**
     * Consulta que permite obtener el total de las evaluaciones de calidad registradas en el sistema para una referencia
     *
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @return Double con el total de la evaluación de calidad de la referencia dada
     */
    private Double calcularTotalEvaluacionCalidad(Integer id) {
        return ReferenciaGetTotalEvaluacionCalidad.createQuery(entityManager, id).getSingleResult();
    }

    public void adicionarTopico(Integer id, Integer idTopico) {
        Referencia referencia = obtener(id);
        Topico topico = topicoEJB.obtener(idTopico);
        referencia.getTopicos().add(topico);
    }

    public void limpiarTopicos(Integer id) {
        Referencia referencia = obtener(id);
        referencia.getTopicos().clear();
    }

    public void actualizarRelevancia(Integer id, Integer relevancia) {
        Referencia referencia = obtener(id);
        if (referencia != null) {
            referencia.setRelevancia(relevancia);
        }
    }

    public void actualizarCita(Integer id, Integer citas) {

        Referencia referencia = obtener(id);
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

    public void actualizarNota(Integer id, String nota) {

        Referencia referencia = obtener(id);
        if (referencia != null) {
            referencia.setNota(nota);
        }
    }

    public void actualizarResumen(Integer id, String resumen) {
        Referencia referencia = obtener(id);
        if (referencia != null) {
            referencia.setResumen(resumen);
        }
    }

    public void removerTopico(Integer id, Integer idTopico) {
        Referencia referencia = obtener(id);
        Topico topico = entityManager.find(Topico.class, idTopico);
        referencia.getTopicos().remove(topico);

    }

    public void evaluacionAutomatica(Integer id) {
        Referencia referencia = obtener(id);
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
        AtributoCalidad atributoCalidad = atributoCalidadEJB.obtener(AtributoCalidadEJB.CVI,
                referencia.getRevision().getId());
        EvaluacionCalidad evaluacionCalidad = determinarEvaluacionCalidad(referencia, atributoCalidad);

        if (referencia.getRelevancia() == null || referencia.getRelevancia() < 3) {
            evaluacionCalidad.setEvaluacionCualitativa(EvaluacionCualitativa.NO_CUMPLE);
        } else if (referencia.getRelevancia() < 5) {
            evaluacionCalidad.setEvaluacionCualitativa(EvaluacionCualitativa.PARCIALMENTE);
        } else {
            evaluacionCalidad.setEvaluacionCualitativa(EvaluacionCualitativa.CUMPLE);
        }

        guardarEvaluacion(evaluacionCalidad);
    }

    private void evaluarSegunCitas(Referencia referencia) {

        AtributoCalidad atributoCalidad = atributoCalidadEJB.obtener(AtributoCalidadEJB.SCI,
                referencia.getRevision().getId());
        EvaluacionCalidad evaluacionCalidad = determinarEvaluacionCalidad(referencia, atributoCalidad);

        float media = referencia.getCitas()
                / (float) (1 + Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(referencia.getYear()));

        List<Float> scis = obtenerSCIs(referencia.getRevision().getId());

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

        guardarEvaluacion(evaluacionCalidad);

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
        AtributoCalidad atributoCalidad = atributoCalidadEJB.obtener(AtributoCalidadEJB.IRRQ,
                referencia.getRevision().getId());
        EvaluacionCalidad evaluacionCalidad = determinarEvaluacionCalidad(referencia, atributoCalidad);

        int totalPreguntas = (int) preguntaEJB.totalPreguntas(referencia.getRevision().getId());
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
        guardarEvaluacion(evaluacionCalidad);

    }

    /**
     * Consulta que permite obtener el número de preguntas relacionadas con una referencia
     *
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @return número de preguntas relacionadas con una referencia
     */
    private long calcularTotalPreguntasRelacionadas(Integer id) {
        return ReferenciaCountPreguntaRelacionada.createQuery(entityManager, id).getSingleResult();
    }

    /**
     * Consulta que permite obtener los SCI de las referencia de una revision
     *
     * @param id Id de la Revision
     * @return List<Float> listado de los SCI de las referencias de una revision
     */
    private List<Float> obtenerSCIs(Integer id) {
        return ReferenciaGetAllSCI.createQuery(entityManager, id).getResultList();
    }

    public void actualizarSPS(Integer id, String spsid) {
        Referencia referencia = obtener(id);
        if (referencia != null) {
            referencia.setSpsid(spsid);
        }
    }

    /**
     * Consulta que permite obtener las referencias con una determinada calificación de un atributo de calidad dado
     *
     * @param id                Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @param idAtributoCalidad Id del atributo de calidad
     * @param valorEvaluacion   Evaluación que deben cumplir las referencias seleccionadas
     * @return List<ReferenciaDTO> listado de las referencias que cumplen con la evaluación solicitada en el atributo de calidad dado
     */
    public List<ReferenciaDTO> obtenerReferenciasAtributoCalidadEvaluacion(Integer id,
                                                                           Integer idAtributoCalidad, EvaluacionCualitativa valorEvaluacion) {
        return poblarReferenciaDTOS(
                ReferenciaGetAllByEvaluacionOfAtributoCalidad
                        .createQuery(entityManager, id, idAtributoCalidad, valorEvaluacion).getResultList());
    }

    /**
     * Consulta que permite obtener las referencias con evaliacón de un atributo de calidad dado
     *
     * @param id                Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @param idAtributoCalidad Id del atributo de calidad
     * @return List<ReferenciaDTO> Listado de las con evaliacón de un atributo de calidad del id dado
     */
    public List<ReferenciaDTO> obtenerReferenciasAtributoCalidadEvaluacion(Integer id,
                                                                           Integer idAtributoCalidad) {
        return poblarReferenciaDTOS(ReferenciaGetAllWithEvaluacionOfAtributoCalidad.createQuery(
                entityManager, id, idAtributoCalidad).getResultList());
    }

    public void actualizarYear(Integer id, String year) {
        Referencia referencia = obtener(id);
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
     * @param id Id de la Revision
     * @return List<ReferenciaDTO> listado de las referencias destacadas, en caso de no haber ninguna el listado sera vacio
     */
    public List<ReferenciaDTO> obtenerDestacadas(Integer id) {
        return poblarReferenciaDTOS(ReferenciaGetDestacadas.createQuery(entityManager, id).getResultList());
    }

    public void avanzarReferecias(int idPaso) {
        if (idPaso >= 1) {
            PasoProceso paso = procesoEJB.obtenerOrThrow(idPaso);
            PasoProceso pasoSiguiente = procesoEJB.obtenerOrThrow(idPaso + 1);
            paso.getReferencias().forEach(
                    r -> {
                        if (r.getFiltro() == null || r.getFiltro() < (idPaso + 1)) {
                            r.setFiltro(idPaso + 1);
                            if (!pasoSiguiente.getReferencias().contains(r)) {
                                pasoSiguiente.getReferencias().add(r);
                            }
                        }
                    }
            );
        }
    }

    public void referenciaDuplicada(Integer id, Boolean duplicada) {
        Referencia referencia = obtenerOrThrow(id);
        referencia.setDuplicada(duplicada);
    }
}
