package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.CadenaBusqueda;
import co.edu.utp.gia.sms.negocio.CadenaBusquedaEJB;
import lombok.Getter;
import lombok.Setter;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class CadenaBusquedaBean extends GenericBean<CadenaBusqueda> {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 9060626480979863537L;
    @Getter @Setter
    private String baseDatos;
    @Getter @Setter
    private String consulta;
    @Getter @Setter
    private Date fechaConsulta;
    @Getter @Setter
    private Integer resultadoPreliminar;
    @Getter @Setter
    private Integer resultadoFinal;

    @Getter
    @Setter
    private List<CadenaBusqueda> cadenasBusqueda;

    @Inject
    private CadenaBusquedaEJB cadenaBusquedaEJB;

    public void inicializar() {
        if (getRevision() != null) {
            cadenasBusqueda = cadenaBusquedaEJB.obtenerCadenasBusqueda(getRevision().getId());
            sugerirConsulta();
        }
    }


    public void registrar() {
        CadenaBusqueda cadenaBusqueda = cadenaBusquedaEJB.registrar(baseDatos, consulta,fechaConsulta,resultadoPreliminar,resultadoFinal, getRevision().getId());
        cadenasBusqueda.add(cadenaBusqueda);
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        baseDatos = "";
        fechaConsulta = null;
        resultadoPreliminar = null;
        resultadoFinal = null;
        sugerirConsulta();
    }

    public void sugerirConsulta(){
        consulta = cadenaBusquedaEJB.sugerirConsulta(getRevision().getId());
    }

    @Override
    public void actualizar(CadenaBusqueda cadenaBusqueda) {
        cadenaBusquedaEJB.actualizar(cadenaBusqueda);
    }

    /**
     * Permite eliminar un Objetivo
     *
     * @param cadenaBusqueda a eliminar
     */
    public void eliminar(CadenaBusqueda cadenaBusqueda) {
        cadenaBusquedaEJB.eliminar(cadenaBusqueda.getId());
        cadenasBusqueda.remove(cadenaBusqueda);
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
    }

}
