package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.seguridad.SeguridadBean;
import co.edu.utp.gia.sms.entidades.PasoProceso;
import co.edu.utp.gia.sms.negocio.ProcesoService;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import java.util.Collection;
/**
 * Clase controladora de interfaz web que se encarga de la gestión del proceso.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@Named
@ViewScoped
public class ProcesoBean extends AbstractRevisionBean {

    @Inject
    private ProcesoService procesoService;
    @Getter
    private Collection<PasoProceso> pasosProceso;

    @Inject
    @ManagedProperty("#{seguridadBean}")
    private SeguridadBean seguridadBean;

    @Getter
    private MenuModel model;

    @Getter
    private MenuModel stepsModel;


    public void inicializar() {
        pasosProceso = procesoService.get();
        configurarMenu();
        configurarStepsMenu();
    }

    private void configurarStepsMenu() {
        stepsModel = new DefaultMenuModel();
        getRevision().getPasosProceso().forEach( this::addStep );


    }

    private void addStep(PasoProceso pasoProceso) {
        var paso = pasoProceso.getPaso();
        var item = DefaultMenuItem.builder()
                .value(getMessage(paso.nombre()))
                //.url(paso.recurso().getUrl())
                .command("#{revisionBean.irPaso("+pasoProceso.getOrden()+")}")
                .style("margin-left:10px; margin-right:10px; border-radius: 10px; overflow-x: auto; max-width: 90hw;")

                .build();
        stepsModel.getElements().add(item);
    }

    private void configurarMenu() {
        model = new DefaultMenuModel();
        configurarPasoActual();
        configurarStage1(model);
//        configurarStage2Static(model);
        configurarStage2(model);
        configurarStage3(model);
        configurarStage4(model);
        configurarStage5(model);
        configurarStage6(model);
        configurarAyuda(model);
    }

    private void configurarPasoActual() {
        var paso = getRevision().getPasoActual().getPaso();
        var item = DefaultMenuItem.builder()
                .value(getMessage(paso.nombre()))
                .url(paso.recurso().getUrl())
                .build();
        model.getElements().add(item);
    }

    private void configurarStage2(MenuModel model) {
        DefaultSubMenu stage = DefaultSubMenu.builder()
                .label("Stage 2 Search for studies")
                .styleClass(".ui-menu-parent")
                .style("overflow-y: auto; max-height: 60vh;")
                .build();
        pasosProceso.forEach(paso -> {
            final String url = paso.getPaso().recurso().getUrl();
//            DefaultMenuItem menuItem = addItem(stage, paso.getPaso().getNombre(), url);
            DefaultMenuItem menuItem= addItem(stage, getMessage(paso.getPaso().nombre()), url,
                    getRevision() != null&&seguridadBean.verifivarAcceso(url));
//            menuItem.setParam("paso", paso.getId());

        });
        stage.setExpanded(false);
        model.getElements().add(stage);
    }

    private void configurarStage2Static(MenuModel model) {
        String[] labels = {"etiquetaMenuReferenciasImportar", "etiquetaMenuReferenciasDuplicadas",
                "etiquetaMenuReferenciasSeleccionar", "etiquetaMenuReferenciasSeleccionadas"};
        String[] urls = {"/atributocalidad/registro.xhtml", "/revision/gestionarReferenciasRepetidas.xhtml",
                "/revision/aplicarCriterios.xhtml", "/revision/resumenReferenciasSeleccionadas.xhtml"};
        DefaultSubMenu stage = DefaultSubMenu.builder()
                .label("Stage 2 Search for studies")
                .build();
        for (int i = 0; i < urls.length; i++) {
            addItem(stage, getMessage(labels[i]), urls[i]);
        }
        stage.setExpanded(false);
        model.getElements().add(stage);
    }

    private void configurarStage1(MenuModel model) {
        String[] urls = {"/referencia/navegar.xhtml","/referencia/navegar.xhtml?all=true","/proceso/registro.xhtml","/backup/importar.xhtml"};
        String[] labels = {"etiquetaMenuReferenciasSeleccionadas","etiquetaMenuReferencias","etiquetaProceso","etiquetaRestaurar"};
        DefaultSubMenu stage = DefaultSubMenu.builder()
                .label("Stage 1 Planing")
                .build();
        addItem(stage, getMessage(labels[0]), urls[0], seguridadBean.verifivarAcceso(urls[0]));
        for (int i = 1; i < urls.length; i++) {
            addItem(stage, getMessage(labels[i]), urls[i]);
        }
        stage.getElements().add(itemBackup());
        stage.setExpanded(false);
        model.getElements().add(stage);
    }

    private void configurarStage3(MenuModel model) {
        String[] urls = {"/referencia/adicionarCitas.xhtml", "/calidad/evaluarReferencias.xhtml",
                "/calidad/resumenEvaluacionReferencias.xhtml", "/calidad/resumenEvaluacionReferenciasAtributo.xhtml"};
        String[] labels = {"etiquetaMenuReferenciaNumeroCitas", "etiquetaMenuEvaluarReferencia", "etiquetaMenuCalidadTablaResumen",
                "etiquetaMenuCalidadTablaResumenAtributo"};
        DefaultSubMenu stage = DefaultSubMenu.builder()
                .label("Stage 3 Quality analisys")
                .build();
        for (int i = 0; i < urls.length; i++) {
            addItem(stage, getMessage(labels[i]), urls[i]);
        }
        stage.setExpanded(false);
        model.getElements().add(stage);
    }

    private void configurarStage4(MenuModel model) {
        String[] urls = {"/estadisticas/palabrasClave.xhtml", "/estadisticas/referenciaPalabrasClave.xhtml"};
        String[] labels = {"etiquetaMenuPalabrasClave", "etiquetaMenuMetadato"};
        DefaultSubMenu stage = DefaultSubMenu.builder()
                .label("Stage 4 Data collection")
                .build();
        for (int i = 0; i < urls.length; i++) {
            addItem(stage, getMessage(labels[i]), urls[i]);
        }
        stage.setExpanded(false);
        model.getElements().add(stage);
    }

    private void configurarStage5(MenuModel model) {
        String[] urls = {"/referencia/analizar.xhtml", "/estadisticas/tablaReferenciasPreguntas.xhtml",
                "/estadisticas/tablaReferenciasTopicos.xhtml"};
        String[] labels = {"etiquetaMenuAnalizarReferencias", "etiquetaMenuAnalisisTablaReferenciaPregunta",
                "etiquetaMenuAnalisisTablaReferenciaTopico"};
        DefaultSubMenu stage = DefaultSubMenu.builder()
                .label("Stage 5 Analysis and classification of studies")
                .build();
        for (int i = 0; i < urls.length; i++) {
            addItem(stage, getMessage(labels[i]), urls[i]);
        }
        stage.setExpanded(false);
        model.getElements().add(stage);
    }

    private void configurarStage6(MenuModel model) {
        String[] urls = {"/estadisticas/referenciasYear.xhtml", "/estadisticas/referenciasTipo.xhtml",
                "/estadisticas/referenciasTipoFuente.xhtml", "/estadisticas/referenciasTopico.xhtml",
                "/estadisticas/referenciasTopicoAtributoCalidad.xhtml", "/estadisticas/referenciasPregunta.xhtml",
                "/estadisticas/referenciasCalidadYear.xhtml","/estadisticas/referenciasTermino.xhtml"};
        String[] labels = {"etiquetaMenuAnalisisReferenciaYear", "etiquetaMenuAnalisisReferenciaTipo",
                "etiquetaMenuAnalisisReferenciaTipoFuente", "etiquetaMenuAnalisisReferenciaTopico",
                "etiquetaMenuAnalisisReferenciaTopicoAtributoCalidad", "etiquetaMenuAnalisisReferenciaPregunta",
                "etiquetaMenuAnalisisCalidadYear","etiquetaMenuAnalisisReferenciaTermino"};
        DefaultSubMenu stage = DefaultSubMenu.builder()
                .label("Stage 6 Results")
                .build();
        for (int i = 0; i < urls.length; i++) {
            addItem(stage, getMessage(labels[i]), urls[i]);
        }
        stage.setExpanded(false);
        model.getElements().add(stage);
    }

    private void configurarGestionas(MenuModel model) {
        String[] urls = {"/usuario/index.xhtml", "/seguridad/recurso/index.xhtml", "/seguridad/rol/index.xhtml",
                "/seguridad/usuario/actualizar.xhtml", "/seguridad/usuario/index.xhtml"};
        String[] labels = {"menu.usuarios", "menu.recurso", "menu.rol", "menu.usuario.actualizar", "menu.usuario.registrar"};
        DefaultSubMenu stage = DefaultSubMenu.builder()
                .label(getMessage("menu.gestionar"))
                .build();
        for (int i = 0; i < urls.length; i++) {
            addItem(stage, getMessage(labels[i]), urls[i], seguridadBean.verifivarAcceso(urls[i]));
        }
        stage.setExpanded(false);
        model.getElements().add(stage);
    }

    private void configurarAyuda(MenuModel model) {
        String[] urls = {"/ayuda/proceso.xhtml"};
        String[] labels = {"menu.ayuda.proceso"};
        DefaultSubMenu stage = DefaultSubMenu.builder()
                .label(getMessage("menu.ayuda"))
                .build();
        for (int i = 0; i < urls.length; i++) {
            addItem(stage, getMessage(labels[i]), urls[i], seguridadBean.verifivarAcceso(urls[i]));
        }
        stage.setExpanded(false);

        model.getElements().add(stage);
    }

    private void addItem(DefaultSubMenu stage, String label, String url) {
        addItem(stage, label, url, getRevision() != null && seguridadBean.verifivarAcceso(url));
    }

    private DefaultMenuItem addItem(DefaultSubMenu stage, String label, String url, boolean rendered) {

        DefaultMenuItem item = DefaultMenuItem.builder()
                .value(label)
                .ajax(false)
                .url(url)
                //.command(url)
                .styleClass(".ui-menu-child")
                .style("overflow-y: auto; max-height: 60vh;")
                .rendered(rendered)
                .build();
        stage.getElements().add(item);
        return item;
    }

    private DefaultMenuItem itemBackup(){
        return DefaultMenuItem.builder()
                .value(getMessage("etiquetaBackup"))
                .ajax(false)
                .command("#{backupBean.export()}")

                //.command(url)

                .rendered(true)
                .build();
    }

}
