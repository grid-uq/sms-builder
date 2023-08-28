package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.beans.estadisticas.util.SerieDatos;
import co.edu.utp.gia.sms.dtos.DatoDTO;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.ChartOptions;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.primefaces.model.charts.pie.PieChartOptions;

import java.util.*;
import java.util.stream.Collectors;
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
public abstract class EstaditicaDatoDTOBaseBean extends EstadisticaBean {
    @Getter
    @Setter
    private List<DatoDTO> datos;
    @Getter
    private final Map<String, SerieDatos> datosSeries;


    public List<SerieDatos> getSeries(){
        return new ArrayList<>(datosSeries.values());
    }

    public EstaditicaDatoDTOBaseBean() {
        datosSeries = new HashMap<>();
    }

    protected void addSerie(List<DatoDTO> datos, String etiqueta) {
        setDatos(datos);
        datosSeries.put(etiqueta, new SerieDatos(etiqueta, datos));
    }


    protected ChartData createData(ChartOptions options) {
        ChartData data = new ChartData();

        if (datosSeries.isEmpty()) {
            addSerie(datos, "");
        }

        getDatosSeries().keySet().forEach(key -> {
            if (options instanceof PieChartOptions) {
                data.addChartDataSet(crearPieDataSet(key));
            } else {
                data.addChartDataSet(crearBarDataSet(key));
            }
        });

        if (getTitulo() != null) {
            Title title = new Title();
            title.setDisplay(true);
            title.setText(getTitulo());
            options.setTitle(title);
        }


        if (datos != null) {
            data.setLabels(datos.stream().map(DatoDTO::getEtiqueta).collect(Collectors.toList()));
            data.setLabels(generateLabels());
        }

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        return data;
    }

    private List<String> generateLabels() {
        ArrayList<String> etiquetas = new ArrayList<>();

        getDatosSeries().values().forEach(datos -> etiquetas.addAll( datos.getDatos().stream().map(DatoDTO::getEtiqueta).toList()));

        return etiquetas.stream().distinct().toList();
    }

    protected PieChartModel crearPieModel() {
        PieChartModel model = new PieChartModel();
        PieChartOptions options = new PieChartOptions();
        //datosSeries = new HashMap<>();
        model.setData(createData(options));

        model.setOptions(options);
        return model;
    }

    protected BarChartModel crearBarModel() {
        BarChartModel model = new BarChartModel();
        BarChartOptions options = new BarChartOptions();
        //datosSeries = new HashMap<>();
        model.setData(createData(options));

        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();

        //ticks.setBeginAtZero(true);

        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        model.setOptions(options);
        return model;
    }


    protected PieChartDataSet crearPieDataSet(String key) {
        PieChartDataSet serie = new PieChartDataSet();
        List<String> bgColors = new ArrayList<>();
        List<String> borderColors = new ArrayList<>();
        Random random = new Random();

        datos.forEach(d -> {
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            bgColors.add(String.format("rgba(%d, %d, %d, 0.2)", r, g, b));
            borderColors.add(String.format("rgb(%d, %d, %d)", r, g, b));
        });

//        serie.setLabel(label);
        serie.setData(getDatosSeries().get(key).getDatos().stream().map(DatoDTO::getValor).collect(Collectors.toList()));
        serie.setBackgroundColor(bgColors);
        serie.setBorderColor(borderColors);
        return serie;
    }

    private BarChartDataSet crearBarDataSet(String key) {
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel(key);
        barDataSet.setData(getDatosSeries().get(key).getDatos().stream().map(DatoDTO::getValor).collect(Collectors.toList()));

        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);

        String bgColor = String.format("rgba(%d, %d, %d, 0.2)", r, g, b);

        barDataSet.setBackgroundColor(bgColor);

        String borderColor = String.format("rgb(%d, %d, %d)", r, g, b);
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        return barDataSet;
    }

}
