package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.entidades.PasoProceso;
import co.edu.utp.gia.sms.negocio.ProcesoService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import org.primefaces.model.menu.*;

import java.util.Collection;
import java.util.List;

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

    @Getter
    private MenuModel model;

    @Getter
    private MenuModel stepsModel;


    public void inicializar() {
        pasosProceso = procesoService.get();
        stepsModel = new DefaultMenuModel();
        model = new DefaultMenuModel();

        configurarPasoActual();
        configurarStage1(model);
//        configurarStage2Static(model);
//        configurarStage2(model);
        configurarStepsMenu();

        configurarStage3(model);
        configurarStage4(model);
        configurarStage5(model);
        configurarStage6(model);
        configurarAyuda(model);

    }

    private void configurarStepsMenu() {
        DefaultSubMenu stage = DefaultSubMenu.builder()
                .label("Stage 2 Search for studies")
                .styleClass(".ui-menu-parent")
                .style("overflow-y: auto; max-height: 60vh;")
                .build();

        getRevision().getPasosProceso().stream().map( this::createStep).forEach(
                (item)->{
                    stepsModel.getElements().add(item);
                    stage.getElements().add(item);
                }
        );

        model.getElements().add(stage);
    }

    private MenuItem createStep(PasoProceso pasoProceso) {
        var paso = pasoProceso.getPaso();
        return DefaultMenuItem.builder()
                .value(getMessage(paso.nombre()))
                //.url(paso.recurso().getUrl())
                .command("#{revisionBean.irPaso("+pasoProceso.getOrden()+")}")
                .style("margin-left:10px; margin-right:10px; border-radius: 10px; overflow-x: auto; max-width: 90hw;")

                .build();
    }

    private void configurarPasoActual() {
        var paso = revisionService.getPasoActual().getPaso();
        var item = DefaultMenuItem.builder()
                .value(getMessage(paso.nombre()))
                .url(paso.recurso().getUrl())
                .build();
        model.getElements().add(item);
    }

    private void configurarStage1(MenuModel model) {
        DefaultSubMenu stage = DefaultSubMenu.builder().label("Stage 1 Planing").build();

        var items = List.of( itemOf("etiquetaMenuReferenciasSeleccionadas","/referencia/navegar.xhtml"),
                itemOf("etiquetaMenuReferencias","/referencia/navegar.xhtml?all=true"),
                itemOf("etiquetaProceso","/proceso/registro.xhtml"),
                itemOf("etiquetaRestaurar","/backup/importar.xhtml"));
        items.stream().map(this::createItem).forEach(stage.getElements()::add);
        stage.getElements().add(itemBackup());
        stage.setExpanded(false);

        model.getElements().add(stage);
    }

    private void configurarStage3(MenuModel model) {
        DefaultSubMenu stage = DefaultSubMenu.builder().label("Stage 3 Quality analisys").build();

        var items = List.of( itemOf("etiquetaMenuCalidadTablaResumen","/calidad/resumenEvaluacionReferencias.xhtml"),
                itemOf("etiquetaMenuCalidadTablaResumenAtributo","/calidad/resumenEvaluacionReferenciasAtributo.xhtml"));
        items.stream().map(this::createItem).forEach(stage.getElements()::add);
        stage.setExpanded(false);
        model.getElements().add(stage);
    }

    private void configurarStage4(MenuModel model) {
        DefaultSubMenu stage = DefaultSubMenu.builder().label("Stage 4 Data collection").build();

        var items = List.of( itemOf("etiquetaMenuPalabrasClave","/estadisticas/palabrasClave.xhtml"),
                itemOf("etiquetaMenuMetadato","/estadisticas/referenciaPalabrasClave.xhtml"));
        items.stream().map(this::createItem).forEach(stage.getElements()::add);
        stage.setExpanded(false);
        model.getElements().add(stage);
    }

    private void configurarStage5(MenuModel model) {
        DefaultSubMenu stage = DefaultSubMenu.builder().label("Stage 5 Analysis and classification of studies").build();

        var items = List.of(
                itemOf("etiquetaMenuAnalisisTablaReferenciaPregunta","/estadisticas/tablaReferenciasPreguntas.xhtml"),
                itemOf("etiquetaMenuAnalisisTablaReferenciaTopico","/estadisticas/tablaReferenciasTopicos.xhtml"));


        items.stream().map(this::createItem).forEach(stage.getElements()::add);
        stage.setExpanded(false);
        model.getElements().add(stage);
    }

    private void configurarStage6(MenuModel model) {
        DefaultSubMenu stage = DefaultSubMenu.builder().label("Stage 6 Results").build();

        var items = List.of( itemOf("etiquetaMenuAnalisisReferenciaYear","/estadisticas/referenciasYear.xhtml"),
                itemOf("etiquetaMenuAnalisisReferenciaTipo","/estadisticas/referenciasTipo.xhtml"),
                itemOf("etiquetaMenuAnalisisReferenciaTipoFuente","/estadisticas/referenciasTipoFuente.xhtml"),
                itemOf("etiquetaMenuAnalisisReferenciaTopico","/estadisticas/referenciasTopico.xhtml"),
                itemOf("etiquetaMenuAnalisisReferenciaTopicoAtributoCalidad","/estadisticas/referenciasTopicoAtributoCalidad.xhtml"),
                itemOf("etiquetaMenuAnalisisReferenciaPregunta","/estadisticas/referenciasPregunta.xhtml"),
                itemOf("etiquetaMenuAnalisisCalidadYear","/estadisticas/referenciasCalidadYear.xhtml"),
                itemOf("etiquetaMenuAnalisisReferenciaTermino","/estadisticas/referenciasTermino.xhtml")
                );
        items.stream().map(this::createItem).forEach(stage.getElements()::add);
        stage.setExpanded(false);
        model.getElements().add(stage);
    }

    private void configurarAyuda(MenuModel model) {
        DefaultSubMenu stage = DefaultSubMenu.builder().label(getMessage("menu.ayuda")).build();
        var items = List.of(itemOf("menu.ayuda.proceso","/ayuda/proceso.xhtml"));

        items.stream().map(this::createItem).forEach(stage.getElements()::add);
        stage.setExpanded(false);

        model.getElements().add(stage);
    }

    private MenuItem createItem(Item item) {
        return createItem(item.etiqueta(),item.path());
    }

    private MenuItem createItem(String label, String url) {
        return createItem(label, url, getRevision() != null && hasPermision(url));
    }

    private MenuItem createItem(String label, String url, boolean rendered) {
        return DefaultMenuItem.builder()
                .value(label)
                .ajax(false)
                .url(url)
                //.command(url)
                .styleClass(".ui-menu-child")
                .style("overflow-y: auto; max-height: 60vh;")
                .rendered(rendered)
                .build();
    }

    private MenuItem itemBackup(){
        return DefaultMenuItem.builder()
                .value(getMessage("etiquetaBackup"))
                .ajax(false)
                .command("#{backupBean.export()}")

                //.command(url)

                .rendered(true)
                .build();
    }

    private Item itemOf(String etiqueta,String path){
        return new Item(getMessage(etiqueta),path);
    }

    record Item(String etiqueta,String path){
    }
}
