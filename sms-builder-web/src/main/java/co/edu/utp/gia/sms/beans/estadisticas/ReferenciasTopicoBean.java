package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.dtos.DatoDTO;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Clase controladora de interfaz web que se encarga de presentar los datos estadísticos de los tópicos.
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
@Getter @Setter
public class ReferenciasTopicoBean extends EstaditicaDatoDTOBaseBean {
    private String codigo;
    private List<String> topicos;
    private List<String> topicosSeleccionados;

    public void inicializar() {
        setEjeX(getMessage(MessageConstants.TOPICOS));
        setEjeY("# "+getMessage(MessageConstants.SPS));
        setTitulo(getEjeY() + " - " + getEjeX());
        if (getRevision() != null) {
            onChangePregunta();
        }
    }

    public void onChangePregunta() {
        List<DatoDTO> datos;
        getDatosSeries().clear();

        if (codigo != null) {
            datos = getEstadisticaService().obtenerReferenciasTopicoPregunta(codigo);
        } else {
            datos = getEstadisticaService().obtenerReferenciasTopico();
        }
        actualizarTopicos(datos);
        Predicate<DatoDTO> filtro = dato->topicosSeleccionados.stream().anyMatch(dato.getEtiqueta()::equalsIgnoreCase);
        datos = datos.stream().filter( filtro ).toList();
        addSerie(datos,getTitulo());
        crearModelo();
    }

    private void actualizarTopicos(List<DatoDTO> datos) {
        topicos = datos.stream().map(DatoDTO::getEtiqueta).toList();
        Predicate<String> filtroTopicosSeleccionados = topicos::contains;
        topicosSeleccionados = new ArrayList<>(
                (topicosSeleccionados == null ? new ArrayList<>( topicos ): topicosSeleccionados)
                        .stream()
                        .filter(filtroTopicosSeleccionados).toList()
        );
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
        setTopicosSeleccionados(null);
    }
}
