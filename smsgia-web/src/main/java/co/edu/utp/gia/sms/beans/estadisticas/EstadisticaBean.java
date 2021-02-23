package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.beans.AbstractRevisionBean;
import co.edu.utp.gia.sms.negocio.EstadisticaEJB;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.PieChartModel;

import javax.inject.Inject;

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
//		case "line":
//			model = crearLineModel();
//			break;
            case "pie":
            default:
                model = crearPieModel();
                break;
        }
    }

    protected abstract PieChartModel crearPieModel();

    protected abstract BarChartModel crearBarModel();

    public void onChangeTipoGrafica() {
        crearModelo();
    }

}
