package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.beans.util.MessageConstants;
import co.edu.utp.gia.sms.entidades.CadenaBusqueda;
import co.edu.utp.gia.sms.entidades.Fuente;
import co.edu.utp.gia.sms.negocio.CadenaBusquedaService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;
/**
 * Clase controladora de interfaz web que se encarga de la gestión de las cadenas de búsqueda.
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
public class CadenaBusquedaBean extends GenericBean<CadenaBusqueda> {
    /**
     * Variable que representa el atributo serialVersionUID de la clase
     */
    private static final long serialVersionUID = 9060626480979863537L;
    @Getter @Setter
    private Fuente baseDatos;
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
    private Collection<CadenaBusqueda> cadenasBusqueda;

    @Inject
    private CadenaBusquedaService cadenaBusquedaService;

    public void inicializar() {
        cadenasBusqueda = cadenaBusquedaService.get();
        sugerirConsulta();
    }


    public void registrar() {
        CadenaBusqueda cadenaBusqueda = cadenaBusquedaService.save(baseDatos, consulta,fechaConsulta,resultadoPreliminar,resultadoFinal);
        cadenasBusqueda.add(cadenaBusqueda);
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
        baseDatos = null;
        fechaConsulta = null;
        resultadoPreliminar = null;
        resultadoFinal = null;
        sugerirConsulta();
    }

    public void sugerirConsulta(){
        consulta = cadenaBusquedaService.sugerirConsulta();
    }

    @Override
    public void actualizar(CadenaBusqueda cadenaBusqueda) {
        cadenaBusquedaService.update(cadenaBusqueda);
    }

    /**
     * Permite eliminar un Objetivo
     *
     * @param cadenaBusqueda a eliminar
     */
    public void eliminar(CadenaBusqueda cadenaBusqueda) {
        cadenaBusquedaService.delete(cadenaBusqueda.getId());
        cadenasBusqueda.remove(cadenaBusqueda);
        mostrarMensajeGeneral(getMessage(MessageConstants.OPERACION_FINALIZADA));
    }

}
