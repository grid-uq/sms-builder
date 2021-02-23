package co.edu.utp.gia.sms.beans.estadisticas;

import lombok.Getter;
import lombok.Setter;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ReferenciasTopicoBean extends EstaditicaDatoDTOBaseBean {

    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = -7097161925478814637L;
    @Getter
    @Setter
    private String codigo;

    public void inicializar() {
        setTitulo("Referencias x Topico");
        setEjeX("Topicos");
        setEjeY("# Referencias");
        if (getRevision() != null) {
            onChangePregunta();
        }
    }

    public void onChangePregunta() {
        if (codigo != null) {
            setDatos(getEstadisticaEJB().obtenerReferenciasTopico(getRevision().getId(), codigo));
        } else {
            setDatos(getEstadisticaEJB().obtenerReferenciasTopico(getRevision().getId()));
        }
        crearModelo();
    }


}
