package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.beans.util.MessageConstants;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ReferenciasCalidadYearBean extends EstaditicaDatoDTOBaseBean {


    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 1943642325865821264L;

    public void inicializar() {
        setEjeX(getMessage(MessageConstants.YEAR));
        setEjeY(getMessage(MessageConstants.PROMEDIO_CALIDAD));
        setTitulo(getEjeY() + " - " + getEjeX());
        if (getRevision() != null) {
            setDatos(getEstadisticaEJB().obtenerReferenciasCalidadYear(getRevision().getId()));
            crearModelo();
        }
    }
}
