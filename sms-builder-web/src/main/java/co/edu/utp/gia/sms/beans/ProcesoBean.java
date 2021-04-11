package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.seguridad.SeguridadBean;
import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.Objetivo;
import co.edu.utp.gia.sms.entidades.PasoProceso;
import co.edu.utp.gia.sms.entidades.Usuario;
import co.edu.utp.gia.sms.negocio.ProcesoEJB;
import co.edu.utp.gia.sms.negocio.RevisoresEJB;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.beans.BeanProperty;
import java.util.List;

@Named
@ViewScoped
public class ProcesoBean extends AbstractRevisionBean {

    @Inject
    private ProcesoEJB procesoEJB;
    @Getter
    private List<PasoProceso> pasosProceso;

    @Inject
    @ManagedProperty("#{seguridadBean}")
    private SeguridadBean seguridadBean;

    @Getter
    private MenuModel model;

    public void inicializar() {
        if (getRevision() != null) {
            pasosProceso = procesoEJB.listar( getRevision().getId() );
            configurarMenu();
        }
    }

    private void configurarMenu() {
        model = new DefaultMenuModel();

        //First submenu
        DefaultSubMenu stage1 = DefaultSubMenu.builder()
                .label("Stage 1 Planing")
                .build();

//        DefaultMenuItem item = DefaultMenuItem.builder()
//                .value("Step 1")
//                .icon("pi pi-save")
//                .ajax(false)
//                .command("#{menuView.save}")
//                .update("messages")
//                .build();
//        stage1.getElements().add(item);
        DefaultMenuItem item = DefaultMenuItem.builder()
                .value("Crear revision")
                .ajax(false)
                .command("/revision/registroRevision.xhtml")
                .rendered(seguridadBean.verifivarAcceso("/revision/registroRevision.xhtml"))
                .build();
        stage1.getElements().add(item);

        item = DefaultMenuItem.builder()
                .value("Seleccionar revision")
                .ajax(false)
                .command("/revision/seleccionarRevision.xhtml")
                .rendered(seguridadBean.verifivarAcceso("/revision/seleccionarRevision.xhtml"))
                .build();
        stage1.getElements().add(item);

        pasosProceso.forEach( paso->{
            DefaultMenuItem p = DefaultMenuItem.builder()
                    .value(paso.getPaso().getNombre())
                    .ajax(false)
                    .command(paso.getPaso().getRecurso().getUrl())
                    .rendered(seguridadBean.verifivarAcceso(paso.getPaso().getRecurso().getUrl()))
                    .build();
            stage1.getElements().add(p);
        });

        model.getElements().add(stage1);
    }

}
