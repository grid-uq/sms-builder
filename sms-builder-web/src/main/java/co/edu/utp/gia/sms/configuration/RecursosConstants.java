package co.edu.utp.gia.sms.configuration;

/**
 * Clase que define los recursos web de la aplicación
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
public final class RecursosConstants {

    /**
     * Variable que representa el atributo recursos disponibles en la aplicacion
     */
    static final String[] RECURSOS_ADMINISTRADOR = { "/administracion/index.xhtml", "/seguridad/recurso/index.xhtml",
            "/seguridad/rol/index.xhtml", "/seguridad/usuario/index.xhtml", "/seguridad/usuario/actualizar.xhtml","/backup/importar.xhtml" };


    /**
     * Variable que representa el atributo recursosUsuario, el cual contiene el
     * listado de los recursos disponibles para usuarios no empleados o
     * administradores dentro de la aplicacion
     */
    static final String[] RECURSOS_USUARIO = { "/atributocalidad/registro.xhtml", "/seguridad/usuario/actualizar.xhtml",
            "/objetivo/registro.xhtml",
            "/termino/registro.xhtml",
            "/pregunta/registro.xhtml",
            "/cadenabusqueda/registro.xhtml",
            "/criterioseleccion/registro.xhtml",
            "/fuente/registro.xhtml",
            "/proceso/registro.xhtml",
            "/referencia/editar.xhtml",
            "/referencia/adicionarCitas.xhtml",
            "/referencia/analizar.xhtml",
            "/calidad/evaluarReferencia.xhtml",
            "/calidad/evaluarReferencias.xhtml",
            "/calidad/resumenEvaluacionReferencias.xhtml",
            "/calidad/resumenEvaluacionReferenciasAtributo.xhtml",
            "/revision/aplicarCriterios.xhtml",
            "/revision/aplicarCriterios2.xhtml",
            "/revision/gestionarReferenciasRepetidas.xhtml",
            "/revision/editarRevision.xhtml",
            "/revision/registroReferencias.xhtml",
            "/revision/importarReferencias.xhtml",
            "/revision/importarReferenciasBaseDatos.xhtml",
            "/revision/importarReferenciasManual.xhtml",
            "/revision/importarReferenciasBolaNieve.xhtml",
            "/revision/resumenReferenciasDestacadas.xhtml",
            "/revision/resumenReferenciasSeleccionadas.xhtml",
            "/revision/revisores/index.xhtml",
            "/estadisticas/palabrasClave.xhtml",
            "/estadisticas/referenciaPalabrasClave.xhtml",
            "/estadisticas/referenciasCalidadYear.xhtml",
            "/estadisticas/referenciasPregunta.xhtml",
            "/estadisticas/referenciasTipo.xhtml",
            "/estadisticas/referenciasTipoFuente.xhtml",
            "/estadisticas/referenciasTermino.xhtml",
            "/estadisticas/referenciasTopico.xhtml",
            "/estadisticas/referenciasTopicoAtributoCalidad.xhtml",
            "/estadisticas/referenciasYear.xhtml",
            "/estadisticas/tablaReferenciasPreguntas.xhtml",
            "/estadisticas/tablaReferenciasTopicos.xhtml",
            "/ayuda/proceso.xhtml"
    };

    /**
     * Variable que representa el atributo recursos disponibles en la aplicacion
     */
    static final String[] RECURSOS_PUBLICOS = { "/index.xhtml", "/seguridad/usuario/registro.xhtml" };

    private RecursosConstants(){}
}
