package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.beans.AbstractRevisionBean;
import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.EvaluacionCualitativa;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.EstadisticaEJB;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;
import co.edu.utp.gia.sms.negocio.RevisionEJB;
import lombok.Getter;
import lombok.Setter;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.Comparator;
import java.util.List;
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
    private static final long serialVersionUID = -5710032002326306549L;
    @Getter
    @Setter
    private List<ReferenciaDTO> referencias;
    @Inject
    private ReferenciaEJB referenciaEJB;
    @Inject
    private RevisionEJB revisionEJB;
    @Inject
    private EstadisticaEJB estadisticaEJB;

    @Getter @Setter
    private List<String> years;
    @Getter @Setter
    private List<Topico> topicos;

    @Getter @Setter
    private Integer idAtributoCalidad;
    @Getter @Setter
    private EvaluacionCualitativa evaluacion;

    @Getter @Setter
    private Boolean year;

    public void inicializar() {

        if (getRevision() != null) {
            referencias = referenciaEJB.obtenerTodas(getRevision().getPasoSeleccionado().getId())
                    .stream().sorted( Comparator.comparing(ReferenciaDTO::getSpsid) ).collect(Collectors.toList());
            topicos = revisionEJB.obtenerTopicos(getRevision().getId());
            years = estadisticaEJB.obtenerYears( getRevision().getId() );
        }
    }

    public void consultarReferencias() {
        if (idAtributoCalidad == null) {
            referencias = referenciaEJB.obtenerTodas(getRevision().getPasoSeleccionado().getId())
                    .stream().sorted( Comparator.comparing(ReferenciaDTO::getSpsid) ).collect(Collectors.toList());
        } else if (evaluacion != null) {
            referencias = referenciaEJB.obtenerReferenciasAtributoCalidadEvaluacion(getRevision().getId(), idAtributoCalidad,
                    evaluacion)
                    .stream().sorted( Comparator.comparing(ReferenciaDTO::getSpsid) ).collect(Collectors.toList());
        } else {
            referencias = referenciaEJB.obtenerReferenciasAtributoCalidadEvaluacion(getRevision().getId(), idAtributoCalidad)
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

}
