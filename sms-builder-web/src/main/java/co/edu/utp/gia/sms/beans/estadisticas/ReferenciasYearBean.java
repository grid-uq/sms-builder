package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.beans.estadisticas.util.SerieDatos;
import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.negocio.AtributoCalidadEJB;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.ChartDataSet;
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

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class ReferenciasYearBean extends EstaditicaSerieDatoDTOBaseBean {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 1765173044631798246L;

    @Inject
    private AtributoCalidadEJB atributoCalidadEJB;


    @Getter
    @Setter
    private List<String> years;

    public void inicializar() {
        setEjeX(getMessage(MessageConstants.YEAR));
        setEjeY("# "+getMessage(MessageConstants.REFERENCIA));
        setTitulo(getEjeY() + " - " + getEjeX());
        setTipoGrafica("bar");
        setTiposGrafica(new String[]{"bar"});
        if (getRevision() != null) {
            addSerie(getEstadisticaEJB().obtenerReferenciasYear(getRevision().getId()),"All");
//            BarChartDataSet serie = crearBarSerieFromListDatoDTO(getEstadisticaEJB().obtenerReferenciasYear(getRevision().getId()),"All");
            inicializarYears(getDatosSeries().get("All"));
//            addSerie(serie);

            List<AtributoCalidad> atributosCalidad = atributoCalidadEJB.obtenerAtributosCalidad(getRevision().getId());
            for (AtributoCalidad atributoCalidad : atributosCalidad) {
                addSerie(getEstadisticaEJB().obtenerReferenciasYear(getRevision().getId(), atributoCalidad.getId()),atributoCalidad.getDescripcion());
//                serie = crearBarSerieFromListDatoDTO(getEstadisticaEJB().obtenerReferenciasYear(getRevision().getId(), atributoCalidad.getId()),atributoCalidad.getDescripcion());
//                addSerie(serie);
            }
            crearModelo();
        }
    }

    private void inicializarYears(SerieDatos serie) {
        years = new ArrayList<>();
        serie.getDatos().forEach(y -> years.add(y.getEtiqueta()));
    }

    public BarChartModel getModel(){
        return (BarChartModel) super.getModel();
    }

    public BarChartModel getModel2() {
//        return (BarChartModel) super.getModel();
        BarChartModel barModel = new BarChartModel();
        ChartData data = new ChartData();

        getDatosSeries().keySet().forEach( key->{
            data.addChartDataSet(crearDataSet(key));
        } );
                getDatosSeries().keySet().forEach( System.out::println );
//        data.addChartDataSet(crearDataSet("All"));
//        data.addChartDataSet(crearDataSet("CVI"));

        data.setLabels(years);
        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText(getTitulo());
        options.setTitle(title);

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

        barModel.setOptions(options);
        return barModel;
    }

    public BarChartModel getModeloEjemplo() {
//        return (BarChartModel) super.getModel();
        BarChartModel barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("All");

        List<Number> values = new ArrayList<>();
        values.add(65);
        values.add(59);
        values.add(80);
        values.add(81);
        values.add(56);
        values.add(55);
        values.add(40);
        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        data.setLabels(labels);
        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Bar Chart");
        options.setTitle(title);

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

        barModel.setOptions(options);
        return barModel;
    }

    private BarChartDataSet crearDataSet(String key) {
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel(key);
        barDataSet.setData( getDatosSeries().get(key).getDatos().stream().map(DatoDTO::getValor).collect(Collectors.toList()) );

        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);

        String bgColor = String.format("rgba(%d, %d, %d, 0.2)",r,g,b);

        barDataSet.setBackgroundColor(bgColor);

        String borderColor = String.format("rgb(%d, %d, %d)",r,g,b);
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        return barDataSet;
    }
}
