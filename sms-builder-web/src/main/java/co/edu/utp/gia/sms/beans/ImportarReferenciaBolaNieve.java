package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.entidades.TipoFuente;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
/**
 * Clase controladora de interfaz web que se encarga de la importación de las referencias que se han incluido por bola de nieve.
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
public class ImportarReferenciaBolaNieve extends ImportarReferenciasBean{

    public ImportarReferenciaBolaNieve() {
        super();
        setTipoFuente(TipoFuente.BOLA_NIEVE);
    }
}
