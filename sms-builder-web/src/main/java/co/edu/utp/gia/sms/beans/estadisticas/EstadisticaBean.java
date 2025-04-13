package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.beans.AbstractRevisionBean;
import co.edu.utp.gia.sms.negocio.EstadisticaService;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.Setter;


/**
 * Clase controladora de interfaz web que se encarga de presentar los datos estadísticos.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
public abstract class EstadisticaBean extends AbstractRevisionBean {
    @Inject
    @Getter
    private EstadisticaService estadisticaService;

    @Setter
    private String model;

    @Getter
    @Setter
    private String titulo;

    @Getter
    @Setter
    private String ejeX;

    @Getter
    @Setter
    private String ejeY;

    @Getter
    @Setter
    private String[] tiposGrafica = {"bar", "pie"};

    @Getter
    @Setter
    private String tipoGrafica = "pie";


    protected String crearModelo() {
        if (tipoGrafica.equals("bar")) {
            model = crearBarModel();
        } else {
            model = crearPieModel();
        }
        return model;
    }

    protected abstract String crearPieModel();

    protected abstract String crearBarModel();

    public String getModel() {
        model = crearModelo();
        return model;
    }

    public void onChangeTipoGrafica() {
        crearModelo();
    }

}
