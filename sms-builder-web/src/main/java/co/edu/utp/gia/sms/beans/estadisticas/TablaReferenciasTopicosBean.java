package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.beans.AbstractRevisionBean;
import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.EvaluacionCualitativa;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.EstadisticaService;
import co.edu.utp.gia.sms.negocio.ReferenciaService;
import co.edu.utp.gia.sms.negocio.RevisionService;
import lombok.Getter;
import lombok.Setter;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
/**
 * Clase controladora de interfaz web que se encarga de presentar los datos de las referencias por tópico.
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
public class TablaReferenciasTopicosBean extends AbstractRevisionBean {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    @Serial
    private static final long serialVersionUID = -5710032002326306549L;
    @Getter
    @Setter
    private List<ReferenciaDTO> referencias;
    @Inject
    private ReferenciaService referenciaService;
    @Inject
    private RevisionService revisionService;
    @Inject
    private EstadisticaService estadisticaService;

    @Getter @Setter
    private List<String> years;
    @Getter @Setter
    private List<Topico> topicos;
    @Getter @Setter
    private List<Topico> topicosSeleccionados;

    @Getter @Setter
    private String idAtributoCalidad;
    @Getter @Setter
    private EvaluacionCualitativa evaluacion;

    @Getter @Setter
    private Boolean year;

    public void inicializar() {

        if (getRevision() != null) {
            referencias = referenciaService.findByPaso(getPasoActual().getId())
                    .stream().sorted( Comparator.comparing(ReferenciaDTO::getSpsid) ).collect(Collectors.toList());
            topicos = revisionService.getTopicos();
            topicosSeleccionados = new ArrayList<>( topicos );
            years = estadisticaService.obtenerYears( );
        }
    }

    public void consultarReferencias() {
        if (idAtributoCalidad == null) {
            referencias = referenciaService.findByPaso(getPasoActual().getId())
                    .stream().sorted( Comparator.comparing(ReferenciaDTO::getSpsid) ).collect(Collectors.toList());
        } else if (evaluacion != null) {
            referencias = referenciaService.obtenerReferenciasAtributoCalidadEvaluacion(idAtributoCalidad, evaluacion)
                    .stream().sorted( Comparator.comparing(ReferenciaDTO::getSpsid) ).collect(Collectors.toList());
        } else {
            referencias = referenciaService.obtenerReferenciasAtributoCalidadEvaluacion(idAtributoCalidad)
                    .stream().sorted( Comparator.comparing(ReferenciaDTO::getSpsid) ).collect(Collectors.toList());
        }
    }

    /**
     * Permite obtener un arreglo con los valores de la
     * {@link EvaluacionCualitativa}
     *
     * @return Arreglo de valores de la {@link EvaluacionCualitativa}
     */
    public EvaluacionCualitativa[] getListaValores() {
        return EvaluacionCualitativa.values();
    }

    public String referenciasTopicoYear(Topico topico,String year){
        return referenciasSpsId(referencia -> referencia.getTopicos().contains(topico) && referencia.getYear().equals(year));
    }

    public String referenciasTopico(Topico topico){
        return referenciasSpsId(referencia -> referencia.getTopicos().contains(topico));
    }

    private String referenciasSpsId(Predicate<ReferenciaDTO> filtro){
        return referencias.stream()
                .filter(filtro )
                .map(ReferenciaDTO::getSpsid)
                .collect(Collectors.joining(" "));
    }

}
