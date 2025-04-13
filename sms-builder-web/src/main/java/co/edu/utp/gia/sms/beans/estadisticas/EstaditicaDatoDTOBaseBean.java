package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.beans.estadisticas.util.SerieDatos;
import co.edu.utp.gia.sms.dtos.DatoDTO;
import lombok.Getter;
import lombok.Setter;
import software.xdev.chartjs.model.charts.BarChart;
import software.xdev.chartjs.model.charts.PieChart;
import software.xdev.chartjs.model.color.RGBAColor;
import software.xdev.chartjs.model.data.BarData;
import software.xdev.chartjs.model.data.PieData;
import software.xdev.chartjs.model.dataset.BackgroundBorderHoverDataset;
import software.xdev.chartjs.model.dataset.BarDataset;
import software.xdev.chartjs.model.dataset.PieDataset;
import software.xdev.chartjs.model.enums.IndexAxis;
import software.xdev.chartjs.model.options.BarOptions;
import software.xdev.chartjs.model.options.Options;
import software.xdev.chartjs.model.options.PieOptions;
import software.xdev.chartjs.model.options.Plugins;
import software.xdev.chartjs.model.options.scale.Scales;
import software.xdev.chartjs.model.options.scale.cartesian.CartesianScaleOptions;
import software.xdev.chartjs.model.options.scale.cartesian.CartesianTickOptions;

import java.util.*;

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
@Getter
public abstract class EstaditicaDatoDTOBaseBean extends EstadisticaBean {
    @Setter
    private List<DatoDTO> datos;
    protected final Map<String, SerieDatos> datosSeries;


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

    protected String crearPieModel() {
        return new PieChart()
                .setData(new PieData()
                        .addDataset(new PieDataset()
                                .setData(datosToValues(datos))
//                                .setLabel(getTitulo())
                                .setBackgroundColor(generateColors(datos))
                        )
                        .setLabels(datosToLabels(datos)))
                .setOptions(createOptions(new PieOptions()))
                .toJson();
    }

    protected String crearBarModel() {
        var datosSerie = datosSeries.values().stream().findFirst().map(SerieDatos::getDatos).orElseThrow();
        return new BarChart()
                .setData(new BarData()
                        .setDatasets(createDataSets())
                        .setLabels(datosToLabels(datosSerie)))
                .setOptions(createOptions(new BarOptions())).toJson();
    }

    protected Collection<BarDataset> createDataSets() {
        return datosSeries.values().stream().map(serie->initDataSet(new BarDataset(),serie)).toList();
    }

    private <T extends BackgroundBorderHoverDataset<T,Number>> T initDataSet(T dataSet, SerieDatos serie){
        var color = generateColor();
        dataSet.setData(datosToValues(serie.getDatos()))
                .setLabel(serie.getEtiqueta())
                .setBackgroundColor(new RGBAColor(color.getR(), color.getG(), color.getB(), 0.2))
                .setBorderColor(color)
                .setBorderWidth(1);
        return dataSet;
    }

    private <T extends Options> T createOptions(T options){
        options.setResponsive(true)
            .setMaintainAspectRatio(false)
            .setIndexAxis(IndexAxis.X)
            .setScales(new Scales().addScale(Scales.ScaleAxis.Y, new CartesianScaleOptions()
                    .setStacked(false)
                    .setTicks(new CartesianTickOptions()
                            .setAutoSkip(true)
                            .setMirror(true)))
            )
            .setPlugins(new Plugins()
                    .setTitle(new software.xdev.chartjs.model.options.Title()
                            .setDisplay(true)
                            .setText(getTitulo())));
        return options;
    }

    private List<Object> generateColors(List<DatoDTO> datos) {
        Random random = new Random();
        return datos.stream()
                .map(v->new RGBAColor(random.nextInt(256), random.nextInt(256), random.nextInt(256)))
                .map(Object.class::cast)
                .toList();
    }

    private Collection<String> datosToLabels(List<DatoDTO> datos) {
        return datos.stream().map(DatoDTO::getEtiqueta).toList();
    }

    private Collection<Number> datosToValues(List<DatoDTO> datos) {
        return datos.stream().map(DatoDTO::getValor).map(Number.class::cast).toList();
    }

    private RGBAColor generateColor(){
        Random random = new Random();
        return new RGBAColor(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}
