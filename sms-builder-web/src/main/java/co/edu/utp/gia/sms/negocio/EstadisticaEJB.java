package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.entidades.Fuente;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.TipoFuente;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.query.estadistica.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class EstadisticaEJB {
    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private FuenteEJB fuenteEJB;
    /**
     * Consulta que permite obtener el número de referencias por año en una revision
     *
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @return Listado de {@link DatoDTO} con estadisticas del número de referencias por año en una revision
     */
    public List<DatoDTO> obtenerReferenciasYear(Integer id) {
        return EstadisticaReferenciaByYear.createQuery(entityManager, id).getResultList();
    }

    /**
     * Consulta que permite obtener el número de referencias que cumplem con un determinado atributo de calidad por Año en una revision
     *
     * @param id                Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @param idAtributoCalidad Id del atributo de calidad
     * @return List<DatoDTO> Listado de {@link DatoDTO} con estadisticas del número de referencias que cumplem con un determinado atributo de calidad por Año en una revision
     */
    public List<DatoDTO> obtenerReferenciasYear(Integer id, Integer idAtributoCalidad) {
        return EstadisticaReferenciaWithAtributoCalidadByYear.createQuery(entityManager, id, idAtributoCalidad).getResultList();
    }

    /**
     * Consulta que permite obtener el número de referencias por Tipo en una revision
     *
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @return List<DatoDTO> Listado de {@link DatoDTO} con estadisticas del número de referencias por Tipo en una revision
     */
    public List<DatoDTO> obtenerReferenciasTipo(Integer id) {
        return EstadisticaReferenciaByTipo.createQuery(entityManager, id).getResultList();
    }

    /**
     * Consulta que permite obtener el promedio de calidad por año
     *
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @return List<DatoDTO> Listado de {@link DatoDTO} con estadisticas del promedio de calidad por año
     */
    public List<DatoDTO> obtenerReferenciasCalidadYear(Integer id) {
        return EstadisticaCalidadByYear.createQuery(entityManager, id).getResultList();
    }

    /**
     * Consulta que permite obtener el promedio de la evaluación de calidad de un determinado atributo de calidad por Año en una revision
     *
     * @param id                Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @param idAtributoCalidad Id del atributo de calidad
     * @return List<DatoDTO> Listado de {@link DatoDTO} con estadisticas del promedio de la evaluación de calidad de un determinado atributo de calidad por Año en una revision
     */
    public List<DatoDTO> obtenerReferenciasCalidadYear(Integer id, Integer idAtributoCalidad) {
        return EstadisticaCalidadOfAtributoCalidadByYear.createQuery(entityManager, id, idAtributoCalidad).getResultList();
    }

    /**
     * Consulta que permite obtener el número de referencias por Pregunta en una revision
     *
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @return List<DatoDTO> Listado de {@link DatoDTO} con estadisticas del número de referencias por pregunta
     */
    public List<DatoDTO> obtenerReferenciasPregunta(Integer id) {
        return EstadisticaReferenciaByPregunta.createQuery(entityManager, id).getResultList();
    }

    /**
     * Consulta que permite obtener el número de referencias por tipo de fuente en una revision <br />
     *
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @return List<DatoDTO> con Estadisticas de los datos obtenidos
     */
    public List<DatoDTO> obtenerReferenciasTipoFuente(Integer id) {
        List<DatoDTO> resultado = EstadisticaReferenciaByTipoFuente.createQuery(entityManager, id).getResultList();

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
     * @param id   Id de la revision
     * @param tipo Tipo de fuente a consultar
     * @return List<DatoDTO> Con Estadisticas de referencia por tipo de fuente.
     */
    public List<DatoDTO> obtenerReferenciasTipoFuenteNombre(Integer id, TipoFuente tipo) {
        List<DatoDTO> resultado = EstadisticaReferenciaByTipoFuenteAndNombre.createQuery(entityManager, id, tipo)
                .getResultList();
        List<Fuente> fuentes = fuenteEJB.listarByTipoFuente(tipo,id);
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
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @return List<DatoDTO> Con Estadisticas de el número de referencias por Topico en una revision
     */
    public List<DatoDTO> obtenerReferenciasTopico(Integer id) {
        return EstadisticaReferenciaByTopico.createQuery(entityManager, id).getResultList();
    }

    /**
     * Consulta que permite obtener el número de referencias que cumplem con un determinado atributo de calidad por Topico en una revision
     *
     * @param id                Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @param idAtributoCalidad Id del atributo de calidad
     * @return List<DatoDTO> Con Estadisticas de referencias que cumplem con un determinado atributo de calidad por Topico en una revision
     */
    public List<DatoDTO> obtenerReferenciasTopico(Integer id, Integer idAtributoCalidad) {
        return EstadisticaReferenciaWithAtributoCalidadByTopico.createQuery(entityManager, id, idAtributoCalidad).getResultList();
    }

    /**
     * Consulta que permite obtener el número de referencias por Topico de una Pregunta en una revision
     *
     * @param id     Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @param codigo Codigo de la pregunta de la que se desean obtener las estadisticas
     * @return List<DatoDTO> Con Estadisticas de referencias por Topico de una Pregunta en una revision
     */
    public List<DatoDTO> obtenerReferenciasTopico(Integer id, String codigo) {
        return EstadisticaReferenciaOfPreguntaByTopico.createQuery(entityManager, id, codigo).getResultList();
    }

    /**
     * Consulta que permite obtener el número de referencias que cumplem con un determinado atributo de calidad por Topico de una Pregunta en una revision
     *
     * @param id                Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @param codigo            Codigo de la pregunta de la que se desean obtener las estadisticas
     * @param idAtributoCalidad Id del atributo de calidad
     * @return List<DatoDTO> Con Estadisticas de referencias que cumplem con un determinado atributo de calidad por Topico de una Pregunta en una revision
     */
    public List<DatoDTO> obtenerReferenciasTopico(Integer id, String codigo, Integer idAtributoCalidad) {
        return EstadisticaReferenciaOfPreguntaWithAtributoCalidadByTopico.createQuery(entityManager, id, codigo, idAtributoCalidad).getResultList();
    }

    /**
     * Consulta que permite obtener las palabras claves y su número de apariciones en las referencias seleccionadas
     *
     * @param id     Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @param minimo Cantidad minima de apariciones que debe tener una palabra clave para ser considerada
     * @return List<DatoDTO> Con Estadisticas de las palabras claves y su número de apariciones en las referencias seleccionadas
     */
    public List<DatoDTO> obtenerPalabrasClave(Integer id, int minimo) {
        return EstadisticaPalabrasClave.createQuery(entityManager, id, minimo).getResultList();
    }

    /**
     * Consulta que permite obtener las Referencias que contienen en uno de sus metadatos la palabra dada <br />
     *
     * @param id        Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @param keyword   Palabra a buscar
     * @param metadatos Listado de tipos de metadatos a incluir en la búsqueda.
     * @return List<Referencia> que contienen la keyword buscada en uno de sus metadatos
     */
    public List<Referencia> obtenerReferencias(Integer id, String keyword, List<TipoMetadato> metadatos) {
        return EstadisticaReferenciaByPalabrasClave.createQuery(entityManager, id, keyword, metadatos).getResultList();
    }

    /**
     * Consulta que permite obtener el número de referencias por termino
     *
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @return List<DatoDTO> Con Estadisticas de referencia por termino.
     */
    public List<DatoDTO> obtenerReferenciasTermino(Integer id) {
        return EstadisticaNumeroReferenciasByTermino.createQuery(entityManager, id).getResultList();
    }

    /**
     * Consulta que permite obtener el número de referencias por termino y sinonimo
     *
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @return List<DatoDTO> Con Estadisticas de referencia por termino y sinonimo.
     */
    public List<DatoDTO> obtenerReferenciasSinonimo(Integer id) {
        return EstadisticaNumeroReferenciasBySinonimo.createQuery(entityManager, id).getResultList();
    }

    /**
     * Consulta que permite obtener el listado de años que comprenden las Referencias de la Revision
     *
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Revision}
     * @return List<String> Listado de años que comprenden las Referencias de la Revision
     */
    public List<String> obtenerYears(Integer id) {
        return EstadisticaGetYears.createQuery(entityManager, id).getResultList();
    }

}
