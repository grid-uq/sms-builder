package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.negocio.AtributoCalidadEJB;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.chart.ChartSeries;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class ReferenciasTopicoAtributoCalidadBean extends EstaditicaSerieDatoDTOBaseBean {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 3695939063364135580L;

    @Inject
    private AtributoCalidadEJB atributoCalidadEJB;

    @Getter
    @Setter
    private String codigo;

    @Getter
    @Setter
    private List<String> topicos;

    public void inicializar() {
        setEjeX(getMessage(MessageConstants.TOPICOS));
        setEjeY("# "+getMessage(MessageConstants.REFERENCIA));
        setTipoGrafica("bar");
        setTiposGrafica(new String[]{"bar"});
        setTitulo(getEjeY() + " - " + getEjeX());

        if (getRevision() != null) {
            onChangePregunta();
        }
    }

    public void onChangePregunta() {
        getSeries().clear();
        ChartSeries serie;
        if (codigo != null) {
            serie = crearSerieFromListDatoDTO(getEstadisticaEJB().obtenerReferenciasTopico(getRevision().getId(), codigo));
        } else {
            serie = crearSerieFromListDatoDTO(getEstadisticaEJB().obtenerReferenciasTopico(getRevision().getId()));
        }
        serie.setLabel("SPSs");
        addSerie(serie);
        inicializarTopicos(serie);

        List<AtributoCalidad> atributosCalidad = atributoCalidadEJB.obtenerAtributosCalidad(getRevision().getId());
        for (AtributoCalidad atributoCalidad : atributosCalidad) {
            if (codigo != null) {
                serie = crearSerieFromListDatoDTO(getEstadisticaEJB().obtenerReferenciasTopico(getRevision().getId(), codigo, atributoCalidad.getId()));
            } else {
                serie = crearSerieFromListDatoDTO(getEstadisticaEJB().obtenerReferenciasTopico(getRevision().getId(), atributoCalidad.getId()));
            }
            serie.setLabel(atributoCalidad.getDescripcion());
            addSerie(serie);
        }

        crearModelo();
    }

    private void inicializarTopicos(ChartSeries serie) {
        topicos = new ArrayList<>();
        serie.getData().keySet().forEach(y -> topicos.add(y.toString()));
    }


}
