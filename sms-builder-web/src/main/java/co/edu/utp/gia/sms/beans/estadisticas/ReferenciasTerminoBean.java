package co.edu.utp.gia.sms.beans.estadisticas;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import lombok.Getter;
import lombok.Setter;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ReferenciasTerminoBean extends EstaditicaDatoDTOBaseBean {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = -7097161925478814637L;
    @Getter
    @Setter
    private String informacion;

    public void inicializar() {
        setEjeX(getMessage(MessageConstants.TERMINO));
        setEjeY("# "+getMessage(MessageConstants.SPS));
        setTitulo(getEjeY() + " - " + getEjeX());
        if (getRevision() != null) {
            onChangePregunta();
        }
    }


    public void onChangePregunta() {
        if ( "2".equals(informacion) ) {
            setDatos(getEstadisticaEJB().obtenerReferenciasSinonimo(getRevision().getId()));
        } else {
            setDatos(getEstadisticaEJB().obtenerReferenciasTermino(getRevision().getId()));
        }
        crearModelo();
    }


}
