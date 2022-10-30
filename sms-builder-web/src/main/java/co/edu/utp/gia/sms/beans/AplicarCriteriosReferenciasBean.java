package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.importutil.FindReferenceCitation;
import co.edu.utp.gia.sms.negocio.ReferenciaEJB;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
/**
 * Clase controladora de interfaz web que se encarga de la aplicación de los criterios de evaluación.
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
@Log
public class AplicarCriteriosReferenciasBean extends GenericBean<ReferenciaDTO> {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 6757021713542648202L;
    @Getter
    @Setter
    private List<ReferenciaDTO> referencias;
    @Inject
    private ReferenciaEJB referenciaEJB;


    public void inicializar() {
        if (getRevision() != null) {
//            referencias = referenciaEJB.obtenerTodas(getRevision().getId(), 1);
            log.info("Aplicar criterios paso "+paso);
            referencias = referenciaEJB.obtenerTodas(paso-1);
            log.info("Numero referecias "+referencias.size());
        }
    }

    public void actualizarNota(ReferenciaDTO referencia) {
        referenciaEJB.actualizarNota(referencia.getId(), referencia.getNota());
    }

    public void adicionarResumen(ReferenciaDTO referencia) {
        String tranduccion = FindReferenceCitation.getInstans().findTranslate(referencia.getResumen());
        referencia.setNota(referencia.getNota() + "\n" + tranduccion);
        actualizarNota(referencia);
    }

    public void seleccionarReferencia(ReferenciaDTO referencia) {
        if( referencia.isSeleccionada() ){
            if( referencia.getFiltro() < paso ) {
                referencia.setFiltro(paso);
                referenciaEJB.actualizarFiltro(referencia.getId(), paso);
            }
        } else {
            if( referencia.getFiltro() >= paso ){
                referencia.setFiltro(paso-1);
                referenciaEJB.actualizarFiltro(referencia.getId(), paso-1);
            }
        }
        //referenciaEJB.actualizarFiltro(referencia.getId(), referencia.getFiltro());
    }

    public void actualizarRelevancia(ReferenciaDTO referencia) {
        referenciaEJB.actualizarRelevancia(referencia.getId(), referencia.getRelevancia());
    }


}
