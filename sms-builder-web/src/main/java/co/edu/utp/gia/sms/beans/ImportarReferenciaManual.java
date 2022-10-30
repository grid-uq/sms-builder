package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.entidades.TipoFuente;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
/**
 * Clase controladora de interfaz web que se encarga de la importación de las referencias que se han incluido de forma directa.
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
public class ImportarReferenciaManual extends ImportarReferenciasBean{

    public ImportarReferenciaManual() {
        super();
        setTipoFuente(TipoFuente.INCLUSION_DIRECTA);
    }
}
