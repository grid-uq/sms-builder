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
            "/seguridad/rol/index.xhtml", "/seguridad/usuario/index.xhtml", "/seguridad/usuario/actualizar.xhtml","/backup/importar.xhtml",
            "/revision/editarRevision.xhtml",
            "/objetivo/registro.xhtml",
            "/pregunta/registro.xhtml",
            "/termino/registro.xhtml",
            "/atributocalidad/registro.xhtml",
            "/fuente/registro.xhtml",
            "/criterioseleccion/registro.xhtml",
            "/cadenabusqueda/registro.xhtml",
            "/revision/importarReferencias.xhtml",
            "/revision/importarReferenciasBaseDatos.xhtml",
            "/revision/importarReferenciasManual.xhtml",
            "/revision/importarReferenciasBolaNieve.xhtml",
            "/revision/gestionarReferenciasRepetidas.xhtml",
            "/referencia/aplicarCriterios.xhtml",
            "/revision/resumenReferenciasSeleccionadas.xhtml",
            "/referencia/adicionarCitas.xhtml",
            "/calidad/evaluarReferencias.xhtml",
            "/referencia/analizar.xhtml"};


    /**
     * Variable que representa el atributo recursosUsuario, el cual contiene el
     * listado de los recursos disponibles para usuarios no empleados o
     * administradores dentro de la aplicacion
     */
    static final String[] RECURSOS_USUARIO = { "/sms.xhtml", "/seguridad/usuario/actualizar.xhtml",
            "/referencia/navegar.xhtml",
            "/referencia/navegar.xhtml?all=true",
            "/proceso/registro.xhtml",
            "/referencia/editar.xhtml",
            "/calidad/evaluarReferencia.xhtml",
            "/calidad/resumenEvaluacionReferencias.xhtml",
            "/calidad/resumenEvaluacionReferenciasAtributo.xhtml",
            "/revision/registroReferencias.xhtml",
            "/revision/resumenReferenciasDestacadas.xhtml",
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
