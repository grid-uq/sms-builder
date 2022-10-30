package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.beans.estadisticas.util.SerieDatos;
import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.negocio.AtributoCalidadEJB;
import lombok.Getter;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase controladora de interfaz web que se encarga de presentar los datos estadísticos de calidad por año.
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
public class ReferenciasCalidadYearBean extends EstaditicaDatoDTOBaseBean {

    @Getter
    private List<String> years;
    @Inject
    private AtributoCalidadEJB atributoCalidadEJB;


    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 1943642325865821264L;

    public void inicializar() {
        setEjeX(getMessage(MessageConstants.YEAR));
        setEjeY(getMessage(MessageConstants.PROMEDIO_CALIDAD));
        setTitulo(getEjeY() + " - " + getEjeX());
        setTipoGrafica("bar");
        setTiposGrafica(new String[]{"bar"});
        if (getRevision() != null) {
            addSerie(getEstadisticaEJB().obtenerReferenciasCalidadYear(getRevision().getId()),"All");
            inicializarYears(getDatosSeries().get("All"));
            List<AtributoCalidad> atributosCalidad = atributoCalidadEJB.obtenerAtributosCalidad(getRevision().getId());
            for (AtributoCalidad atributoCalidad : atributosCalidad) {
                addSerie(getEstadisticaEJB().obtenerReferenciasCalidadYear(getRevision().getId(), atributoCalidad.getId()),atributoCalidad.getDescripcion());
            }
            crearModelo();
        }
    }

    private void inicializarYears(SerieDatos serie) {
        years = new ArrayList<>();
        serie.getDatos().forEach(y -> years.add(y.getEtiqueta()));
    }
}
