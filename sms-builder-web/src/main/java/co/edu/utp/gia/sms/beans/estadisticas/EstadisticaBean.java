package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.beans.AbstractRevisionBean;
import co.edu.utp.gia.sms.negocio.EstadisticaEJB;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.charts.ChartModel;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.pie.PieChartModel;

import jakarta.inject.Inject;
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
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 4686100580073797808L;


    @Inject
    @Getter
    private EstadisticaEJB estadisticaEJB;

    @Getter
    @Setter
    private ChartModel model;

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

    protected void crearModelo() {
        switch (tipoGrafica) {
            case "bar":
                model = crearBarModel();
                break;
            case "pie":
            default:
                model = crearPieModel();
                break;
        }
    }

    protected abstract PieChartModel crearPieModel();

    protected abstract BarChartModel crearBarModel();

    public BarChartModel getBarModel() {
        if (!(model instanceof BarChartModel)) {
            model = crearBarModel();
        }
        return (BarChartModel) model;

    }

    public PieChartModel getPieModel() {
        if (!(model instanceof PieChartModel)) {
            model = crearPieModel();
        }
        return (PieChartModel) model;
    }

    public void onChangeTipoGrafica() {
        crearModelo();
    }

}
