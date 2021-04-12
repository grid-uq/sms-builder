package co.edu.utp.gia.sms.configuration;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.utp.gia.sms.entidades.Recurso;
import co.edu.utp.gia.sms.entidades.Rol;
import co.edu.utp.gia.sms.entidades.Usuario;
import co.edu.utp.gia.sms.importutil.Fuente;
import co.edu.utp.gia.sms.negocio.RecursoEJB;
import co.edu.utp.gia.sms.negocio.RolEJB;
import co.edu.utp.gia.sms.negocio.UsuarioEJB;
import lombok.extern.java.Log;

@Startup
@javax.ejb.Singleton
@Log
public class Setup {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Variable que representa el atributo ID_USUARIO, que debería ser el ID de
	 * registro del rol de usuario usado por la aplicación
	 */
	public static final Integer ID_USUARIO = 2;
	/**
	 * Variable que representa el atributo usuarioBO de la clase
	 */
	@Inject
	private UsuarioEJB usuarioBO;
	/**
	 * Variable que representa el atributo recursoBO de la clase
	 */
	@Inject
	private RecursoEJB recursoBO;
	/**
	 * Variable que representa el atributo rolBO de la clase
	 */
	@Inject
	private RolEJB rolBO;

	/**
	 * Variable que representa el atributo recursos disponibles en la aplicacion
	 */
	private String[] recursosAdministrador = { "/administracion/index.xhtml", "/seguridad/recurso/index.xhtml",
			"/seguridad/rol/index.xhtml", "/seguridad/usuario/index.xhtml", "/seguridad/usuario/actualizar.xhtml" };


	/**
	 * Variable que representa el atributo recursosUsuario, el cual contiene el
	 * listado de los recursos disponibles para usuarios no empleados o
	 * administradores dentro de la aplicacion
	 */
	private String[] recursosUsuario = { "/boleta/vender.xhtml", "/seguridad/usuario/actualizar.xhtml",
			"/revision/analizarReferencias.xhtml",
			"/revision/aplicarCriterios.xhtml",
			"/revision/aplicarCriterios2.xhtml",
			"/revision/evaluarReferencia.xhtml",
			"/revision/evaluarReferencias.xhtml",
			"/revision/gestionarReferenciasRepetidas.xhtml",
			"/revision/referenciaAdicionarCitas.xhtml",
			"/revision/registrarTopico.xhtml",
			"/revision/registroAtributoCalidad.xhtml",
			"/revision/registroInicial.xhtml",
			"/revision/registroObjetivo.xhtml",
			"/revision/registroPregunta.xhtml",
			"/revision/registroReferencias.xhtml",
			"/revision/registroRevision.xhtml",
			"/revision/registroTermino.xhtml",
			"/revision/resumenReferenciasDestacadas.xhtml",
			"/revision/resumenReferenciasSeleccionadas.xhtml",
			"/revision/seleccionarRevision.xhtml",
			"/revision/tablaResumenEvaluacionReferencias.xhtml",
			"/revision/tablaResumenEvaluacionReferenciasAtributo.xhtml",
			"/revision/revisores/index.xhtml",
			"/estadisticas/palabrasClave.xhtml",
			"/estadisticas/referenciaPalabrasClave.xhtml",
			"/estadisticas/referenciasCalidadYear.xhtml",
			"/estadisticas/referenciasPregunta.xhtml",
			"/estadisticas/referenciasTipo.xhtml",
			"/estadisticas/referenciasTipoFuente.xhtml",
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
	private String[] recursosPublicos = { "/index.xhtml", "/seguridad/usuario/registro.xhtml" };


	@PostConstruct
	public void inicializar() {
		setupRecursos();
		setupRol();
		setupUsuario();
		setupFuentes();
	}

	private void setupFuentes() {
		for (Fuente fuente : Fuente.values()) {
			co.edu.utp.gia.sms.entidades.Fuente f = entityManager.find(co.edu.utp.gia.sms.entidades.Fuente.class, fuente);
			if( f == null ) {
				entityManager.persist( new co.edu.utp.gia.sms.entidades.Fuente(fuente) );
			}
		}
	}


	/**
	 * Metodo encargado de registrar los roles por defecto usados en la
	 * aplicacion
	 */
	private void setupRol() {
//		if (rolBO.listar().isEmpty()) {
			registrarRecursosRol(1,"Administrador", recursosAdministrador);
			registrarRecursosRol(2,"Usuario", recursosUsuario);
//		}
	}

	private void registrarRecursosRol(Integer id,String nombre,String[] recursos){
		Rol rol = new Rol(id,nombre);
		for (String url : recursos) {
			Recurso recurso = recursoBO.buscarRecurso(url);
			if (recurso != null) {
				rol.getRecursos().add(recurso);
			}
		}
		if( rolBO.obtener(id) == null ){
			rolBO.registrar(rol);
		} else {
			rolBO.actualizar(rol);
		}
	}

	/**
	 * Método encargado de registrar los recursos usados por la aplicación
	 */
	private void setupRecursos() {
		for (String recurso : recursosAdministrador) {
			setupRecurso(recurso, false);
		}
		for (String recurso : recursosUsuario) {
			setupRecurso(recurso, false);
		}
		for (String recurso : recursosPublicos) {
			setupRecurso(recurso, true);
		}
	}

	/**
	 * Método encargado de registrar un recurso en el sistema
	 *
	 * @param url
	 *            Url del recurso a ser registrado
	 * @param publico
	 *            Indica si el registro es público o no
	 */
	private void setupRecurso(String url, boolean publico) {
		if (recursoBO.buscarRecurso(url) == null) {
			Recurso recurso = new Recurso();
			recurso.setUrl(url);
			recurso.setNombre( getNombreRecurso(url) );
			recurso.setPublico(publico);
			recursoBO.registrar(recurso);
		}
	}

	private String getNombreRecurso(String url) {
		String[] nombre = url.split("/");
		String nombreRecurso = nombre[1];
		for (int i = 2; i < nombre.length && !"index.xhtml".equals(nombre[i]); i++) {
			nombreRecurso += "_" + nombre[i];
		}
		nombreRecurso = nombreRecurso.replaceAll(".xhtml", "");
		return nombreRecurso;
	}

	/**
	 * Método encargado de registrar en el sistema el usuario administrador por
	 * defecto usado para la gestión inicial del sistema
	 */
	private void setupUsuario() {
		if (usuarioBO.listar().isEmpty()) {
			Usuario usuario = new Usuario();
			usuario.setNombreUsuario("root");
			usuario.setClave("12345");
			usuario.setRoles(rolBO.listar());
			usuario.setNombre("root");
			usuario.setEmail("root@email.com");
			usuarioBO.registrar(usuario, usuario.getClave());
		}
	}

}
