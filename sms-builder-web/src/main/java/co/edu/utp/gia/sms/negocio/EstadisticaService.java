package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.entidades.Fuente;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.TipoFuente;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.query.estadistica.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
/**
 * Clase de negocio encargada de implementar las funciones correspondientes a la
 * gestión de las estadísticas.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
@ApplicationScoped
public class EstadisticaService {
    @Inject
    private FuenteService fuenteService;
    @Inject
    private RevisionService revisionService;
    /**
     * Consulta que permite obtener el número de referencias por año en una revision
     *
     * @return Listado de {@link DatoDTO} con estadísticas del número de referencias por año en una revision
     */
    public List<DatoDTO> obtenerReferenciasYear() {
        return EstadisticaReferenciaByYear.createQuery(revisionService.getPasoActual()::getReferencias).toList();
    }

    /**
     * Consulta que permite obtener el número de referencias que cumplen con un determinado atributo de calidad por Año en una revision
     *
     * @param idAtributoCalidad Id del atributo de calidad
     * @return List<DatoDTO> Listado de {@link DatoDTO} con estadísticas del número de referencias que cumplen con un determinado atributo de calidad por Año en una revision
     */
    public List<DatoDTO> obtenerReferenciasYear(String idAtributoCalidad) {
        return EstadisticaReferenciaWithAtributoCalidadByYear.createQuery(revisionService.getPasoActual()::getReferencias,idAtributoCalidad).toList();
    }

    /**
     * Consulta que permite obtener el número de referencias por Tipo en una revision
     *
     * @return List<DatoDTO> Listado de {@link DatoDTO} con estadísticas del número de referencias por Tipo en una revision
     */
    public List<DatoDTO> obtenerReferenciasTipo() {
        return EstadisticaReferenciaByTipo.createQuery(revisionService.getPasoActual()::getReferencias).toList();
    }

    /**
     * Consulta que permite obtener el promedio de calidad por año
     *
     * @return List<DatoDTO> Listado de {@link DatoDTO} con estadísticas del promedio de calidad por año
     */
    public List<DatoDTO> obtenerReferenciasCalidadYear() {
        return EstadisticaCalidadByYear.createQuery(revisionService.getPasoActual()::getReferencias).toList();
    }

    /**
     * Consulta que permite obtener el promedio de la evaluación de calidad de un determinado atributo de calidad por Año en una revision
     *
     * @param idAtributoCalidad Id del atributo de calidad
     * @return List<DatoDTO> Listado de {@link DatoDTO} con estadísticas del promedio de la evaluación de calidad de un determinado atributo de calidad por Año en una revision
     */
    public List<DatoDTO> obtenerReferenciasCalidadYear(String idAtributoCalidad) {
        return EstadisticaCalidadOfAtributoCalidadByYear.createQuery(revisionService.getPasoActual()::getReferencias,idAtributoCalidad).toList();
    }

    /**
     * Consulta que permite obtener el número de referencias por Pregunta en una revision
     *
     * @return List<DatoDTO> Listado de {@link DatoDTO} con estadísticas del número de referencias por pregunta
     */
    public List<DatoDTO> obtenerReferenciasPregunta() {
        return EstadisticaReferenciaByPregunta.createQuery(revisionService.getPasoActual()::getReferencias).toList();
    }

    /**
     * Consulta que permite obtener el número de referencias por tipo de fuente en una revision <br />
     *
     * @return List<DatoDTO> con Estadísticas de los datos obtenidos
     */
    public List<DatoDTO> obtenerReferenciasTipoFuente() {
        List<DatoDTO> resultado = new ArrayList<>(EstadisticaReferenciaByTipoFuente.createQuery(revisionService.getPasoActual()::getReferencias).toList());

        for (TipoFuente fuente : TipoFuente.values()) {
            if (resultado.stream().noneMatch(d -> d.getEtiqueta().equals(fuente.toString()))) {
                resultado.add(new DatoDTO(fuente.toString(), 0L));
            }
        }
        return resultado;
    }

    /**
     * Permite obtener el número de referencias por cada fuente de un determinado tipo de fuente en una revision
     *
     * @param tipo Tipo de fuente a consultar
     * @return List<DatoDTO> Con Estadísticas de referencia por tipo de fuente.
     */
    public List<DatoDTO> obtenerReferenciasTipoFuenteNombre(TipoFuente tipo) {
        List<DatoDTO> resultado = new ArrayList<>(EstadisticaReferenciaByTipoFuenteAndNombre.createQuery(revisionService.getPasoActual()::getReferencias,tipo)
                .toList());
        List<Fuente> fuentes = fuenteService.getByTipoFuente(tipo);
        for (Fuente fuente : fuentes) {
            if ( resultado.stream().noneMatch(d -> d.getEtiqueta().equals(fuente.getNombre()))) {
                resultado.add(new DatoDTO(fuente.getNombre(), 0L));
            }
        }

        return resultado;
    }

    /**
     * Consulta que permite obtener el número de referencias por Topico en una revision
     *
     * @return List<DatoDTO> Con Estadísticas del número de referencias por Topico en una revision
     */
    public List<DatoDTO> obtenerReferenciasTopico() {
        return EstadisticaReferenciaByTopico.createQuery(revisionService.getPasoActual()::getReferencias).toList();
    }

    /**
     * Consulta que permite obtener el número de referencias que cumplen con un determinado atributo de calidad por Topico en una revision
     *
     * @param idAtributoCalidad Id del atributo de calidad
     * @return List<DatoDTO> Con Estadísticas de referencias que cumplen con un determinado atributo de calidad por Topico en una revision
     */
    public List<DatoDTO> obtenerReferenciasTopico(String idAtributoCalidad) {
        return EstadisticaReferenciaWithAtributoCalidadByTopico.createQuery(revisionService.getPasoActual()::getReferencias,idAtributoCalidad).toList();
    }

    /**
     * Consulta que permite obtener el número de referencias por Topico de una Pregunta en una revision
     *
     * @param codigo Codigo de la pregunta de la que se desean obtener las estadísticas
     * @return List<DatoDTO> Con Estadisticas de referencias por Topico de una Pregunta en una revision
     */
    public List<DatoDTO> obtenerReferenciasTopicoPregunta(String codigo) {
        return EstadisticaReferenciaOfPreguntaByTopico.createQuery(revisionService.getPasoActual()::getReferencias,codigo).toList();
    }

    /**
     * Consulta que permite obtener el número de referencias que cumplen con un determinado atributo de calidad por Topico de una Pregunta en una revision
     *
     * @param codigo            Codigo de la pregunta de la que se desean obtener las estadísticas
     * @param idAtributoCalidad Id del atributo de calidad
     * @return List<DatoDTO> Con Estadísticas de referencias que cumplen con un determinado atributo de calidad por Topico de una Pregunta en una revision
     */
    public List<DatoDTO> obtenerReferenciasTopico(String codigo, String idAtributoCalidad) {
        return EstadisticaReferenciaOfPreguntaWithAtributoCalidadByTopico.createQuery(revisionService.getPasoActual()::getReferencias,codigo, idAtributoCalidad).toList();
    }

    /**
     * Consulta que permite obtener las palabras claves y su número de apariciones en las referencias seleccionadas
     *
     * @param minimo Cantidad minima de apariciones que debe tener una palabra clave para ser considerada
     * @return List<DatoDTO> Con Estadísticas de las palabras claves y su número de apariciones en las referencias seleccionadas
     */
    public List<DatoDTO> obtenerPalabrasClave(int minimo) {
        return EstadisticaPalabrasClave.createQuery(revisionService.getPasoActual()::getReferencias,minimo).toList();
    }

    /**
     * Consulta que permite obtener las Referencias que contienen en uno de sus metadatos la palabra dada <br />
     *
     * @param keyword   Palabra a buscar
     * @param metadatos Listado de tipos de metadatos a incluir en la búsqueda.
     * @return List<Referencia> que contienen la keyword buscada en uno de sus metadatos
     */
    public List<Referencia> obtenerReferencias(String keyword, List<TipoMetadato> metadatos) {
        return EstadisticaReferenciaByPalabrasClave.createQuery(revisionService.getPasoActual()::getReferencias,keyword, metadatos).toList();
    }

    /**
     * Consulta que permite obtener el número de referencias por término
     *
     * @return List<DatoDTO> Con Estadísticas de referencia por término.
     */
    public List<DatoDTO> obtenerReferenciasTermino() {
        return EstadisticaNumeroReferenciasByTermino.createQuery(revisionService.getPasoActual()::getReferencias).toList();
    }

    /**
     * Consulta que permite obtener el número de referencias por término y sinónimo
     *
     * @return List<DatoDTO> Con Estadísticas de referencia por término y sinónimo.
     */
    public List<DatoDTO> obtenerReferenciasSinonimo() {
        return EstadisticaNumeroReferenciasBySinonimo.createQuery(revisionService.getPasoActual()::getReferencias).toList();
    }

    /**
     * Consulta que permite obtener el listado de años que comprenden las Referencias de la Revision
     *
     * @return List<String> Listado de años que comprenden las Referencias de la Revision
     */
    public List<String> obtenerYears() {
        return EstadisticaGetYears.createQuery().toList();
    }

}
