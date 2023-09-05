package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.negocio.AtributoCalidadService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
/**
 * Clase controladora de interfaz web que se encarga de presentar los datos estadísticos de los tópicos por atributo de calidad.
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
public class ReferenciasTopicoAtributoCalidadBean extends EstaditicaSerieDatoDTOBaseBean {
    @Inject
    private AtributoCalidadService atributoCalidadService;

    @Getter
    @Setter
    private String codigo;

    @Getter
    @Setter
    private List<String> topicos;

    public void inicializar() {
        setEjeX(getMessage(MessageConstants.TOPICOS));
        setEjeY("# "+getMessage(MessageConstants.SPS));
        setTipoGrafica("bar");
        setTiposGrafica(new String[]{"bar"});
        setTitulo(getEjeY() + " - " + getEjeX());

        if (getRevision() != null) {
            onChangePregunta();
        }
    }

    public void onChangePregunta() {
        getDatosSeries().clear();
        if (codigo != null) {
            addSerie(getEstadisticaService().obtenerReferenciasTopicoPregunta(codigo),"All");
        } else {
            addSerie(getEstadisticaService().obtenerReferenciasTopico(),"All");
        }
        inicializarTopicos(getDatosSeries().get("All").getDatos());

        var atributosCalidad = atributoCalidadService.get();
        for (AtributoCalidad atributoCalidad : atributosCalidad) {
            if (codigo != null) {
                addSerie(getEstadisticaService().obtenerReferenciasTopico(codigo, atributoCalidad.getId()),atributoCalidad.getDescripcion());
            } else {
                addSerie(getEstadisticaService().obtenerReferenciasTopico(atributoCalidad.getId()),atributoCalidad.getDescripcion());
            }
        }

        crearModelo();
    }

    private void inicializarTopicos(List<DatoDTO> datos) {
        topicos = new ArrayList<>();
        datos.forEach(y -> topicos.add(y.getEtiqueta()));
    }


}
